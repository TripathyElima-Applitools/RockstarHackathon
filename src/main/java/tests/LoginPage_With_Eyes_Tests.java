package tests;

import com.applitools.eyes.RectangleSize;
import entities.TestSet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.Listeners.TestListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners({ TestListener.class })
public class LoginPage_With_Eyes_Tests extends TestSet {

    @BeforeClass
    public static void startVisualTestSuite()
    {
        eyesManager.setBatchName("Applitools Hackathon Login Page Regression Test cases");
    }

    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {

        return new Object[][] { { "tripathyelima@yahoo.co.in", "", "Password must be present", "Case1" }, { "", "testing@123", "Username must be present",  "Case2" }
                ,{"","", "Both Username and Password must be present", "Case3"}, {"tripathyelima@yahoo.co.i","testing@123", "Your nearest branch closes in: 30m 5s", "Case4"}};
    }

    @Test(priority = 0)
    // Open the login page and write assertions to ensure everything looks OK on that page.
    public void verify_Login_Page_UI_Elements_Are_Present()
    {
        eyesManager.validateWindow("UI elements");
    }


    @Test(priority = 2, dataProvider = "Authentication")
    //Test the login functionality by entering different values to username and password fields
    public void verify_Login_Page_Data_Driven_Cases(String sUsername, String sPassword, String message, String testname) {
        if (sUsername == "" || sPassword == "") {
            loginPage.clear_with_username_and_password();
            loginPage.login_with_username_and_password(sUsername, sPassword);
            eyesManager.validateElement(loginPage.getWarmingError(), testname, null);
            Assert.assertEquals(loginPage.getWarningAlert(), message, "Validation Message is Broken");
        } else {
            loginPage.clear_with_username_and_password();
            loginPage.login_with_username_and_password(sUsername, sPassword);
            eyesManager.validateElement(loginPage.getLoginSuccessMessage(), testname, new RectangleSize(1022, 673));
            Assert.assertEquals(loginPage.getACMEPageTitle(), message, "User is unable to login");
        }
    }

    @Test(priority = 2)
    //Verify after click, amount column is in ascending order and that each row’s data stayed in tact after the sorting.
    public void verify_Table_Sorting_Works() throws Exception
    {
        acmePage.clickOnAmountColumn();
        List<Integer> obtainedList = acmePage.getAllAmountsAfterClick();
        List<Integer> sortedList = new ArrayList<>();
        for(Integer f:obtainedList){
            sortedList.add(f);
        }
        Collections.sort(sortedList);
        System.out.println("Sorted List is" + sortedList);
        eyesManager.validateWindow("Sorted");
        Assert.assertTrue(sortedList.equals(obtainedList), "Amount column didn't sort after click");
    }

}
