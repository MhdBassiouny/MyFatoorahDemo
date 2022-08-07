package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class RefundTestCases extends BaseTest {

    public RefundTestCases(String browserType) {
        super("Chrome", "1");
    }

    @Test //Add Account Statement Validation
    public void deductRefund() {
        SoftAssert assertion = new SoftAssert();

        home.openQuickInvoicePage();
        invoice.createQuickInvoice(inputs.getInvoiceValue(), invoice.KDCurrency);
        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");
        driver.close();

        driver.switchTo().window(newTab.get(0));
        wait.until(ExpectedConditions.elementToBeClickable(home.xpathRefundSection));
        home.refunds.click();
        wait.until(ExpectedConditions.elementToBeClickable(home.xpathMyFatoorahRefund));
        home.myFatoorahRefund.click();

        refund.openRefundWithInvoiceReference(invoiceReference);
        String refundTotalAmount = refund.refundAmount.getAttribute("value");
        refund.refundSubmit.click();
        String refundReference = refund.getRefundReference();
        String amountRefunded = refund.getAmountRefunded(refundReference);
        String refundInvoiceReference = refund.getRefundInvoiceReference(refundReference);
        assertion.assertTrue(Float.parseFloat(amountRefunded) == Float.parseFloat(refundTotalAmount));

        home.openInvoiceListPage();
        String refundInvoiceAmount = invoice.getInvoiceAmountWithReference(refundInvoiceReference);
        assertion.assertTrue(Float.parseFloat(refundInvoiceAmount) == -Float.parseFloat(amountRefunded));

        assertion.assertAll();
    }

    @Test
    public void partialDeductRefund() {
        SoftAssert assertion = new SoftAssert();

        home.openQuickInvoicePage();
        invoice.createQuickInvoice(inputs.getInvoiceValue(), invoice.KDCurrency);
        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");
        driver.close();

        driver.switchTo().window(newTab.get(0));
        home.openMyFatoorahRefundPage();
        refund.openRefundWithInvoiceReference(invoiceReference);

        refund.isPartialRefund.click();
        refund.refundAmount.clear();
        refund.refundAmount.sendKeys(inputs.getPartialRefundAmount());
        refund.refundSubmit.click();

        String refundReference = refund.getRefundReference();
        String amountRefunded = refund.getAmountRefunded(refundReference);
        assertion.assertTrue(Float.parseFloat(amountRefunded) == Float.parseFloat(inputs.getPartialRefundAmount()));

        assertion.assertAll();
    }

    @Test
    public void checkRefundInputs() {
        SoftAssert assertion = new SoftAssert();

        home.openQuickInvoicePage();
        invoice.createQuickInvoice(inputs.getInvoiceValue(), invoice.KDCurrency);
        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");
        driver.close();

        driver.switchTo().window(newTab.get(0));
        home.openMyFatoorahRefundPage();
        refund.openRefundWithInvoiceReference(invoiceReference);

        refund.isPartialRefund.click();
        refund.myFatoorahFees.click();
        refund.refundAmount.clear();
        refund.refundAmount.sendKeys("0.01");
        refund.refundSubmit.click();
        String actualResult = refund.getRefundValidationSummaryError();
        String expectedResult = "greater than zero";
        assertion.assertTrue(actualResult.contains(expectedResult), "Negative Amount Error");

        refund.isPartialRefund.click();
        refund.refundAmount.clear();
        refund.refundAmount.sendKeys("0.001");
        refund.refundSubmit.click();
        String actualResult2 = refund.getRefundAmountError();
        String expectedResult2 = "min value is 0.01";
        assertion.assertTrue(actualResult2.contains(expectedResult2), "Minimum Value Error");

        refund.refundAmount.clear();
        refund.refundAmount.sendKeys(String.valueOf(Float.parseFloat(inputs.getInvoiceValue())+1));
        String actualResult3 = refund.getRefundAmountError();
        String expectedResult3 = "Amount max value";
        assertion.assertTrue(actualResult3.contains(expectedResult3), "Max Value Exceeded");

        assertion.assertAll();
    }

    @Test
    public void sendRefund() {
        SoftAssert assertion = new SoftAssert();

        home.openQuickInvoicePage();
        invoice.createQuickInvoice(inputs.getInvoiceValue(), invoice.KDCurrency);
        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");
        driver.close();

        driver.switchTo().window(newTab.get(0));
        home.openMyFatoorahRefundPage();
        refund.openRefundWithInvoiceReference(invoiceReference);

        refund.sendRefund.click();
        String amountRefunded = refund.customerRecieve.getText().split(" ")[0];
        refund.refundSubmit.click();

        invoice.payWithKNET();
        String refundReference = invoice.getInvoiceReferenceFromInvoicePage();
        home.openInvoiceListPage();
        String refundInvoiceReference = String.valueOf(Integer.parseInt(refundReference) + 1);
        String refundInvoiceAmount = invoice.getInvoiceAmountWithReference(refundInvoiceReference);
        assertion.assertTrue(Float.parseFloat(refundInvoiceAmount) == -Float.parseFloat(amountRefunded));

        assertion.assertAll();
    }

    @Test
    public void deductWireTransfer() throws InterruptedException {
        SoftAssert assertion = new SoftAssert();

        home.openWireTransferPage();
        refund.sendInitialWireTransferData();
        refund.countryButton(driver).click();
        refund.USASelection.click();
        refund.refundAmount.sendKeys("10");
        refund.refundSubmit.click();

        String actualResult = refund.getRefundValidationSummaryError();
        String expectedResult = "pages.Refund amount must be greater than zero";
        assertion.assertTrue(actualResult.contains(expectedResult));

        refund.otherBank(driver).click();
        refund.refundAmount.clear();
        refund.refundAmount.sendKeys(inputs.getWireTransfereAmount());
        refund.refundSubmit.click();
        String refundReference = refund.getRefundReference();

        home.openInvoiceListPage();
        String actualResult2 = invoice.getLatestInvoiceAmount();
        String expectedResult2 = "-" + inputs.getWireTransfereAmount();
        assertion.assertTrue(actualResult2.contains(expectedResult2), "Check negative invoice");

        assertion.assertAll();
    }

    @Test
    public void sendWireTransfer()  {
        SoftAssert assertion = new SoftAssert();

        home.openWireTransferPage();
        refund.sendRefund.click();
        refund.sendInitialWireTransferData();
        refund.refundAmount.sendKeys(inputs.getWireTransfereAmount());
        refund.refundSubmit.click();
        invoice.payWithKNET();
        String refundReference = invoice.getInvoiceReferenceFromInvoicePage();

        home.openInvoiceListPage();
        String refundInvoiceReference = String.valueOf(Integer.parseInt(refundReference) + 1);
        String refundInvoiceAmount = invoice.getInvoiceAmountWithReference(refundInvoiceReference);
        assertion.assertTrue(Float.parseFloat(refundInvoiceAmount) == -Float.parseFloat(inputs.getWireTransfereAmount()));

        assertion.assertAll();
    }

}
