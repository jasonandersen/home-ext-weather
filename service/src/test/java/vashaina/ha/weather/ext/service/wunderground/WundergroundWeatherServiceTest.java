package vashaina.ha.weather.ext.service.wunderground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vashaina.ha.weather.ext.domain.Forecast;
import vashaina.ha.weather.ext.domain.ZipCode;
import vashaina.ha.weather.ext.test.BaseIntegrationTest;
import vashaina.ha.weather.ext.test.TestUtils;

/**
 * Test the {@link WundergroundWeatherService} class.
 */
public class WundergroundWeatherServiceTest extends BaseIntegrationTest {

    private static Logger log = LoggerFactory.getLogger(WundergroundWeatherServiceTest.class);

    @Autowired
    private WundergroundWeatherService service;
    private String response;
    private Forecast forecast;

    @Before
    public void setup() throws Exception {
        response = TestUtils.readFileFromClasspath("/data/wunderground/responses/forecast/98070.json");
        assertNotNull(response);
        assertFalse(response.isEmpty());
        String url = "http://api.wunderground.com/api/0b50d6f48b0d3720/forecast/q/98070.json";
        TestUtils.mockRestTemplateJsonResponse(service.getRestTemplate(), url, response);
        log.debug("calling URL {}", url);
        forecast = service.getForecast(new ZipCode("98070"));
    }

    @Test
    public void testDependencyInjection() {
        assertNotNull(service);
    }

    @Test
    public void testTodaysForecast() throws Exception {
        String expectedToday = "Monday: Considerable cloudiness. Lows overnight in the mid 50s. Monday Night: Mostly cloudy skies. A stray shower or thunderstorm is possible. Low 56F. Winds light and variable.";
        assertEquals(expectedToday, forecast.getTodaysForecast());
    }

    @Test
    public void testTomorrowsForecast() {
        String expectedTomorrow = "Tuesday: Cloudy. Slight chance of a rain shower. High 71F. Winds SSW at 5 to 10 mph. Tuesday Night: Mostly cloudy skies. Low 57F. Winds SSW at 5 to 10 mph.";
        assertEquals(expectedTomorrow, forecast.getTomorrowsForecast());
    }

    @Test
    public void testSource() {
        assertEquals("Wunderground.com", forecast.getSource());
    }

    @Test
    public void testZip() {
        assertEquals("98070", forecast.getZipCode());
    }

}
