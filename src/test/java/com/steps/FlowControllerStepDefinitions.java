package com.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FlowControllerStepDefinitions {

    private String apiEndpoint;
    private String requestBody;
    private Response response;

    @Given("I have the Flow POST API endpoint {string}")
    public void i_have_the_flow_post_api_endpoint(String endpoint) {
    	String url = "http://localhost:9090"+ endpoint;
    	this.apiEndpoint = url; // Set the API endpoint
    }

    @Given("I have the following valid JSON request body:")
    public void i_have_the_following_valid_json_request_body(String jsonBody) {
        this.requestBody = jsonBody; // Store the JSON request body
    }

    @When("I send a POST request to the Flow POST API with the provided request body")
    public void i_send_a_post_request_to_the_flow_post_api_with_the_provided_request_body() {
        // Send the POST request using RestAssured
        this.response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(apiEndpoint);
        System.out.println(" Body: " + requestBody);
        System.out.println("url: " + apiEndpoint);
System.out.println("Response Body: " + response.asString());
System.out.println("Response Code: " + response.statusCode());
assertEquals(200, response.statusCode());  
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        // Assert the response status code
        assertEquals(expectedStatusCode, response.getStatusCode(), "Unexpected status code");
        if (response.getStatusCode() == 400) {
            System.out.println("Response Body: " + response.getBody().asString());  // Print the response body for debugging
        }
    }

    @Then("the response message should be {string}")
    public void the_response_message_should_be(String expectedMessage) {
        // Assert the response message
        String actualMessage = response.getBody().asString();
        assertEquals(expectedMessage, actualMessage, "Unexpected response message");
    }
}
