package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(className = "auth-header")
    private WebElement LoginPage_Title = null;

    @FindBy(id = "username")
    private WebElement LoginPage_Username = null;

    @FindBy(id = "password")
    private WebElement LoginPage_Password = null;

    @FindBy(id = "log-in")
    private WebElement LoginPage_Submit = null;

    @FindBy(className = "alert-warning")
    private WebElement LoginPage_Alert = null;

    @FindBy(className = "logo-w")
    private WebElement LoginPage_Header_Logo = null;

    @FindBy(className = "os-icon-user-male-circle")
    private WebElement LoginPage_Username_Logo = null;

    @FindBy(className = "os-icon-fingerprint")
    private WebElement LoginPage_FingerPrint_Logo = null;

    @FindBy(className = "form-check-inline")
    private WebElement LoginPage_RememberMe = null;

    @FindBy(id = "time")
    private WebElement ACME_Page = null;

    public LoginPage(WebDriver webDriver) {
        this.Driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void login_with_username_and_password(String username, String password){
        LoginPage_Username.sendKeys(username);
        LoginPage_Password.sendKeys(password);
        LoginPage_Submit.click();
    }

    public void clear_with_username_and_password(){
        LoginPage_Username.clear();
        LoginPage_Password.clear();
    }
    public By getWarmingError()
    {
        return By.className("alert-warning");
    }

    public By getLoginSuccessMessage()
    {
        return By.id("time");
    }

    public String getLoginPageTitle()
    {
        return LoginPage_Title.getText();
    }

    public String getWarningAlert()
    {
        return LoginPage_Alert.getText();
    }

    public boolean verifyLoginPageHeaderLogoExists()
    {
        return LoginPage_Header_Logo.isDisplayed();
    }

    public boolean verifyLoginPageUserNameLogoExists()
    {
        return LoginPage_Username_Logo.isDisplayed();
    }

    public boolean verifyLoginPageFingerPrintLogoExists()
    {
        return LoginPage_FingerPrint_Logo.isDisplayed();
    }

    public boolean verifyLoginPageRememberMeIconExists()
    {
        return LoginPage_RememberMe.isDisplayed();
    }

    public String getACMEPageTitle(){return ACME_Page.getText();}
}
