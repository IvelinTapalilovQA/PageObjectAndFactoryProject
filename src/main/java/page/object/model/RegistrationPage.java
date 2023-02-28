package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import java.time.Duration;

public class RegistrationPage {
    public static final String PAGE_URl = "http://training.skillo-bg.com:4300/users/register";

    public final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlContains(PAGE_URl));
    }
    public String getSignUpElementText(){
        WebElement signUpFormTitle = driver.findElement(By.xpath("//*[text() ='Sign up']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signUpFormTitle));
        return signUpFormTitle.getText();
    }

    public void typeUsername(String username){
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }
    public void typeEmail(String password){
        WebElement emailInput = driver.findElement(By.xpath("//*[@type='email']"));
        emailInput.clear();
        emailInput.sendKeys(password);
    }
    public void typeDateOfBirth(String date){
        WebElement birthDateInput = driver.findElement(By.xpath("//*[@type='date']"));
        birthDateInput.clear();
        birthDateInput.sendKeys(date);
    }
    public void typePassword (String password){
        WebElement passwordInput = driver.findElement(By.id("defaultRegisterFormPassword"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    public void confirmPassword(String password){
        WebElement confirmPasswordInput = driver.findElement(By.id("defaultRegisterPhonePassword"));
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
    }
    public void typeInfo(){
        WebElement infoInput = driver.findElement(By.name("pulic-info"));
        infoInput.clear();
        infoInput.sendKeys("The profile was created for test purposes!");
    }
    public void clickSignInButton(){
        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }
}

