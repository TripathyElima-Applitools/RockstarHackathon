package entities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.ACMEPage;
import pages.LoginPage;
import util.PropertyReader;

public class TestSet {

    public RemoteWebDriver driver;
    public DesiredCapabilities capabilities = new DesiredCapabilities();
    public static PropertyReader propertyReader;
    public LoginPage loginPage;
    public ACMEPage acmePage;
    protected static EyesManager eyesManager;

    public TestSet() {
        propertyReader = new PropertyReader();
    }

    @BeforeClass
    public void setUp() throws Exception{
        // LOGGER.info("SetUp");
        setDriver();
        loginPage = new LoginPage(driver);
        acmePage = new ACMEPage(driver);
        loadUrl();
        eyesManager = new EyesManager(driver, "Applitools Hackathon Login Page Tests");
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }

    public void setDriver() throws MalformedURLException {
        // Running tests on Selenium GRID
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1600,900");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // Running test cases using Docker container Selenium GRID
        driver = new RemoteWebDriver(new URL(propertyReader.readProperty("seleniumHub")), capabilities);
        // Running test cases locally using ChromeDriver
//        System.setProperty("webdriver.chrome.driver", "/Users/tripate/Downloads/chromedriver 2");
//        driver = new ChromeDriver(options);
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