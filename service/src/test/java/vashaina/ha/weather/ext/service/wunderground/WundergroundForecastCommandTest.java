package vashaina.ha.weather.ext.service.wunderground;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import vashaina.ha.weather.ext.test.TestUtils;

/**
 * Test the @{link WundergroundForecastCommand} class.
 */
public class WundergroundForecastCommandTest {

    private String url = "http://api.wunderground.com/api/0b50d6f48b0d3720/forecast/q/98070.json";

    private WundergroundForecastCommand command;

    private RestTemplate restTemplate;

    @Before
    public void setupRestTemplate() throws IOException {
        restTemplate = new RestTemplate();
        String response = TestUtils.readFileFromClasspath("/data/wunderground/responses/forecast/98070.json");
        TestUtils.mockRestTemplateJsonResponse(restTemplate, url, response);
        command = new WundergroundForecastCommand(url, restTemplate);
    }

    @Test
    public void testRunHappyPath() throws Exception {
        WundergroundForecast forecast = command.run();
        String expected = "Tuesday: Cloudy. Slight chance of a rain shower. High 71F. Winds SSW at 5 to 10 mph. Tuesday Night: Mostly cloudy skies. Low 57F. Winds SSW at 5 to 10 mph.";
        assertEquals(expected, forecast.getTomorrowsTextForecast());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRestTemplate() {
        command = new WundergroundForecastCommand(url, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUrl() throws Exception {
        command = new WundergroundForecastCommand(null, restTemplate);
    }
}
