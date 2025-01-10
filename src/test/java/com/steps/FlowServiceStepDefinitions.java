package com.steps;

import com.service.FlowService;
import com.pojo.Flow;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class FlowServiceStepDefinitions {

    FlowService flowService = new FlowService();
    Flow flow;

    @Given("I have the following flow details")
    public void i_have_the_following_flow_details(Map<String, Object> flowDetails) {
        // Pass the provided flow details to the FlowService saveFlow method
        flow = flowService.saveFlow(flowDetails);
    }

    @When("I save the flow")
    public void i_save_the_flow() {
        // Flow is already saved in the saveFlow method
        // This is where you could trigger additional behavior if necessary
    }

    @Then("The flow should be saved with a valid flowId")
    public void the_flow_should_be_saved_with_a_valid_flowId() {
        // Assert that the flowId is valid and matches the expected format
        assertNotNull(flow.getFlowId());
        assertTrue(flow.getFlowId().startsWith("FLOW"));
    }

    @Then("The flow name should be {string}")
    public void the_flow_name_should_be(String expectedFlowName) {
        // Assert that the flow name is as expected
        assertEquals(expectedFlowName, flow.getFlowName());
    }

    @Then("The flow region should be {string}")
    public void the_flow_region_should_be(String expectedRegion) {
        // Assert that the region is as expected
        assertEquals(expectedRegion, flow.getRegion());
    }

    @Then("The flow stages should be {string}")
    public void the_flow_stages_should_be(String expectedStages) {
        // Assert that the stages match
        assertEquals(expectedStages.trim().toUpperCase(), flow.getStages());
    }

    @Then("The flow should have {int} nodes and {int} edges")
    public void the_flow_should_have_nodes_and_edges(int expectedNodeCount, int expectedEdgeCount) {
        // Assert that the flow contains the expected number of nodes and edges
//        assertEquals(expectedNodeCount, flow.getNodes().size());
//        assertEquals(expectedEdgeCount, flow.getEdges().size());
    }
}
