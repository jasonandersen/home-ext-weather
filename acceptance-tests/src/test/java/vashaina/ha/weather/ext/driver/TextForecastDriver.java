package vashaina.ha.weather.ext.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vashaina.ha.weather.ext.driver.wunderground.TemplatedStub;
import vashaina.ha.weather.ext.driver.wunderground.WundergroundStub;

/**
 * A driver to control tests against the text forecast element of the external
 * weather service. All text forecast tests should only touch this class.
 */
@Component
public class TextForecastDriver {

    private static Logger log = LoggerFactory.getLogger(TextForecastDriver.class);

    private ServiceGateway gateway;
    private String todaysForecast;
    private String tonightsForecast;
    private String tomorrowsForecast;
    private String tomorrowNightsForecast;
    private Response response;

    /**
     * Constructor
     */
    public TextForecastDriver(@Autowired ServiceGateway gateway) {
        this.gateway = gateway;
        setDefaultValues();
        log.info("starting up text forecast service driver");
    }

    /**
     * @param todaysForecast stubbed value to use for todays forecast text
     */
    public void setTodaysForecast(String todaysForecast) {
        this.todaysForecast = todaysForecast;
    }

    /**
     * @param tonightsForecast stubbed value to use for tonights forecast text
     */
    public void setTonightsForecast(String tonightsForecast) {
        this.tonightsForecast = tonightsForecast;
    }

    /**
     * @param tomorrowsForecast stubbed value to use for tomorrows forecast text
     */
    public void setTomorrowsForecast(String tomorrowsForecast) {
        this.tomorrowsForecast = tomorrowsForecast;
    }

    /**
     * @param tomorrowNightsForecast stubbed value to use for tomorrow nights forecast text
     */
    public void setTomorrowNightsForecast(String tomorrowNightsForecast) {
        this.tomorrowNightsForecast = tomorrowNightsForecast;
    }

    /**
     * Executes a request for a text forecast for a specific zip code.
     * @param zip
     */
    public void requestForecastForZip(String zip) {
        log.info("executing request");
        WundergroundStub stub = buildStub(zip);
        response = gateway.executeFromZip(zip, stub);
    }

    /**
     * @return the value of todays forecast returned from the service
     */
    public String getActualTodaysForecast() {
        return response.getTodaysForecast();
    }

    /**
     * @return the value of tomorrows forecast returned from the service
     */
    public String getActualTomorrowsForecast() {
        return response.getTomorrowsForecast();
    }

    /**
     * @return the value of the forecast source returned from the service
     */
    public String getActualSource() {
        return response.getSource();
    }

    /**
     * @return the value of the forecast's zip code returned from the service
     */
    public String getActualZip() {
        return response.getZip();
    }

    /**
     * @return true if the response from the service came back with any problems
     */
    public boolean hasError() {
        return response.hasError();
    }

    /**
     * @param errorType
     * @return true if an error was returned of this type
     */
    public boolean errorExists(String errorType) {
        String returnedErrorType = response.getErrorType();
        return errorType.equals(returnedErrorType);
    }

    /**
     * @return the message contained in the error, will return a blank string if no error was returned
     */
    public String getErrorMessage() {
        return response.getErrorMessage();
    }

    /**
     * Resets any state in the driver.
     */
    public void reset() {
        log.info("resetting driver state");
        setDefaultValues();
    }

    /**
     * @return the wunderground stubbed out call
     */
    private WundergroundStub buildStub(String zip) {
        TemplatedStub stub = new TemplatedStub(zip);
        stub.setTodaysForecast(todaysForecast);
        stub.setTonightsForecast(tonightsForecast);
        stub.setTomorrowsForecast(tomorrowsForecast);
        stub.setTomorrowNightsForecast(tomorrowNightsForecast);
        return stub;
    }

    /**
     * Sets any class fields to default values;
     */
    private void setDefaultValues() {
        todaysForecast = "TODAYS_FORECAST";
        tonightsForecast = "TONIGHTS_FORECAST";
        tomorrowsForecast = "TOMORROWS_FORECAST";
        tomorrowNightsForecast = "TOMORROW_NIGHTS_FORECAST";
        response = null;
    }

}
