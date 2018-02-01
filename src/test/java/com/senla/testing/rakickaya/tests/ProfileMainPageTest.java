package com.senla.testing.rakickaya.tests;

import com.senla.testing.rakickaya.data.UserCredentials;
import com.senla.testing.rakickaya.pages.LoginPage;
import com.senla.testing.rakickaya.pages.ProfileMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProfileMainPageTest {
    private static final String EXPECTED_IMAGE_URL = "http://www.placehold.it/250x300/EFEFEF/AAAAAA&text=no+image";
    private static final String EXPECTED_EMAIL = "petr@mail.ru";
    private static final String EXPECTED_NUMBER = "8-029-3692514";
    private static final String EXPECTED_SKYPE = "df";
    private static final String EXPECTED_UNIT = "unit1";
    private static final String EXPECTED_LOCATION = "BLR_GRO";
    private static final String EXPECTED_COUNTRY = "BLR";
    private static final String EXPECTED_CITY = "GRO";
    private static final String EXPECTED_OFFICE = "1";
    private static final String EXPECTED_BIRTH_DAY = "";
    private static final String EXPECTED_SPECIALIZATION = "Developer";
    private static final String EXPECTED_HIRING_DATE = "";
    private static final String EXPECTED_PROBATION_DATE = "";
    private static final String EXPECTED_PASSING = "";
    private static final String EXPECTED_WORK_STATION = "wert34534wertwert";
    private static final String EXPECTED_COMMENT = "comment4";
    private static final String EXPECTED_ACTIVITY = "true";
    private static final String EXPECTED_USER_NAME = "Petr Petrov";
    private WebDriver driver;
    private ProfileMainPage profileMainPage;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        profileMainPage = loginPage.inputUserCredentials(new UserCredentials("petia", "empl")).setSubmitButton().openProfile();

    }

    @Test
    public void followSettingPage() {
        profileMainPage.followSettingLink();
    }

    @Test
    public void checkEmailText() {
        Assert.assertEquals(profileMainPage.getTextFromEmail(), EXPECTED_EMAIL);
    }

    @Test
    public void checkPhoneNumber() {
        Assert.assertEquals(profileMainPage.getTextFromPhoneNumber(), EXPECTED_NUMBER);
    }

    @Test
    public void checkSkype() {
        Assert.assertEquals(profileMainPage.getTextFromSkype(), EXPECTED_SKYPE);
    }

    @Test
    public void checkUnit() {
        Assert.assertEquals(profileMainPage.getTextFromUnit(), EXPECTED_UNIT);
    }

    @Test
    public void checkLocation() {
        Assert.assertEquals(profileMainPage.getTextFromLocation(), EXPECTED_LOCATION);
    }

    @Test
    public void checkCountry() {
        Assert.assertEquals(profileMainPage.getTextFromCountry(), EXPECTED_COUNTRY);
    }

    @Test
    public void checkCity() {
        Assert.assertEquals(profileMainPage.getTextFromCity(), EXPECTED_CITY);
    }

    @Test
    public void checkOffice() {
        Assert.assertEquals(profileMainPage.getTextFromOffice(), EXPECTED_OFFICE);
    }

    @Test
    public void checkActivity() {
        Assert.assertEquals(profileMainPage.getActivity(), EXPECTED_ACTIVITY);
    }

    @Test
    public void checkBirthDay() {
        Assert.assertEquals(profileMainPage.getTextFromBirthDay(), EXPECTED_BIRTH_DAY);
    }

    @Test
    public void checkSpecialization() {
        Assert.assertEquals(profileMainPage.getTextFromSpecialization(), EXPECTED_SPECIALIZATION);
    }

    @Test
    public void checkHiringDate() {
        Assert.assertEquals(profileMainPage.getTextFromHiringDate(), EXPECTED_HIRING_DATE);
    }

    @Test
    public void checkProbationDate() {
        Assert.assertEquals(profileMainPage.getTextFromProbationDate(), EXPECTED_PROBATION_DATE);
    }

    @Test
    public void checkProbationPeriodPass() {
        Assert.assertEquals(profileMainPage.getTextFromProbationPeriodPass(), EXPECTED_PASSING);
    }

    @Test
    public void checkWorkStation() {
        Assert.assertEquals(profileMainPage.getTextFromWorkStation(), EXPECTED_WORK_STATION);
    }

    @Test
    public void checkComment() {
        Assert.assertEquals(profileMainPage.getTextFromComment(), EXPECTED_COMMENT);
    }

    @Test
    public void checkUserImage() {
        Assert.assertEquals(profileMainPage.getUserImageURL(), EXPECTED_IMAGE_URL);
    }

    @Test
    public void checkUserName() {
        Assert.assertEquals(profileMainPage.getUserName(), EXPECTED_USER_NAME);
    }


    @Test
    public void openCalendarPopUp() {
        profileMainPage.setCalendarPopup();
    }

    @AfterMethod
    public void back() {
        profileMainPage.toBackState();
    }

    @AfterClass
    public void exit() {
        driver.quit();
    }


}
