package org.nkorobov.cucumberTests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.nkorobov.pages.QuantopianContestPage;
import org.nkorobov.pages.QuantopianHomePage;
import org.nkorobov.pages.QuantopianLoginPage;
import org.openqa.selenium.WebDriver;

public class StepDefinitions {
    private QuantopianLoginPage loginPage;
    private QuantopianContestPage contestPage;
    private QuantopianHomePage homePage;

    @Given("^Login Page is open$")
    public void loginPageIsOpen() {
        loginPage = new QuantopianLoginPage(CucumberHooks.getDriver(), true);
    }

    @Given("^Contest Page is open$")
    public void contestPageIsOpen() {
        contestPage = new QuantopianContestPage(CucumberHooks.getDriver(), true);
    }

    @Given("^Home Page is open$")
    public void homePageIsOpen() {
        homePage = new QuantopianHomePage(CucumberHooks.getDriver(), true);
    }

    @Then("^Login Page has been opened$")
    public void loginPageCheck() {
        if (loginPage == null) {
            loginPage = new QuantopianLoginPage(CucumberHooks.getDriver(), false);
        }
        Assert.assertEquals(loginPage.getPageTitle(), CucumberHooks.getDriver().getTitle());
        Assert.assertEquals(loginPage.getPageUrl(), CucumberHooks.getDriver().getCurrentUrl());
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

    @Then("^Contest Page has been opened$")
    public void contestPageCheck() {
        if (contestPage == null) {
            contestPage = new QuantopianContestPage(CucumberHooks.getDriver(), false);
        }
        Assert.assertEquals(contestPage.getPageTitle(), CucumberHooks.getDriver().getTitle());
        Assert.assertEquals(contestPage.getPageUrl(), CucumberHooks.getDriver().getCurrentUrl());
    }
}
