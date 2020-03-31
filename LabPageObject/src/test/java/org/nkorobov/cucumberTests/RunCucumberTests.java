package org.nkorobov.cucumberTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"org.nkorobov.pages", "org.nkorobov.cucumberTests"},
        plugin = {"pretty", "summary"},
        strict = true,
        tags = {"@Transition or @Login"},
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class RunCucumberTests {
}
