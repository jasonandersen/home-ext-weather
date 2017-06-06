package vashaina.ha.weather.ext.service;

/**
 * Constants used to consistently declare {@link HystrixCommandGroup}s.
 */
public class CommandGroup {

    public static final String WUNDERGROUND_API = "api.wunderground.com";

    /**
     * Private constructor
     */
    private CommandGroup() {
        //no instantiation for you!
    }
}
