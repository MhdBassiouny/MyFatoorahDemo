package pages;

import base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Refund extends Page {

    public Refund(WebDriver driver) {
        super(driver);
    }

    private final By byValidationSummaryErrors = By.className("validation-summary-errors");
    private final By byAmountError = By.id("Amount-error");
    private final By byAlert = By.className("alert");
    private final By byAmount = By.id("Amount");

    @FindBy(xpath = "//label/input[@value='False']/following-sibling::span")
    public WebElement sendRefund;

    @FindBy(xpath = "//label/input[@value='True']/following-sibling::span")
    public WebElement deductFromWallet;

    @FindBy(xpath = "//label/input[@name='IsPartialRefund']/following-sibling::span")
    public WebElement isPartialRefund;

    @FindBy(id = "Amount")
    public WebElement refundAmount;

    @FindBy(id = "btn-submit")
    public WebElement refundSubmit;

    @FindBy(className = "validation-summary-errors")
    public WebElement validationErrors;

    @FindBy(id = "Amount-error")
    public WebElement amountError;

    @FindBy(className = "alert")
    public WebElement refundMessage;

    @FindBy(xpath = "//input[@id='IsDeductServiceChargeFromCustomer']/following-sibling::span")
    public WebElement myFatoorahFees;

    @FindBy(id = "customer-recieve")
    public WebElement customerRecieve;

    @FindBy(id = "AccountHolderName")
    public WebElement accountHolderName;

    @FindBy(id = "AccountNumber")
    public WebElement accountNumber;

    public WebElement countryButton(WebDriver driver){
        return driver.findElement(By.cssSelector(".btn-group:nth-child(2) .filter-option"));}

    @FindBy(xpath = "//li[@data-original-index='1']/a[1]")
    public WebElement KuwaitSelection;

    @FindBy(xpath = "//li[@data-original-index='9']/a[1]")
    public WebElement USASelection;

    @FindBy(xpath = "//button[@data-id,'BankId']")
    public WebElement bankButton;

    public WebElement otherBank(WebDriver driver){
        return driver.findElement(By.cssSelector(".input-group:nth-child(2) #BankType"));}

    @FindBy(id = "BankName")
    public WebElement bankName;

    @FindBy(className = "validation-summary-errors")
    public WebElement validationSummaryErrors;


    public void openRefundWithInvoiceReference(String invoiceReference){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'Refund')]", invoiceReference))));
        driver.findElement(By.xpath(String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'Refund')]", invoiceReference))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(byAmount));
    }

    public void sendInitialWireTransferData(){
        accountHolderName.sendKeys("Test");
        accountNumber.sendKeys("123");
        otherBank(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("BankName")));
        bankName.sendKeys("test");
        refundAmount.clear();
    }

    public String getRefundReference(){
        wait.until(ExpectedConditions.presenceOfElementLocated(byAlert));
        return refundMessage.getText().split(" ")[8];
    }

    public String getAmountRefunded(String refundReference){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//td[contains(.,'%s')]/following-sibling::td[6]", refundReference))));
        return driver.findElement(By.xpath(String.format("//td[contains(.,'%s')]/following-sibling::td[6]", refundReference))).getText();
    }

    public String getRefundInvoiceReference(String refundReference){
        String refundInvoiceReference = driver.findElement(By.xpath(String.format("//td[contains(.,'%s')]/following-sibling::td[3]", refundReference))).getText();
        return String.valueOf(Integer.parseInt(refundInvoiceReference) + 1);
    }

    public String getRefundValidationSummaryError(){
        wait.until(ExpectedConditions.presenceOfElementLocated(byValidationSummaryErrors));
        return validationErrors.getText();
    }

    public String getRefundAmountError(){
        wait.until(ExpectedConditions.presenceOfElementLocated(byAmountError));
        return amountError.getText();
    }

}
