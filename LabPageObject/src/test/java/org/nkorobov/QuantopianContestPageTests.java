package org.nkorobov;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QuantopianContestPageTests extends BaseTests {

    @Category(TestCategories.TransitionTests.class)
    @Test
    public void transitionFromContestToHome() {
        driver.get(QuantopianContestPage.getContestPageUrl());

        QuantopianContestPage contestPage = new QuantopianContestPage(driver);
        contestPage.transitionToHomePage();

        Assert.assertEquals(QuantopianHomePage.getHomePageTitle(), driver.getTitle());
        Assert.assertEquals(QuantopianHomePage.getFullHomePageUrl(), driver.getCurrentUrl());
    }

    @Category(TestCategories.LoginTests.class)
    @Test
    public void submitEntryEmptyFieldsTest() {
        driver.get(QuantopianContestPage.getContestPageUrl());

        QuantopianContestPage contestPage = new QuantopianContestPage(driver);
        contestPage.pressSubmitEntry();
        contestPage.fillCheckboxAndJoin();

        Assert.assertTrue(contestPage.allWarningsAreActive());
    }

}
