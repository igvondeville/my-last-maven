package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/all";
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }

    public String getUserName() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }
    public By editButton = By.xpath("//button[text()='Edit']");
    public By publicInfoField = By.id("publicInformation");
    public By saveButton = By.xpath("//button[text()='Save']");

    public ProfilePage editProfile(String newPublicInfo) {
        driver.findElement(editButton).click();
        return this;
    }

    public String publicInfoField(By publicInformationDisplay) {
        WebElement publicInfoElement = driver.findElement(publicInfoField);
        publicInfoElement.clear();
        publicInfoElement.sendKeys();
        driver.findElement(saveButton).click();
        return String.valueOf(this);
    }

    public String getPublicInfo() {
        WebElement publicInfoElement = driver.findElement(publicInfoField);
        return publicInfoElement.getText();
    }

    public ProfilePage saveProfile() {
        driver.findElement(saveButton).click();
        return this;
    }
}