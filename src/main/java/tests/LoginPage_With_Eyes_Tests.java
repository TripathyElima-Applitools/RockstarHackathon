package tests;

import entities.TestSet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.Listeners.TestListener;

import static org.junit.jupiter.api.Assertions.assertAll;

@Listeners({ TestListener.class })
public class LoginPage_With_Eyes_Tests extends TestSet {

    @BeforeClass
    public static void startVisualTestSuite()
    {
        setBatchName("Applitools Hackathon Login Page Regression Test cases");
    }

    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {

        return new Object[][] { { "tripathyelima@yahoo.co.in", "", "Password must be present", "Case1" }, { "", "testing@123", "Username must be present",  "Case2" }
                ,{"","", "Both Username and Password must be present", "Case3"}};
    }

    @Test(priority = 0)
    // Open the login page and write assertions to ensure everything looks OK on that page.
    public void verify_Login_Page_UI_Elements_Are_Present()
    {
        validateWindow();
    }


    @Test(priority = 2, dataProvider = "Authentication")
    public void verify_Login_Page_Data_Driven_Cases(String sUsername, String sPassword, String message, String testname)
    {
            loginPage.clear_with_username_and_password();
            loginPage.login_with_username_and_password(sUsername, sPassword);
            Assert.assertEquals(loginPage.getLoginPageAlert(), message, "Validation Message is Broken");
            validateElement(loginPage.getWarmingError(), testname);
    }

}
