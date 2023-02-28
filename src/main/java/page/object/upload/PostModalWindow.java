package page.object.upload;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PostModalWindow {

    public final WebDriver driver;

    public final WebElement modalElement;

    public PostModalWindow(WebDriver driver) {
        this.driver = driver;
        this.modalElement = driver.findElement(By.className("post-modal"));
    }
    public boolean isImageVisible() {
        try {
            WebElement image = modalElement.findElement(By.cssSelector(".post-modal-img img"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getModalWindowCaption(){
        WebElement modalWindowCaption = modalElement.findElement(By.className("post-title"));
        return  modalWindowCaption.getText();
    }
    public String getPostUsernameTitle(){
        WebElement usernameTitle = modalElement.findElement(By.className("post-user"));
        return usernameTitle.getText();
    }
}