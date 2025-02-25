package org.examples.managers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserManagers {
    private Playwright playwright;
    private Browser browser;

    public Page createPage(String browserTypeString) {
        playwright = Playwright.create();
        BrowserType browserType;

        switch (browserTypeString.toLowerCase()) {
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "webkit":
                browserType = playwright.webkit();
                break;
            default:
                browserType = playwright.chromium();
        }

        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(4.0));
        return browser.newPage();
    }

    public void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
