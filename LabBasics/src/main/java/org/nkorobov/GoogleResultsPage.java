package org.nkorobov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleResultsPage {

    private WebDriverWait webDriverWait;
    private static final String firstLinkXPath = "//div[@id='rso']//child::div[@class='g']//descendant::a[1]";

    GoogleResultsPage(WebDriver driver) {
        webDriverWait = new WebDriverWait(driver, 10);
    }

    public String getFirstLink() {
        WebElement firstLink = webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(firstLinkXPath)));
        return firstLink.getAttribute("href");
    }
}
