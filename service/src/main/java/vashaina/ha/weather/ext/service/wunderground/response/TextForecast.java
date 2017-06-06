package vashaina.ha.weather.ext.service.wunderground.response;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class TextForecast {

    @JsonProperty("forecastday")
    private List<ForecastDayText> forecastDays = new ArrayList<>();

    @JsonProperty("forecastday")
    public List<ForecastDayText> getForecastDays() {
        return forecastDays;
    }

    @JsonProperty("forecastday")
    public void setForecastDays(List<ForecastDayText> forecastDays) {
        this.forecastDays = forecastDays;
    }

}