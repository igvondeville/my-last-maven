package ProfilePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public static final String TEST_RESOURCES_DIR = "src/test/resources/";
    public static final String DOWNLOAD_DIR = TEST_RESOURCES_DIR.concat("download/");
    public static final String SCREENSHOTS_DIR = TEST_RESOURCES_DIR.concat("screenshots/");
    public static final String REPORTS_DIR = TEST_RESOURCES_DIR.concat("reports/");
    protected WebDriver driver;

    @BeforeSuite
    protected void setupTestSuite() throws IOException {
        WebDriverManager.chromedriver().setup();
        createDirectory(DOWNLOAD_DIR);
        createDirectory(SCREENSHOTS_DIR);
        createDirectory(REPORTS_DIR);
        System.out.println("Test suite setup completed.");
    }

    @BeforeMethod
    protected void setUpTest() {
        this.driver = new ChromeDriver(configChromeOptions());
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("WebDriver initialized and ready.");
    }

    @AfterMethod
    protected void tearDownTest(ITestResult testResult) {
        takeScreenshot(testResult);
        quitDriver();
    }

    @AfterSuite
    protected void deleteDownloadedFiles() throws IOException {
        cleanDirectory(DOWNLOAD_DIR);
    }

    private void createDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
        }
    }

    private void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        Assert.assertTrue(directory.isDirectory(), "Invalid directory: " + directoryPath);
        FileUtils.cleanDirectory(directory);
        System.out.println("Directory cleaned: " + directoryPath);
    }

    private void takeScreenshot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
                System.out.println("Screenshot saved for failed test: " + testName);
            } catch (IOException e) {
                System.err.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }

    private void quitDriver() {
        if (this.driver != null) {
            this.driver.quit();
            System.out.println("WebDriver closed successfully.");
        }
    }

    protected WebDriver getDriver() {
        return this.driver;
    }

    private ChromeOptions configChromeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("user.dir") + "/" + DOWNLOAD_DIR);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;
    }


    protected void verifyElementDisplayed(WebElement element, String elementDescription) {
        Assert.assertTrue(element.isDisplayed(), elementDescription + " is not displayed!");
        System.out.println(elementDescription + " is displayed correctly.");
    }

    protected void clickElement(WebElement element, String elementDescription) {
        Assert.assertTrue(element.isDisplayed(), elementDescription + " is not clickable as it's not displayed!");
        element.click();
        System.out.println(elementDescription + " was clicked successfully.");
    }


    protected void logTestStep(String message) {
        System.out.println("TEST STEP: " + message);
    }
}
