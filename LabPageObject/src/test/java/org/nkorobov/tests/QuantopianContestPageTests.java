package org.nkorobov.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nkorobov.pages.QuantopianContestPage;
import org.nkorobov.pages.QuantopianHomePage;

public class QuantopianContestPageTests extends BaseTests {

    @Category(TestCategories.TransitionTests.class)
    @Test
    public void transitionFromContestToHome() {
        QuantopianContestPage contestPage = new QuantopianContestPage(driver, true);
        QuantopianHomePage homePage = contestPage.pressHomeButton();

        Assert.assertEquals(homePage.getPageTitle(), driver.getTitle());
        Assert.assertEquals(homePage.getFullHomePageUrl(), driver.getCurrentUrl());
    }

    @Category(TestCategories.LoginTests.class)
    @Test
    public void submitEntryEmptyFieldsTest() {
        QuantopianContestPage contestPage = new QuantopianContestPage(driver, true);
        contestPage.pressSubmitEntry().checkTermsOfUseCheckbox().pressJoinButton();

        contestPage.assertAllWarnings();
    }

}
