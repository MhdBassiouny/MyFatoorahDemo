package pages;

import base.Page;
import com.google.common.base.CaseFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Invoice extends Page {

    public Invoice(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "CustomerName")
    public WebElement customerName;

    @FindBy(xpath = "//button[@data-id='SendInvoiceOption']")
    public WebElement sendInvoiceOptionButton;
    @FindBy(linkText = "Link")
    public WebElement linkSelection;
    @FindBy(linkText = "SMS and Email")
    public WebElement SMSandEmailSelection;
    @FindBy(linkText = "SMS")
    public WebElement SMSSelection;
    @FindBy(linkText = "Email")
    public WebElement emailSelection;

    @FindBy(id = "CustomerReference")
    public WebElement customerReference;

    @FindBy(xpath = "//button[@data-id='DisplayCurrencyId']")
    public WebElement displayCurrencyButton;
    @FindBy(linkText = "Kuwait (KD)")
    public WebElement KDCurrency;
    @FindBy(linkText = "Saudi Arabia (SR)")
    public WebElement SRCurrency;
    @FindBy(linkText = "Bahrain (BD)")
    public WebElement BDCurrency;
    @FindBy(linkText = "UAE (AED)")
    public WebElement AEDCurrency;
    @FindBy(linkText = "Qatar (QR)")
    public WebElement QRCurrency;
    @FindBy(linkText = "Jordan (JD)")
    public WebElement JDCurrency;
    @FindBy(linkText = "USA (USD)")
    public WebElement USDCurrency;
    @FindBy(linkText = "Europe (EUR)")
    public WebElement EURCurrency;

    @FindBy(id = "CustomerMobile")
    public WebElement customerMobile;
    @FindBy(id = "CustomerEmail")
    public WebElement customerEmail;
    @FindBy(id = "InvoiceValue")
    public WebElement invoiceValue;
    @FindBy(xpath = "//input[@name = 'Language' and @value = '1']/following-sibling::span")
    public WebElement arabicLanguage;

    // Error Messages
    @FindBy(id = "CustomerName-error")
    public WebElement customerNameError;
    @FindBy(id = "CustomerMobile-error")
    public WebElement customerMobileError;
    @FindBy(id = "CustomerEmail-error")
    public WebElement customerEmailError;
    @FindBy(id = "CustomerReference-error")
    public WebElement customerReferenceError;
    @FindBy(id = "InvoiceValue-error")
    public WebElement invoiceValueError;

    @FindBy(id = "btn-submit")
    public WebElement createInvoice;
    @FindBy(className = "alert")
    public WebElement alertMessage;

    @FindBy(className = "accordion-toggle")
    public WebElement moreOptions;
    @FindBy(id = "InvoiceItemsCreate_0__ProductName")
    public WebElement product0;
    @FindBy(id = "InvoiceItemsCreate_0__Quantity")
    public WebElement quantity0;
    @FindBy(id = "InvoiceItemsCreate_0__UnitPrice")
    public WebElement price0;

    @FindBy(xpath = "//button[@onclick = 'addNewProduct()']")
    public WebElement addProduct;

    @FindBy(id = "InvoiceItemsCreate_1__ProductName")
    public WebElement product1;
    @FindBy(id = "InvoiceItemsCreate_1__Quantity")
    public WebElement quantity1;
    @FindBy(id = "InvoiceItemsCreate_1__UnitPrice")
    public WebElement price1;

    @FindBy(id = "InvoiceItemsCreate_1__ProductName")
    public WebElement product2;
    @FindBy(id = "InvoiceItemsCreate_2__Quantity")
    public WebElement quantity2;
    @FindBy(id = "InvoiceItemsCreate_2__UnitPrice")
    public WebElement price2;

    @FindBy(xpath = "//*[@id=\"notOpenInvoice\"]/div/div[1]/label[2]/span")
    public WebElement rateDiscount;
    @FindBy(id = "DiscountValue")
    public WebElement discountValue;
    @FindBy(xpath = "//*[@id=\"grid-view-invoice-items\"]/tfoot/tr/td[5]")
    public WebElement invoiceLocalValue;
    @FindBy(xpath = "//*[@id=\"collapseOne\"]/div/div[6]/div/label[1]/span")
    public WebElement openAmount;

    @FindBy(id = "MinimumAmount")
    public WebElement minimumAmount;
    @FindBy(id = "MaximumAmount")
    public WebElement maximumAmount;
    @FindBy(id = "EnterInvoiceValue")
    public WebElement enterInvoiceValue;

    @FindBy(xpath = "//a[@target='_blank']")
    public WebElement invoiceLink;

    public By xpathInvoiceValue = By.xpath("//label[@for='InvoiceValue']/following-sibling::div");
    public By xpathDisplayValue = By.xpath("//label[@for='InvoiceDisplayValue']/following-sibling::div");
    public By xpathInvoiceStatus = By.xpath("//label[@for='InvoiceStatus']/following-sibling::div");
    public By classDisplayAmountInvoicePage = By.className("invoiceAmountWithDisplayCurrency");
    public By xpathSendInvoiceOption = By.xpath("//button[@data-id='SendInvoiceOption']");
    public By xpathDisplayCurrencyOption = By.xpath("//button[@data-id='DisplayCurrencyId']");
    public By classAlert = By.className("alert");
    public By idEnterInvoiceValue = By.id("EnterInvoiceValue");

    private final By xpathEmbeddedPayButton = By.xpath("//button[@onclick='cardViewPay()']");
    private final By xpathEmbeddedConfirmButton = By.xpath("//input[@type='submit']");


    public void payWithKNET() {
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("payment-form")));
            driver.findElement(By.xpath("//*[@paymentgatewayid=20]")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='FCUseDebitEnable']/div[1]/div[2]/select")));
            WebElement selectCard = driver.findElement(By.xpath("//*[@id='FCUseDebitEnable']/div[1]/div[2]/select"));
            Select testCard = new Select(selectCard);
            testCard.selectByValue("201825717889145|Knet Test Card [KNET1]|0.000");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='debitNumber']")));
            driver.findElement(By.xpath("//*[@id='debitNumber']")).sendKeys("0000000000");

            WebElement selectMonth = driver.findElement(By.xpath("//*[@id='cardExpdate']/div[2]/select[1]"));
            Select testMonth = new Select(selectMonth);
            testMonth.selectByValue("9");

            WebElement selectYear = driver.findElement(By.xpath("//*[@id='cardExpdate']/div[2]/select[2]"));
            Select testYear = new Select(selectYear);
            testYear.selectByValue("2025");

            driver.findElement(By.xpath("//*[@id='cardPin']")).sendKeys("1234");
            driver.findElement(By.xpath("//*[@id='proceed']")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceedConfirm']")));
            driver.findElement(By.xpath("//*[@id='proceedConfirm']")).click();
        } catch (Exception e){
            System.out.println("Error in Invoice Payment");
        }
    }

    public void payEmbedded() {
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("paymentCardView-form")));
            driver.findElement(By.xpath("//*[@id='cardholdername']")).sendKeys("testt");
            driver.findElement(By.xpath("//*[@id='cardnumber']")).sendKeys("5454545454545454");
            driver.findElement(By.xpath("//*[@id='expirationdate']")).sendKeys("09/25");
            driver.findElement(By.xpath("//*[@id='securitycode']")).sendKeys("000");

            wait.until(ExpectedConditions.elementToBeClickable(xpathEmbeddedPayButton));
            driver.findElement(xpathEmbeddedPayButton).click();

            wait.until(ExpectedConditions.elementToBeClickable(xpathEmbeddedConfirmButton));
            driver.findElement(xpathEmbeddedConfirmButton).click();
        } catch (Exception e){
            System.out.println("Error in Invoice Payment");
        }
    }

    public void createQuickInvoice(String amount, WebElement currency) {
        customerName.sendKeys("Automation");
        wait.until(ExpectedConditions.elementToBeClickable(xpathSendInvoiceOption));
        sendInvoiceOptionButton.click();
        linkSelection.click();
        if (currency == KDCurrency) {}
        else{
            displayCurrencyButton.click();
            currency.click();
        }
        invoiceValue.sendKeys(amount);
        createInvoice.click();
    }

    public void createInvoiceBaseData(){
        customerName.sendKeys("Automation");
        wait.until(ExpectedConditions.elementToBeClickable(xpathSendInvoiceOption));
        sendInvoiceOptionButton.click();
        linkSelection.click();
        moreOptions.click();
    }

    public void openInvoiceWithReference(String invoiceReference){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(invoiceReference)));
        driver.findElement(By.partialLinkText(invoiceReference)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='InvoiceUrl']")));
    }

    public String getInvoiceStatus(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("white-text")));
        return driver.findElement(By.className("white-text")).getText();
    }

    public String getInvoiceReference(){
        wait.until(ExpectedConditions.presenceOfElementLocated(classAlert));
        return alertMessage.getText().split(":")[1].replaceAll(" ", "");
    }

    public String getInvoiceReferenceFromInvoicePage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[p[contains(text(), 'Invoice Reference') or contains(text(), ' مرجع الفاتورة ')]]/following-sibling::p[1]")));
        return driver.findElement(By.xpath("//div[p[contains(text(), 'Invoice Reference') or contains(text(), ' مرجع الفاتورة ')]]/following-sibling::p[1]")).getText();
    }

    public String getInvoiceAmountWithReference(String invoiceReference){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//td[contains(.,'%s')]/following-sibling::td[4]", invoiceReference))));
        return driver.findElement(By.xpath(String.format("//td[contains(.,'%s')]/following-sibling::td[4]", invoiceReference))).getText();
    }

    public String getLatestInvoiceAmount(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='grid-view']//tr[1]/td[5]")));
        return driver.findElement(By.xpath("//table[@id='grid-view']//tr[1]/td[5]")).getText();
    }

    public void checkQuickInvoiceCreation(String name, String notification, String mobile, String email,
                                          String reference, WebElement currency, String amount, String invoiceLanguage)
    {
        if (notification.equals("SMS")){
            customerMobile.sendKeys(mobile);
        } else {
            wait.until(ExpectedConditions.presenceOfElementLocated(xpathSendInvoiceOption));
            sendInvoiceOptionButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("SMS and Email")));
            switch (notification){
                case("SMSEmail"):
                    SMSandEmailSelection.click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.id("CustomerEmail")));
                    customerEmail.sendKeys(email);
                    customerMobile.sendKeys(mobile);
                    break;
                case("Link"):
                    linkSelection.click();
                    break;
                case("Email"):
                    emailSelection.click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.id("CustomerEmail")));
                    customerEmail.sendKeys(email);
                    break;
            }
        }

        if (currency == KDCurrency) {}
        else{
            displayCurrencyButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Kuwait (KD)")));
            currency.click();
        }

        customerName.sendKeys(name);
        customerReference.sendKeys(reference);
        invoiceValue.sendKeys(amount);

        if (invoiceLanguage.equals("Ar")){
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'Language' and @value = '1']/following-sibling::span")));
            arabicLanguage.click();
        }
    }


}
