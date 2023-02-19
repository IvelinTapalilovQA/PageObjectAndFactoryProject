package pageObjectModelTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjectModel.*;

import java.time.Duration;

public class LogoTest {

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

    @Test
    public void testLogo() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Assert.assertTrue(homePage.isUrlLoaded(), "The Home page URL is not loaded!");

        LogoPage logo = new LogoPage(driver);
        Assert.assertTrue(logo.getLogo().isDisplayed());

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded());

        Assert.assertTrue(logo.getLogo().isDisplayed());

        logo.clickableLogo();
        header.clickLogo();

        Assert.assertTrue(homePage.isUrlLoaded());
    }

    @DataProvider(name = "userNames")
    public Object[][] userName() {
        return new Object[][]{
                {"ivelinQA", "Ivelin123", "ivelinQA"}};
    }
    @Test(dataProvider = "userNames")
    public void testLogoLogin(String username, String password, String name) {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded());

        String SignInTextLabel = loginPage.getSignInElementText();

        Assert.assertEquals(SignInTextLabel, "Sign in");

        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clickSignInButton();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded());

        header.clickProfile();
        profilePage.getProfileTextName(name);

        LogoPage logo = new LogoPage(driver);
        Assert.assertTrue(logo.getLogo().isDisplayed());

        header.clickNewPost();

        logo.clickableLogo();
        header.clickLogo();

        homePage.isUrlLoaded();

        Assert.assertTrue(logo.getLogo().isDisplayed());
    }
        @Test(dataProvider = "userNames")
        public void afterLogoutTest(String username, String password, String name) {
            HomePage homePage = new HomePage(driver);
            homePage.navigateTo();

            Header header = new Header(driver);
            header.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            Assert.assertTrue(loginPage.isUrlLoaded());

            String SignInTextLabel = loginPage.getSignInElementText();

            Assert.assertEquals(SignInTextLabel, "Sign in");

            loginPage.typeUsername(username);
            loginPage.typePassword(password);
            loginPage.clickSignInButton();

            ProfilePage profilePage = new ProfilePage(driver);
            Assert.assertTrue(profilePage.isUrlLoaded());

            loginPage.isUrlLoaded();
            header.clickProfile();
            profilePage.getProfileTextName(name);

            LogoPage logo = new LogoPage(driver);
            Assert.assertTrue(logo.getLogo().isDisplayed());

            header.logoutButton();
            loginPage.isUrlLoaded();


            Assert.assertTrue(logo.getLogo().isDisplayed());
            header.clickLogo();

            homePage.isUrlLoaded();
            Assert.assertTrue(logo.getLogo().isDisplayed());
        }
        @AfterMethod
        public void browserClosing () {
            if (this.driver != null) {
                this.driver.close();
            }
        }
    }