package vashaina.ha.weather.ext.cucumber;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import vashaina.ha.weather.ext.driver.ServiceDriver;
import vashaina.ha.weather.ext.test.TestUtils;

/**
 * These step definitions allow for tests defining the actual service specification.
 */
@ContextConfiguration(locations = { "classpath:features/cucumber.xml" })
public class ServiceSpecificationSteps {

    @Autowired
    private ServiceDriver driver;

    @Before
    public void setupDriver() {
        driver.reset();
    }

    @Given("^this stubbed response:$")
    public void thisStubbedResponse(Map<String, String> attributes) throws Throwable {
        driver.setStubUrl(attributes.get("url"));
        driver.setStubResponsePath(attributes.get("response"));
    }

    @When("^this document is requested:$")
    public void thisRequestIsRequested(Map<String, String> attributes) throws Throwable {
        driver.requestPath(attributes.get("path"));
    }

    @Then("^a status code ([^\"]*) is returned$")
    public void aStatusCodeIsReturned(int expectedCode) throws Throwable {
        assertEquals(expectedCode, driver.getStatusCode());
    }

    @Then("^this response body is returned:$")
    public void thisResponseBodyIsReturned(String expectedResponse) throws Throwable {
        String expected = TestUtils.minifyJson(expectedResponse);
        String actual = TestUtils.minifyJson(driver.getResponseBody());
        assertEquals(expected, actual);
    }

}
