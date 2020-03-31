package org.nkorobov.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nkorobov.pages.QuantopianLoginPage;

public class QuantopianLoginPageTests extends BaseTests {

    @Category(TestCategories.LoginTests.class)
    @Test
    public void loginAttemptEmptyFieldsTest() {
        QuantopianLoginPage loginPage = new QuantopianLoginPage(driver, true);
        loginPage.signIn();

        Assert.assertTrue(loginPage.emailWarningIsActive());
        Assert.assertTrue(loginPage.passwordWarningIsActive());
    }

    @Category(TestCategories.LoginTests.class)
    @Test
    public void loginAttemptIncorrectEmailTest() {
        QuantopianLoginPage loginPage = new QuantopianLoginPage(driver, true);
        loginPage.enterEmail("Sometimes stonks go down, but then they go up").enterPassword("");

        Assert.assertTrue(loginPage.emailWarningIsActive());
    }

    @Category(TestCategories.LoginTests.class)
    @Test
    public void loginAttemptFailedTest() {
        QuantopianLoginPage loginPage = new QuantopianLoginPage(driver, true);
        loginPage.enterEmail("$SPY_100P_4/17@gmail.com").enterPassword("BearsBearsBears").signIn();

        Assert.assertTrue(loginPage.failedLoginMessageIsActive());
    }

}
