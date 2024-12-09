package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/all";
    private final WebDriver driver;
    @FindBy(tagName = "h2")
    private WebElement username;

    @FindBy(xpath = "//button[text()='Edit']")
    private WebElement editButton;

    @FindBy(id = "publicInformation")
    private WebElement publicInfoField;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButton;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(ProfilePage.PAGE_URL));
    }

    public String getUserName() {
        return username.getText();
    }

    public ProfilePage editProfile(String newPublicInfo) {
        editButton.click();
        publicInfoField.clear();
        publicInfoField.sendKeys(newPublicInfo);
        saveButton.click();
        return this;
    }

    public String getPublicInfo() {
        return publicInfoField.getText();
    }

    public ProfilePage saveProfile() {
        saveButton.click();
        return this;
    }
}