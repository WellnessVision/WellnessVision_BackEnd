package com.automation.registration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class NormalUser {
    WebDriver driver;
    String URL = "http://localhost:5173/";

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    public void testEmptySubmission(){
        driver.findElement(By.id("register_landing_page")).click();

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        try{
            registerButton.click();
        }
        catch (ElementClickInterceptedException e){
            System.out.println("Failed due to empty required fields");
        }

        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains(URL + "NU_Register"), "Form should NOT be submitted");
    }

    // //h5[text()='Registration Successful. Thank you!']
    // //button[text()=' Thanks! Got it']
    // //div[text()='Error registering user']

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
