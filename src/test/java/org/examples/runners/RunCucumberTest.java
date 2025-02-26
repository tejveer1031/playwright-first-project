package org.examples.runners;

import org.junit.platform.suite.api.*;


import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "org.examples.stepDefinitions, org.examples.hooks"
)
@ConfigurationParameter(
        key = "cucumber.publish.quiet",
        value = "@Regression or @Smoke"
)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME, // For report formats
        value = "pretty, html:target/cucumber-reports/cucumber.html, json:target/cucumber-reports/cucumber.json, junit:target/cucumber-reports/cucumber.xml"
)
public class RunCucumberTest {
}