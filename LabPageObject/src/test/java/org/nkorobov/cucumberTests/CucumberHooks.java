package org.nkorobov.cucumberTests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CucumberHooks {
    private static WebDriver driver = null;
    private static boolean wasSetUp = false;

    @Before
    public void setupDriver() {
        if (!wasSetUp) {
            WebDriverManager.chromedriver().setup();
            wasSetUp = true;
        }

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @After
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
