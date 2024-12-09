package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/login";
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL));
    }

    public String getSignInElementText(){
        WebElement signInTitle = driver.findElement(By.className("h4"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signInTitle));
        return signInTitle.getText();
    }

    public void populateUserName(String username) {
        WebElement userNameInput = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameInput.sendKeys(username);
    }

    public void populatePassword(String password){
        WebElement passwordInput = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordInput.sendKeys(password);
    }
    public void clickSignIn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();
    }
}