package org.nkorobov;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected String pageTitle = "BasePage Title";
    protected String pageUrl = "BasePage URL";

    protected final WebDriver driver;
    protected final WebDriverWait webDriverWait;

    BasePage(WebDriver driver, long timeOutInSeconds) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, timeOutInSeconds);
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    protected void waitForReadyStateComplete() {
        webDriverWait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript(
                "return document.readyState").equals("complete"));
    }

    protected void openPage() {
        driver.get(pageUrl);
    }
}
