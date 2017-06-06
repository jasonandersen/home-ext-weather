package vashaina.ha.weather.ext.cucumber;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Then;
import vashaina.ha.weather.ext.driver.ServiceDriver;

/**
 * Step definitions to validate logging messages.
 */
public class LogMessagesSteps {

    @Autowired
    private ServiceDriver driver;

    @Then("^I see log messages with \"([^\"]*)\" of \"([^\"]*)\"$")
    public void iSeeLogMessagesWithOf(String fieldName, String fieldValue) throws Throwable {
        assertTrue(driver.logMessagesContain(fieldName, fieldValue));
    }

    @Then("^I see log messages with with a valid correlation ID$")
    public void iSeeLogMessagesWithWithAValidCorrelationID() throws Throwable {
        assertTrue(driver.logMessagesHaveValidCorrelationIds());
    }
}
