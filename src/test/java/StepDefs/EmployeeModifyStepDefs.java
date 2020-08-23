package StepDefs;

import Model.RequestResponseSpecData;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import java.net.URISyntaxException;
import static org.hamcrest.Matchers.containsString;


/*
 * Below Cucumber Step Defenitions for all Modify Api's within the test
 * */

public class EmployeeModifyStepDefs extends GenericActions{

    @Given("^I have the 'updateEmployee' stub service up and running with the URL \"([^\"]*)\" to update details of employee \"([^\"]*)\"$")
    public void iHaveTheupdateEmployeetStubServiceUpAndRunning(String path, String employeeName) {
        requestResponseSpecData.setPath(path);
        requestResponseSpecData.setEmployeename(employeeName);
        restStubsInitiateLibrary.putEmployeeEmployeeNameStubInitiate(path, employeeName);
    }

    @When("^I do a PUT API request to update employeename")
    public void iDoAPutApiRequestToUpdateEmplopyeename() throws URISyntaxException {
        RestAssured.defaultParser = Parser.JSON;

        RestAssured.defaultParser = Parser.JSON;
        Response response = RestAssured.given()
                .when().auth().preemptive().basic("mike@test.com", "test#123")
                .baseUri(baseStubUrl)
                .pathParam("employeename", requestResponseSpecData.getEmployeename())
                .basePath(requestResponseSpecData.getPath() + "/{employeename}")
                .put()
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the response of 'updateEmployee' api service$")
    public void iVerifyTheResponseOfUpdateEmployeeApiService() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .body(containsString("Record Updated"));
    }

    @Given("^I have the 'deleteEmployee' stub service up and running with the URL \"([^\"]*)\" to delete details of employee \"([^\"]*)\"$")
    public void iHaveThedeleteEmployeetStubServiceUpAndRunning(String path, String employeeName) {
        requestResponseSpecData.setPath(path);
        requestResponseSpecData.setEmployeename(employeeName);
        restStubsInitiateLibrary.deleteEmployeeEmployeeNameStubInitiate(path, employeeName);
    }

    @When("^I do a DELETE API request to delete employeename")
    public void iDoADeleteApiRequestToDeleteEmplopyeename() throws URISyntaxException {

        Response response = RestAssured.given()
                .when().auth().preemptive().basic("mike@test.com", "test#123")
                .baseUri(baseStubUrl)
                .pathParam("employeename", requestResponseSpecData.getEmployeename())
                .basePath(requestResponseSpecData.getPath() + "/{employeename}")
                .delete()
                .then()
                .extract()
                .response();

        requestResponseSpecData.setResponse(response);
    }

    @Then("^I verify the response of 'deleteEmployee' api service$")
    public void iVerifyTheResponseOfDeleteEmployeeApiService() {

        requestResponseSpecData.getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("Record Updated"));
    }
}
