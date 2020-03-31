package org.nkorobov.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public QuantopianHomePage pressHomeButton() {
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

    public void pressJoinButton() {
        WebElement joinButton = modalSubmitWindow.findElement(joinButtonLocator);
        joinButton.click();
    }

    public void assertAllWarnings() {
        Assert.assertTrue(allWarningsAreActive());
    }

    public boolean allWarningsAreActive() {
        return  waitUntilVisibleOrTimedOut(firstNameWarningLocator) &&
                waitUntilVisibleOrTimedOut(lastNameWarningLocator) &&
                waitUntilVisibleOrTimedOut(emailWarningLocator) &&
                waitUntilVisibleOrTimedOut(passwordWarningLocator);
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
