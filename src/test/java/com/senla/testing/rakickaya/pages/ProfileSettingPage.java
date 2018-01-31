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

public class ProfileSettingPage extends Page{

    private WebDriver driver;
    private WebDriverWait driverWait;

    private WebElement mainTabBar;

    private WebElement contactInfoTable;
    private WebElement profileInfoTable;

    private WebElement userImage;
    private WebElement selectImageButton;

    private WebElement profileLabel;
    private WebElement settingLink;

    //Where is the button Save?
    private WebElement saveButton;

    private Map<String,Map<String,WebElement>> tableMap;


    public ProfileSettingPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driverWait = new WebDriverWait(driver,5);
        findAllElements();
        tableMap = new HashMap<String, Map<String, WebElement>>();
        registerTable(contactInfoTable);
        registerTable(profileInfoTable);
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


    public void findAllElements(){
        mainTabBar = driver.findElement(By.xpath("id(\"main\")/section[1]/div[1]/div[1]/nav[1]"));
        contactInfoTable = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[2]/form[1]/table[2]"));
        profileInfoTable = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[2]/form[1]/table[1]"));
        userImage = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/img[1]"));
        selectImageButton = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/span[1]"));
        profileLabel = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[1]/span[1]"));
        settingLink = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[1]/div[1]/a[1]/span[1]"));
    }
    public void findButtonSave(){
        saveButton = driver.findElement(By.xpath("SOME LOCATOR FOR THE SAVE BUTTON"));
    }
    public ProfileSettingPage inputName(String name){
        WebElement webElement = tableMap.get("Информация профиля").get("Имя").findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(name);
        return  this;

    }
    public ProfileSettingPage inputMiddleName(String middleName){
        WebElement webElement = tableMap.get("Информация профиля").get("Отчество").findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(middleName);
        return  this;
    }
    public ProfileSettingPage inputLastName(String lastName){
        WebElement webElement = tableMap.get("Информация профиля").get("Фамилия").findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(lastName);
        return  this;
    }
    public ProfileSettingPage inputBirthDate(String date){
        WebElement webElement = tableMap.get("Информация профиля").get("Дата Рождения").findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(date);
        return  this;
    }
    //TODO Password
    public ProfileSettingPage changePassword(String date){
        WebElement webElement = tableMap.get("Информация профиля").get("Password");
        webElement.findElement(By.xpath("a")).sendKeys(date);
        return  this;
    }
    public ProfileSettingPage inputEmail(String email){
        WebElement webElement = tableMap.get("Информация профиля").get("Email").findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(email);
        return  this;
    }
    public ProfileSettingPage inputNumber(String numberPhone){
        WebElement webElement = tableMap.get("Информация профиля").get("Телефон").findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(numberPhone);
        return  this;
    }
    public ProfileSettingPage inputSkype(String skype){
        WebElement webElement = tableMap.get("Информация профиля").get("Skype").findElement(By.xpath("input"));
        webElement.clear();
        webElement.sendKeys(skype);
        return  this;
    }


    public ProfileMainPage followSettingLink(){
        settingLink.click();
        driverWait.until(ExpectedConditions.textToBe(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[1]/span[1]"), "Профиль"));
        return  new ProfileMainPage(driver);
    }

    public ProfileMainPage save(){
        saveButton.click();
        return new ProfileMainPage(driver);
    }


    public String getEmail() {
        return tableMap.get("Информация профиля").get("Email").findElement(By.xpath("input")).getText();
    }
}
