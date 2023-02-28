package page.factory.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPageFactory {

    public static final String PAGE_URl = "http://training.skillo-bg.com:4300/users/register";

    public final WebDriver driver;

    public RegistrationPageFactory(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text() ='Sign up']")
    WebElement signUpTitle;

    @FindBy(name = "username")
    WebElement usernameInput;

    @FindBy(xpath = "//*[@type='email']")
    WebElement emailInput;

    @FindBy(xpath = "//*[@type='date']")
    WebElement birthDateInput;

    @FindBy(id = "defaultRegisterFormPassword")
    WebElement passwordInput;

    @FindBy(id = "defaultRegisterPhonePassword")
    WebElement confirmPasswordInput;

    @FindBy(name = "pulic-info")
    WebElement infoInput;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlContains(PAGE_URl));
    }
    public String getSignUpElementText(){;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signUpTitle));
        return signUpTitle.getText();
    }
    public void typeUsername(String username){
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }
    public void typeEmail(String password){
        emailInput.clear();
        emailInput.sendKeys(password);
    }
    public void typeDateOfBirth(String date){
        birthDateInput.clear();
        birthDateInput.sendKeys(date);
    }
    public void typePassword (String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    public void confirmPassword(String password){
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
    }
    public void typeInfo(){
        infoInput.clear();
        infoInput.sendKeys("The profile was created for test purposes!");
    }
    public void clickSignInButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }
}

