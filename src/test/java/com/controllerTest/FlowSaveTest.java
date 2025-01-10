package com.controllerTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
public class FlowSaveTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I am on the Flow Management page")
    public void i_am_on_the_flow_management_page() {
        System.setProperty("webdriver.chrome.driver", "http://localhost:3000/");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000"); // Update with your app URL
        driver.manage().window().maximize();
    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonName) {
        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class, 'nav-item') and .//i[contains(@class, 'bi-save')]]")
        ));
        saveButton.click();
    }

    @Then("I should see a success message {string}")
    public void i_should_see_a_success_message(String expectedMessage) {
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Flow Creation is success')]")
        ));
        String actualMessage = successMessage.getText();
        if (!actualMessage.contains(expectedMessage)) {
            throw new AssertionError("Expected: " + expectedMessage + ", but got: " + actualMessage);
        }
        driver.quit();
    }
}
