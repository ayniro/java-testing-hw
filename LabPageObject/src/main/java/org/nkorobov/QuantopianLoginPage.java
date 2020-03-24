package org.nkorobov;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public QuantopianLoginPage(WebDriver driver) {
        this(driver, true);
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

    public QuantopianLoginPage enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public QuantopianLoginPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public void signIn() {
        signInButton.click();
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

    private boolean waitUntilVisibleOrTimedOut(By locator) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
