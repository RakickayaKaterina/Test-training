package com.senla.testing.rakickaya.pages;

import com.senla.testing.rakickaya.data.UserCredentials;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends Page{

    public final static String URL_PAGE = "http://86.57.161.116:10008/";
    public static final String LOGININPUT = "logininput";
    public static final String PASSWORDINPUT = "passwordinput";
    public static final String BUTTON_LOGIN = "buttonLogin";
    public static final String BUTTON_RESET = "buttonReset";
    public static final String CHECKBOXES_REMEMBER = "checkboxesRemember-0";
    public static final String LOGO_PATH = "//*[@id=\"main\"]/section/div/div/table/tbody/tr[1]/td/img";

    private WebDriver driver;
    private WebDriverWait driverWait;

    private WebElement usernameField;
    private WebElement passwordField;
    private WebElement submitButton;
    private WebElement clearAllButton;
    private WebElement rememberCheckbox;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(URL_PAGE +"#/login");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        findAllElements();
        driverWait = new WebDriverWait(driver,5);

    }

    private void findAllElements(){
        usernameField = driver.findElement(By.id(LOGININPUT));
        passwordField = driver.findElement(By.id(PASSWORDINPUT));
        submitButton = driver.findElement(By.id(BUTTON_LOGIN));
        clearAllButton = driver.findElement(By.id(BUTTON_RESET));
        rememberCheckbox = driver.findElement(By.id(CHECKBOXES_REMEMBER));


    }
    public LoginPage inputUserCredentials(UserCredentials user){
        usernameField.sendKeys(user.getUserName());
        passwordField.sendKeys(user.getPassword());
        return this;
    }
    public LoginPage setRememberMeCheckBox(){
        rememberCheckbox.click();
        return  this;
    }
    public VacationPage setSubmitButton(){
            submitButton.click();
            driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));
            return new VacationPage(driver);

    }
    public LoginPage setClearButton(){
        clearAllButton.click();
        return  this;

    }
    public boolean existLogo(){
      try {
          //
          WebElement logo = driver.findElement(By.xpath(LOGO_PATH));
          return  true;
      }catch (NoSuchElementException e){
          return  false;
      }
    }

    public void toBackState(){
        driver.get(URL_PAGE);
        findAllElements();
    }
    public boolean isFieldsEmpty(){
        return usernameField.getText().isEmpty() && passwordField.getText().isEmpty();
    }

}
