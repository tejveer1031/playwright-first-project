package org.examples.scenarioContext;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ScenarioContext {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;


    /**
     * Default constructor.
     * Recommended: Initialization will be handled in Cucumber hooks.
     */
    public ScenarioContext() {
        // Default constructor - resources will be set via setters (e.g., in hooks)
    }

    // Getters and Setters

    public Playwright getPlaywright() {
        return playwright;
    }

    public void setPlaywright(Playwright playwright) {
        this.playwright = playwright;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public BrowserContext getBrowserContext() {
        return browserContext;
    }

    public void setBrowserContext(BrowserContext browserContext) {
        this.browserContext = browserContext;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    /**
     * Closes all Playwright resources in the proper order.
     * This method ensures that each resource is safely closed to prevent resource leaks.
     */
    public void closeResources() {
        if (page != null) {
            page.close();
        }
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
