package tests;

import entities.TestSet;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.Listeners.TestListener;

import static org.junit.jupiter.api.Assertions.assertAll;

@Listeners({ TestListener.class })
public class LoginPage_Tests extends TestSet {

    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {

        return new Object[][] { { "tripathyelima@yahoo.co.in", "", "Password must be present" }, { "", "testing@123", "Username must be present" }
        ,{"","", "Both Username and Password must be present"}};
    }

    @Test(priority = 0)
    // Open the login page and write assertions to ensure everything looks OK on that page.
    public void verify_Login_Page_UI_Elements_Are_Present()
    {
        assertAll(
                // Login pageTitle,  Header Logo , Username Icon, FingerPrint Icon , RememberMe Icon are present
                () -> Assert.assertEquals(loginPage.getLoginPageTitle(), "Login Form", "Login Page title is broken"),
                () -> Assert.assertEquals( loginPage.verifyLoginPageRememberMeIconExists(), true,"Remember Me Icon is missing"),
                () -> Assert.assertEquals( loginPage.verifyLoginPageHeaderLogoExists(), true,"Header Icon is missing"),
                () -> Assert.assertEquals( loginPage.verifyLoginPageUserNameLogoExists(), true, "Username Logo is missing"),
                () -> Assert.assertEquals( loginPage.verifyLoginPageFingerPrintLogoExists(), true, "FingerPrint Logo is missing")
        );
        // Unable to verify Twitter, Facebook, Linkedin icons as there is no way to uniquely identify the elements
    }

    @Test(priority = 2, dataProvider = "Authentication")
    //Test the login functionality by entering different values to username and password fields
    public void verify_Login_Page_Data_Driven_Cases(String sUsername, String sPassword, String message)
    {
            loginPage.clear_with_username_and_password();
            loginPage.login_with_username_and_password(sUsername, sPassword);
            Assert.assertEquals(loginPage.getLoginPageAlert(), message, "Validation Message is Broken");
    }
}
