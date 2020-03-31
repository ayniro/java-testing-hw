package org.nkorobov.cucumberTests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.nkorobov.pages.QuantopianContestPage;

public class ContestPageStepDefinitions {
    private QuantopianContestPage contestPage;

    @Given("^Contest Page is open$")
    public void contestPageIsOpen() {
        contestPage = new QuantopianContestPage(CucumberHooks.getDriver(), true);
    }

    @Then("^Contest Page has been opened$")
    public void contestPageCheck() {
        if (contestPage == null) {
            contestPage = new QuantopianContestPage(CucumberHooks.getDriver(), false);
        }
        Assert.assertEquals(contestPage.getPageTitle(), CucumberHooks.getDriver().getTitle());
        Assert.assertEquals(contestPage.getPageUrl(), CucumberHooks.getDriver().getCurrentUrl());
    }

    @When("^I press Home button on Contest page$")
    public void pressHomeButton() {
        contestPage.pressHomeButton();
    }

    @When("^I press Submit Entry$")
    public void pressSubmitEntry() {
        contestPage.pressSubmitEntry();
    }

    @When("^I check Terms of Use checkbox$")
    public void checkTermsOfUseCheckbox() {
        contestPage.checkTermsOfUseCheckbox();
    }

    @When("^I press Join Button$")
    public void pressJoinButton() {
        contestPage.pressJoinButton();
    }

    @Then("^All register warnings are active$")
    public void assertAllWarnings() {
        contestPage.assertAllWarnings();
    }
}
