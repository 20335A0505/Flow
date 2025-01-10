package com.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FlowControllerStepDefinitions {

	private String apiEndpoint;
	private String requestBody;
	private Response response;

	@Given("I have the Flow POST API endpoint {string}")
	public void i_have_the_flow_post_api_endpoint(String endpoint) {
		String url = "http://localhost:9090" + endpoint;
		this.apiEndpoint = url;
	}

	@Given("I have the following valid JSON request body:")
	public void i_have_the_following_valid_json_request_body(String jsonBody) {
		this.requestBody = jsonBody;
	}

	@When("I send a POST request to the Flow POST API with the provided request body")
	public void i_send_a_post_request_to_the_flow_post_api_with_the_provided_request_body() {
		this.response = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(requestBody)
				.post(apiEndpoint);
	}

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(Integer expectedStatusCode) {
		assertEquals(expectedStatusCode, response.getStatusCode(), "Unexpected status code");
		if (response.getStatusCode() == 400) {
			System.out.println("Response Body: " + response.getBody().asString()); 
		}
	}
	
	@Then("the response message should contain {string}")
	public void the_response_message_should_contain(String expectedMessage) {
	    String actualMessage = response.getBody().asString();
	    assertTrue(actualMessage.contains(expectedMessage), 
	        "Expected message not found in response. Actual message: " + actualMessage);
	}


	@Then("the response message should be {string}")
	public void the_response_message_should_be(String expectedMessage) {
		String actualMessage = response.getBody().asString();
		assertEquals(expectedMessage, actualMessage, "Unexpected response message");
	}
}
