package com.senla.testing.rakickaya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*
* TODO
 */
public class VacationPage extends Page {

    private WebDriver driver;
    private WebDriverWait driverWait;
    private WebElement dropdownToggle;



    public VacationPage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver,3);
        findAllElements();
    }
    public void findAllElements(){
        dropdownToggle = driver.findElement(By.xpath("id(\"bs-example-navbar-collapse-1\")/ul[2]/li[2]/a[1]"));
    }

    public ProfileMainPage openProfile(){
        dropdownToggle.click();
        new Actions(driver).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
        return  new ProfileMainPage(driver);


    }
}
