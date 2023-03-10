package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String PAGE_URl = "http://training.skillo-bg.com:4300/users/login";
    public final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSignInElementText(){
        WebElement signInFormTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signInFormTitle));
        return signInFormTitle.getText();
    }
    public void typeUsername(String username){
        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(username);
    }
    public void typePassword(String password){
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);
    }
    public void clickSignInButton(){
        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URl));
    }
    public void navigateTo(){
        this.driver.get(PAGE_URl);
    }
    public boolean checkLoginTitle(){
        WebElement signInTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(signInTitle)).isDisplayed();
    }
    public void clickRegistrationButton(){
        WebElement registerLink = driver.findElement(By.xpath("//*[text()='Register']"));
        registerLink.click();
    }
}
