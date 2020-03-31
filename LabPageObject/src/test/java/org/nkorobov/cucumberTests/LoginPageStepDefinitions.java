package org.nkorobov.cucumberTests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.nkorobov.pages.QuantopianLoginPage;

public class LoginPageStepDefinitions {
    private QuantopianLoginPage loginPage;

    @Given("^Login Page is open$")
    public void loginPageIsOpen() {
        loginPage = new QuantopianLoginPage(CucumberHooks.getDriver(), true);
    }

    @Then("^Login Page has been opened$")
    public void loginPageCheck() {
        if (loginPage == null) {
            loginPage = new QuantopianLoginPage(CucumberHooks.getDriver(), false);
        }
        Assert.assertEquals(loginPage.getPageTitle(), CucumberHooks.getDriver().getTitle());
        Assert.assertEquals(loginPage.getPageUrl(), CucumberHooks.getDriver().getCurrentUrl());
    }

    @When("^I enter email \"(.*)\"$")
    public void enterEmail(String email) {
        loginPage.enterEmail(email);
    }

    @When("^I enter password \"(.*)\"$")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("^I press SignIn$")
    public void pressSignIn() {
        loginPage.signIn();
    }

    @Then("^Login Email warning is active$")
    public void assertEmailWarning() {
        loginPage.assertEmailWarning();
    }

    @Then("^Login Password warning is active$")
    public void assertPasswordWarning() {
        loginPage.assertPasswordWarning();
    }

    @Then("^Failed Login message is active$")
    public void assertFailedLoginMessage() {
        loginPage.assertFailedLoginMessage();
    }
}
