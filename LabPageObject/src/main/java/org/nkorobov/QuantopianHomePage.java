package org.nkorobov;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuantopianHomePage extends BasePage {

    {
        pageTitle = "Quantopian: The Place For Learning Quant Finance";
        pageUrl = "https://www.quantopian.com/";
    }

    private static String fullPageUrl = "https://www.quantopian.com/home";

    @FindBy(xpath = "//a[contains(text(),'Log In')]")
    private WebElement loginButton;
    @FindBy(xpath = "//a[contains(text(),'Contest')]")
    private WebElement contestButton;

    public QuantopianHomePage(WebDriver driver) {
        this(driver, true);
    }

    public QuantopianHomePage(WebDriver driver, boolean openPage) {
        super(driver, 10);
        if (openPage) {
            openPage();
        }

        if (!pageTitle.equals(driver.getTitle()) ||
                !(pageUrl.equals(driver.getCurrentUrl()) || fullPageUrl.equals(driver.getCurrentUrl()))) {
            throw new IllegalStateException("Wrong page");
        }

        PageFactory.initElements(driver, this);
    }

    public QuantopianLoginPage transitionToLoginPage() {
        loginButton.click();
        waitForReadyStateComplete();
        return new QuantopianLoginPage(driver, false);
    }

    public QuantopianContestPage transitionToContestPage() {
        contestButton.click();
        waitForReadyStateComplete();
        return new QuantopianContestPage(driver, false);
    }

    public String getFullHomePageUrl() {
        return pageUrl + "home";
    }

}
