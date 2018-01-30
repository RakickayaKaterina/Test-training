package com.senla.testing.rakickaya.tests;

import com.senla.testing.rakickaya.data.UserCredentials;
import com.senla.testing.rakickaya.pages.LoginPage;
import com.senla.testing.rakickaya.pages.ProfileMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class ProfileMainPageTest {
    private WebDriver driver;
    private ProfileMainPage profileMainPage;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        profileMainPage = new LoginPage(driver).inputUserCredentials(new UserCredentials("petia", "empl")).setSubmitButton().openProfile();
    }

    @Test
    public void followSettingPage() {
        profileMainPage.followSettingLink();
    }
    @Test
    public void openCalendarPopUp(){
        profileMainPage.setCalendarPopup();
    }

    @AfterMethod
    public void back(){
        profileMainPage.toBackState();
    }

    @AfterClass
    public void exit() {
        driver.quit();
    }


}
