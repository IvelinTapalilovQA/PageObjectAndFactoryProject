package pageObjectModelTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.profiler.model.Profile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjectModel.Header;
import pageObjectModel.HomePage;
import pageObjectModel.LoginPage;
import pageObjectModel.ProfilePage;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;

    @BeforeSuite
    public void setUpSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @DataProvider(name = "userNames")
    public Object[][] userName() {
        return new Object[][]{
                {"ivelinQA", "Ivelin123", "ivelinQA"},
                {"testAdmin@gmail.com", "Admin1.User1", "AdminUser"},
                {"manager@gmail.com", "Manager1.Use1", "ManagerUser"}};
    }

    @Test(dataProvider = "userNames")
    public void testLogin(String username, String password, String name) {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Assert.assertTrue(homePage.isUrlLoaded(), "The Home page URL is not loaded!");

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login page URL is not loaded!");

        String signInText = loginPage.getSignInElementText();
        Assert.assertEquals(signInText, "Sign in");

        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clickSignInButton();

        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded());
        Assert.assertTrue(profilePage.getProfileTextName(name));
    }
    @AfterMethod
    public void browserClosing() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}