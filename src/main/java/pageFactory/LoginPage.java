package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/login";
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(className = "h4")
    private WebElement signInTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameInput;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInput;
    @FindBy(id = "sign-in-button")
    private WebElement signInButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded() {
        return wait.until(ExpectedConditions.urlToBe(LoginPage.PAGE_URL));
    }

    public String getSignInElementText() {
        wait.until(ExpectedConditions.visibilityOf(signInTitle));
        return signInTitle.getText();
    }

    public void populateUserName(String username) {
        usernameInput.sendKeys(username);
    }

    public void populatePassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }
}