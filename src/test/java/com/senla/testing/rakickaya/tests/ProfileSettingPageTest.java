package com.senla.testing.rakickaya.tests;

import com.senla.testing.rakickaya.data.UserCredentials;
import com.senla.testing.rakickaya.pages.LoginPage;
import com.senla.testing.rakickaya.pages.ProfileMainPage;
import com.senla.testing.rakickaya.pages.ProfileSettingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class ProfileSettingPageTest {
    public static final String CORRECT_EMAIL = "\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$\"";
    private WebDriver driver;
    private ProfileSettingPage profileSettingPage;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        profileSettingPage = new LoginPage(driver).inputUserCredentials(new UserCredentials("petia", "empl")).setSubmitButton().openProfile().followSettingLink();
    }

    @Test
    public void inputValidateEmail() {
       profileSettingPage.inputEmail("rakickayakate@mail.ru");
       String email = profileSettingPage.getEmail();
       boolean isCorrectEmail = email.matches(CORRECT_EMAIL);
       if(!isCorrectEmail){
           //TODO exist some alert message after input the field
           Assert.fail();
       }



      //  Assert.assertEquals(profileMainPage.getTextFrom("Контактная информация","Email"),"rakickayakate@mail.ru");
    }
    @Test
    public void inputEmailWithoutCommercialAt() {
        profileSettingPage.inputEmail("rakickayakatemail.ru");
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL);
        if(!isCorrectEmail){
            //TODO exist some alert message after input the field
        }

    }
    @Test
    public void inputEmailWithoutDomainPart() {
        profileSettingPage.inputEmail("bvkld@fdsfs");
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL);
        if (!isCorrectEmail) {
            //TODO exist some alert message after input the field
        }
    }
    @Test
    public void inputEmailWithoutName() {
        profileSettingPage.inputEmail("@fdsfs.ru");
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL);
        if (!isCorrectEmail) {
            //TODO exist some alert message after input the field
        }
    }

    @Test
    public void saveEmail(){
        ProfileMainPage profileMainPage = profileSettingPage.inputEmail("rakickaya@mail.ru").save();
        Assert.assertEquals(profileMainPage.getTextFrom("Контактная информация", "Email"),"rakickaya@mail.ru");

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
