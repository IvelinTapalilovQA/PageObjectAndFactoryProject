package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoPage {
    public final WebDriver driver;

    public LogoPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getLogo(){
        return driver.findElement(By.id("homeIcon"));
    }
    public void clickableLogo(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(getLogo()));
    }
}
