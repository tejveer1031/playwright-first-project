package org.examples.hooks;


import io.cucumber.java.After;  // Correct import
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.examples.scenarioContext.ScenarioContext;

public class CucumberHooks {
    private final ScenarioContext context;

    // Dependency Injection via Constructor
    public CucumberHooks(ScenarioContext context) {
        this.context = context;
    }

    @Before(order = 0) // Lowest order runs first
    public void setup(Scenario scenario) {
        context.initializeBrowser(); // Sets up the Page
    }

    @After
    public void teardown(Scenario scenario) {
        context.closeResources();
    }

    }