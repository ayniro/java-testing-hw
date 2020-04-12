package org.nkorobov;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;

import java.util.concurrent.TimeUnit;

public class AppTests {
    static AndroidDriver<MobileElement> driver;
    private LoginLayout loginLayout;

    private static final String userName = "Kappa";
    private static final String password = "kappakappahey";
    private static final String email = "kappa@kappa.com";

    @BeforeClass
    public static void prepareTest() {
        AndroidDriverManager driverManager = new AndroidDriverManager();
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Before
    public void initPageObject() {
        AndroidDriverManager driverManager = new AndroidDriverManager();
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        loginLayout = new LoginLayout(driver);
    }

    @After
    public void resetApp() {
        driver.resetApp();
    }

    @Test
    public void loginTest() {
        registerNewUser();
        loginLayout.enterEmailAndPassword(email, password);
    }

    @Test
    public void registerTest() {
        registerNewUser();
        Assert.assertEquals(LoginLayout.getActivityName(), driver.currentActivity());
    }

    @Test
    public void registerAndAddExpenseTest() {
        registerNewUser();
        ExpensesLayout expensesLayout = loginLayout.enterEmailAndPassword(email, password);
        Assert.assertEquals(ExpensesLayout.getActivityName(), driver.currentActivity());
        AddExpenseLayout addExpenseLayout = expensesLayout.pressAddExpense();
        Assert.assertEquals(AddExpenseLayout.getActivityName(), driver.currentActivity());
        expensesLayout = addExpenseLayout.addExpense("Kappa", "1000", "01/01/2020", "Kappa");

        Assert.assertTrue(expensesLayout.containsExpense("Kappa"));
    }

    private void registerNewUser() {
        RegisterLayout registerLayout = loginLayout.pressRegisterNewAccount();
        loginLayout = registerLayout.fillFields(email, userName, password);
    }
}
