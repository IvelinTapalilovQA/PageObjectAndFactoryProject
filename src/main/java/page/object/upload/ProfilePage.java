package page.object.upload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {

    public static final String PAGE_URl = "http://training.skillo-bg.com:4300/posts/create";
    public final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URl));
    }
    public boolean isUsernameTitleVisible(String username){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.textToBe(By.tagName("h2"), username));
    }
    public int getPostsCount(){
        List<WebElement> postsCount = driver.findElements(By.tagName("app-post"));
        return postsCount.size();
    }
    public void clickPost(int postIndex){
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();
    }
}
