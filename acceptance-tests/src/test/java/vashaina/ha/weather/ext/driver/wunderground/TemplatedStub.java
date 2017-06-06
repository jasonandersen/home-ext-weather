package vashaina.ha.weather.ext.driver.wunderground;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import vashaina.ha.weather.ext.test.Template;

/**
 * Creates a stubbed response from Wunderground.com using a template and
 * replacing different parts of the response with values from the scenario.
 */
public class TemplatedStub implements WundergroundStub {

    private static final String FORECAST_TEMPLATE = "data/wunderground/forecast/template.json";

    private final Map<String, String> values;
    private final String zip;

    /**
     * Constructor
     * @param url
     */
    public TemplatedStub(String zip) {
        values = new HashMap<>();
        this.zip = zip;
    }

    /**
     * @see vashaina.ha.weather.ext.driver.wunderground.WundergroundStub#getResponse()
     */
    @Override
    public String getResponse() {
        Template template = new Template(FORECAST_TEMPLATE, values);
        try {
            return template.render();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @see vashaina.ha.weather.ext.driver.wunderground.WundergroundStub#getUrl()
     */
    @Override
    public String getPath() {
        return String.format("/api/APIKEY/forecast/q/%s.json", zip);
    }

    public void setTodaysForecast(String value) {
        values.put("TODAYS_FORECAST", value);
    }

    public void setTonightsForecast(String value) {
        values.put("TONIGHTS_FORECAST", value);
    }

    public void setTomorrowsForecast(String value) {
        values.put("TOMORROWS_FORECAST", value);
    }

    public void setTomorrowNightsForecast(String value) {
        values.put("TOMORROW_NIGHTS_FORECAST", value);
    }

}
