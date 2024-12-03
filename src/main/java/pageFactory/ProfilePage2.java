package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage2 {
    private WebDriver driver;

    @FindBy(css = "testtest")
    private WebElement usernameElement;

    public ProfilePage2(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getUsername() {
        return usernameElement.getText();
    }

    public boolean isProfilePageLoaded() {
        return usernameElement.isDisplayed();
    }
}