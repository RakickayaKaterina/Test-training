package com.senla.testing.rakickaya.pages;

import com.senla.testing.rakickaya.data.UserCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.senla.testing.rakickaya.pages.LoginPage.URL_PAGE;

public class ProfilePage {

    private WebDriver driver;

    private WebElement contactInfoTable;
    private WebElement generalInfoTable;
    private WebElement additionalInfoTable;
    private WebElement nameLabel;
    private WebElement userImage;
    private WebElement profileLabel;
    private WebElement settingLink;

    public ProfilePage(WebDriver driver){
        this.driver = driver;
     //   driver.get(URL_PAGE+"");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        findAllElements();
        WebDriverWait driverWait = new WebDriverWait(driver,5);
    }


    public void findAllElements(){
        contactInfoTable = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[2]/table[1]"));
        generalInfoTable = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[2]/table[2]"));
        additionalInfoTable = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[2]/table[3]"));
        profileLabel = driver.findElement(By.xpath("id(\"main\")/section[1]/div[2]/div[1]/div[2]/div[1]/div[1]/h4[1])"));
    }

}
