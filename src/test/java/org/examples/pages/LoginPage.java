package org.examples.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.examples.scenarioContext.ScenarioContext;

public class LoginPage {
    private final Page page;
    private final String baseUrl;

    // Locators
    private final String usernameSelector = "#username";
    private final String passwordSelector = "#password";

    public LoginPage(Page page, String baseUrl) {
        if (page == null) {
            throw new IllegalArgumentException("Page cannot be null");
        }
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalArgumentException("baseUrl cannot be null/empty");
        }
        this.page = page;
        this.baseUrl = baseUrl;
    }

    public void navigateToLoginPage() {
        page.navigate(baseUrl + "/login");
    }

    public void enterCredentials(String username, String password) {
        page.fill(usernameSelector, username);
        page.fill(passwordSelector, password);
    }

    public void clickLoginButton() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }

    public boolean isErrorMessageVisible(String message) {
        return page.getByText(message).isVisible();
    }
}