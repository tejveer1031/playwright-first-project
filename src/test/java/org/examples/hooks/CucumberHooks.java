package org.examples.hooks;

import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.examples.managers.BrowserManagers;
import org.examples.scenarioContext.ScenarioContext;

public class CucumberHooks {

    private BrowserManagers browserManager;
    private ScenarioContext scenarioContext;

    // Dependency injection for ScenarioContext
    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        this.browserManager = new BrowserManagers(); // Initialize BrowserManager
    }

    @Before
    public void setupScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        String browserType = System.getProperty("browser", "chromium");

        // Initialize Playwright resources using BrowserManager
        Page page = browserManager.createPage(browserType);
        scenarioContext.setPage(page); // Store the Page in ScenarioContext for use in step definitions

        System.out.println("Playwright Page initialized and available in ScenarioContext.");
    }

    @After
    public void teardownScenario(Scenario scenario) {
        System.out.println("Ending scenario: " + scenario.getName() + " - Status: " + scenario.getStatus());

        // Attempt to close Playwright resources safely
        try {
            browserManager.closeBrowser();
        } catch (Exception e) {
            System.err.println("Error closing browser: " + e.getMessage());
        }

        try {
            scenarioContext.closeResources();
        } catch (Exception e) {
            System.err.println("Error closing ScenarioContext resources: " + e.getMessage());
        }

        System.out.println("Playwright Browser and resources closed.");
    }
}
