package org.examples.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.examples.pages.DashboardPage;
import org.examples.pages.LoginPage;
import org.examples.scenarioContext.ScenarioContext;
import org.examples.utilities.ConfigReader;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private final String baseUrl;
    private final ScenarioContext context; // Declare ScenarioContext

    public LoginSteps(ScenarioContext context) {
        this.context = context;
        this.baseUrl = ConfigReader.getProperty("base.url","http://localhost:4200");
        this.loginPage = new LoginPage(context.getPage(), baseUrl);
        this.dashboardPage = new DashboardPage(context.getPage(), baseUrl);
    }
    @Before(order = 1) // Runs AFTER CucumberHooks setup
    public void initializePages() {
        // Validate Page is initialized
        if (context.getPage() == null) {
            throw new IllegalStateException("Page not initialized in ScenarioContext");
        }
        this.loginPage = new LoginPage(context.getPage(), baseUrl);
        this.dashboardPage = new DashboardPage(context.getPage(), baseUrl);
    }

    private void validateConfiguration() {
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("base.url not configured in config.properties");
        }
        if (context.getPage() == null) {
            throw new IllegalStateException("Page not initialized in ScenarioContext");
        }
    }

    @Given("User is on main dashboard page")
    public void userIsOnMainDashboardPage() {
        dashboardPage.navigateToDashboard();
    }

    @And("navigate to login page")
    public void navigateToLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @Then("User is on the login page")
    public void userIsOnTheLoginPage() {
        Assertions.assertTrue(context.getPage().url().contains("/login"));
    }

    @When("User enters valid username and password")
    public void enterValidCredentials() {
        loginPage.enterCredentials("test1", "Password@123");
    }

    @When("User clicks the {string} button")
    public void clickButton(String buttonText) {
        loginPage.clickLoginButton();
    }

    @Then("User should be logged in successfully log in and navigate to main dashboard")
    public void verifySuccessfulLogin() {
        dashboardPage.verifyOnDashboard(); // Uses Playwright's assertion
    }
}