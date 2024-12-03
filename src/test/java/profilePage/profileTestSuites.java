package profilePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class profileTestSuites {

    WebDriver driver;

    @BeforeSuite
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update with the actual path
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://training.skillo-bg.com:4300/posts/all");
    }

    @BeforeMethod
    public void navigateToProfilePage() {
        try {
            driver.findElement(By.id("nav-link-login")).click();
            driver.findElement(By.id("defaultLoginFormUsername")).sendKeys("testuser");
            driver.findElement(By.id("defaultLoginFormPassword")).sendKeys("password123");
            driver.findElement(By.id("sign-in-button")).click();
            driver.findElement(By.cssSelector("a[href='/profile']")).click();
        } catch (Exception e) {
            Assert.fail("Failed to navigate to Profile Page: " + e.getMessage());
        }
    }

    @Test
    public void testProfilePageLoads() {
        WebElement profileHeader = driver.findElement(By.tagName("h2"));
        Assert.assertEquals(profileHeader.getText().trim(), "Profile Page", "Profile page did not load correctly.");
    }

    @Test
    public void testEditProfileButton() {
        WebElement editButton = driver.findElement(By.id("edit-profile-btn"));
        Assert.assertTrue(editButton.isDisplayed(), "Edit Profile button is not visible.");
    }

    @Test
    public void testPostCountDisplayed() {
        WebElement postCount = driver.findElement(By.id("post-count"));
        Assert.assertTrue(postCount.isDisplayed(), "Post count is not displayed.");
        try {
            Assert.assertTrue(Integer.parseInt(postCount.getText()) >= 0, "Post count is invalid.");
        } catch (NumberFormatException e) {
            Assert.fail("Post count is not a valid number.");
        }
    }

    @Test
    public void testProfilePictureIsVisible() {
        WebElement profilePicture = driver.findElement(By.id("profile-pic"));
        Assert.assertTrue(profilePicture.isDisplayed(), "Profile picture is not visible.");
    }

    @Test
    public void testLogoutButtonWorks() {
        WebElement logoutButton = driver.findElement(By.id("logout-btn"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible.");
        logoutButton.click();
        Assert.assertTrue(driver.findElement(By.id("nav-link-login")).isDisplayed(), "Logout did not redirect to login page.");
    }

    @Test
    public void testFollowedUsersCount() {
        WebElement followedUsers = driver.findElement(By.id("followed-count"));
        Assert.assertTrue(followedUsers.isDisplayed(), "Followed users count is not visible.");
        try {
            Assert.assertTrue(Integer.parseInt(followedUsers.getText()) >= 0, "Followed users count is invalid.");
        } catch (NumberFormatException e) {
            Assert.fail("Followed users count is not a valid number.");
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
