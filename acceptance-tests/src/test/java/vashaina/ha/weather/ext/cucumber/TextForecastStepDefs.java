package vashaina.ha.weather.ext.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import vashaina.ha.weather.ext.driver.TextForecastDriver;

/**
 * Step definitions to retrieve text forecasts from the ext-weather web service.
 */
@ContextConfiguration(locations = { "classpath:features/cucumber.xml" })
public class TextForecastStepDefs {

    @Autowired
    private TextForecastDriver driver;

    @Before
    public void setupDriver() {
        driver.reset();
    }

    @SuppressWarnings("unused")
    @Given("^today is \"([^\"]*)\"$")
    public void todayIs(String today) throws Throwable {
        //NOOP - nothing is required for this, just a documenting step
    }

    @SuppressWarnings("unused")
    @Given("^we are requesting a forecast for zip code (\\d+)$")
    public void weAreRequestingAForecastForZipCode(String zip) throws Throwable {
        //NOOP - this didn't seem to do anything?
    }

    @Given("^the Wunderground forecast for today is \"([^\"]*)\"$")
    public void theWundergroundForecastForTodayIs(String todaysForecast) throws Throwable {
        driver.setTodaysForecast(todaysForecast);
    }

    @Given("^the Wunderground forecast for tonight is \"([^\"]*)\"$")
    public void theWundergroundForecastForTonightIs(String tonightsForecast) throws Throwable {
        driver.setTonightsForecast(tonightsForecast);
    }

    @Given("^the Wunderground forecast for tomorrow is \"([^\"]*)\"$")
    public void theWundergroundForecastForTomorrowIs(String tomorrowsForecast) throws Throwable {
        driver.setTomorrowsForecast(tomorrowsForecast);
    }

    @Given("^the Wunderground forecast for tomorrow night is \"([^\"]*)\"$")
    public void theWundergroundForecastForTomorrowNightIs(String tomorrowNightsForecast) throws Throwable {
        driver.setTomorrowNightsForecast(tomorrowNightsForecast);
    }

    @When("^I request a forecast for zip code \"([^\"]*)\"$")
    public void iRequestAForecastForZipCode(String zip) throws Throwable {
        driver.requestForecastForZip(zip);
    }

    @Then("^the forecast for today is \"([^\"]*)\"$")
    public void theForecastForTodayIs(String expectedTodaysForecast) throws Throwable {
        String actual = driver.getActualTodaysForecast();
        assertEquals(expectedTodaysForecast, actual);
    }

    @Then("^the forecast for tomorrow is \"([^\"]*)\"$")
    public void theForecastForTomorrowIs(String expectedTomorrowsForecast) throws Throwable {
        String actualTomorrowsForecast = driver.getActualTomorrowsForecast();
        assertEquals(expectedTomorrowsForecast, actualTomorrowsForecast);
    }

    @Then("^the source is \"([^\"]*)\"$")
    public void theSourceIs(String expectedSource) throws Throwable {
        String actualSource = driver.getActualSource();
        assertEquals(expectedSource, actualSource);
    }

    @Then("^the zip code is \"([^\"]*)\"$")
    public void theZipCodeIs(String expectedZip) throws Throwable {
        String actualZip = driver.getActualZip();
        assertEquals(expectedZip, actualZip);
    }

    @Then("^there are no errors$")
    public void thereAreNoErrors() throws Throwable {
        assertFalse(driver.hasError());
    }

    @Then("^I get an \"([^\"]*)\" error$")
    public void iGetAnError(String errorType) throws Throwable {
        assertTrue(driver.errorExists(errorType));
    }

    @Then("^the error message is \"([^\"]*)\"$")
    public void theErrorMessageIs(String errorMessage) throws Throwable {
        assertEquals(errorMessage, driver.getErrorMessage());
    }

}
