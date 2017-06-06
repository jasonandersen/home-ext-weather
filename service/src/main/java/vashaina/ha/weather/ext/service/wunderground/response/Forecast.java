package vashaina.ha.weather.ext.service.wunderground.response;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "txt_forecast",
        "simpleforecast"
})
public class Forecast {

    @JsonProperty("txt_forecast")
    private TextForecast txtForecast;
    @JsonProperty("simpleforecast")
    private SimpleForecast simpleforecast;

    @JsonProperty("txt_forecast")
    public TextForecast getTxtForecast() {
        return txtForecast;
    }

    @JsonProperty("txt_forecast")
    public void setTxtForecast(TextForecast txtForecast) {
        this.txtForecast = txtForecast;
    }

    @JsonProperty("simpleforecast")
    public SimpleForecast getSimpleforecast() {
        return simpleforecast;
    }

    @JsonProperty("simpleforecast")
    public void setSimpleforecast(SimpleForecast simpleforecast) {
        this.simpleforecast = simpleforecast;
    }

}