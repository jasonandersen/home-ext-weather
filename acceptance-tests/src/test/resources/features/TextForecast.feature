Feature: Call an external weather service to retrieve a text forecast

    Background:
        Given today is "Monday"
        And the Wunderground forecast for today is "Today's forecast."
        And the Wunderground forecast for tonight is "Tonight's forecast."
        And the Wunderground forecast for tomorrow is "Tomorrow's forecast."
        And the Wunderground forecast for tomorrow night is "Tomorrow night's forecast."

    Scenario: Retrieve text forecast based on zip code
        When I request a forecast for zip code "98070"
        Then the forecast for today is "Monday: Today's forecast. Monday Night: Tonight's forecast."
        And the forecast for tomorrow is "Tuesday: Tomorrow's forecast. Tuesday Night: Tomorrow night's forecast."
        And the source is "Wunderground.com"
        And the zip code is "98070"
        And there are no errors
        