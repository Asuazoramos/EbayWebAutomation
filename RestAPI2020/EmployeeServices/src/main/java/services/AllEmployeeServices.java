package services;

import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class AllEmployeeServices {

    String baseUrl = "http://info.venturepulse.org:8080/service-webapp";
    String resourcePath = "/api/AllEmployeeResources";

    public void callAllEmployeeResources(){
        Response response = given().when().get(baseUrl + resourcePath).then().statusCode(200).extract().response();
        String statusLine = response.statusLine();
        int statusCode = response.getStatusCode();
        System.out.println(statusLine);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
        response.getBody().prettyPrint();
        Assert.assertEquals(statusCode, 200);
    }

}
