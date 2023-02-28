package page.object.model.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TestObject {
    public static final String DOWNLOAD_DIR = "src\\main\\java\\TestResources\\downloads\\";
    public static final String SCREENSHOTS_DIR = "src\\main\\java\\TestResources\\screenshots\\";

    private WebDriver driver;

    @BeforeSuite
    public void setUpSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUpTest() {
        this.driver = new ChromeDriver(configChromeOptions());
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void quitDriver(ITestResult testResult) {
        takeScreenshot(testResult);
        this.driver.quit();
        }
    @AfterSuite
    public void deleteDownloadFiles() throws IOException {
        File downloadDirectory = new File(DOWNLOAD_DIR);
        Assert.assertTrue(downloadDirectory.isDirectory());

        FileUtils.cleanDirectory(downloadDirectory);
        String[] fileList = downloadDirectory.list();
        if (fileList != null && fileList.length == 0){
            System.out.println("Download files are deleted!");
        }
        else
            System.out.println("Download files are NOT deleted!");
    }
    private void takeScreenshot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            } catch (IOException e) {
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }




    public WebDriver getDriver(){
        return driver;
    }
    private ChromeOptions configChromeOptions(){
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("user.dir") + ("\\") + (DOWNLOAD_DIR));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;
    }
}