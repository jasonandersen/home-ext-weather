Feature: Respond to incorrectly formatted requests

    Scenario: Zip code with alphabetic characters
        When I request a forecast for zip code "ABC"
        Then I get an "InvalidZipCodeException" error
        And the error message is "zip code must be numeric"
        
    Scenario: Zip code with both numbers and letters
        When I request a forecast for zip code "9807a"
        Then I get an "InvalidZipCodeException" error
        And the error message is "zip code must be numeric"
    
    Scenario: Zip code with not enough digits
        When I request a forecast for zip code "9807"
        Then I get an "InvalidZipCodeException" error
        And the error message is "zip code must be 5 digits"
        
    Scenario: Zip code with too many digits
        When I request a forecast for zip code "980700"
        Then I get an "InvalidZipCodeException" error
        And the error message is "zip code must be 5 digits"
        