package page.factory.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageFactory {

    public static final String PAGE_URl = "http://training.skillo-bg.com:4300/users/login";
    public final WebDriver driver;

    @FindBy (xpath = "//p[text()='Sign in']")
    WebElement signInFormTitle;

    @FindBy(id = "defaultLoginFormUsername")
    WebElement usernameField;

    @FindBy(id = "defaultLoginFormPassword")
    WebElement passwordField;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;

    @FindBy (xpath = "//p[text()='Sign in']")
    WebElement signInTitle;

    @FindBy (xpath = "//*[text()='Register']")
    WebElement registerLink;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getSignInElementText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signInFormTitle));
        return signInFormTitle.getText();
    }
    public void typeUsername(String username){
        usernameField.sendKeys(username);
    }
    public void typePassword(String password){
        passwordField.sendKeys(password);
    }
    public void clickSignInButton(){
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(signInTitle)).isDisplayed();
    }
    public void clickRegistrationButton(){
        registerLink.click();
    }
}

