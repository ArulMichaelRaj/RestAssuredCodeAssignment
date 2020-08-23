@getapitest @apitests
Feature: All Operations of Employee API related to API method GET

  Scenario: Login as valid employee
    Given I have the 'login' stub service up and running with the URL "/employee/login" and below credentials
      | mike@test.com | test#123 |
    When I do a GET API request to login
    Then I verify the success message for login and response status code as OK

  Scenario: Employee logs out of the System
    Given I have the 'logout' stub service up and running with the URL "/employee/logout"
    When I do a GET API request to logout
    Then I verify the success message for logout and response status code as OK

  Scenario: Get an Employee details with employee name
    Given I have the 'getEmployee' stub service up and running with the URL "/employee" to return details of employee "Mike"
    When I do a GET API request to get employee details
    Then I verify the response of 'getEmployee' api service