package vashaina.ha.weather.ext.domain;

/**
 * Provides a response to a request for a text forecast.
 */
public class ForecastResponse {

    public static class Problem {
        private final String type;
        private final String description;

        /**
         * Constructor
         * @param type
         * @param description
         */
        public Problem(String type, String description) {
            this.type = type;
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }

    }

    private final Forecast forecast;
    private final Problem problem;

    /**
     * Constructor based on just the forecast
     * @param forecast
     */
    public ForecastResponse(Forecast forecast) {
        this(forecast, null);
    }

    /**
     * Constructor based on just the problem
     * @param problem
     */
    public ForecastResponse(Problem problem) {
        this(null, problem);
    }

    /**
     * Constructor
     * @param forecast
     * @param problem
     */
    public ForecastResponse(Forecast forecast, Problem problem) {
        this.forecast = forecast;
        this.problem = problem;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public Problem getProblem() {
        return problem;
    }

}
