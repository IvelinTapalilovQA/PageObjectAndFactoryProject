package page.object.upload;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class NewPostPage {

    public static final String PAGE_URl = "http://training.skillo-bg.com:4300/posts/create";
    public final WebDriver driver;

    public NewPostPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URl));
    }

    public void uploadPicture(File file) {
        WebElement field = driver.findElement(By.cssSelector(".file[type='file']"));
        field.sendKeys(file.getAbsolutePath());
    }

    public boolean isImageVisible() {
        try {
            WebElement image = driver.findElement(By.cssSelector(".image-preview"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getInputTitle(){
        WebElement titleInput = driver.findElement(By.cssSelector("input[placeholder='qa_pic.jpg']"));
        return titleInput.getAttribute("placeholder");
    }
    public void typingCaption(String caption) {
        WebElement captionElement = driver.findElement(By.name("caption"));
        captionElement.sendKeys(caption);
    }
    public void clickCreatePostButton(){
        WebElement button = driver.findElement(By.id("create-post"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }


}