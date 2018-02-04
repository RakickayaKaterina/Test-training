package com.senla.testing.rakickaya.testsDB;

import com.senla.testing.rakickaya.data.UserCredentials;
import com.senla.testing.rakickaya.db.dbentities.Employee;
import com.senla.testing.rakickaya.db.dbservices.DbService;
import com.senla.testing.rakickaya.pages.LoginPage;
import com.senla.testing.rakickaya.pages.ProfileMainPage;
import com.senla.testing.rakickaya.pages.ProfileSettingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ProfileSettingPageTest {
    private static final String CORRECT_EMAIL_FORMAT = "\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$\"";
    private static final String CORRECT_DATE_FORMAT = "((19|20)\\d\\d)\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])";
    private static final String VALID_EMAIL = "rakickayakate@mail.ru";
    private static final String EMAIL_WITHOUT_COMMERCIAL_AT = "rakickayakatemail.ru";
    private static final String EMAIL_WITHOUT_DOMAIN_PART = "bvkld@fdsfs";
    private static final String EMAIL_WITHOUT_NAME = "@fdsfs.ru";
    private static final String CONTACT_INFO = "Контактная информация";
    private static final String EMAIL = "Email";
    private static final String VALID_DATE = "1997-05-10";
    private static final String INVALID_DATE = "Cat";
    private static final String VALID_PHONE_NUMBER = "8-029-888-24-16";
    private static final String PHONE_NUMBER = "Телефон";
    private static final String VALID_SKYPE = "Kate Rakickaya";
    private static final String SKYPE = "Skype";
    private static final String EMPTY_STRING = "";
    private static final String VALID_NAME = "Kate";
    private static final String VALID_MIDDLE_NAME = "Dmitrievna";
    private static final String VALID_LAST_NAME = "Rakickaya";
    private WebDriver driver;
    private ProfileSettingPage profileSettingPage;
    private Employee employee;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        driver = new ChromeDriver();
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        profileSettingPage = loginPage.inputUserCredentials(new UserCredentials("petia", "empl")).setSubmitButton().openProfile().followSettingLink();
        employee = new DbService().getEmployee();
    }

    @Test
    public void inputValidEmail() {
        profileSettingPage.inputEmail(VALID_EMAIL);
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
        profileSettingPage.inputEmail(EMAIL_WITHOUT_COMMERCIAL_AT);
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL_FORMAT);
        if (!isCorrectEmail) {
            //TODO exist some alert message after input the field
        }

    }

    @Test
    public void inputEmailWithoutDomainPart() {
        profileSettingPage.inputEmail(EMAIL_WITHOUT_DOMAIN_PART);
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL_FORMAT);
        if (!isCorrectEmail) {
            //TODO exist some alert message after input the field
        }
    }

    @Test
    public void inputEmailWithoutName() {
        profileSettingPage.inputEmail(EMAIL_WITHOUT_NAME);
        String email = profileSettingPage.getEmail();
        boolean isCorrectEmail = email.matches(CORRECT_EMAIL_FORMAT);
        if (!isCorrectEmail) {
            //TODO exist some alert message after input the field
        }
    }

    @Test
    public void saveEmail() {
        ProfileMainPage profileMainPage = profileSettingPage.inputEmail(VALID_EMAIL).save();
        Assert.assertEquals(profileMainPage.getTextFrom(CONTACT_INFO, EMAIL), VALID_EMAIL);

    }

    @Test
    public void inputValidDate() {
        profileSettingPage.inputBirthDate(VALID_DATE);
        String date = profileSettingPage.getDate();
        boolean isCorrectDate = date.matches(CORRECT_DATE_FORMAT);
        if (!isCorrectDate) {
            //TODO exist some alert message after input the field
            Assert.fail();
        }
    }

    @Test
    public void inputInvalidDate() {
        profileSettingPage.inputBirthDate(INVALID_DATE);
        String date = profileSettingPage.getDate();
        boolean isCorrectDate = date.matches(CORRECT_DATE_FORMAT);
        if (!isCorrectDate) {
            //TODO exist some alert message after input the field
        }
    }

    @Test
    public void savePhoneNumber() {
        ProfileMainPage profileMainPage = profileSettingPage.inputNumber(VALID_PHONE_NUMBER).save();
        Assert.assertEquals(profileMainPage.getTextFrom(CONTACT_INFO, PHONE_NUMBER), VALID_PHONE_NUMBER);
    }

    @Test
    public void saveSkype() {
        ProfileMainPage profileMainPage = profileSettingPage.inputSkype(VALID_SKYPE).save();
        Assert.assertEquals(profileMainPage.getTextFrom(CONTACT_INFO, SKYPE), VALID_SKYPE);
    }

    @Test
    public void inputEmptyName() {
        profileSettingPage.inputName(EMPTY_STRING);
        //TODO alert message

    }

    @Test
    public void inputEmptyLastName() {
        profileSettingPage.inputLastName(EMPTY_STRING);
        //TODO alert message

    }

    @Test
    public void saveName() {
        ProfileMainPage profileMainPage = profileSettingPage.inputName(VALID_NAME).save();
        Assert.assertEquals(profileMainPage.getFullName().contains(VALID_NAME), VALID_NAME);

    }

    @Test
    public void saveMiddleName() {
        ProfileMainPage profileMainPage = profileSettingPage.inputMiddleName(VALID_MIDDLE_NAME).save();
        Assert.assertEquals(profileMainPage.getFullName().contains(VALID_MIDDLE_NAME), VALID_MIDDLE_NAME);

    }

    @Test
    public void saveLastName() {
        ProfileMainPage profileMainPage = profileSettingPage.inputLastName(VALID_LAST_NAME).save();
        Assert.assertEquals(profileMainPage.getFullName().contains(VALID_LAST_NAME), VALID_LAST_NAME);

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
