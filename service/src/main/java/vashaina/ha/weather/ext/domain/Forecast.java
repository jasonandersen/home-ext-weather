package vashaina.ha.weather.ext.domain;

/**
 * A weather forecast for a particular zip code.
 */
public class Forecast {

    private final String todaysForecast;
    private final String tomorrowsForecast;
    private final String source;
    private final String zipCode;

    /**
     * Constructor.
     * @param source 
     * @param highTemp
     * @param zip
     */
    public Forecast(String todaysForecast, String tomorrowsForecast, String source, String zipCode) {
        this.todaysForecast = todaysForecast;
        this.tomorrowsForecast = tomorrowsForecast;
        this.source = source;
        this.zipCode = zipCode;
    }

    /**
     * @return forecast text for today
     */
    public String getTodaysForecast() {
        return todaysForecast;
    }

    /**
     * @return forecast text for tomorrow
     */
    public String getTomorrowsForecast() {
        return tomorrowsForecast;
    }

    /**
     * @return the source of the forecast
     */
    public String getSource() {
        return source;
    }

    /**
     * @return the zip code the forecast was requested for
     */
    public String getZipCode() {
        return zipCode;
    }
}
