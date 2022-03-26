package co.com.sofka.runner.create;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/create/create.feature",
        glue = "co.com.sofka.stepdefinition.create"
)
public class CreateTest {
}
