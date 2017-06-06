package vashaina.ha.weather.ext.service;

import vashaina.ha.weather.ext.domain.Forecast;
import vashaina.ha.weather.ext.domain.ZipCode;

/**
 * Provides forecasts from external weather services.
 */
@FunctionalInterface
public interface ExternalWeatherService {

    /**
     * Retrieves a forecast for the specified zip code.
     * @param zip
     * @return tomorrow's forecast
     * @throws Exception 
     */
    Forecast getForecast(ZipCode zip);

}
