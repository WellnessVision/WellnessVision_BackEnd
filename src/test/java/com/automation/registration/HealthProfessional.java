package com.automation.registration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HealthProfessional {
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
        WebElement registerButton = driver.findElement(By.xpath("//a[text()='Be a Health Professional !']"));
        Actions actionRegister = new Actions(driver);
        actionRegister.moveToElement(registerButton).perform();
        registerButton.click();

        String sendFirstName = "Nalinda";
        String sendLastName = "Jayatissa";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendDistrict = "Gampaha";
        String sendProvince = "Western";
        String sendPostalCode = "11126";
        String sendEmail = "nalinda@gmail.com";
        String sendMobilePhone = "0779876543";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";
        String sendPassword = "Inod@2000";
        String sendPasswordConfirm = "Inod@2000";
        String sendProfession = "Consultant (Counselor)";
        String sendOwnership = "Government Organization";
        String sendRegisteredOrganization = "National Hospital";
        String sendRegistrationNumber = "MBBS1822";
        String sendVerificationCertificate = "C:\\Users\\User\\Desktop\\Final Examinations Timetable.pdf";
        String sendVerificationOtherCertificates = "C:\\Users\\User\\Desktop\\Final Examinations Timetable.pdf";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        Select selectDistrict = new Select(district);
        selectDistrict.selectByVisibleText(sendDistrict);
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        Select selectProvince = new Select(province);
        selectProvince.selectByVisibleText(sendProvince);
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement mobilePhone = driver.findElement(By.xpath("//input[@placeholder='Mobiled Number']"));
        mobilePhone.sendKeys(sendMobilePhone);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[text()='Profile Picture']/following-sibling::input[@id='exampleFormControlFile1']"));
        profilePic.sendKeys(sendProfilePic);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.xpath("//input[@placeholder='Conformation Password']"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement profession = driver.findElement(By.xpath("(//select[@id='inputState'])[3]"));
        Select selectProfession = new Select(profession);
        selectProfession.selectByVisibleText(sendProfession);
        WebElement registeredOrganization = driver.findElement(By.id("organization"));
        registeredOrganization.sendKeys(sendRegisteredOrganization);
        WebElement registrationNumber = driver.findElement(By.id("regNo"));
        registrationNumber.sendKeys(sendRegistrationNumber);
        WebElement ownership = driver.findElement(By.xpath("(//select[@id='inputState'])[4]"));
        Select selectOwnership = new Select(ownership);
        selectOwnership.selectByVisibleText(sendOwnership);
        WebElement verificationCertificate = driver.findElement(By.xpath("//div/label[text()='Verification certificate']/following-sibling::input[@id='exampleFormControlFile1']"));
        verificationCertificate.sendKeys(sendVerificationCertificate);
        WebElement verificationOtherCertificates = driver.findElement(By.xpath("//div/label[contains(text(),'Other Verification Details')]/following-sibling::input[@id='exampleFormControlFile1']"));
        verificationOtherCertificates.sendKeys(sendVerificationOtherCertificates);
        WebElement registerRequestButton = driver.findElement(By.xpath("//button[text()='Submit the Request']"));
        Actions actionRequest = new Actions(driver);
        actionRequest.moveToElement(registerRequestButton).perform();
        registerRequestButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By message = By.xpath("//h5[contains(text(),'Your registration request has been successfully created.')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(message));
            System.out.println("Registration request successful message appeared.");
            driver.findElement(By.xpath("//button[contains(text(),'Thanks!')]")).click();
        } catch (TimeoutException e) {
            System.out.println("Registration request successful message did not appear within the timeout.");
        }
    }

    @Test
    public void invalidRegistrationOrganization() {
        WebElement registerButton = driver.findElement(By.xpath("//a[text()='Be a Health Professional !']"));
        Actions actionRegister = new Actions(driver);
        actionRegister.moveToElement(registerButton).perform();
        registerButton.click();

        String sendFirstName = "Milinda";
        String sendLastName = "Mihiran";
        String sendAddress1 = "83/C";
        String sendAddress2 = "Meegalla";
        String sendCity = "Urapola";
        String sendDistrict = "Gampaha";
        String sendProvince = "Western";
        String sendPostalCode = "11126";
        String sendEmail = "milinda@gmail.com";
        String sendMobilePhone = "0779876543";
        String sendProfilePic = "C:\\Users\\User\\Desktop\\profilePic.jpg";
        String sendPassword = "Inod@2000";
        String sendPasswordConfirm = "Inod@2000";
        String sendProfession = "Consultant (Counselor)";
        String sendOwnership = "Government Organization";
        String sendRegisteredOrganization = "#Save_Sri_L@nka";
        String sendRegistrationNumber = "MBBS1822";
        String sendVerificationCertificate = "C:\\Users\\User\\Desktop\\Final Examinations Timetable.pdf";
        String sendVerificationOtherCertificates = "C:\\Users\\User\\Desktop\\Final Examinations Timetable.pdf";

        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(sendFirstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(sendLastName);
        WebElement address1 = driver.findElement(By.id("inputAddress"));
        address1.sendKeys(sendAddress1);
        WebElement address2 = driver.findElement(By.id("inputAddress2"));
        address2.sendKeys(sendAddress2);
        WebElement city = driver.findElement(By.id("inputCity"));
        city.sendKeys(sendCity);
        WebElement district = driver.findElement(By.xpath("(//select[@id='inputState'])[1]"));
        Select selectDistrict = new Select(district);
        selectDistrict.selectByVisibleText(sendDistrict);
        WebElement province = driver.findElement(By.xpath("(//select[@id='inputState'])[2]"));
        Select selectProvince = new Select(province);
        selectProvince.selectByVisibleText(sendProvince);
        WebElement postalCode = driver.findElement(By.id("inputZip"));
        postalCode.sendKeys(sendPostalCode);
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys(sendEmail);
        WebElement mobilePhone = driver.findElement(By.xpath("//input[@placeholder='Mobiled Number']"));
        mobilePhone.sendKeys(sendMobilePhone);
        WebElement profilePic = driver.findElement(By.xpath("//div/label[text()='Profile Picture']/following-sibling::input[@id='exampleFormControlFile1']"));
        profilePic.sendKeys(sendProfilePic);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(sendPassword);
        WebElement passwordConfirm = driver.findElement(By.xpath("//input[@placeholder='Conformation Password']"));
        passwordConfirm.sendKeys(sendPasswordConfirm);
        WebElement profession = driver.findElement(By.xpath("(//select[@id='inputState'])[3]"));
        Select selectProfession = new Select(profession);
        selectProfession.selectByVisibleText(sendProfession);
        WebElement registeredOrganization = driver.findElement(By.id("organization"));
        registeredOrganization.sendKeys(sendRegisteredOrganization);
        WebElement registrationNumber = driver.findElement(By.id("regNo"));
        registrationNumber.sendKeys(sendRegistrationNumber);
        WebElement ownership = driver.findElement(By.xpath("(//select[@id='inputState'])[4]"));
        Select selectOwnership = new Select(ownership);
        selectOwnership.selectByVisibleText(sendOwnership);
        WebElement verificationCertificate = driver.findElement(By.xpath("//div/label[text()='Verification certificate']/following-sibling::input[@id='exampleFormControlFile1']"));
        verificationCertificate.sendKeys(sendVerificationCertificate);
        WebElement verificationOtherCertificates = driver.findElement(By.xpath("//div/label[contains(text(),'Other Verification Details')]/following-sibling::input[@id='exampleFormControlFile1']"));
        verificationOtherCertificates.sendKeys(sendVerificationOtherCertificates);
        WebElement registerRequestButton = driver.findElement(By.xpath("//button[text()='Submit the Request']"));
        Actions actionRequest = new Actions(driver);
        actionRequest.moveToElement(registerRequestButton).perform();
        registerRequestButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By message = By.xpath("//h5[contains(text(),'Your registration request has been successfully created.')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(message));
            System.out.println("Registration request successful message appeared with invalid registered organization.");
            driver.findElement(By.xpath("//button[contains(text(),'Thanks!')]")).click();
        } catch (TimeoutException e) {
            System.out.println("Registration request successful message did not appear within the timeout.");
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
