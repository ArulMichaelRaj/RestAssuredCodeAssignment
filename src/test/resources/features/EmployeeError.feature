@errorapitest @apitests
Feature: All Operations of Employee API related to error

  Scenario: Hit any invalid url path
    Given I try to hit an invalid URL "/invalidUrl"
    Then I verify the response of the invalid URL is NOT_FOUND
