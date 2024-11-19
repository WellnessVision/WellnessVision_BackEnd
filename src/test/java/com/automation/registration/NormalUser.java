package com.automation.registration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class NormalUser {
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
    public void validRegistration() {
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "Inod";
        String sendLastName = "Shanilka";
        String sendEmail = "inod@gmail.com";
        String sendPassword = "Inod@2000";
        String sendPasswordConfirm = "Inod@2000";
        String sendPhone = "0771234567";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By message = By.xpath("//h5[text()='Registration Successful. Thank you!']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(message));
            System.out.println("Registration successful message appeared.");
            driver.findElement(By.xpath("//button[contains(text(),'Thanks!')]")).click();
        } catch (TimeoutException e) {
            System.out.println("Registration successful message did not appear within the timeout.");
        }
    }

    @Test
    public void testEmptySubmission() {
        driver.findElement(By.id("register_landing_page")).click();

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        try {
            registerButton.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Failed due to empty required fields");
        }

        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains(URL + "NU_Register"), "Form should NOT be submitted");
    }

    @Test
    public void duplicateEmail(){
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "Inod";
        String sendLastName = "Shanilka";
        String sendEmail = "inod@gmail.com";
        String sendPassword = "Inod@2000";
        String sendPasswordConfirm = "Inod@2000";
        String sendPhone = "0771234567";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By message = By.xpath("//div[text()='Error registering user']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(message));
            System.out.println("Error message for registration appeared.");
        } catch (TimeoutException e) {
            System.out.println("Error message for registration not appeared.");
        }
    }

    @Test
    public void validNameWith30Chars() {
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "AlexandriannaMontefalco";
        String sendLastName = "FeatherstonehaughWorthington";
        String sendEmail = "alex.feather@gmail.com";
        String sendPassword = "Inod@2000";
        String sendPasswordConfirm = "Inod@2000";
        String sendPhone = "0771234567";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By message = By.xpath("//h5[text()='Registration Successful. Thank you!']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(message));
            System.out.println("Registration successful message appeared.");
            driver.findElement(By.xpath("//button[contains(text(),'Thanks!')]")).click();
        } catch (TimeoutException e) {
            System.out.println("Registration successful message did not appear within the timeout.");
        }
    }

    @Test
    public void invalidNameWithNonAlphabeticChars() {
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "Inod1";
        String sendLastName = "Shan!lka";
        String sendEmail = "inod.non.alphabetic@gmail.com";
        String sendPassword = "Inod@2000";
        String sendPasswordConfirm = "Inod@2000";
        String sendPhone = "0771234567";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By message = By.xpath("//h5[text()='Registration Successful. Thank you!']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(message));
            System.out.println("Registration successful message appeared.");
            driver.findElement(By.xpath("//button[contains(text(),'Thanks!')]")).click();
        } catch (TimeoutException e) {
            System.out.println("Registration successful message did not appear within the timeout.");
        }
    }

    @Test
    public void invalidEmail() {
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "Inod";
        String sendLastName = "Shanilka";
        String sendEmail = "inod#@shanilka@gmail.com";
        String sendPassword = "Inod@2000";
        String sendPasswordConfirm = "Inod@2000";
        String sendPhone = "0771234567";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By message = By.xpath("//h5[text()='Registration Successful. Thank you!']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(message));
            System.out.println("Registration successful message appeared.");
            driver.findElement(By.xpath("//button[contains(text(),'Thanks!')]")).click();
        } catch (TimeoutException e) {
            System.out.println("Registration successful message did not appear within the timeout.");
        }
    }

    @Test
    public void weakPassword() {
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "Namal";
        String sendLastName = "Rajapaksha";
        String sendEmail = "namalbaby@gmail.com";
        String sendPassword = "Rajapaksha";
        String sendPasswordConfirm = "Rajapaksha";
        String sendPhone = "0771234567";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By errorMessage = By.xpath("//div[@role='alert']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            System.out.println("Error message for password appeared. Registration failed");
        } catch (TimeoutException e) {
            System.out.println("Error message for password did not appeared. Successfully registered with weak password");
        }
    }

    @Test
    public void passwordMismatch() {
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "Rohitha";
        String sendLastName = "Rajapaksha";
        String sendEmail = "rohitha@gmail.com";
        String sendPassword = "Inod@2000";
        String sendPasswordConfirm = "Inod#2000";
        String sendPhone = "0771234567";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By errorMessage = By.xpath("//div[@role='alert']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            System.out.println("Error message for password appeared. Registration failed");
        } catch (TimeoutException e) {
            System.out.println("Error message for password did not appeared. Successfully registered with mismatched password");
        }
    }

    @Test
    public void passwordCharLimitValidationLessThan8() {
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "Sapumal";
        String sendLastName = "Wasantha";
        String sendEmail = "sapumal@gmail.com";
        String sendPassword = "Sap#123";
        String sendPasswordConfirm = "Sap#123";
        String sendPhone = "0771234567";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By errorMessage = By.xpath("//div[@role='alert']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            System.out.println("Error message for password appeared. Registration failed");
        } catch (TimeoutException e) {
            System.out.println("Error message for password did not appeared. Successfully registered with less than 8 chars but strong password");
        }
    }

    @Test
    public void passwordCharLimitValidationMoreThan64() {
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "Wasantha";
        String sendLastName = "Sapumal";
        String sendEmail = "wasantha@gmail.com";
        String sendPassword = "&,}dD>~#{Jx/&%Dd2,4ZHT'J<xpP$n#IP.6~hE/NdBfk~EQlxIsKSbsc'zq@)SZ2Giwx(TG";
        String sendPasswordConfirm = "&,}dD>~#{Jx/&%Dd2,4ZHT'J<xpP$n#IP.6~hE/NdBfk~EQlxIsKSbsc'zq@)SZ2Giwx(TG";
        String sendPhone = "0771234567";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By errorMessage = By.xpath("//div[@role='alert']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            System.out.println("Error message for password appeared. Registration failed");
        } catch (TimeoutException e) {
            System.out.println("Error message for password did not appeared. Successfully registered with strong and more than 64 chars password");
        }
    }

    @Test
    public void invalidPhoneNumber() {
        driver.findElement(By.id("register_landing_page")).click();

        String sendFirstName = "Nalin";
        String sendLastName = "Bandara";
        String sendEmail = "nalin@gmail.com";
        String sendPassword = "Inod@2000";
        String sendPasswordConfirm = "Inod@2000";
        String sendPhone = "Nalin_Phone_Number";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendPostalCode = "11126";
        String sendPreferences = "diabetics";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.id("Password_confirm"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(sendPhone);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        district.click();
        WebElement selectDistrict = driver.findElement(By.xpath("//option[text()='Gampaha']"));
        selectDistrict.click();
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        province.click();
        WebElement selectProvince = driver.findElement(By.xpath("//option[text()='Western']"));
        selectProvince.click();
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement preferences = driver.findElement(By.xpath("//div/label[contains(text(),'what are you looking for')]/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        preferences.sendKeys(sendPreferences);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[normalize-space(text())='Profile pic']/following-sibling::input[@placeholder='Eg: diabetes, yoga']"));
        profilePic.sendKeys(sendProfilePic);

        WebElement registerButton = driver.findElement(By.xpath("//button[text()='Register']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).perform();
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By message = By.xpath("//h5[text()='Registration Successful. Thank you!']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(message));
            System.out.println("Registration successful message appeared. Registered with invalid phone number");
            driver.findElement(By.xpath("//button[contains(text(),'Thanks!')]")).click();
        } catch (TimeoutException e) {
            System.out.println("Registration successful message did not appear within the timeout.");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
