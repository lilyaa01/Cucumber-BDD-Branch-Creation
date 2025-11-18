package org.tss.cucumberdemo.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/create-github-repository.feature",
        glue = {"stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        tags = ""
)
public class TestRunner {
}
