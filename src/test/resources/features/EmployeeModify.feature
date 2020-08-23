@modifyapitest @apitests
Feature: All Operations of Employee API related to API method PUT/DELETE

  Scenario: Update an Employee with employee name
    Given I have the 'updateEmployee' stub service up and running with the URL "/employee" to update details of employee "Jack"
    When I do a PUT API request to update employeename
    Then I verify the response of 'updateEmployee' api service

  Scenario: Delete an Employee with employee name
    Given I have the 'deleteEmployee' stub service up and running with the URL "/employee" to delete details of employee "Aron"
    When I do a DELETE API request to delete employeename
    Then I verify the response of 'deleteEmployee' api service

  Scenario: Hit any invalid url path
    Given I try to hit an invalid URL "/invalidUrl"
    Then I verify the response of the invalid URL is NOT_FOUND


