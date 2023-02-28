package page.object.model.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class ConfigObject {
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

        @AfterMethod
        public void quitDriver() {
            this.driver.quit();
        }

        public WebDriver getDriver(){
        return driver;
    }
}
