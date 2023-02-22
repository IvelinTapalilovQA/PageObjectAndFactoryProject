package page.factory.mode.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.factory.model.HeaderFactory;
import page.factory.model.HomePageFactory;
import page.factory.model.LoginPageFactory;
import page.factory.model.ProfilePageFactory;

import java.time.Duration;

public class LogoTestFactory {

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
            HomePageFactory homePage = new HomePageFactory(driver);
            homePage.navigateTo();

            Assert.assertTrue(homePage.isUrlLoaded(), "The Home page URL is not loaded!");

            HeaderFactory header = new HeaderFactory(driver);
            Assert.assertTrue(header.getLogo().isDisplayed());

            header.clickLogin();

            LoginPageFactory loginPage = new LoginPageFactory(driver);
            Assert.assertTrue(loginPage.isUrlLoaded());

            Assert.assertTrue(header.getLogo().isDisplayed());

            header.clickableLogo();
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
            HomePageFactory homePage = new HomePageFactory(driver);
            homePage.navigateTo();

            HeaderFactory header = new HeaderFactory(driver);
            header.clickLogin();

            LoginPageFactory loginPage = new LoginPageFactory(driver);
            Assert.assertTrue(loginPage.isUrlLoaded());

            String SignInTextLabel = loginPage.getSignInElementText();

            Assert.assertEquals(SignInTextLabel, "Sign in");

            loginPage.typeUsername(username);
            loginPage.typePassword(password);
            loginPage.clickSignInButton();

            ProfilePageFactory profilePage = new ProfilePageFactory(driver);
            Assert.assertTrue(profilePage.isUrlLoaded());

            header.clickProfile();
            profilePage.getProfileTextName(name);

            Assert.assertTrue(header.getLogo().isDisplayed());

            header.clickNewPost();

            header.getLogo();
            header.clickLogo();

            homePage.isUrlLoaded();

            Assert.assertTrue(header.getLogo().isDisplayed());
        }
        @Test(dataProvider = "userNames")
        public void afterLogoutTest(String username, String password, String name) {
            HomePageFactory homePage = new HomePageFactory(driver);
            homePage.navigateTo();

            HeaderFactory header = new HeaderFactory(driver);
            header.clickLogin();

            LoginPageFactory loginPage = new LoginPageFactory(driver);
            Assert.assertTrue(loginPage.isUrlLoaded());

            String SignInTextLabel = loginPage.getSignInElementText();

            Assert.assertEquals(SignInTextLabel, "Sign in");

            loginPage.typeUsername(username);
            loginPage.typePassword(password);
            loginPage.clickSignInButton();

            ProfilePageFactory profilePage = new ProfilePageFactory(driver);
            Assert.assertTrue(profilePage.isUrlLoaded());

            loginPage.isUrlLoaded();
            header.clickProfile();
            profilePage.getProfileTextName(name);


            Assert.assertTrue(header.getLogo().isDisplayed());

            header.logoutButton();
            loginPage.isUrlLoaded();


            Assert.assertTrue(header.getLogo().isDisplayed());
            header.clickLogo();

            homePage.isUrlLoaded();
            Assert.assertTrue(header.getLogo().isDisplayed());
        }
        @AfterMethod
        public void browserClosing () {
            if (this.driver != null) {
                this.driver.close();
            }
        }
    }
