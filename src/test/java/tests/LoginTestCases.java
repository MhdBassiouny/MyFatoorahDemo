package tests;


import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestCases extends BaseTest {

    public LoginTestCases() {
        super("Chrome", "1");
    }

    @BeforeMethod
    public void beforeMethods() {
        driver.navigate().to(inputs.getLogin());
    }

    @Test
    public void loginSuccessfully() {
        login.userName.sendKeys(inputs.getCorrectEmail());
        login.password.sendKeys(inputs.getCorrectPassword());
        login.password.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("header-title")));
        String actualResult = driver.findElement(By.className("header-title")).getText();
        String expectedResult = "Dashboard";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void loginError() {
        login.userName.sendKeys(inputs.getIncorrectEmail());
        login.password.sendKeys(inputs.getIncorrectPassword());
        login.password.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("validation-summary-errors")));
        String actualResult = login.loginMessage.getText();
        String expectedResult = "Invalid";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void forgetPassword() {
        login.forgetPassword.click();
        login.emailForgetPassword.sendKeys(inputs.getCorrectEmail());
        login.emailForgetPassword.sendKeys(Keys.ENTER);

        String actualResult = login.resetPassMessage.getText();
        String expectedResult = "Your request has been received";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void register() {
        login.register.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Kuwait")));
        register.KuwaitAccount.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Home Business")));
        register.homeBusiness.click();
        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("/html/body/app/form/section/div/div/div[3]/div/div[1]/div[1]/div/input")));
        register.homeBusinessNameEN.sendKeys(inputs.getBusinessName());
        register.homeBusinessNameAR.sendKeys(inputs.getBusinessName());
        register.phoneNumber.sendKeys("12345678");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("categories")));
        register.categories.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value = '12']")));
        driver.findElement(By.xpath("//option[@value = '12']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("next-btn")));
        register.nextButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("next-btn")));
        register.nextButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("/html/body/app/form/section/div/div/div[3]/div/div[1]/div[1]/div/input")));
        register.fullName.sendKeys("Testing");
        register.registerEmail.sendKeys(inputs.getRegisterEmail());
        register.phoneNumber.sendKeys("123456789");
        register.registerPassword.sendKeys("A@qw1234");
        register.registerConfirmPassword.sendKeys("A@qw1234");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("next-btn")));
        register.nextButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("terms-chkBox")));
        register.agreeTerms.click();
        register.finishButton.click();

        String actualResult = register.finishButton.getText();
        String expectedResult = "Finish";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

}
