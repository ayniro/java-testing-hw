package org.nkorobov;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QuantopianHomePageTests extends BaseTests {

    @Category(TestCategories.TransitionTests.class)
    @Test
    public void transitionFromHomeToLoginTest() {
        driver.get(QuantopianHomePage.getHomePageUrl());

        QuantopianHomePage homePage = new QuantopianHomePage(driver);
        homePage.transitionToLoginPage();

        Assert.assertEquals(QuantopianLoginPage.getLoginPageTitle(), driver.getTitle());
        Assert.assertEquals(QuantopianLoginPage.getLoginPageUrl(), driver.getCurrentUrl());
    }

    @Category(TestCategories.TransitionTests.class)
    @Test
    public void transitionFromHomeToContestTest() {
        driver.get(QuantopianHomePage.getHomePageUrl());

        QuantopianHomePage homePage = new QuantopianHomePage(driver);
        homePage.transitionToContestPage();

        Assert.assertEquals(QuantopianContestPage.getContestPageTitle(), driver.getTitle());
        Assert.assertEquals(QuantopianContestPage.getContestPageUrl(), driver.getCurrentUrl());
    }

}
