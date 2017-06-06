package vashaina.ha.weather.ext.service.wunderground.response;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class ForecastDaySimple {

    @JsonProperty("date")
    private ForecastDate date;
    @JsonProperty("period")
    private Integer period;
    @JsonProperty("high")
    private High high;
    @JsonProperty("low")
    private Low low;
    @JsonProperty("conditions")
    private String conditions;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("date")
    public ForecastDate getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(ForecastDate date) {
        this.date = date;
    }

    @JsonProperty("period")
    public Integer getPeriod() {
        return period;
    }

    @JsonProperty("period")
    public void setPeriod(Integer period) {
        this.period = period;
    }

    @JsonProperty("high")
    public High getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(High high) {
        this.high = high;
    }

    @JsonProperty("low")
    public Low getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Low low) {
        this.low = low;
    }

    @JsonProperty("conditions")
    public String getConditions() {
        return conditions;
    }

    @JsonProperty("conditions")
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonProperty("icon_url")
    public String getIconUrl() {
        return iconUrl;
    }

    @JsonProperty("icon_url")
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}