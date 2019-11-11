package entities;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import pages.BasePage;

import static entities.TestSet.propertyReader;

public class EyesManager extends BasePage {

    private static Eyes eyes;
    private RemoteWebDriver driver;
    private String appName;

    public EyesManager(RemoteWebDriver driver, String appName) {
        this.driver = driver;
        this.appName = appName;
        eyes = new Eyes();
        eyes.setApiKey(propertyReader.readProperty("applitools.api.key"));
        eyes.setForceFullPageScreenshot(true);
    }

    public void validateWindow(String testname)
    {
        eyes.open(driver, "Applitools Hackathon Login Page Tests",  testname + " " + Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkWindow();
        eyes.close();
    }

    public void validateElement(By element, String testname, RectangleSize viewPort)
    {
        eyes.open(driver, "Applitools Hackathon Login Page Tests", testname + " " + Thread.currentThread().getStackTrace()[2].getMethodName(), viewPort);
        eyes.checkElement(element);
        eyes.close();
    }

    public static void setBatchName(String batchName)
    {
        eyes.setBatch(new BatchInfo(batchName));
    }

    @AfterClass
    public void tearDown(){
        eyes.abortIfNotClosed();
    }

}
