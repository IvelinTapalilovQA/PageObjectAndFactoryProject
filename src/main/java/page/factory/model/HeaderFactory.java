package page.factory.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderFactory {
    public final WebDriver driver;

    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;

    @FindBy(id = "nav-link-login")
    private WebElement loginLink;

    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;

    @FindBy(id = "homeIcon")
    private WebElement logo;

    @FindBy(css = "#navbarColor01 > ul.navbar-nav.my-ml.d-none.d-md-block > li > a > i")
    private WebElement logoutButton;

    public HeaderFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();
    }

    public void clickLogin() {
        loginLink.click();
    }

    public void clickNewPost() {
        newPostLink.click();
    }

    public void clickLogo() {
        logo.click();
    }

    public WebElement getLogo() {
        return logo;
    }

    public void clickableLogo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(getLogo()));
    }

    public void logoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }
}
