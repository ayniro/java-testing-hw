package org.nkorobov;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuantopianContestPage {

    private static final String contestPageUrl = "https://www.quantopian.com/contest";
    private static final String contestPageTitle = "Quantopian Contest: A Quant Finance Competition";

    private static final String submissionDivXPath = "//div[@class='hero-wrapper']//div[@class='contest-submission-modal']";

    private static final String modalBodyXPath = "//div[@id='join-modal']//div[@class='modal-body']";
    private static final String termsOfUseCheckboxXPath = ".//input[@id='eula-checkbox']";
    private static final String joinButtonXPath = ".//button[@id='sign-up-button']";
    private static final String firstNameWarningXPath = ".//label[@id='user_firstname-error']";
    private static final String lastNameWarningXPath = ".//label[@id='user_lastname-error']";
    private static final String emailWarningXPath = ".//label[@id='user_email-error']";
    private static final String passwordWarningXPath = ".//label[@id='user_password-error']";

    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//a[@class='navbar-brand']")
    private WebElement homeLink;
    @FindBy(xpath = "//div[@class='hero-wrapper']//div[@class='contest-submission-modal']//child::button")
    private WebElement submitButton;

    private WebElement modalSubmitWindow;

    QuantopianContestPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 100);
    }

    public void transitionToHomePage() {
        homeLink.click();
        waitForPageChange();
    }

    public void pressSubmitEntry() {
        submitButton.click();
    }

    public void fillCheckboxAndJoin() {
        waitForModalWindow();
        checkTermsOfUse();
        pressJoinButton();
    }

    public boolean allWarningsAreActive() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstNameWarningXPath)));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lastNameWarningXPath)));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailWarningXPath)));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordWarningXPath)));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public static String getContestPageUrl() {
        return contestPageUrl;
    }

    public static String getContestPageTitle() {
        return contestPageTitle;
    }

    private void waitForPageChange() {
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(submissionDivXPath)));
    }

    private void waitForModalWindow() {
        modalSubmitWindow = webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(modalBodyXPath)));
    }

    private void checkTermsOfUse() {
        WebElement checkBox = modalSubmitWindow.findElement(By.xpath(termsOfUseCheckboxXPath));
        webDriverWait.until(ExpectedConditions.elementSelectionStateToBe(checkBox, false));
        checkBox.sendKeys(Keys.SPACE); // Animated form made me suffer
    }

    private void pressJoinButton() {
        WebElement joinButton = modalSubmitWindow.findElement(By.xpath(joinButtonXPath));
        joinButton.sendKeys(Keys.ENTER); // Animated form made me suffer
    }
}
