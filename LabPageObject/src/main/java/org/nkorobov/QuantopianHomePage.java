package org.nkorobov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuantopianHomePage {

    private static final String homePageUrl = "https://www.quantopian.com/";
    private static final String homePageTitle = "Quantopian: The Place For Learning Quant Finance";

    private static final String homePageVideoXPath = "//div[@class='video-wrapper']//video";

    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//a[contains(text(),'Log In')]")
    private WebElement loginButton;
    @FindBy(xpath = "//a[contains(text(),'Contest')]")
    private WebElement contestButton;

    public QuantopianHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 10);
    }

    public void transitionToLoginPage() {
        loginButton.click();
        waitForPageChange();
    }

    public void transitionToContestPage() {
        contestButton.click();
        waitForPageChange();
    }

    public static String getHomePageUrl() {
        return homePageUrl;
    }

    public static String getFullHomePageUrl() {
        return homePageUrl + "home";
    }

    public static String getHomePageTitle() {
        return homePageTitle;
    }

    private void waitForPageChange() {
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(homePageVideoXPath)));
    }
}
