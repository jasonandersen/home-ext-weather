package vashaina.ha.weather.ext.service.wunderground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import vashaina.ha.weather.ext.domain.Forecast;
import vashaina.ha.weather.ext.service.wunderground.response.ForecastResponse;
import vashaina.ha.weather.ext.test.BaseIntegrationTest;
import vashaina.ha.weather.ext.test.TestUtils;

/**
 * Test that the {@link ForecastResponseConverter} class has been properly registered
 * and works like we expect.
 */
public class WundergroundForecastConverterTest extends BaseIntegrationTest {

    @Autowired
    @Qualifier("conversionService")
    private ConversionService service;

    private WundergroundForecast source;

    private Forecast target;

    @Before
    public void setupSource() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
        String response = TestUtils.readFileFromClasspath("/data/wunderground/responses/forecast/98070.json");
        String testUrl = "/url/doesnt/matter";
        server.expect(once(), requestTo(testUrl)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));
        ForecastResponse forecastResponse = restTemplate.getForObject(testUrl, ForecastResponse.class);
        source = new WundergroundForecast(forecastResponse, testUrl);
        assertNotNull(source);
    }

    @Test
    public void testDependencyInjection() {
        assertNotNull(service);
    }

    @Test
    public void testCanConvert() {
        assertTrue(service.canConvert(WundergroundForecast.class, Forecast.class));
    }

    @Test
    public void testTodaysForecast() {
        target = service.convert(source, Forecast.class);
        String expectedForecast = "Monday: Considerable cloudiness. Lows overnight in the mid 50s. Monday Night: Mostly cloudy skies. A stray shower or thunderstorm is possible. Low 56F. Winds light and variable.";
        assertEquals(expectedForecast, target.getTodaysForecast());
    }

    @Test
    public void testTomorrowsForecast() {
        target = service.convert(source, Forecast.class);
        String expectedForecast = "Tuesday: Cloudy. Slight chance of a rain shower. High 71F. Winds SSW at 5 to 10 mph. Tuesday Night: Mostly cloudy skies. Low 57F. Winds SSW at 5 to 10 mph.";
        assertEquals(expectedForecast, target.getTomorrowsForecast());
    }

}
