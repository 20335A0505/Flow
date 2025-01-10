package com.controllerTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/flowController.feature",
		glue = "com.steps",
		plugin = {"pretty", "html:target/cucumber-reports"}
		)
public class FlowTest {
}
 