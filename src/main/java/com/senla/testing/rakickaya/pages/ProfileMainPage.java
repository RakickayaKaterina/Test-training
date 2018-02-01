package com.senla.testing.rakickaya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ProfileMainPage extends Page {

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
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
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
        settingLink.click();
        driverWait.until(ExpectedConditions.textToBe(By.xpath(SETTING_LABEL_PATH), SETTING_LABEL_NAME));
        return new ProfileSettingPage(driver);
    }

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

    public CalendarPopupPage setCalendarPopup() {
        tableMap.get(GENERAL_INFO).get(CALENDAR).findElement(By.xpath("a[1]")).click();
        driverWait.until(ExpectedConditions.textToBe(By.xpath(CALENDAR_PATH), USER_CALENDAR_NAME));
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

    public String getFullName() {
        return  usernameLabel.getText();
    }
}
