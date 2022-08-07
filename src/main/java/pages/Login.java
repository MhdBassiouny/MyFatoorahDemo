package pages;

import base.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends Page {

    public Login(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "switch-lang")
    public WebElement switchLang;

    @FindBy(id="UserName")
    public WebElement userName;

    @FindBy(id="Password")
    public WebElement password;

    @FindBy(className = "user-signup-link")
    public WebElement register;

    @FindBy(className = "forgot-password-link")
    public WebElement forgetPassword;

    @FindBy(id = "Email")
    public WebElement emailForgetPassword;

    @FindBy(className = "alert")
    public WebElement forgetPasswordMessage;

    @FindBy(className = "validation-summary-errors")
    public WebElement loginMessage;

    @FindBy(className = "alert")
    public WebElement resetPassMessage;


    public void loginData(String email, String pass) {
        userName.sendKeys(email);
        password.sendKeys(pass);
        password.sendKeys(Keys.ENTER);
    }


}
