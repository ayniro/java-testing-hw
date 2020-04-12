package org.nkorobov;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterLayout {
    private final AndroidDriver<MobileElement> driver;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    MobileElement emailEditText;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    MobileElement usernameEditText;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    MobileElement passwordEditText;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    MobileElement confirmPasswordEditText;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    MobileElement registerButton;

    public RegisterLayout(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    public LoginLayout fillFields(String email, String username, String password) {
        emailEditText.sendKeys(email);
        usernameEditText.sendKeys(username);
        passwordEditText.sendKeys(password);
        confirmPasswordEditText.sendKeys(password);
        driver.hideKeyboard();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_email")));

        return new LoginLayout(driver);
    }
}