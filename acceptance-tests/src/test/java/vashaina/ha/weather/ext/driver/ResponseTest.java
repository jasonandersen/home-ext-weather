package vashaina.ha.weather.ext.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import vashaina.ha.weather.ext.test.TestUtils;

/**
 * Just testing we got the regex right in {@link Response}.
 */
public class ResponseTest {

    private Response success;
    private Response failed;

    @Before
    public void setupSuccessfulResponse() throws IOException {
        String json = TestUtils.readFileFromClasspath("/data/ext-weather/text-forecast/success-response.json");
        success = new Response(json, 200);
    }

    @Before
    public void setupFailedResponse() throws IOException {
        String json = TestUtils.readFileFromClasspath("/data/ext-weather/text-forecast/problem-response.json");
        failed = new Response(json, 200);
    }

    /*
     * Success
     */

    @Test
    public void testTodaysForecast() {
        assertEquals("TODAYS_FORECAST", success.getTodaysForecast());
    }

    @Test
    public void testTomorrowsForecast() {
        assertEquals("TOMORROWS_FORECAST", success.getTomorrowsForecast());
    }

    @Test
    public void testGetSource() {
        assertEquals("Wunderground.com", success.getSource());
    }

    @Test
    public void testGetZip() {
        assertEquals("98070", success.getZip());
    }

    @Test
    public void testHasNoErrors() {
        assertFalse(success.hasError());
    }

    @Test
    public void testHasNoProblemType() {
        assertEquals("", success.getErrorType());
    }

    @Test
    public void testHasNoProblemDescription() {
        assertEquals("", success.getErrorMessage());
    }

    /*
     * Failed
     */

    @Test
    public void testHasErrors() {
        assertTrue(failed.hasError());
    }

    @Test
    public void testProblemType() {
        assertEquals("InvalidZipCodeException", failed.getErrorType());
    }

    @Test
    public void testProblemDescription() {
        assertEquals("zip code must be numeric", failed.getErrorMessage());
    }

}
