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

public class AddExpenseLayout {
    private final AndroidDriver<MobileElement> driver;

    private static final String activityName = ".activities.NewExpenseActivity";

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/new_expense_title_edit")
    MobileElement expenseTitleEditText;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/new_expense_sum_edit")
    MobileElement expenseSumEditText;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/new_expense_date_edit")
    MobileElement expenseDateEditText;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/new_expense_category_picker")
    MobileElement expenseCategoryEditText;
    @FindBy(id = "platkovsky.alexey.epamtestapp:id/save_new_expense")
    MobileElement saveExpenseButton;

    public AddExpenseLayout(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    public ExpensesLayout addExpense(String title, String sum, String date, String category) {
        expenseTitleEditText.sendKeys(title);
        expenseSumEditText.sendKeys(sum);
        expenseDateEditText.sendKeys(date);
        expenseCategoryEditText.sendKeys(category);
        driver.hideKeyboard();
        saveExpenseButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_new_expense")));

        return new ExpensesLayout(driver);
    }

    public static String getActivityName() {
        return activityName;
    }
}
