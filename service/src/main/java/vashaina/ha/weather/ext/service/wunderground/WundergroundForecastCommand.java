package vashaina.ha.weather.ext.service.wunderground;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import vashaina.ha.weather.ext.exception.ExternalCallFailedException;
import vashaina.ha.weather.ext.service.CommandGroup;
import vashaina.ha.weather.ext.service.wunderground.response.ForecastResponse;

/**
 * Resilient command to run a call to Wunderground to obtain a forecast.
 */
public class WundergroundForecastCommand extends HystrixCommand<WundergroundForecast> {

    private static Logger log = LoggerFactory.getLogger(WundergroundForecastCommand.class);

    private final String url;

    private final RestTemplate restTemplate;

    /**
     * Constructor
     * @param url
     * @param restTemplate
     */
    public WundergroundForecastCommand(String url, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey(CommandGroup.WUNDERGROUND_API));
        Validate.notNull(restTemplate, "REST template cannot be null");
        Validate.notNull(url, "URL cannot be null");
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * @see com.netflix.hystrix.HystrixCommand#run()
     */
    @Override
    protected WundergroundForecast run() {
        log.debug("requesting wunderground forecast from {}", url);
        // TODO: why aren't I using getForObject() ?
        ForecastResponse response = deserialize(getForecastJson());
        WundergroundForecast forecast = new WundergroundForecast(response, url);
        log.debug("response received from wunderground API: {}", forecast.toString());
        return forecast;
    }

    /**
     * @param forecastJson
     * @return a forecast response deserialized from JSON
     */
    private ForecastResponse deserialize(String forecastJson) {
        String json = forecastJson;
        if (json == null) {
            log.warn("forecast JSON response is null");
            json = "";
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(json, ForecastResponse.class);
        } catch (IOException e) {
            log.error("deserializing forecast response failed", e);
            throw new ExternalCallFailedException("deserializing forecast response failed", e);
        }
    }

    /**
     * @return pure JSON
     */
    private String getForecastJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> request = new HttpEntity<>(headers);
        log.debug("calling Wunderground URL {}", url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        log.debug("JSON response body {}", response.getBody());
        return response.getBody();
    }

}
