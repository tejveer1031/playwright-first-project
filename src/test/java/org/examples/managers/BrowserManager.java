package org.examples.managers;

import com.microsoft.playwright.*;
import org.examples.utilities.ConfigReader;

public class BrowserManager {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;

    public static Page initializeBrowser() {
        playwright = Playwright.create();

        // Read configurations
        String browserType = ConfigReader.getProperty("browser.type", "chromium");
        boolean isHeadless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless", "false")
        );
        int slowMo = Integer.parseInt(
                ConfigReader.getProperty("slow.mo", "0")
        );

        // Configure browser options
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(isHeadless)
                .setSlowMo(slowMo);

        // Initialize browser based on config
        switch (browserType.toLowerCase()) {
            case "firefox" -> browser = playwright.firefox().launch(options);
            case "webkit" -> browser = playwright.webkit().launch(options);
            default -> browser = playwright.chromium().launch(options);
        }

        context = browser.newContext();
        return context.newPage();
    }

    public static void closeBrowser() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}