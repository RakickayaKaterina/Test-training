package com.senla.testing.rakickaya.pages;

import com.senla.testing.rakickaya.db.dbentities.Employee;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ProfileMainPage extends Page {

    public static final Logger log = Logger.getLogger(ProfileMainPage.class);

    public static final String ACTIVITY = "Активный";
    private static final String URL_PAGE = "http://86.57.161.116:10008/";
    private static final String SETTING_LINK_PATH = "//*[@id=\"main\"]/section/div[2]/div/div[1]/div/a";
    private static final String PROFILE_LABEL_PATH = "//*[@id=\"main\"]/section/div[2]/div/div[1]/span";
    private static final String USERNAME_LABEL_PATH = "//*[@id=\"main\"]/section/div[2]/div/div[2]/div/div[1]/h4";
    private static final String USER_IMAGE_PATH = "//*[@id=\"main\"]/section/div[2]/div/div[2]/div/div[1]/div/img";
    private static final String MAIN_TAB_BAR_PATH = "//*[@id=\"bs-example-navbar-collapse-1\"]";
    private static final String CONTACT_INFO_TABLE_PATH = "//*[@id=\"main\"]/section/div[2]/div/div[2]/div/div[2]/table[1]";
    private static final String GENERAL_INFO_TABLE_PATH = "//*[@id=\"main\"]/section/div[2]/div/div[2]/div/div[2]/table[2]";
    private static final String ADDITIONAL_INFO_TABLE_PATH = "//*[@id=\"main\"]/section/div[2]/div/div[2]/div/div[2]/table[3]";
    private static final String SETTING_LABEL_PATH = "//*[@id=\"main\"]/section/div[2]/div[1]/div[1]/span";
    private static final String SETTING_LABEL_NAME = "Настройки";
    private static final String GENERAL_INFO = "Общая информация";
    private static final String CALENDAR = "Календарь";
    private static final String CALENDAR_PATH = "//*[@id=\"userCalendarTitle\"]";
    private static final String USER_CALENDAR_NAME = "User Calendar";
    private static final String CONTACT_INFO = "Контактная информация";
    private static final String EMAIL = "Email";
    private static final String PHONE_NUMBER = "Телефон";
    private static final String SKYPE = "Skype";
    private static final String UNIT = "Подразделение";
    private static final String LOCATION = "Локация";
    private static final String COUNTRY = "Страна";
    private static final String CITY = "Город";
    private static final String OFFICE = "Офис";
    private static final String ADDITIONAL_INFO = "Дополнительная информация";
    private static final String BIRTH_DAY = "Дата Рождения";
    private static final String SPECIALIZATION = "Специализация";
    private static final String HIRING_DATE = "Дата приема";
    private static final String PROBATION_DATE = "Дата ИС";
    private static final String PROBATION_PERIOD_PASS = "ИС пройден";
    private static final String WORK_STATION = "Рабочая станция";
    private static final String COMMENT = "Комментарий";
    private WebDriver driver;
    private WebDriverWait driverWait;
    private WebElement mainTabBar;

    private WebElement contactInfoTable;
    private WebElement generalInfoTable;
    private WebElement additionalInfoTable;

    private WebElement usernameLabel;
    private WebElement userImage;

    private WebElement profileLabel;
    private WebElement settingLink;

    private HashMap<String, Map<String, WebElement>> tableMap;

    public ProfileMainPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverWait = new WebDriverWait(driver, 5);
        tableMap = new HashMap<String, Map<String, WebElement>>();
        findAllElements();

    }

    public void findAllElements() {
        settingLink = driver.findElement(By.xpath(SETTING_LINK_PATH));
        profileLabel = driver.findElement(By.xpath(PROFILE_LABEL_PATH));
        usernameLabel = driver.findElement(By.xpath(USERNAME_LABEL_PATH));
        userImage = driver.findElement(By.xpath(USER_IMAGE_PATH));
        mainTabBar = driver.findElement(By.xpath(MAIN_TAB_BAR_PATH));
        contactInfoTable = driver.findElement(By.xpath(CONTACT_INFO_TABLE_PATH));
        generalInfoTable = driver.findElement(By.xpath(GENERAL_INFO_TABLE_PATH));
        additionalInfoTable = driver.findElement(By.xpath(ADDITIONAL_INFO_TABLE_PATH));
        registerTable(contactInfoTable);
        registerTable(generalInfoTable);
        registerTable(additionalInfoTable);

    }

    public ProfileSettingPage followSettingLink() {
        try {
            settingLink.click();
            driverWait.until(ExpectedConditions.textToBe(By.xpath(SETTING_LABEL_PATH), SETTING_LABEL_NAME));
        }catch (TimeoutException e){
            log.error("Settings haven't started");
        }return new ProfileSettingPage(driver);
    }

    //registration tables
    private void registerTable(WebElement table) {
        WebElement nameTable = table.findElement(By.xpath("thead/tr"));
        String titleTable = nameTable.getText();
        List<WebElement> tableRows = table.findElements(By.xpath("tbody/tr"));
        Map<String, WebElement> cells = new HashMap<String, WebElement>();

        for (WebElement row : tableRows) {
            List<WebElement> rowElements = row.findElements(By.tagName("td"));
            cells.put(rowElements.get(0).getText(), rowElements.get(1));
        }
        tableMap.put(titleTable, cells);
    }
    public String getTextFromEmail(){
        return tableMap.get(CONTACT_INFO).get(EMAIL).getText();
    }
    public String getTextFromPhoneNumber(){
        return tableMap.get(CONTACT_INFO).get(PHONE_NUMBER).getText();
    }
    public String getTextFromSkype(){
        return tableMap.get(CONTACT_INFO).get(SKYPE).findElement(By.xpath("a")).getText();
    }
    public String getTextFromUnit(){
        return tableMap.get(GENERAL_INFO).get(UNIT).getText();
    }
    public String getTextFromLocation(){
        return tableMap.get(GENERAL_INFO).get(LOCATION).getText();
    }
    public String getTextFromCountry(){
        return tableMap.get(GENERAL_INFO).get(COUNTRY).getText();
    }
    public String getTextFromCity(){
        return tableMap.get(GENERAL_INFO).get(CITY).getText();
    }
    public String getTextFromOffice(){
        return tableMap.get(GENERAL_INFO).get(OFFICE).getText();
    }
    public String getActivity(){
        return  tableMap.get(GENERAL_INFO).get(ACTIVITY).findElement(By.xpath("div")).getAttribute("value");
    }
    public String getTextFromBirthDay(){
        return tableMap.get(ADDITIONAL_INFO).get(BIRTH_DAY).getText();
    }
    public String getTextFromSpecialization(){
        return tableMap.get(ADDITIONAL_INFO).get(SPECIALIZATION).getText();
    }
    public String getTextFromHiringDate(){
        return tableMap.get(ADDITIONAL_INFO).get(HIRING_DATE).getText();
    }
    public String getTextFromProbationDate(){
        return tableMap.get(ADDITIONAL_INFO).get(PROBATION_DATE).getText();
    }
    public String getTextFromProbationPeriodPass(){
        return tableMap.get(ADDITIONAL_INFO).get(PROBATION_PERIOD_PASS).getText();
    }
    public String getTextFromWorkStation(){
        return tableMap.get(ADDITIONAL_INFO).get(WORK_STATION).getText();
    }
    public String getTextFromComment(){
        return tableMap.get(ADDITIONAL_INFO).get(COMMENT).getText();
    }

    public String getUserImageURL(){
        return userImage.getAttribute("src");
    }
    public String getUserName(){
        return usernameLabel.getText();
    }




    public CalendarPopupPage setCalendarPopup() {
        try {
            tableMap.get(GENERAL_INFO).get(CALENDAR).findElement(By.xpath("a[1]")).click();
            driverWait.until(ExpectedConditions.textToBe(By.xpath(CALENDAR_PATH), USER_CALENDAR_NAME));
        }catch (TimeoutException e){
            log.error("Calendar pop-up hasn't started");
        }

        return new CalendarPopupPage(driver);
    }

    public void toBackState() {
        driver.get(URL_PAGE + "#/profile");
        tableMap = new HashMap<String, Map<String, WebElement>>();
        findAllElements();
    }

    public String getTextFrom(String nameTable, String nameRow) {
        return tableMap.get(nameTable).get(nameRow).findElement(By.xpath("input")).getText();
    }
    public String getFullNameFromDb(Employee employee){
        String name = employee.getFirst_name();
        String middleName = employee.getMiddle_name();
        String lastName = employee.getLast_name();
        return String.format("%s %s%s",name,middleName.isEmpty()? middleName : middleName+" ",lastName);
    }
    public String getFullName() {
        return usernameLabel.getText();
    }

}
