package vashaina.ha.weather.ext.service.wunderground.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastResponse {

    @JsonProperty("response")
    private Response response;
    @JsonProperty("forecast")
    private Forecast forecast;

    @JsonProperty("response")
    public Response getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(Response response) {
        this.response = response;
    }

    @JsonProperty("forecast")
    public Forecast getForecast() {
        return forecast;
    }

    @JsonProperty("forecast")
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

}