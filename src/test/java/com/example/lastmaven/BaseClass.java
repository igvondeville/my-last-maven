package com.example.lastmaven;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseClass {

    protected WebDriver driver;

    @BeforeSuite
    public void setupTestSuite() {
        // Setup WebDriver for Chrome browser
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUpTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDownTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"igvondeville", "testtest1", "testtest"},       // Login with username
                {"testtest1@gmail.com", "testtest1234", "testtest@gmail.com"} // Login with email
        };
    }

}

