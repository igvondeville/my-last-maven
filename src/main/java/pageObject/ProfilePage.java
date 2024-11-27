package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    private WebDriver driver;

    @FindBy(css = "h2.user-name") // Replace with the correct locator for username display on the profile page
    private WebElement displayedUsername;

    @FindBy(xpath = "//button[contains(text(),'Edit Profile')]")
    private WebElement editProfileButton;

    @FindBy(xpath = "//button[contains(text(),'Log Out')]")
    private WebElement logOutButton;

    // Constructor to initialize elements
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDisplayedUsername() {
        return displayedUsername.getText();
    }


    public void clickEditProfile() {
        editProfileButton.click();
    }

    public void clickLogout() {
        logOutButton.click();
    }

    public boolean isProfilePageLoaded() {
        return displayedUsername.isDisplayed();
    }
}
