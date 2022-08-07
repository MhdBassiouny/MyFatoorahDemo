package tests;

import base.BaseTestChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestCases extends BaseTestChrome {

    @BeforeMethod
    public void beforeMethods() throws InterruptedException {
        driver.navigate().to(inputs.getLogin());
        Thread.sleep(inputs.getSleepTime());
    }

    @Test
    public void loginSuccessfully() throws InterruptedException {
        login.userName.sendKeys(inputs.getCorrectEmail());
        login.password.sendKeys(inputs.getCorrectPassword());
        login.password.sendKeys(Keys.ENTER);
        Thread.sleep(inputs.getSleepTime());

        String actualResult = driver.findElement(By.className("header-title")).getText();
        String expectedResult = "Dashboard";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void loginError() throws InterruptedException {
        login.userName.sendKeys(inputs.getIncorrectEmail());
        login.password.sendKeys(inputs.getIncorrectPassword());
        login.password.sendKeys(Keys.ENTER);
        Thread.sleep(inputs.getSleepTime());

        String actualResult = login.loginMessage.getText();
        String expectedResult = "Invalid";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void forgetPassword() throws InterruptedException {
        login.forgetPassword.click();
        Thread.sleep(inputs.getSleepTime());
        login.emailForgetPassword.sendKeys(inputs.getCorrectEmail());
        login.emailForgetPassword.sendKeys(Keys.ENTER);

        String actualResult = login.resetPassMessage.getText();
        String expectedResult = "Your request has been received";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void register() throws InterruptedException {
        login.register.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Kuwait")));
        register.KuwaitAccount.click();
        Thread.sleep(inputs.getSleepTime() * 2);
        register.homeBusiness.click();
        Thread.sleep(inputs.getSleepTime() * 2);
        register.homeBusinessNameEN.sendKeys(inputs.getBusinessName());
        register.homeBusinessNameAR.sendKeys(inputs.getBusinessName());
        register.phoneNumber.sendKeys("12345678");
        Select categorySelection = new Select(register.categories);
        Thread.sleep(inputs.getSleepTime());
        categorySelection.selectByVisibleText("Airlines");
        Thread.sleep(inputs.getSleepTime() / 2);
        register.nextButton.click();
        Thread.sleep(inputs.getSleepTime());
        register.nextButton.click();
        Thread.sleep(inputs.getSleepTime());
        register.fullName.sendKeys("Testing");
        register.registerEmail.sendKeys(inputs.getRegisterEmail());
        register.phoneNumber.sendKeys("123456789");
        register.registerPassword.sendKeys("A@qw1234");
        register.registerConfirmPassword.sendKeys("A@qw1234");
        register.nextButton.click();
        Thread.sleep(inputs.getSleepTime() * 2);
        register.agreeTerms.click();
        Thread.sleep(inputs.getSleepTime() / 2);
        register.finishButton.click();

        String actualResult = register.finishButton.getText();
        String expectedResult = "Finish";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

}
