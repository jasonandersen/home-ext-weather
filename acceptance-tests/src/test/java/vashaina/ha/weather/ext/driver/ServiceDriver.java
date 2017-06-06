package vashaina.ha.weather.ext.driver;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vashaina.ha.weather.ext.driver.wunderground.SimpleStub;
import vashaina.ha.weather.ext.test.TestUtils;

/**
 * A driver to make direct raw JSON calls against the ext-weather service
 * and evaluate the raw JSON results.
 */
@Component
public class ServiceDriver {

    @Autowired
    private ServiceGateway gateway;
    private String stubUrl;
    private String stubResponsePath;
    private Response response;

    /**
     * Constructor
     */
    public ServiceDriver() {
        setDefaultValues();
    }

    /**
     * Reset all state within the driver.
     */
    public void reset() {
        setDefaultValues();
    }

    /**
     * @param stubUrl the URL to be replaced with a test double
     */
    public void setStubUrl(String stubUrl) {
        this.stubUrl = stubUrl;
    }

    /**
     * @param stubResponsePath the classpath to a file containing the body of the stubbed response
     */
    public void setStubResponsePath(String stubResponsePath) {
        this.stubResponsePath = stubResponsePath;
    }

    /**
     * Performs a request against the service.
     * @param servicePath the path portion of the URI for the service call
     * @throws IOException 
     */
    public void requestPath(String servicePath) throws IOException {
        String stubResponse = TestUtils.readFileFromClasspath(stubResponsePath);
        SimpleStub stub = new SimpleStub(stubUrl, stubResponse);
        response = gateway.executeFromPath(servicePath, stub);
    }

    /**
     * @return the HTTP status code from the service response
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * @return the raw response body from the service response
     */
    public String getResponseBody() {
        return response.getResponseBody();
    }

    /**
     * Resets all the state in the driver back to default values
     */
    private void setDefaultValues() {
        stubUrl = "http://api.wunderground.com/api/APIKEY/forecast/q/98070.json";
        stubResponsePath = "/data/wunderground/forecast/98070.json";
        response = null;
    }

    /**
     * @param fieldName
     * @param fieldValue
     * @return true if the log messages passed during this test contain the specified value
     *      in the specified field.
     */
    public boolean logMessagesContain(String fieldName, String fieldValue) {
        return false;
    }

    /**
     * @return true if all the log messages that have been collected during this test contain
     *      valid correlation IDs.
     */
    public boolean logMessagesHaveValidCorrelationIds() {
        return false;
    }

}
