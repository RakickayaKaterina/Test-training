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

public class ProfileSettingPage extends Page {

    private static final String MAIN_TAB_BAR_PATH = "#bs-example-navbar-collapse-1";
    private static final String CONTACT_INFO_TABLE_PATH = "div#main table:nth-child(1)";
    private static final String PROFILE_INFO_TABLE_PATH = "div#main table:nth-child(2)";
    private static final String USER_IMAGE_PATH = "div#main img";
    private static final String IMAGE_BUTTON_PATH = "div#main span.btn.btn-primary";
    private static final String PROFILE_LABEL_PATH = "div#main div.panel-heading > span";
    private static final String SETTING_LABEL_PATH = "div#main div.panel-heading > span";
    private static final String SETTING_LINK_PATH = "div#main div.panel-heading > div > a";
    private static final String SAVE_BUTTON_LOCATOR = "SOME LOCATOR FOR THE SAVE BUTTON";
    private static final String PROFILE_INFO = "Информация профиля";
    private static final String NAME = "Имя";
    private static final String MIDDLE_NAME = "Отчество";
    private static final String LAST_NAME = "Фамилия";
    private static final String BIRTH_DAY = "Дата Рождения";
    private static final String PASSWORD = "Password";
    private static final String EMAIL = "Email";
    private static final String PHONE_NUMBER = "Телефон";
    private static final String SKYPE = "Skype";

    private static final String PROFILE = "Профиль";
    private WebDriver driver;
    private WebDriverWait driverWait;

    private WebElement mainTabBar;

    private WebElement contactInfoTable;
    private WebElement profileInfoTable;

    private WebElement userImage;
    private WebElement selectImageButton;

    private WebElement settingLabel;
    private WebElement settingLink;

    //Where is the button Save?
    private WebElement saveButton;

    private Map<String, Map<String, WebElement>> tableMap;


    public ProfileSettingPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driverWait = new WebDriverWait(driver, 5);
        findAllElements();
        tableMap = new HashMap<String, Map<String, WebElement>>();
        registerTable(contactInfoTable);
        registerTable(profileInfoTable);
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


    public void findAllElements() {
        mainTabBar = driver.findElement(By.cssSelector(MAIN_TAB_BAR_PATH));
        contactInfoTable = driver.findElement(By.cssSelector(CONTACT_INFO_TABLE_PATH));
        profileInfoTable = driver.findElement(By.cssSelector(PROFILE_INFO_TABLE_PATH));
        userImage = driver.findElement(By.cssSelector(USER_IMAGE_PATH));
        selectImageButton = driver.findElement(By.cssSelector(IMAGE_BUTTON_PATH));
        settingLabel = driver.findElement(By.cssSelector(SETTING_LABEL_PATH));
        settingLink = driver.findElement(By.cssSelector(SETTING_LINK_PATH));
    }

    public void findButtonSave() {
        saveButton = driver.findElement(By.cssSelector(SAVE_BUTTON_LOCATOR));
    }

    public ProfileSettingPage inputName(String name) {
        WebElement webElement = tableMap.get(PROFILE_INFO).get(NAME).findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(name);
        return this;

    }

    public ProfileSettingPage inputMiddleName(String middleName) {
        WebElement webElement = tableMap.get(PROFILE_INFO).get(MIDDLE_NAME).findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(middleName);
        return this;
    }

    public ProfileSettingPage inputLastName(String lastName) {
        WebElement webElement = tableMap.get(PROFILE_INFO).get(LAST_NAME).findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(lastName);
        return this;
    }

    public ProfileSettingPage inputBirthDate(String date) {
        WebElement webElement = tableMap.get(PROFILE_INFO).get(BIRTH_DAY).findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(date);
        return this;
    }

    //TODO Password
    public ProfileSettingPage changePassword(String date) {
        WebElement webElement = tableMap.get(PROFILE_INFO).get(PASSWORD);
        webElement.findElement(By.xpath("a")).sendKeys(date);
        return this;
    }

    public ProfileSettingPage inputEmail(String email) {
        WebElement webElement = tableMap.get(PROFILE_INFO).get(EMAIL).findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(email);
        return this;
    }

    public ProfileSettingPage inputNumber(String numberPhone) {
        WebElement webElement = tableMap.get(PROFILE_INFO).get(PHONE_NUMBER).findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(numberPhone);
        return this;
    }

    public ProfileSettingPage inputSkype(String skype) {
        WebElement webElement = tableMap.get(PROFILE_INFO).get(SKYPE).findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(skype);
        return this;
    }


    public ProfileMainPage followSettingLink() {
        settingLink.click();
        driverWait.until(ExpectedConditions.textToBe(By.cssSelector(PROFILE_LABEL_PATH), PROFILE));
        return new ProfileMainPage(driver);
    }

    public ProfileMainPage save() {
        saveButton.click();
        return new ProfileMainPage(driver);
    }


    public String getEmail() {
        return tableMap.get(PROFILE_INFO).get(EMAIL).findElement(By.xpath("input")).getText();
    }

    public String getDate() {
        return tableMap.get(PROFILE_INFO).get(BIRTH_DAY).findElement(By.xpath("input")).getText();
    }


}
