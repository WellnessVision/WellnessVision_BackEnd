package com.automation.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Volunteer {
    WebDriver driver;
    String URL = "http://localhost:5173/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    public void validLogin() {
        driver.findElement(By.id("login_landing_page")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By popUp = By.xpath("//span[text()='Welcome to WellnessVision']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUp));

        String sendEmail = "supun@gmail.com";
        String sendPassword = "1Aa#1111";

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Enter email']"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter password']"));
        password.sendKeys(sendPassword);
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        try {
            By confirmation = By.xpath("//span[text()='Dashboard']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation));
            System.out.println("Login Success");
        } catch (TimeoutException e) {
            System.out.println("Login Failed");
        }
    }

    @Test
    public void invalidEmailValidPasswordLogin() {
        driver.findElement(By.id("login_landing_page")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By popUp = By.xpath("//span[text()='Welcome to WellnessVision']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUp));

        String sendEmail = "supun.anee@gmail.com";
        String sendPassword = "1Aa#1111";

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Enter email']"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter password']"));
        password.sendKeys(sendPassword);
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        try {
            By confirmation = By.xpath("//span[text()='Dashboard']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation));
            System.out.println("Login Success");
        } catch (TimeoutException e) {
            System.out.println("Login Failed");
        }
    }

    @Test
    public void invalidPasswordValidEmailLogin() {
        driver.findElement(By.id("login_landing_page")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By popUp = By.xpath("//span[text()='Welcome to WellnessVision']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUp));

        String sendEmail = "supun@gmail.com";
        String sendPassword = "1a1111";

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Enter email']"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter password']"));
        password.sendKeys(sendPassword);
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        try {
            By confirmation = By.xpath("//span[text()='Dashboard']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation));
            System.out.println("Login Success");
        } catch (TimeoutException e) {
            System.out.println("Login Failed");
        }
    }

    @Test
    public void invalidEmailInvalidPasswordLogin() {
        driver.findElement(By.id("login_landing_page")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By popUp = By.xpath("//span[text()='Welcome to WellnessVision']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUp));

        String sendEmail = "supun.anee@gmail.com";
        String sendPassword = "1a1111";

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Enter email']"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter password']"));
        password.sendKeys(sendPassword);
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        try {
            By confirmation = By.xpath("//span[text()='Dashboard']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation));
            System.out.println("Login Success");
        } catch (TimeoutException e) {
            System.out.println("Login Failed");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
