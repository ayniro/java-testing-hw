package org.nkorobov;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QuantopianHomePageTests extends BaseTests {

    @Category(TestCategories.TransitionTests.class)
    @Test
    public void transitionFromHomeToLoginTest() {
        QuantopianHomePage homePage = new QuantopianHomePage(driver);
        QuantopianLoginPage loginPage = homePage.transitionToLoginPage();

        Assert.assertEquals(loginPage.getPageTitle(), driver.getTitle());
        Assert.assertEquals(loginPage.getPageUrl(), driver.getCurrentUrl());
    }

    @Category(TestCategories.TransitionTests.class)
    @Test
    public void transitionFromHomeToContestTest() {
        QuantopianHomePage homePage = new QuantopianHomePage(driver);
        QuantopianContestPage contestPage = homePage.transitionToContestPage();

        Assert.assertEquals(contestPage.getPageTitle(), driver.getTitle());
        Assert.assertEquals(contestPage.getPageUrl(), driver.getCurrentUrl());
    }

}
