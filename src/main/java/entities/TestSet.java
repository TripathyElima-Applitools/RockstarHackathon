package entities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import util.PropertyReader;

public class TestSet {

    public RemoteWebDriver driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    public static PropertyReader propertyReader;
    protected static Eyes eyes;

    public LoginPage loginPage;

    public TestSet() {
        propertyReader = new PropertyReader();
    }

    @BeforeClass
    public void setUp() throws Exception{
        // LOGGER.info("SetUp");
        setDriver();
        loginPage = new LoginPage(driver);
        loadUrl();
        initiateEyes();
    }

    private static void initiateEyes()
    {
        eyes = new Eyes();
        eyes.setApiKey(propertyReader.readProperty("applitools.api.key"));
    }

    public void validateWindow()
    {
        eyes.open(driver, "Applitools Hackathon Login Page Tests", Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkWindow();
        eyes.close();
    }

    public void validateElement(By element, String testname)
    {
        eyes.open(driver, "Applitools Hackathon Login Page Tests", testname);
        eyes.checkElement(element);
        eyes.close();
    }

    public static void setBatchName(String batchName)
    {
        eyes.setBatch(new BatchInfo(batchName));
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        eyes.abortIfNotClosed();
    }

    public void setDriver() throws MalformedURLException {
        // Running tests on Selenium GRID
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1600,900");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // Running test cases using Docker container Selenium GRID
      //  driver = new RemoteWebDriver(new URL(propertyReader.readProperty("seleniumHub")), capabilities);
        // Running test cases locally using ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/Users/tripate/Downloads/chromedriver 2");
        driver = new ChromeDriver(options);
        driver.manage().window().fullscreen();

    }

    public void loadUrl(){
        URL baseUrl;
        HttpURLConnection connection = null;
        try {
            baseUrl = new URL(propertyReader.readProperty("url"));
            connection = (HttpURLConnection) baseUrl.openConnection();
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            }
            driver.get(propertyReader.readProperty("url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}