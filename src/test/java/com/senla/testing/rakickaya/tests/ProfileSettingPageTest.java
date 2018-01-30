package com.senla.testing.rakickaya.tests;

import com.senla.testing.rakickaya.data.UserCredentials;
import com.senla.testing.rakickaya.pages.LoginPage;
import com.senla.testing.rakickaya.pages.ProfileSettingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProfileSettingPageTest {
    private WebDriver driver;
    private ProfileSettingPage profileSettingPage;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        profileSettingPage = new LoginPage(driver).inputUserCredentials(new UserCredentials("petia", "empl")).setSubmitButton().openProfile().followSettingLink();
    }

    @Test
    public void inputValidateName() {
       profileSettingPage.inputName("Kate").save();
        Assert.assertEquals(profileSettingPage.getTextFrom("Информация профиля","Имя"),"Kate");
    }
    @Test
    public void openMainProfilePage(){
        profileSettingPage.followSettingLink();
    }


    @AfterClass
    public void exit() {
        driver.quit();
    }


}
