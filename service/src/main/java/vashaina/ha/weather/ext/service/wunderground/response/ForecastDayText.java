package vashaina.ha.weather.ext.service.wunderground.response;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class ForecastDayText {

    @JsonProperty("period")
    private Integer period;
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("title")
    private String title;
    @JsonProperty("fcttext")
    private String fcttext;

    @JsonProperty("period")
    public Integer getPeriod() {
        return period;
    }

    @JsonProperty("period")
    public void setPeriod(Integer period) {
        this.period = period;
    }

    @JsonProperty("icon_url")
    public String getIconUrl() {
        return iconUrl;
    }

    @JsonProperty("icon_url")
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("fcttext")
    public String getFcttext() {
        return fcttext;
    }

    @JsonProperty("fcttext")
    public void setFcttext(String fcttext) {
        this.fcttext = fcttext;
    }

}