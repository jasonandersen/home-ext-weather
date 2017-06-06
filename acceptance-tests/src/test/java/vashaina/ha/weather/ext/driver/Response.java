package vashaina.ha.weather.ext.driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A response returned from calling the external weather service.
 */
public class Response {

    private final String response;
    private final int statusCode;
    private final Pattern todaysForecastPattern = Pattern.compile("(?<=\"todaysForecast\":\").+?(?=\")");
    private final Pattern tomorrowsForecastPattern = Pattern.compile("(?<=\"tomorrowsForecast\":\").+?(?=\")");
    private final Pattern sourcePattern = Pattern.compile("(?<=\"source\":\").+?(?=\")");
    private final Pattern zipPattern = Pattern.compile("(?<=\"zipCode\":\").+?(?=\")");
    private final Pattern problemDescriptionPattern = Pattern.compile("(?<=\"description\":\").+?(?=\")");
    private final Pattern problemTypePattern = Pattern.compile("(?<=\"type\":\").+?(?=\")");

    /**
     * Constructor
     * @param rawResponse
     * @param statusCode 
     */
    public Response(String response, int statusCode) {
        this.response = response;
        this.statusCode = statusCode;
    }

    /**
     * @return the text of the response body
     */
    public String getResponseBody() {
        return response;
    }

    /**
     * @return today's forecast from the response, will return a blank string if not found
     */
    public String getTodaysForecast() {
        return findSubstring(todaysForecastPattern);
    }

    /**
     * @return the HTTP status code returned from response
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return tomorrow's forecast from the response, will return a blank string if not found
     */
    public String getTomorrowsForecast() {
        return findSubstring(tomorrowsForecastPattern);
    }

    /**
     * @return the forecast's original source
     */
    public String getSource() {
        return findSubstring(sourcePattern);
    }

    /**
     * @return the forecast's zip code
     */
    public String getZip() {
        return findSubstring(zipPattern);
    }

    /**
     * @return true if this response has an error
     */
    public boolean hasError() {
        return !getErrorType().isEmpty();
    }

    /**
     * @return the type of the error
     */
    public String getErrorType() {
        return findSubstring(problemTypePattern);
    }

    /**
     * @return the message in the error
     */
    public String getErrorMessage() {
        return findSubstring(problemDescriptionPattern);
    }

    /**
     * Given a regex pattern, will find a substring in the JSON response
     * @param pattern
     * @return the substring if found, otherwise a blank string
     */
    private String findSubstring(Pattern pattern) {
        if (response == null) {
            return "";
        }

        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

}
