package com.senla.testing.rakickaya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ProfileMainPage extends Page {

    public static final String URL_PAGE = "http://86.57.161.116:10008/";
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
        settingLink = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[1]/div[1]/a[1]"));
        profileLabel = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[1]/span[1]"));
        usernameLabel = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[1]/h4[1]"));
        userImage = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/img[1]"));
        mainTabBar = driver.findElement(By.xpath("id(\"bs-example-navbar-collapse-1\")"));
        contactInfoTable = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[2]/table[1]"));
        generalInfoTable = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[2]/table[2]"));
        additionalInfoTable = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[2]/table[3]"));
        registerTable(contactInfoTable);
        registerTable(generalInfoTable);
        registerTable(additionalInfoTable);
    }

    public ProfileSettingPage followSettingLink() {
        settingLink.click();
        driverWait.until(ExpectedConditions.textToBe(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[1]/span[1]"), "Настройки"));
        return new ProfileSettingPage(driver);
    }

    private void registerTable(WebElement table) {
        WebElement nameTable = table.findElement(By.xpath("thead/tr"));
        String titleTable = nameTable.getText();
        List<WebElement> tableRows = table.findElements(By.xpath("tbody/tr"));
        Map<String,WebElement> cells= new HashMap<String, WebElement>();

        for (WebElement row : tableRows) {
            List<WebElement> rowElements = row.findElements(By.tagName("td"));
            cells.put(rowElements.get(0).getText(),rowElements.get(1));
        }
        tableMap.put(titleTable, cells);
    }

    public CalendarPopupPage setCalendarPopup() {
        tableMap.get("Общая информация").get("Календарь").findElement(By.xpath("a[1]")).click();
        driverWait.until(ExpectedConditions.textToBe(By.xpath("id(\"userCalendarTitle\")"), "User Calendar"));
        return new CalendarPopupPage(driver);
    }

    public void toBackState() {
        driver.get(URL_PAGE + "#/profile");
        tableMap = new HashMap<String, Map<String, WebElement>>();
        findAllElements();
    }
    public String getTextFrom(String nameTable, String nameRow){
        return tableMap.get(nameTable).get(nameRow).findElement(By.xpath("input")).getText();
    }
}
