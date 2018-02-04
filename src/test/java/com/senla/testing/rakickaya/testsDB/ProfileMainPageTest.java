package com.senla.testing.rakickaya.testsDB;

import com.senla.testing.rakickaya.data.UserCredentials;
import com.senla.testing.rakickaya.db.dbentities.Employee;
import com.senla.testing.rakickaya.db.dbservices.DbService;
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
    private WebDriver driver;
    private ProfileMainPage profileMainPage;
    private Employee employee;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
        driver = new ChromeDriver();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        profileMainPage = loginPage.inputUserCredentials(new UserCredentials("petia", "empl")).setSubmitButton().openProfile();
        employee = new DbService().getEmployee();

    }

    @Test
    public void followSettingPage() {
        profileMainPage.followSettingLink();
    }

    @Test
    public void checkEmailText() {
        Assert.assertEquals(profileMainPage.getTextFromEmail(), employee.getEmail());
    }

    @Test
    public void checkPhoneNumber() {
        Assert.assertEquals(profileMainPage.getTextFromPhoneNumber(), employee.getPhone());
    }

    @Test
    public void checkSkype() {
        Assert.assertEquals(profileMainPage.getTextFromSkype(), employee.getSkype());
    }

    @Test
    public void checkUnit() {
        Assert.assertEquals(profileMainPage.getTextFromUnit(), employee.getUnit_fk().getUnit_name());
    }

    @Test
    public void checkLocation() {
        Assert.assertEquals(profileMainPage.getTextFromLocation(), employee.getLocation_fk().getName());
    }

    @Test
    public void checkCountry() {
        Assert.assertEquals(profileMainPage.getTextFromCountry(), employee.getLocation_fk().getCountry());
    }

    @Test
    public void checkCity() {
        Assert.assertEquals(profileMainPage.getTextFromCity(), employee.getLocation_fk().getCity());
    }

    @Test
    public void checkOffice() {
        Assert.assertEquals(profileMainPage.getTextFromOffice(), employee.getLocation_fk().getOffice());
    }

    @Test
    public void checkActivity() {
        Assert.assertEquals(profileMainPage.getActivity(), String.valueOf(employee.isActive()));
    }

    @Test
    public void checkBirthDay() {
        Assert.assertEquals(profileMainPage.getTextFromBirthDay(), employee.getBirth_date());
    }

    @Test
    public void checkSpecialization() {
        Assert.assertEquals(profileMainPage.getTextFromSpecialization(), employee.getSpecialization_fk().getName());
    }

    @Test
    public void checkHiringDate() {
        Assert.assertEquals(profileMainPage.getTextFromHiringDate(), employee.getHire_date());
    }

    @Test
    public void checkProbationDate() {
        Assert.assertEquals(profileMainPage.getTextFromProbationDate(), employee.getProbation_till_date());
    }

    @Test
    public void checkProbationPeriodPass() {
        Assert.assertEquals(profileMainPage.getTextFromProbationPeriodPass(), employee.getProbation_approved());
    }

    @Test
    public void checkWorkStation() {
        Assert.assertEquals(profileMainPage.getTextFromWorkStation(), employee.getWorkstation());
    }

    @Test
    public void checkComment() {
        Assert.assertEquals(profileMainPage.getTextFromComment(), employee.getRm_comment());
    }

    @Test
    public void checkUserImage() {
        Assert.assertEquals(profileMainPage.getUserImageURL(), EXPECTED_IMAGE_URL);
    }

    @Test
    public void checkUserName() {

        Assert.assertEquals(profileMainPage.getUserName(), profileMainPage.getFullNameFromDb(employee));
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
