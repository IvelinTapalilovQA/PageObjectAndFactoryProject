package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static final String PAGE_URl = "http://training.skillo-bg.com:4300/posts/all";

    public final WebDriver  driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(){
        this.driver.get(PAGE_URl);
    }
    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URl));
    }
}
