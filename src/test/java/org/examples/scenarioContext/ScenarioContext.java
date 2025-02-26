package org.examples.scenarioContext;

import com.microsoft.playwright.Page;
import org.examples.managers.BrowserManager;

public class ScenarioContext {
    private Page page;

    // Initialize via BrowserManager
    public void initializeBrowser() {
        this.page = BrowserManager.initializeBrowser();
    }

    // Cleanup via BrowserManager
    public void closeResources() {
        BrowserManager.closeBrowser();
    }

    // Page management
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}