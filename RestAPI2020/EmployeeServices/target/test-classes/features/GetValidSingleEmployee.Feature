Feature: Rest API for Valid Single Employee

  Scenario: Get Single Employee Info Valid Get Request

    Given baseUrl and valid resourcePath
    When Make a valid Get call
    Then I should receive status code 200