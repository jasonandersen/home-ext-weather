package vashaina.ha.weather.ext.service.wunderground.response;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class ForecastDate {

    @JsonProperty("pretty")
    private String pretty;
    @JsonProperty("day")
    private Integer day;
    @JsonProperty("month")
    private Integer month;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("yday")
    private Integer yday;
    @JsonProperty("hour")
    private Integer hour;
    @JsonProperty("min")
    private String min;
    @JsonProperty("sec")
    private Integer sec;

    @JsonProperty("pretty")
    public String getPretty() {
        return pretty;
    }

    @JsonProperty("pretty")
    public void setPretty(String pretty) {
        this.pretty = pretty;
    }

    @JsonProperty("day")
    public Integer getDay() {
        return day;
    }

    @JsonProperty("day")
    public void setDay(Integer day) {
        this.day = day;
    }

    @JsonProperty("month")
    public Integer getMonth() {
        return month;
    }

    @JsonProperty("month")
    public void setMonth(Integer month) {
        this.month = month;
    }

    @JsonProperty("year")
    public Integer getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(Integer year) {
        this.year = year;
    }

    @JsonProperty("yday")
    public Integer getYday() {
        return yday;
    }

    @JsonProperty("yday")
    public void setYday(Integer yday) {
        this.yday = yday;
    }

    @JsonProperty("hour")
    public Integer getHour() {
        return hour;
    }

    @JsonProperty("hour")
    public void setHour(Integer hour) {
        this.hour = hour;
    }

    @JsonProperty("min")
    public String getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(String min) {
        this.min = min;
    }

    @JsonProperty("sec")
    public Integer getSec() {
        return sec;
    }

    @JsonProperty("sec")
    public void setSec(Integer sec) {
        this.sec = sec;
    }

}