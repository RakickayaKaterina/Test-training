package com.senla.testing.rakickaya.pages;

import com.senla.testing.rakickaya.data.UserCredentials;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends Page {

    public static final Logger log = Logger.getLogger(LoginPage.class);

    public final static String URL_PAGE = "http://86.57.161.116:10008/";

    private static final String LOGININPUT = "logininput";
    private static final String PASSWORDINPUT = "passwordinput";
    private static final String BUTTON_LOGIN = "buttonLogin";
    private static final String BUTTON_RESET = "buttonReset";
    private static final String CHECKBOXES_REMEMBER = "checkboxesRemember-0";
    private static final String LOGO_PATH = "//*[@id=\"main\"]/section/div/div/table/tbody/tr[1]/td/img";

    private WebDriver driver;
    private WebDriverWait driverWait;

    @FindBy(id = LOGININPUT)
    private WebElement usernameField;
    @FindBy(id = PASSWORDINPUT)
    private WebElement passwordField;
    @FindBy(id = BUTTON_LOGIN)
    private WebElement submitButton;
    @FindBy(id = BUTTON_RESET)
    private WebElement clearAllButton;
    @FindBy(id = CHECKBOXES_REMEMBER)
    private WebElement rememberCheckbox;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(URL_PAGE + "#/login");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driverWait = new WebDriverWait(driver, 5);

    }

    public LoginPage inputUserCredentials(UserCredentials user) {
        usernameField.sendKeys(user.getUserName());
        passwordField.sendKeys(user.getPassword());
        return this;
    }

    public LoginPage setRememberMeCheckBox() {
        rememberCheckbox.click();
        return this;
    }

    public VacationPage setSubmitButton() {
            submitButton.click();
            driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));
        return new VacationPage(driver);

    }

    public LoginPage setClearButton() {
        clearAllButton.click();
        return this;

    }

    public boolean existLogo() {
        try {
            WebElement logo = driver.findElement(By.xpath(LOGO_PATH));
            return true;
        } catch (NoSuchElementException e) {
            log.error("Logo isn't exist on the login page");
            return false;
        }
    }

    public void toBackState() {
        driver.get(URL_PAGE);
    }

    public boolean isFieldsEmpty() {
        return usernameField.getText().isEmpty() && passwordField.getText().isEmpty();
    }

}
