# ext-weather
# ===========
# 
# These scenarios are intended to document the service specification. Any consumers
# of this service should rely on this document as the canonical specification for 
# this service.
# 
# Jason Andersen
# 2016.08.27
@ServiceTest
Feature: ext-weather service specification

    # Stub out the upstream dependency on Wunderground.com
    Background:
        Given this stubbed response:
            | verb     | GET                                                          |
            | url      | http://api.wunderground.com/api/APIKEY/forecast/q/98070.json |
            | response | /data/wunderground/forecast/98070.json                       |

    # This is a valid request and response. 
    Scenario: Happy path
        When this document is requested:
            | verb     | GET                       |
            | path     | /weather/forecast/98070   |
            | header   | Accept: application/json  |
        Then a status code 200 is returned
        And this response body is returned:
"""
{
  "forecast": {
    "todaysForecast":"Monday: Considerable cloudiness. Lows overnight in the mid 50s. Monday Night: Mostly cloudy skies. A stray shower or thunderstorm is possible. Low 56F. Winds light and variable.",
    "tomorrowsForecast":"Tuesday: Cloudy. Slight chance of a rain shower. High 71F. Winds SSW at 5 to 10 mph. Tuesday Night: Mostly cloudy skies. Low 57F. Winds SSW at 5 to 10 mph.",
    "source":"Wunderground.com",
    "zipCode":"98070"
  },
  "problem":null
}  
"""

    # An invalid request will return a 400 status code and describe what the issue is within the 
    # problem object
    Scenario: Exception path - alphabetic characters in the zipcode
        When this document is requested:
            | verb     | GET                       |
            | path     | /weather/forecast/ABC     |
            | header   | Accept: application/json  |
        Then a status code 400 is returned
        And this response body is returned:
"""
{
    "forecast":null,
    "problem":{
        "type":"InvalidZipCodeException",
        "description":"zip code must be numeric"
    }
}
"""
    
    # Correlation IDs
    # ================
    # 
    # ext-weather uses correlation IDs to tie log messages from a single request together so all
    # log messages from one request will have the same correlation ID. If a correlation ID is 
    # passed in from another application, ext-weather will pick up that correlation ID and use it.
    # This means a single user request will have the same correlation ID even across multiple 
    # applications. 
    #
    # Correlation IDs are stored in an HTTP header called "X-Correlation-ID". If no header is present
    # in the request, ext-weather will create a correlation ID from a random long integer and encode
    # the long in Base62 format.
    #
    # Any correlation ID longer than 50 characters will be truncated down to 50 characters.
    
    Scenario: Requests that come in without an X-Correlation-ID header will have one generated automatically
        When this document is requested:
            | verb     | GET                      |
            | path     | /weather/forecast/ABC    |
            | header   | Accept: application/json |
        Then I see log messages with with a valid correlation ID
    
    Scenario: Correlation IDs that get passed in to the X-Correlation-ID header are used for logging
        When this document is requested:
            | verb     | GET                                                         |
            | path     | /weather/forecast/ABC                                       |
            | header   | Accept: application/json, X-Correlation-ID: i-like-monkeys  |
        Then I see log messages with "correlationId" of "i-like-monkeys"

    Scenario: Requests that come in with an X-Correlation-ID header longer than 50 characters will be truncated
        When this document is requested:
            | verb     | GET                                                                                                           |
            | path     | /weather/forecast/ABC                                                                                         |
            | header   | Accept: application/json, X-Correlation-ID: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA  |
        Then I see log messages with "correlationId" of "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        