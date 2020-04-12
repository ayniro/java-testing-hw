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

public class LoginLayout {

    private final AndroidDriver<MobileElement> driver;
    private static final String activityName = ".activities.LoginActivity";

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    MobileElement loginEmail;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    MobileElement loginPwd;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    MobileElement signInButton;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    MobileElement registerButton;

    public LoginLayout(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    public ExpensesLayout enterEmailAndPassword(String email, String password) {
        loginEmail.sendKeys(email);
        loginPwd.sendKeys(password);
        signInButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_new_expense")));

        return new ExpensesLayout(driver);
    }

    public RegisterLayout pressRegisterNewAccount() {
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("registration_email")));

        return new RegisterLayout(driver);
    }

    public static String getActivityName() {
        return activityName;
    }

}
