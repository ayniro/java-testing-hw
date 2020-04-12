package org.nkorobov;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;

import java.util.concurrent.TimeUnit;

public class AppTests {
    static AndroidDriver<MobileElement> driver;
    static AndroidDriverManager driverManager;
    private LoginLayout loginLayout;

    private static final String userName = "Kappa";
    private static final String password = "kappakappahey";
    private static final String email = "kappa@kappa.com";

    @BeforeClass
    public static void prepareTest() {
        driverManager = new AndroidDriverManager();
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void teardownDriver() {
        driverManager.quitDriver();
    }

    @Before
    public void initPageObject() {
        driver.launchApp();
        loginLayout = new LoginLayout(driver);
    }

    @After
    public void closeApp() {
        driver.closeApp();
    }

    @Test
    public void loginTest() {
        registerNewUser();
        loginLayout.enterEmailAndPassword(email, password);
        Assert.assertEquals(ExpensesLayout.getActivityName(), driver.currentActivity());
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
