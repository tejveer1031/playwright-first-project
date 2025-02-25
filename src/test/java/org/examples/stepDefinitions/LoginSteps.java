package org.examples.stepDefinitions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.examples.scenarioContext.ScenarioContext;
import org.junit.jupiter.api.Assertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class LoginSteps {


    private final Page page;

    public LoginSteps(ScenarioContext scenarioContext) {
        this.page = scenarioContext.getPage();
    }

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        Assertions.assertEquals("http://localhost:4200/login", page.url());
    }

    @When("User enters valid username")
    public void user_enters_valid_username() {
        page.locator("#username").fill("validUser"); // Replace with actual username field locator
    }

    @When("User enters valid password")
    public void user_enters_valid_password() {
        page.locator("#password").fill("validPassword"); // Replace with actual password field locator
    }

    @When("User enters invalid password")
    public void user_enters_invalid_password() {
        page.locator("#password").fill("invalidPassword");
    }

    @When("User clicks the {string} button") // Parameterized step for button name
    public void user_clicks_the_button(String buttonText) {
        Locator loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        loginButton.click();
    }

    @Then("User should be logged in successfully log in and navigate to main dashboard")
    public void user_should_be_logged_in_successfully_and_see_the_dashboard() {
        assertThat(page).hasURL("http://localhost:4200/");
    }

    @Then("User should see an error message {string}") // Parameterized step for error message
    public void user_should_see_an_error_message(String errorMessage) {
        Assertions.assertTrue(page.getByText(errorMessage).isVisible()); // Assert error message is visible
    }

    @Then("User should remain on the login page")
    public void user_should_remain_on_the_login_page() {
        Assertions.assertTrue(page.url().contains("/login")); // Assert URL still contains "/login"
    }

    @Given("User is on main dashboard page")
    public void userIsOnMainDashboardPage() {
        page.navigate("http://localhost:4200");

    }

    @Then("User enters valid username and password")
    public void userEntersValidUsernameAndPassword() {
        Locator usernameField = page.locator("input[type='text'][id='username']");
        usernameField.fill("test1");
        Locator passwordFiled = page.locator("input[type='password'][id='password']");
        passwordFiled.fill("Password@123");
    }

    @And("navigate to login page")
    public void navigateToLoginPage() {
        Locator loginLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login"));
        loginLink.click();
    }
}