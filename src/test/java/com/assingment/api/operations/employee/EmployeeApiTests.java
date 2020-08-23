package com.assingment.api.operations.employee;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"StepDefs"},
        features = {"src/test/resources"})

public class EmployeeApiTests  {

}
