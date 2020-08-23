package StepDefs;

import Model.RequestResponseSpecData;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.Matchers.containsString;


public class EmployeeCreateStepDefs extends GenericActions {

    /*
     * Below Cucumber Step Defenitions for all POST Api's within the test
     * */

    @Given("^I have the 'create employee' stub service up and running with the URL \"([^\"]*)\"$")

    public void iHaveTheCreateEmployeeStubServiceUpAndRunning(String path) {
        requestResponseSpecData.setPath(path);
        restStubsInitiateLibrary.postCreateEmployeeStubInitiate(path);
    }

    @When("^I do a POST API request to create an employee$")
    public void iDoAPostApiRequestToCreateEmployeeURL() throws URISyntaxException {

        Response response = RestAssured.given()
                .auth().preemptive().basic("mike@test.com", "test#123").when()

                .post(new URI(baseStubUrl + requestResponseSpecData.getPath()))
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the success message for 'Create Employee' and response status code as CREATED")
    public void iVerifyTheSuccessMessageAndResponseStatusCodeAsCreated() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body(containsString("successful operation"));
    }

    @Given("^I have the 'create multiple employee' stub service up and running with the URL \"([^\"]*)\"$")
    public void iHaveTheCreateMultipleEmployeeStubServiceUpAndRunning(String path) {
        requestResponseSpecData.setPath(path);
        restStubsInitiateLibrary.postCreateMultipleEmployeeStubInitiate(path);
    }

    @When("^I do a POST API request to create an employee to create multiple employees$")
    public void iDoAPostApiRequestToCreateMutipleEmployee(List<String> employees) throws URISyntaxException {

        Response response = RestAssured.given()
                .auth().preemptive().basic("mike@test.com", "test#123").formParam("empName", employees).when()
                .post(new URI(baseStubUrl + requestResponseSpecData.getPath()))
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the success message for 'Create List Employees' and response status code as CREATED")
    public void iVerifyTheSuccessMessageForCreateListEmployeesAndResponseStatusCodeAsCreated() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body(containsString("successful operation"));
    }


}
