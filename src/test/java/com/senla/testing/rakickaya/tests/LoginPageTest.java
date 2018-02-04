package com.senla.testing.rakickaya.tests;

import com.senla.testing.rakickaya.data.UserCredentials;
import com.senla.testing.rakickaya.pages.LoginPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.senla.testing.rakickaya.pages.LoginPage.URL_PAGE;

public class LoginPageTest {
    private static final String EMPTY_STRING = "";
    private static final String VALID_USER_NAME = "petia";
    private static final String VALID_PASSWORD = "empl";
    private static final String INVALID_PASSWORD = "randompass";
    private static final String INVALID_NAME = "invalidName";
    private WebDriver driver;

    private LoginPage loginPage;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = PageFactory.initElements(driver,LoginPage.class);
    }


    @Test
    public void findLogo() {
        Assert.assertTrue(loginPage.existLogo());
    }


    @Test
    public void loginPositive() {
        loginPage.inputUserCredentials(new UserCredentials(VALID_USER_NAME, VALID_PASSWORD))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithInvalidPassword() {
        loginPage.inputUserCredentials(new UserCredentials(VALID_USER_NAME, INVALID_PASSWORD))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithInvalidUserName() {
        loginPage.inputUserCredentials(new UserCredentials(INVALID_NAME, VALID_PASSWORD))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithInvalidUsernameAndPassword() {
        loginPage.inputUserCredentials(new UserCredentials(INVALID_NAME, INVALID_PASSWORD))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithoutUsername() {
        loginPage.inputUserCredentials(new UserCredentials(EMPTY_STRING, VALID_PASSWORD))
                .setSubmitButton();

    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithoutPassword() {
        loginPage.inputUserCredentials(new UserCredentials(VALID_USER_NAME, EMPTY_STRING))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithEmptyFields() {
        loginPage.inputUserCredentials(new UserCredentials(EMPTY_STRING, EMPTY_STRING))
                .setSubmitButton();
    }

    @Test
    public void clearAll() {
        boolean isEmptyField = loginPage.inputUserCredentials(new UserCredentials(INVALID_NAME, INVALID_PASSWORD))
                .setClearButton().isFieldsEmpty();
        Assert.assertTrue(isEmptyField);
    }

    @Test
    public void loginWithRememberMeButton() {
        loginPage.inputUserCredentials(new UserCredentials(VALID_USER_NAME, VALID_PASSWORD))
                .setRememberMeCheckBox().setSubmitButton();
        driver.get(URL_PAGE);
        Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE + "#/vacation");

    }

    @Test
    public void loginWithoutRememberMeButton() {
        loginPage.inputUserCredentials(new UserCredentials(VALID_USER_NAME, VALID_PASSWORD))
                .setSubmitButton();
        driver.get(URL_PAGE);
        Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE + "#/login");

    }


    @AfterMethod
    public void back() {
        loginPage.toBackState();
    }


    @AfterClass
    public void exit() {
        driver.quit();
    }


}
