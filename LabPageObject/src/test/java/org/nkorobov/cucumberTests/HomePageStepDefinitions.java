package org.nkorobov.cucumberTests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.nkorobov.pages.QuantopianHomePage;

public class HomePageStepDefinitions {
    private QuantopianHomePage homePage;

    @Given("^Home Page is open$")
    public void homePageIsOpen() {
        homePage = new QuantopianHomePage(CucumberHooks.getDriver(), true);
    }

    @When("^I press Login button on Home page$")
    public void pressLoginButton() {
        homePage.transitionToLoginPage();
    }

    @When("^I press Contest Button on Home page$")
    public void pressContestButton() {
        homePage.transitionToContestPage();
    }

    @Then("^Home Page has been opened$")
    public void homePageCheck() {
        if (homePage == null) {
            homePage = new QuantopianHomePage(CucumberHooks.getDriver(), false);
        }
        String currentUrl = CucumberHooks.getDriver().getCurrentUrl();
        Assert.assertTrue(homePage.getPageUrl().equals(currentUrl) ||
                homePage.getFullHomePageUrl().equals(currentUrl));
        Assert.assertEquals(homePage.getPageTitle(), CucumberHooks.getDriver().getTitle());
    }
}
