package org.nkorobov;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GooglingGoogleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupDriver() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void googleGoogleTest() {
        driver.get(GoogleSearchPage.getUrl());

        GoogleSearchPage searchPage = new GoogleSearchPage(driver);
        searchPage.inputSearchString("google");
        searchPage.pressSearchButton();

        GoogleResultsPage resultsPage = new GoogleResultsPage(driver);
        String firstFoundLink = resultsPage.getFirstLink();

        Assert.assertTrue(
                firstFoundLink.equals("https://www.google.ru/") ||
                firstFoundLink.equals("https://www.google.com/"));
    }
}
