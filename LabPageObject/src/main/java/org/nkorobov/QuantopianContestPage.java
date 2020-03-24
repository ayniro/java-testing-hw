package org.nkorobov;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuantopianContestPage extends BasePage {

    {
        pageTitle = "Quantopian Contest: A Quant Finance Competition";
        pageUrl = "https://www.quantopian.com/contest";
    }

    @FindBy(xpath = "//a[@class='navbar-brand']")
    private WebElement homeLink;
    @FindBy(xpath = "//div[@class='hero-wrapper']//div[@class='contest-submission-modal']//child::button")
    private WebElement submitButton;

    private By modalBodyLocator = By.xpath("//div[@id='join-modal']//div[@class='modal-body']");
    private By termsOfUseCheckboxLocator = By.xpath(".//input[@id='eula-checkbox']");
    private By joinButtonLocator = By.xpath(".//button[@id='sign-up-button']");
    private By firstNameWarningLocator = By.xpath(".//label[@id='user_firstname-error']");
    private By lastNameWarningLocator = By.xpath(".//label[@id='user_lastname-error']");
    private By emailWarningLocator = By.xpath(".//label[@id='user_email-error']");
    private By passwordWarningLocator = By.xpath(".//label[@id='user_password-error']");
    private By opacityCheckModalLocator = By.xpath("//div[@id='join-modal']");

    private WebElement modalSubmitWindow;

    public QuantopianContestPage(WebDriver driver) {
        this(driver, true);
    }

    public QuantopianContestPage(WebDriver driver, boolean openPage) {
        super(driver, 10);
        if (openPage) {
            openPage();
        }

        if (!pageTitle.equals(driver.getTitle()) || !pageUrl.equals(driver.getCurrentUrl())) {
            throw new IllegalStateException("Wrong page");
        }

        PageFactory.initElements(driver, this);
    }

    public QuantopianHomePage transitionToHomePage() {
        homeLink.click();
        waitForReadyStateComplete();
        return new QuantopianHomePage(driver, false);
    }

    public QuantopianContestPage pressSubmitEntry() {
        submitButton.click();
        waitForModalWindow();
        return this;
    }

    public QuantopianContestPage checkTermsOfUseCheckbox() {
        WebElement checkBox = modalSubmitWindow.findElement(termsOfUseCheckboxLocator);
        checkBox.click();
        return this;
    }

    public QuantopianContestPage pressJoinButton() {
        WebElement joinButton = modalSubmitWindow.findElement(joinButtonLocator);
        joinButton.click();
        return this;
    }

    public boolean allWarningsAreActive() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstNameWarningLocator));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(lastNameWarningLocator));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(emailWarningLocator));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(passwordWarningLocator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    private void waitForModalWindow() {
        modalSubmitWindow = webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(modalBodyLocator));
        webDriverWait.until(
                (ExpectedCondition<Boolean>) webDriver ->
                        driver.findElement(opacityCheckModalLocator).getCssValue("opacity").equals("1")
        );
    }

}
