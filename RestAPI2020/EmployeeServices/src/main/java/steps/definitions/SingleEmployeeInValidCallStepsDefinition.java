package steps.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import services.SingleEmployeeResources;

public class SingleEmployeeInValidCallStepsDefinition {

    String baseUrl = "";
    String resourcePath = "";
    String objectId = "5f18fdf92fc21306c59854ce/ssn";
    int actualStatusCode = 0;
    int expectedStatusCode = 404;


    @Given("baseUrl and invalid resourcePath")
    public void baseurl_and_invalid_resourcePath() {
        baseUrl = "http://info.venturepulse.org:8080/service-webapp";
       resourcePath = "/api/SingleEmployeeResources/";
    }

    @When("Make a invalid Get call")
    public void make_a_invalid_Get_call() {
        actualStatusCode = SingleEmployeeResources.getSingleEmployeeResourcesUnSuccessfulStatus(baseUrl,resourcePath,objectId);
    }

    @Then("I should receive status code 404")
    public void i_should_receive_status_code() {
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

    }


}
