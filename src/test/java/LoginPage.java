import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    public final static String URL_PAGE = "http://86.57.161.116:10008";

    private WebDriver driver;
    private WebDriverWait driverWait;

    private WebElement usernameField;
    private WebElement passwordField;


    private WebElement submitButton;
    private WebElement clearAllButton;


    private WebElement rememberCheckbox;

    @BeforeTest
    public void initialize() throws InterruptedException {
        //System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverWait = new WebDriverWait(driver, 1);
        driver.navigate().to(URL_PAGE + "#/login");
    }

    @Test
    public void loginPositive() {
        usernameField = driver.findElement(By.id("logininput"));
        usernameField.sendKeys("petia");
        //new Actions(driver).sendKeys(Keys.TAB).perform();
        passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.sendKeys("empl");
        submitButton = driver.findElement(By.id("buttonLogin"));
        submitButton.click();
        driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));
        Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE + "#/vacation");
        //output of cookies
        /*Set<Cookie> set = driver.manage().getCookies();
        for (Cookie cookie : set){
            System.out.println(cookie.getName());
        }*/
        driver.manage().deleteAllCookies();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithInvalidPassword() {
        usernameField = driver.findElement(By.id("logininput"));
        usernameField.sendKeys("petia");
        new Actions(driver).sendKeys(Keys.TAB).perform();
        passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.sendKeys("11sfdfhgj");
        submitButton = driver.findElement(By.id("buttonLogin"));
        submitButton.click();
        driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithInvalidUserName() {
        usernameField = driver.findElement(By.id("logininput"));
        usernameField.sendKeys("petia1");
        new Actions(driver).sendKeys(Keys.TAB).perform();
        passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.sendKeys("empl");
        submitButton = driver.findElement(By.id("buttonLogin"));
        submitButton.click();
        driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithInvalidUsernameAndPassword() {
        usernameField = driver.findElement(By.id("logininput"));
        usernameField.sendKeys("petia123");
        new Actions(driver).sendKeys(Keys.TAB).perform();
        passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.sendKeys("empl123");
        submitButton = driver.findElement(By.id("buttonLogin"));
        submitButton.click();
        driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithoutUsername() {
        passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.sendKeys("empl");
        submitButton = driver.findElement(By.id("buttonLogin"));
        submitButton.click();
        driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));

    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithoutPassword() {
        usernameField = driver.findElement(By.id("logininput"));
        usernameField.sendKeys("petia");
        submitButton = driver.findElement(By.id("buttonLogin"));
        submitButton.click();
        driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));

    }

    @Test(expectedExceptions = TimeoutException.class)
    public void loginWithEmptyFields() {
        submitButton = driver.findElement(By.id("buttonLogin"));
        submitButton.click();
        driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));
    }

    @Test
    public void clearAll() {
        usernameField = driver.findElement(By.id("logininput"));
        usernameField.sendKeys("petia");
        new Actions(driver).sendKeys(Keys.TAB).perform();
        passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.sendKeys("empl");
        clearAllButton = driver.findElement(By.id("buttonReset"));
        clearAllButton.click();
        Assert.assertEquals(usernameField.getText(), "");
        Assert.assertEquals(passwordField.getText(), "");
    }

    public void loginWithRememberMeButton() {
        usernameField = driver.findElement(By.id("logininput"));
        usernameField.sendKeys("petia");

        passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.sendKeys("empl");

        rememberCheckbox = driver.findElement(By.id("checkboxesRemember-0"));
        rememberCheckbox.click();

        submitButton = driver.findElement(By.id("buttonLogin"));
        submitButton.click();
        driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));
        driver.close();
        driver.navigate().to(URL_PAGE);
        Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE + "#/vacation");

    }

    public void loginWithoutRememberMeButton() {
        usernameField = driver.findElement(By.id("logininput"));
        usernameField.sendKeys("petia");

        passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.sendKeys("empl");

        submitButton = driver.findElement(By.id("buttonLogin"));
        submitButton.click();
        driverWait.until(ExpectedConditions.urlToBe(URL_PAGE + "#/vacation"));
        driver.close();
        driver.navigate().to(URL_PAGE);
        Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE + "#/login");
    }

    @AfterTest
    public void exit() {
        driver.navigate().to(URL_PAGE + "#/login");
        driver.quit();
    }


}
