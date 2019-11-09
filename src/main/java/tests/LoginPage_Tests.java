package tests;

import entities.TestSet;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.Listeners.TestListener;

import static org.junit.jupiter.api.Assertions.assertAll;

@Listeners({ TestListener.class })
public class LoginPage_Tests extends TestSet {

    @Test
    public void verify_LoginPage_Loads(){
        Assert.assertEquals(loginPage.getLoginPageTitle(), "Login Form", "Login Page title is broken");
    }

    @Test
    public void verify_Password_Is_Mandatory()
    {
        loginPage.login_without_password("tripathyelima@yahoo.co.in");
        Assert.assertEquals(loginPage.getLoginPageAlert(), "Password must be present", "Password Validation Message is Broken");
    }

    @Test
    public void verify_Username_Is_Mandatory()
    {
        loginPage.login_without_username("testing");
        Assert.assertEquals(loginPage.getLoginPageAlert(), "Username must be present", "Username Validation Message is Broken");
    }

    @Test
    public void verify_Both_Username_Password_Are_Mandatory()
    {
        loginPage.login_without_username_and_password();
        Assert.assertEquals(loginPage.getLoginPageAlert(), "Both Username and Password must be present", "Validation Message is Broken");
    }

    @Test
    public void verify_Use_Is_able_To_Login()
    {
        loginPage.login_with_username_and_password("tripathyelima@yahoo.co.in", "testing");
        Assert.assertEquals(loginPage.getACMEPageTitle(), "Your nearest branch closes in: 30m 5s", "User is unable to login");
    }

    @Test
    // Login page Header , Username, FingerPrint, RememberMe Icons, Twitter, Facebook, Linkedin Icons are present
    public void verify_Login_Page_Icons_Are_Present()
    {
        assertAll(
                () -> Assert.assertEquals( loginPage.verifyLoginPageHeaderLogoExists(), true,"Header Icon is missing"),
                () -> Assert.assertEquals( loginPage.verifyLoginPageUserNameLogoExists(), true, "Username Logo is missing"),
                () -> Assert.assertEquals( loginPage.verifyLoginPageFingerPrintLogoExists(), true, "FingerPrint Logo is missing"),
                () -> Assert.assertEquals( loginPage.verifyLoginPageRememberMeIconExists(), true,"Remember Me Icon is missing"),
                () -> Assert.assertTrue(driver.findElements(By.tagName("img")).get(0).getAttribute("src").contains("img/social-icons/twitter.png"), "Twitter Icon is missing"),
                () -> Assert.assertTrue(driver.findElements(By.tagName("img")).get(1).getAttribute("src").contains("img/social-icons/facebook.png"), "Facebook Icon is missing"),
                () -> Assert.assertTrue(driver.findElements(By.tagName("img")).get(2).getAttribute("src").contains("img/social-icons/linkedin.png"), "Linkedin Icon is missing")
        );
    }

}
