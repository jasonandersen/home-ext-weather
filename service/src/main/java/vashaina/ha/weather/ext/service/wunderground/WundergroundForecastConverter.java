package vashaina.ha.weather.ext.service.wunderground;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vashaina.ha.weather.ext.domain.Forecast;
import vashaina.ha.weather.ext.service.wunderground.response.ForecastResponse;

/**
 * Converts {@link ForecastResponse} objects to {@link Forecast} objects. 
 */
@Component
public class WundergroundForecastConverter implements Converter<WundergroundForecast, Forecast> {

    /**
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public Forecast convert(WundergroundForecast source) {
        return new Forecast(source.getTodaysForecast(), source.getTomorrowsTextForecast(),
                WundergroundForecast.SOURCE, source.getZipCode());
    }

}
