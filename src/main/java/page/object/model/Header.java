package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    public final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProfile(){
        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();
    }

    public void clickLogin(){
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();
    }
    public void clickNewPost(){
        WebElement newPostLink = driver.findElement(By.id("nav-link-new-post"));
        newPostLink.click();
    }
    public void clickLogo(){
        WebElement logo = driver.findElement(By.id("homeIcon"));
        logo.click();
    }
    public WebElement getLogo(){
        return driver.findElement(By.id("homeIcon"));
    }
    public void clickableLogo(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(getLogo()));
    }

    public void logoutButton(){
        WebElement logoutButton = driver.findElement(By.cssSelector("#navbarColor01 > ul.navbar-nav.my-ml.d-none.d-md-block > li > a > i"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

}
