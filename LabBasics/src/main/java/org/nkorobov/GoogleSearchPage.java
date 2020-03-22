package org.nkorobov;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;

    @FindBy(xpath = "//input[@name='btnK']")
    private WebElement searchButton;

    public GoogleSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void inputSearchString(String text) {
        searchInput.sendKeys(text);
    }

    public void pressSearchButton() {
        searchButton.submit();
    }

    public static String getUrl() {
        return "https://www.google.com";
    }

}
