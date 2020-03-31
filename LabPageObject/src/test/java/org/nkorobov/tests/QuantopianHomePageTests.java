package org.nkorobov.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nkorobov.pages.QuantopianContestPage;
import org.nkorobov.pages.QuantopianHomePage;
import org.nkorobov.pages.QuantopianLoginPage;

public class QuantopianHomePageTests extends BaseTests {

    @Category(TestCategories.TransitionTests.class)
    @Test
    public void transitionFromHomeToLoginTest() {
        QuantopianHomePage homePage = new QuantopianHomePage(driver, true);
        QuantopianLoginPage loginPage = homePage.transitionToLoginPage();

        Assert.assertEquals(loginPage.getPageTitle(), driver.getTitle());
        Assert.assertEquals(loginPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Category(TestCategories.TransitionTests.class)
    @Test
    public void transitionFromHomeToContestTest() {
        QuantopianHomePage homePage = new QuantopianHomePage(driver, true);
        QuantopianContestPage contestPage = homePage.transitionToContestPage();

        Assert.assertEquals(contestPage.getPageTitle(), driver.getTitle());
        Assert.assertEquals(contestPage.getPageUrl(), driver.getCurrentUrl());
    }

}
