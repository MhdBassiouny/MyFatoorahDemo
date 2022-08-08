package pages;

import base.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Register extends Page {
    public Register (WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Kuwait")
    public WebElement KuwaitAccount;

    @FindBy(linkText = "Home Business")
    public WebElement homeBusiness;

    @FindBy(xpath = "/html/body/app/form/section/div/div/div[3]/div/div[1]/div[1]/div/input")
    public WebElement homeBusinessNameEN;

    @FindBy(xpath = "/html/body/app/form/section/div/div/div[3]/div/div[1]/div[2]/div/input")
    public WebElement homeBusinessNameAR;

    @FindBy(id = "phone")
    public WebElement phoneNumber;

    @FindBy(id = "categories")
    public WebElement categories;

    @FindBy(className = "next-btn")
    public WebElement nextButton;

    @FindBy(xpath = "/html/body/app/form/section/div/div/div[3]/div/div[1]/div[1]/div/input")
    public WebElement fullName;

    @FindBy(xpath = "/html/body/app/form/section/div/div/div[3]/div/div[1]/div[2]/div/input")
    public WebElement registerEmail;

    @FindBy(xpath = "/html/body/app/form/section/div/div/div[3]/div/div[1]/div[4]/div/input")
    public WebElement registerPassword;

    @FindBy(xpath = "/html/body/app/form/section/div/div/div[3]/div/div[1]/div[5]/div/input")
    public WebElement registerConfirmPassword;

    @FindBy(id = "terms-chkBox")
    public WebElement agreeTerms;

    @FindBy(id = "rc-anchor-container")
    public WebElement recaptchaCheckbox;

    @FindBy(id = "neb")
    public WebElement finishButton;

    @FindBy(className = "page-alldone-right-inner")
    public WebElement finalMessage;

    @FindBy()
    public WebElement validationMessage;

}
