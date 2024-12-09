package ProfilePage;

import PageFactory.Header;
import PageFactory.LoginPage;
import PageFactory.HomePage;
import PageFactory.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProfileTests extends TestObject {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"testtest12@gmail.com", "testtest123", "Password123"},
        };
    }

    @Test(dataProvider = "getUsers")
    public void testLogin(String email, String name, String password) {

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigateTo();


        Header header = new Header(super.getDriver());
        header.clickLogin();


        LoginPage loginPage = new LoginPage(super.getDriver());
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page URL is incorrect!");

        String signInText = loginPage.getSignInElementText();
        Assert.assertEquals(signInText, "Sign in", "Sign in text is not as expected!");
        loginPage.populateUserName(email);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();

        Assert.assertTrue(homePage.isUrlLoaded(), "Home page URL is incorrect!");


        header.clickProfile();
        ProfilePage profilePage = new ProfilePage(super.getDriver());
        Assert.assertTrue(profilePage.isUrlLoaded(), "Profile page URL is incorrect!");


        String actualUserName = profilePage.getUserName();
        Assert.assertEquals(actualUserName, name, "Displayed username is incorrect!");


        profilePage.editProfile("QA Automation");
        profilePage.saveProfile();

        String updatedInfo = profilePage.getPublicInfo();
        Assert.assertEquals(updatedInfo, "QA Automation", "Public info was not updated correctly!");
    }

    @Test(dataProvider = "getUsers")
    public void testViewUserPosts(String email, String name, String password) {
        // Navigate to the home page
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigateTo();


        Header header = new Header(super.getDriver());
        header.clickLogin();
        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.populateUserName(email);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();


        header.clickProfile();
        ProfilePage profilePage = new ProfilePage(super.getDriver());
        Assert.assertTrue(profilePage.isUrlLoaded(), "Profile page URL is incorrect!");


    }

    @Test(dataProvider = "getUsers")
    public void testClearPublicInfo(String email, String name, String password) {
        // Navigate to the home page
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigateTo();

        Header header = new Header(super.getDriver());
        header.clickLogin();
        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.populateUserName(email);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();


        header.clickProfile();
        ProfilePage profilePage = new ProfilePage(super.getDriver());
        Assert.assertTrue(profilePage.isUrlLoaded(), "Profile page URL is incorrect!");

        profilePage.editProfile("");
        profilePage.saveProfile();

        String updatedInfo = profilePage.getPublicInfo();
        Assert.assertEquals(updatedInfo, "", "Public info was not cleared correctly!");
    }
}
