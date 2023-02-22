package page.factory.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePageFactory {
    public static final String PAGE_URl = "http://training.skillo-bg.com:4300/users";


    public final WebDriver driver;

    public ProfilePageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public boolean getProfileTextName(String username){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.textToBe(By.tagName("h2"), username));
    }
    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlContains(PAGE_URl));
    }
}


