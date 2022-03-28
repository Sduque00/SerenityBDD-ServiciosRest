package co.com.sofka.stepdefinition.singleresource;

import co.com.sofka.stepdefinition.common.SetUp;
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

import static co.com.sofka.question.singleresource.SingleResourceResponse.recurso;
import static co.com.sofka.question.singleresource.SingleResourceResponse.response;
import static co.com.sofka.task.DoGet.doGet;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.Log4jValues.USER_DIR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.HashMap;

public class SingleResourceStepDefinition extends SetUp {

    private final HashMap<String, Object> headers = new HashMap<>();
    private final Actor actor = Actor.named("Carlos");

    private static final Logger LOGGER = Logger.getLogger(SingleResourceStepDefinition.class);


    @Given("que estoy en el recurso web")
    public void queEstoyEnElRecursoWeb() {
        PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
        generalSetUp();
        actor.can(CallAnApi.at(BASE_URI));
        headers.put("Content-Type", ContentType.APPLICATION_JSON);
        LOGGER.info(ContentType.APPLICATION_JSON);

    }

    @When("realizo la peticion de consultar recurso")
    public void realizoLaPeticionDeConsultarRecurso() {
        actor.attemptsTo(
                doGet().usingTheResource(SINGLE_RESOURCE)
                        .withHeaders(headers)
        );
    }

    @Then("obtendre un codigo de respuesta y el recurso")
    public void obtendreUnCodigoDeRespuestaYElRecurso() {
        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(
                seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)
                ),
                seeThat("El recurso debe ser: ",
                        response(), containsString(recurso()))
        );

    }

    @Given("que estoy en servicio web")
    public void queEstoyEnServicioWeb() {
        PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
        generalSetUp();
        actor.can(CallAnApi.at(BASE_URI));
        headers.put("Content-Type", ContentType.APPLICATION_JSON);
        LOGGER.info(ContentType.APPLICATION_JSON);

    }

    @When("realizo la peticion de consultar recurso con otra url")
    public void realizoLaPeticionDeConsultarRecursoConOtraUrl() {
        actor.attemptsTo(
                doGet().usingTheResource(SINGLE_RESOURCE_NOT_FOUND)
                        .withHeaders(headers)
        );

    }

    @Then("obtendre un codigo de respuesta erroneo")
    public void obtendreUnCodigoDeRespuestaErroneo() {
        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(
                seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_NOT_FOUND,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_NOT_FOUND)
                )
        );

    }


}
