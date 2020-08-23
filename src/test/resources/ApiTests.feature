Feature: Operations of Employee API

  Scenario: Create Employee
    Given I have the 'create employee' stub service up and running with the URL "/employee"
    When I do a POST API request to create an employee
    Then I verify the success message and response status code as CREATED

  Scenario: Create list of Employees
    Given I have the 'create multiple employee' stub service up and running with the URL "/employee/createWithArray"
    When I do a POST API request to create an employee to create multiple employees
      | Deo Raj | Rithik Pac | Mike Jackson | John Kennedy | Dulce Esposa |
    Then I verify the success message and response status code as CREATED

  Scenario: Login as valid employee
    Given I have the 'login' stub service up and running with the URL "/employee/login" and below credentials
      | mike@test.com | test#123 |
    When I do a GET API request to login
    Then I verify the success message and response status code as OK

  Scenario: Employee logs out of the System
    Given I have the 'logout' stub service up and running with the URL "/employee/logout"
    When I do a GET API request to logout
    Then I verify the success message and response status code as OK

  Scenario: Get an Employee details with employee name
    Given I have the 'getEmployee' stub service up and running with the URL "/employee" to return details of employee "Mike"
    When I do a GET API request to get employee details
    Then I verify the response of 'getEmployee' api service

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


