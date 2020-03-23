package org.nkorobov;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QuantopianLoginPageTests extends BaseTests {

    @Category(TestCategories.LoginTests.class)
    @Test
    public void loginAttemptEmptyFieldsTest() {
        driver.get(QuantopianLoginPage.getLoginPageUrl());

        QuantopianLoginPage loginPage = new QuantopianLoginPage(driver);
        loginPage.signIn();

        Assert.assertTrue(loginPage.emailWarningIsActive());
        Assert.assertTrue(loginPage.passwordWarningIsActive());
    }

    @Category(TestCategories.LoginTests.class)
    @Test
    public void loginAttemptIncorrectEmailTest() {
        driver.get(QuantopianLoginPage.getLoginPageUrl());

        QuantopianLoginPage loginPage = new QuantopianLoginPage(driver);
        loginPage.enterEmail("Sometimes stonks go down, but then they go up");
        loginPage.enterPassword("");

        Assert.assertTrue(loginPage.emailWarningIsActive());
    }

    @Category(TestCategories.LoginTests.class)
    @Test
    public void loginAttemptFailed() {
        driver.get(QuantopianLoginPage.getLoginPageUrl());

        QuantopianLoginPage loginPage = new QuantopianLoginPage(driver);
        loginPage.enterEmail("$SPY_100P_4/17@gmail.com");
        loginPage.enterPassword("BearsBearsBears");
        loginPage.signIn();

        Assert.assertTrue(loginPage.failedLoginMessageIsActive());
    }

}
