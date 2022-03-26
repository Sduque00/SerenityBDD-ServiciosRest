package co.com.sofka.runner.singleresource;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/singleresource/singleresource.feature" ,
        glue = "co.com.sofka.stepdefinition.singleresource"
)
public class SingleResourceTest {

}
