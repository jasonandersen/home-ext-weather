package vashaina.ha.weather.ext.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

/**
 * Cucumber JUnit runner class
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty",
        "html:target/cucumber" }, features = "classpath:features/", snippets = SnippetType.CAMELCASE, glue = {
                "vashaina.ha.weather.ext.cucumber", "cucumber.run.java.spring" })
public class CucumberJunitRunnerTest {
    //noop - just need the runner to execute the Cucumber classes
}
