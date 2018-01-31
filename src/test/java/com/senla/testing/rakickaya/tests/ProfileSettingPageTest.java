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


public class ProfileSettingPageTest {
    public static final String CORRECT_EMAIL_FORMAT = "\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$\"";
    public static final String CORRECT_DATE_FORMAT = "((19|20)\\d\\d)\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])";
    private WebDriver driver;
    private ProfileSettingPage profileSettingPage;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        profileSettingPage = new LoginPage(driver).inputUserCredentials(new UserCredentials("petia", "empl")).setSubmitButton().openProfile().followSettingLink();
    }

    @Test
    public void inputValidEmail() {
        profileSettingPage.inputEmail("rakickayakate@mail.ru");
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL_FORMAT);
        if (!isCorrectEmail) {
            //TODO exist some alert message after input the field
            Assert.fail();
        }
        //  Assert.assertEquals(profileMainPage.getTextFrom("Контактная информация","Email"),"rakickayakate@mail.ru");
    }

    @Test
    public void inputEmailWithoutCommercialAt() {
        profileSettingPage.inputEmail("rakickayakatemail.ru");
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL_FORMAT);
        if (!isCorrectEmail) {
            //TODO exist some alert message after input the field
        }

    }

    @Test
    public void inputEmailWithoutDomainPart() {
        profileSettingPage.inputEmail("bvkld@fdsfs");
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL_FORMAT);
        if (!isCorrectEmail) {
            //TODO exist some alert message after input the field
        }
    }

    @Test
    public void inputEmailWithoutName() {
        profileSettingPage.inputEmail("@fdsfs.ru");
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL_FORMAT);
        if (!isCorrectEmail) {
            //TODO exist some alert message after input the field
        }
    }

    @Test
    public void saveEmail() {
        ProfileMainPage profileMainPage = profileSettingPage.inputEmail("rakickaya@mail.ru").save();
        Assert.assertEquals(profileMainPage.getTextFrom("Контактная информация", "Email"), "rakickaya@mail.ru");

    }

    @Test
    public void inputValidDate() {
        profileSettingPage.inputBirthDate("1997-05-10");
        String date = profileSettingPage.getDate();
        boolean isCorrectDate = date.matches(CORRECT_DATE_FORMAT);
        if (!isCorrectDate) {
            //TODO exist some alert message after input the field
            Assert.fail();
        }
    }

    @Test
    public void inputInvalidDate() {
        profileSettingPage.inputBirthDate("Cat");
        String date = profileSettingPage.getDate();
        boolean isCorrectDate = date.matches(CORRECT_DATE_FORMAT);
        if (!isCorrectDate) {
            //TODO exist some alert message after input the field
        }
    }

    @Test
    public void savePhoneNumber(){
        ProfileMainPage profileMainPage = profileSettingPage.inputBirthDate("8-029-888-24-16").save();
        Assert.assertEquals(profileMainPage.getTextFrom("Контактная информация", "Телефон"), "8-029-888-24-16");
    }
    @Test
    public void saveSkype(){
        ProfileMainPage profileMainPage = profileSettingPage.inputBirthDate("Kate Rakickaya").save();
        Assert.assertEquals(profileMainPage.getTextFrom("Контактная информация", "Skype"), "Kate Rakickaya");
    }


    @Test
    public void openMainProfilePage() {
        profileSettingPage.followSettingLink();
    }


    @AfterClass
    public void exit() {
        driver.quit();
    }


}
