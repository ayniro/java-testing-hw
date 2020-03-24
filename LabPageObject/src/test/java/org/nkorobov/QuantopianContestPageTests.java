package org.nkorobov;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QuantopianContestPageTests extends BaseTests {

    @Category(TestCategories.TransitionTests.class)
    @Test
    public void transitionFromContestToHome() {
        QuantopianContestPage contestPage = new QuantopianContestPage(driver);
        QuantopianHomePage homePage = contestPage.transitionToHomePage();

        Assert.assertEquals(homePage.getPageTitle(), driver.getTitle());
        Assert.assertEquals(homePage.getFullHomePageUrl(), driver.getCurrentUrl());
    }

    @Category(TestCategories.LoginTests.class)
    @Test
    public void submitEntryEmptyFieldsTest() {
        QuantopianContestPage contestPage = new QuantopianContestPage(driver);
        contestPage.pressSubmitEntry().checkTermsOfUseCheckbox().pressJoinButton();

        Assert.assertTrue(contestPage.allWarningsAreActive());
    }

}
