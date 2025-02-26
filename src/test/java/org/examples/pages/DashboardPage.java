package org.examples.pages;

import com.microsoft.playwright.Page;
import java.net.URI;
import java.net.URISyntaxException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DashboardPage {
    private final Page page;
    private final String baseUrl;

    public DashboardPage(Page page, String baseUrl) {
        if (page == null) throw new IllegalArgumentException("Page cannot be null");
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalArgumentException("baseUrl cannot be null/empty");
        }
        this.page = page;
        this.baseUrl = baseUrl;
    }

    public void navigateToDashboard() {
        try {
            // Validate URL format
            URI validatedUrl = new URI(baseUrl);
            page.navigate(validatedUrl.toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid base URL: " + baseUrl, e);
        }
    }

    public boolean isOnDashboard() {
        return page.url().equals(baseUrl + "/");
    }

    public void verifyOnDashboard() {
        // Playwright automatically waits and asserts
        assertThat(page).hasURL(baseUrl + "/");
    }
}