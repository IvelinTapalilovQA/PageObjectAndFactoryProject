package page.object.upload;

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

        public void navigatoTo(){
            driver.get(PAGE_URl);
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
    }

