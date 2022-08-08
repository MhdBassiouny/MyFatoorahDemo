package pages;

import base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Home extends Page {

    public Home(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "img-responsive")
    public WebElement homePage;

    @FindBy(linkText = "Profile")
    public WebElement profile;
    @FindBy(linkText = "Update Profile")
    public WebElement updateProfile;
    @FindBy(linkText = "Commission Charges")
    public WebElement commissionCharges;
    @FindBy(linkText = "Custmize Templates")
    public WebElement custmizeTemplates;

    @FindBy(xpath = "//*[contains(text(), 'Invoices Report')]")
    public WebElement invoiceReport;
    @FindBy(linkText = "Invoices List")
    public WebElement invoicesList;
    @FindBy(linkText = "Create Quick pages.Invoice")
    public WebElement quickInvoice;
    @FindBy(linkText = "Create pages.Invoice")
    public WebElement createInvoice;

    @FindBy(linkText = "Batch Invoices")
    public WebElement batchInvoices;
    @FindBy(linkText = "Batch Invoices List")
    public WebElement batchInvoicesList;
    @FindBy(linkText = "Create Batch Invoices")
    public WebElement createBatchInvoices;

    @FindBy(xpath = "//*[contains(text(), 'Payment Links')]")
    public WebElement paymentLink;
    @FindBy(linkText = "Payment Links List")
    public WebElement paymentLinksList;
    @FindBy(linkText = "Create Payment Link")
    public WebElement createPaymentLink;

    @FindBy(xpath = "//*[contains(text(), 'Orders List')]")
    public WebElement ordersList;

    @FindBy(linkText = "Products")
    public WebElement products;
    @FindBy(linkText = "Create Product Category")
    public WebElement createProductCategory;
    @FindBy(linkText = "Create Product")
    public WebElement createProduct;
    @FindBy(linkText = "Create Product Link")
    public WebElement createProductLink;
//    @FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[6]/ul/li[4]")
//    WebElement productsList;
    @FindBy(linkText = "Import")
    public WebElement importProduct;


    @FindBy(linkText = "Customers")
    public WebElement customers;
    @FindBy(linkText = "Customers List")
    public WebElement customersList;
    @FindBy(linkText = "Create Customer")
    public WebElement createCustomer;
    @FindBy(linkText = "Import")
    public WebElement importCustomer;

    @FindBy(xpath = "//*[contains(text(), 'Manage Users')]")
    public WebElement manageUsers;
    @FindBy(linkText = "Users List")
    public WebElement usersList;
    @FindBy(linkText = "Create User")
    public WebElement createUser;
    @FindBy(linkText = "Manage Multi pages.Login")
    public WebElement manageMultiLogin;
    @FindBy(linkText = "Approve Multi pages.Login")
    public WebElement approveMultiLogin;

    @FindBy(linkText = "Deposits")
    public WebElement deposits;
    @FindBy(linkText = "Deposits List")
    public WebElement depositsList;
    @FindBy(linkText = "Deposits Requests")
    public WebElement depositsRequests;
    @FindBy(linkText = "Request Deposit")
    public WebElement requestDeposit;
    @FindBy(linkText = "Service Requests")
    public WebElement serviceRequests;

    @FindBy(xpath = "//*[contains(text(), 'Account Statement')]")
    public WebElement accountStatement;

    @FindBy(xpath = "//span[contains(text(), 'pages.Refund') or contains(text(), 'قائمة المبالغ المعادة')]")
    public WebElement refunds;
    @FindBy(xpath = "//a[contains(text(), 'MyFatoorah pages.Refund') or contains(text(), 'اعادة المبالغ للمشترين')]")
    public WebElement myFatoorahRefund;
    @FindBy(xpath = "//a[text()= 'pages.Refund' or contains(text(), 'اعادة المبلغ')]")
    public WebElement refund;
    @FindBy(xpath = "//a[text()= 'Refunds List' or contains(text(), 'قائمة المبلغ المعادة')]")
    public WebElement refundsList;

    public By xpathRefundSection = By.xpath("//span[contains(text(), 'pages.Refund') or contains(text(), 'قائمة المبالغ المعادة')]");
    public By xpathMyFatoorahRefund = By.xpath("//a[contains(text(), 'MyFatoorah pages.Refund') or contains(text(), 'اعادة المبالغ للمشترين')]");
    public By xpathWireTransfer = By.xpath("//a[text()= 'pages.Refund' or contains(text(), 'اعادة المبلغ')]");

    public By xpathInvoiceSection = By.xpath("//span[contains(text(), 'Invoices Report') or contains(text(), 'تقارير الفواتير')]");

    public By xpathInvoiceList = By.xpath("//a[text() = 'Invoices List' or contains(text(), 'قائمة الفواتير')]");


    @FindBy(xpath = "//*[contains(text(), 'Mng. My Addresses')]")
    public WebElement myAddresses;

    @FindBy(xpath = "//*[contains(text(), 'My Invoices List')]")
    public WebElement myInvoicesList;

    @FindBy(xpath = "//*[contains(text(), 'Integration Settings')]")
    public WebElement integrationSettings;

    @FindBy(xpath = "//*[contains(text(), 'Suppliers')]")
    public WebElement suppliers;
    @FindBy(linkText = "Suppliers List")
    public WebElement suppliersList;
    @FindBy(linkText = "Create Supplier")
    public WebElement createSupplier;
    @FindBy(linkText = "Suppliers Deposits")
    public WebElement suppliersDeposits;

    @FindBy(linkText = "Service")
    public WebElement service;

    @FindBy(linkText = "Contact")
    public WebElement contact;

    @FindBy(linkText = "Create pages.Invoice")
    public WebElement createInvoiceQuick;
    @FindBy(linkText = "View All Invoices")
    public WebElement viewInvoices;
    @FindBy(linkText = "Create Quick pages.Invoice")
    public WebElement createQuickInvoiceQuick;
    @FindBy(linkText = "View All Quick Invoices")
    public WebElement viewQuickInvoices;
    @FindBy(linkText = "Create Payment Link")
    public WebElement createPaymentLinkQuick;
    @FindBy(linkText = "View All Payment Links")
    public WebElement viewPaymentLinks;
    @FindBy(linkText = "Create Batch pages.Invoice")
    public WebElement createBatchInvoiceQuick;
    @FindBy(linkText = "View All Batch Invoices")
    public WebElement viewBatchInvoices;

    public By idCustomerName = By.id("CustomerName");

    public void openQuickInvoicePage(){
        driver.get(inputs.getQuickInvoiceLink());
        wait.until(ExpectedConditions.presenceOfElementLocated(idCustomerName));
    }

    public void openCreateInvoicePage(){
        driver.get(inputs.getCreateInvoiceLink());
        wait.until(ExpectedConditions.presenceOfElementLocated(idCustomerName));
    }

    public void openInvoiceListPage(){
        driver.get(inputs.getInvoiceListLink());
        wait.until(ExpectedConditions.presenceOfElementLocated(idCustomerName));
    }

    public void openMyFatoorahRefundPage(){
        driver.get(inputs.getRefundLink());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main-container")));
    }

    public void openWireTransferPage(){
        driver.get(inputs.getWireTransferLink());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("AccountHolderName")));
    }

    public void openRefundListPage(){
        driver.get(inputs.getRefundList());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id = 'grid-refund']/tbody/tr[1]/td[1]")));
    }


    public void openAccountStatementPage(){
        driver.get(inputs.getAccountStatementLink());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id = 'grid-view']/tbody/tr[1]")));
    }

    public String invoiceTypeAccountStatement(String reference){
        openAccountStatementPage();
        return driver.findElement(By.xpath(String.format("//td[text() = '%s']/following-sibling::td[1]", reference))).getText();
    }

}
