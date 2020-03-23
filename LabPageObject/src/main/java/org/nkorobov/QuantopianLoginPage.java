package org.nkorobov;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuantopianLoginPage {

    private static final String loginPageUrl = "https://www.quantopian.com/signin";
    private static final String loginPageTitle = "Quantopian Login";

    private static final String emailWarningXPath = "//label[@id='user_email-error']";
    private static final String passwordWarningXPath = "//label[@id='user_password-error']";
    private static final String failedLoginMessageXPath = "//div[@class='message']";

    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//input[@id='user_email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='user_password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@id='login-button']")
    private WebElement signInButton;

    public QuantopianLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 2);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void signIn() {
        signInButton.click();
    }

    public boolean emailWarningIsActive() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailWarningXPath)));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean passwordWarningIsActive() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordWarningXPath)));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean failedLoginMessageIsActive() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(failedLoginMessageXPath)));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public static String getLoginPageUrl() {
        return loginPageUrl;
    }

    public static String getLoginPageTitle() {
        return loginPageTitle;
    }
}
