package org.nkorobov.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    protected WebDriver driver;

    @BeforeClass
    public static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public final void setupDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public final void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
