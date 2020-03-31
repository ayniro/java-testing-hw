package org.nkorobov.pages;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.nkorobov.cucumberTests.CucumberHooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuantopianLoginPage extends BasePage {

    {
        pageTitle = "Quantopian Login";
        pageUrl = "https://www.quantopian.com/signin";
    }

    @FindBy(xpath = "//input[@id='user_email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='user_password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@id='login-button']")
    private WebElement signInButton;

    private By emailWarningLocator = By.xpath("//label[@id='user_email-error']");
    private By passwordWarningLocator = By.xpath("//label[@id='user_password-error']");
    private By failedLoginMessageLocator = By.xpath("//div[@class='message']");

    public QuantopianLoginPage() {
        this(CucumberHooks.getDriver(), false);
        // Probably it is not the best idea to mix cucumber-specific calls with junit-only oriented page object
        // But it kinda works, so yeah
    }

    public QuantopianLoginPage(WebDriver driver, boolean openPage) {
        super(driver, 2);
        if (openPage) {
            openPage();
        }

        if (!pageTitle.equals(driver.getTitle()) || !pageUrl.equals(driver.getCurrentUrl())) {
            throw new IllegalStateException("Wrong page");
        }

        PageFactory.initElements(driver, this);
    }

    @When("^I enter email \"(.*)\"$")
    public QuantopianLoginPage enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @When("^I enter password \"(.*)\"$")
    public QuantopianLoginPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @When("^I press SignIn$")
    public void signIn() {
        signInButton.click();
    }

    @Then("^Login Email warning is active$")
    public void assertEmailWarning() {
        Assert.assertTrue(emailWarningIsActive());
    }

    @Then("^Login Password warning is active$")
    public void assertPasswordWarning() {
        Assert.assertTrue(passwordWarningIsActive());
    }

    @Then("^Failed Login message is active$")
    public void assertFailedLoginMessage() {
        Assert.assertTrue(failedLoginMessageIsActive());
    }

    public boolean emailWarningIsActive() {
        return waitUntilVisibleOrTimedOut(emailWarningLocator);
    }

    public boolean passwordWarningIsActive() {
        return waitUntilVisibleOrTimedOut(passwordWarningLocator);
    }

    public boolean failedLoginMessageIsActive() {
        return waitUntilVisibleOrTimedOut(failedLoginMessageLocator);
    }
}
