package org.nkorobov;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager {

    static AppiumDriverLocalService appiumDriverLocalService = null;

    public static AndroidDriver<MobileElement> driver;

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public AndroidDriver<MobileElement> getDriver() {
        if (driver == null) {
            startService();
            createDriver();
        }
        return driver;
    }

    protected void startService() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingAnyFreePort();
        builder.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"));
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        AppiumDriverLocalService appiumDriverLocalService = builder.build();
        AndroidDriverManager.appiumDriverLocalService = appiumDriverLocalService;
        appiumDriverLocalService.start();
    }

    protected void createDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();//.android();
        capabilities.setCapability("device", "android");
        capabilities.setCapability("deviceName", "My Phone");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "platkovsky.alexey.epamtestapp");
        capabilities.setCapability("appActivity", "platkovsky.alexey.epamtestapp.activities.LoginActivity");
        capabilities.setCapability("udid", "H9AXB772N643GXN");
        capabilities.setCapability("--local-timezone", "false");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability("appWaitDuration", 30000);
        capabilities.setCapability("disabledAndroidWatchers", "true");
        driver = new AndroidDriver<>(appiumDriverLocalService.getUrl(), capabilities);
        //launchDriverWithStartedAppium(capabilities);
    }

    private void launchDriverWithStartedAppium(Capabilities capabilities) {
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
