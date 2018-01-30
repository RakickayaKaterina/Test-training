package com.senla.testing.rakickaya.tests;

import com.senla.testing.rakickaya.data.UserCredentials;
import com.senla.testing.rakickaya.pages.LoginPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.senla.testing.rakickaya.pages.LoginPage.URL_PAGE;

public class LoginPageTest {
    public static final String EMPTY_STRING = "";
    private WebDriver driver;

    private LoginPage loginPage;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void findLogo() {
        Assert.assertTrue(loginPage.existLogo());
    }


    @Test
    public void loginPositive() {
        loginPage.inputUserCredentials(new UserCredentials("petia", "empl"))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithInvalidPassword() {
        loginPage.inputUserCredentials(new UserCredentials("petia", "randompass"))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithInvalidUserName() {
        loginPage.inputUserCredentials(new UserCredentials("invalidName", "empl"))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithInvalidUsernameAndPassword() {
        loginPage.inputUserCredentials(new UserCredentials("invalidName", "invalidPass"))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithoutUsername() {
        loginPage.inputUserCredentials(new UserCredentials(EMPTY_STRING, "empl"))
                .setSubmitButton();

    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithoutPassword() {
        loginPage.inputUserCredentials(new UserCredentials("petia", EMPTY_STRING))
                .setSubmitButton();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithEmptyFields() {
        loginPage.inputUserCredentials(new UserCredentials(EMPTY_STRING, EMPTY_STRING))
                .setSubmitButton();
    }

    @Test
    public void clearAll() {
        boolean isEmptyField = loginPage.inputUserCredentials(new UserCredentials("invalidName", "invalidPass"))
                .setClearButton().isFieldsEmpty();
        Assert.assertTrue(isEmptyField);
    }

    @Test
    public void loginWithRememberMeButton() {
        loginPage.inputUserCredentials(new UserCredentials("petia", "empl"))
                .setRememberMeCheckBox().setSubmitButton();
        driver.get(URL_PAGE);
        Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE + "#/vacation");

    }

    @Test
    public void loginWithoutRememberMeButton() {
        loginPage.inputUserCredentials(new UserCredentials("petia", "empl"))
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
