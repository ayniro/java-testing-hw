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
import java.util.List;

public class ExpensesLayout {
    private final AndroidDriver<MobileElement> driver;

    private static final String activityName = ".activities.BudgetActivity";

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/add_new_expense")
    MobileElement addExpenseButton;

    public ExpensesLayout(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    public AddExpenseLayout pressAddExpense() {
        addExpenseButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_expense_title_edit")));

        return new AddExpenseLayout(driver);
    }

    public boolean containsExpense(String title) {
        List<MobileElement> elements = driver.findElementsById("platkovsky.alexey.epamtestapp:id/expense_title");
        for (MobileElement element : elements) {
            if (element.getText().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public static String getActivityName() {
        return activityName;
    }
}
