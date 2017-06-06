package vashaina.ha.weather.ext.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import vashaina.ha.weather.ext.domain.Forecast;
import vashaina.ha.weather.ext.domain.ForecastResponse;
import vashaina.ha.weather.ext.domain.ForecastResponse.Problem;
import vashaina.ha.weather.ext.domain.ZipCode;
import vashaina.ha.weather.ext.exception.ExternalWeatherServiceException;
import vashaina.ha.weather.ext.service.ExternalWeatherService;

/**
 * REST controller to provide external weather forecasts.
 */
@RestController
@RequestMapping("/weather")
public class ExternalWeatherController {

    private static Logger log = LoggerFactory.getLogger(ExternalWeatherController.class);

    @Autowired
    private ExternalWeatherService weatherService;

    /**
     * @param zip
     * @return tomorrow's forecast
     * @throws Exception 
     */
    @RequestMapping("/forecast/{zip}")
    public ForecastResponse retrieveForecast(@PathVariable String zip) {
        log.info("retrieving forecast for zip {}", zip);
        ZipCode zipCode = new ZipCode(zip);
        Forecast forecast = weatherService.getForecast(zipCode);
        return new ForecastResponse(forecast);
    }

    /**
     * Handle recoverable exceptions that extend from {@link ExternalWeatherServiceException}.
     * @param request
     * @param e
     * @return a response containing a description of the problem
     */
    @ExceptionHandler
    public ResponseEntity<ForecastResponse> error(WebRequest request, ExternalWeatherServiceException e) {
        String requestUrl = request.getDescription(true);
        log.warn("There was an exception processing a request {}, type: {} description:{} ",
                requestUrl, e.getClass().getSimpleName(), e.getMessage());
        Problem problem = new Problem(e.getClass().getSimpleName(), e.getMessage());
        ForecastResponse response = new ForecastResponse(problem);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
