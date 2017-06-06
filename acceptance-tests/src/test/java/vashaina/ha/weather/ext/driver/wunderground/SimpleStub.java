package vashaina.ha.weather.ext.driver.wunderground;

/**
 * Simple stub setup for a call to Wunderground.com
 */
public class SimpleStub implements WundergroundStub {

    private static final String HOST = "http://api.wunderground.com";

    private final String path;
    private final String response;

    /**
     * Constructor.
     * @param path
     * @param response
     */
    public SimpleStub(String path, String response) {
        this.path = path;
        this.response = response;
    }

    /**
     * @see vashaina.ha.weather.ext.driver.wunderground.WundergroundStub#getPath()
     */
    @Override
    public String getPath() {
        if (path.startsWith(HOST)) {
            return path.substring(HOST.length());
        }
        return path;
    }

    /**
     * @see vashaina.ha.weather.ext.driver.wunderground.WundergroundStub#getResponse()
     */
    @Override
    public String getResponse() {
        return response;
    }

}
