package vashaina.ha.weather.ext.service.wunderground.response;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Both {@link High} and {@link Low} extend this class so they don't have
 * to duplicate each other's code.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public abstract class TempReading {

    @JsonProperty("fahrenheit")
    private String fahrenheit;
    @JsonProperty("celsius")
    private String celsius;

    @JsonProperty("fahrenheit")
    public String getFahrenheit() {
        return fahrenheit;
    }

    @JsonProperty("fahrenheit")
    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    @JsonProperty("celsius")
    public String getCelsius() {
        return celsius;
    }

    @JsonProperty("celsius")
    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

}