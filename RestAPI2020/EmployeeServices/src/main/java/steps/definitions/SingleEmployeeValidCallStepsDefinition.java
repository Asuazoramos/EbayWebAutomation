package steps.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import services.SingleEmployeeResources;

public class SingleEmployeeValidCallStepsDefinition {

    String baseUrl = "";
    String resourcePath = "";
    String objectId = "5f18fdf92fc21306c59854ce";
    int actualStatusCode = 0;
    int expectedStatusCode = 200;


    @Given("baseUrl and valid resourcePath")
    public void baseurl_and_valid_resourcePath() {
        baseUrl = "http://info.venturepulse.org:8080/service-webapp";
       resourcePath = "/api/SingleEmployeeResources/";
    }

    @When("Make a valid Get call")
    public void make_a_valid_Get_call() {
        actualStatusCode = SingleEmployeeResources.getSingleEmployeeResourcesSuccessStatus(baseUrl,resourcePath,objectId);
    }

    @Then("I should receive status code 200")
    public void i_should_receive_status_code() {
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

    }


}
