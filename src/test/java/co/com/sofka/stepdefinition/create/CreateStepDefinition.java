package co.com.sofka.stepdefinition.create;

import co.com.sofka.stepdefinition.common.SetUp;
import co.com.sofka.util.CreateKeys;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hamcrest.Matchers;

import java.util.HashMap;

import static co.com.sofka.question.create.CreateResponse.response;
import static co.com.sofka.task.DoPost.doPost;
import static co.com.sofka.util.FileUtilities.readFile;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.Log4jValues.USER_DIR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CreateStepDefinition extends SetUp {

    private static final String CREATE_FILE = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\create.json";
    private final HashMap<String, Object> headers = new HashMap<>();
    private final Actor actor = Actor.named("Carlos");
    private String bodyRequest;

    private static final Logger LOGGER = Logger.getLogger(CreateStepDefinition.class);

    @Given("que el usuario esta en el recurso web indicando nombre {string} y trabajo {string}")
    public void queElUsuarioEstaEnElRecursoWebIndicandoNombreYTrabajo(String name, String job) {
        try {

            PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
            generalSetUp();
            actor.can(CallAnApi.at(BASE_URI));
            headers.put("Content-Type", ContentType.APPLICATION_JSON.toString());

            bodyRequest = defineBodyRequest(name, job);
            LOGGER.info(bodyRequest);
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }

    }

    @When("realizo la peticion de crear")
    public void realizoLaPeticionDeCrear() {
        try {

            actor.attemptsTo(
                    doPost()
                            .usingTheResource(CREATE_RESOURCE)
                            .withHeaders(headers)
                            .andBodyRequest(bodyRequest)
            );
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Then("obtendre un codigo de respuesta exitoso")
    public void obtendreUnCodigoDeRespuestaExitoso() {
        try {
            LastResponse.received().answeredBy(actor).prettyPrint();
            actor.should(
                    seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_CREATED,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_CREATED)
                    ),
                    seeThat("La respuesta deberia no ser nula: ", response(), Matchers.notNullValue())
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }


    }

    @When("envio la peticion, pero con un contenttype de texto")
    public void envioLaPeticionPeroConUnContenttypeDeTexto() {
        try {

            headers.put("Content-Type", ContentType.DEFAULT_TEXT.toString());
            actor.attemptsTo(
                    doPost()
                            .usingTheResource(CREATE_RESOURCE)
                            .withHeaders(headers)
                            .andBodyRequest(bodyRequest));
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Then("se creara un nuevo registro con solo el campo id")
    public void seCrearaUnNuevoRegistroConSoloElCampoId() {
        try {

            LastResponse.received().answeredBy(actor).prettyPrint();
            actor.should(
                    seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_CREATED,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_CREATED)
                    ),
                    seeThat("La respuesta deberia no ser nula: ", response(), Matchers.notNullValue())
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
    }

    private String defineBodyRequest(String name, String job){
        return readFile(CREATE_FILE)
                .replace(CreateKeys.NAME.getValue(), name)
                .replace(CreateKeys.JOB.getValue(), job);
    }

}
