@postapitest @apitests
Feature:  All Operations of Employee API related to API method POST

  Scenario: Create Employee
    Given I have the 'create employee' stub service up and running with the URL "/employee"
    When I do a POST API request to create an employee
    Then I verify the success message for 'Create Employee' and response status code as CREATED

  Scenario: Create list of Employees
    Given I have the 'create multiple employee' stub service up and running with the URL "/employee/createWithArray"
    When I do a POST API request to create an employee to create multiple employees
      | Deo Raj | Rithik Pac | Mike Jackson | John Kennedy | Dulce Esposa |
    Then I verify the success message for 'Create List Employees' and response status code as CREATED