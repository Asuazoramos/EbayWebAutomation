Feature: Rest API for InValid Single Employee

  Scenario: Get Single Employee Info InValid Get Request

    Given baseUrl and valid resourcePath
    When Make a invalid Get call
    Then I should receive status code 404