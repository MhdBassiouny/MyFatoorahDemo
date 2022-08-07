package tests;

import base.BaseTestFirefox;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class InvoiceTestCases extends BaseTestFirefox {

    @DataProvider
    public Object[][] invoiceValues() {
        Object[][] data = new Object[4][1];

        data[0][0] = "10.00";
        data[1][0] = "11.15";
        data[2][0] = "0.235";
        data[3][0] = "125";

        return data;
    }

    @Test(dataProvider = "invoiceValues")
    public void createQuickInvoice(String invoiceValue) {
        SoftAssert assertion = new SoftAssert();

        home.openQuickInvoicePage();
        invoice.createQuickInvoice(invoiceValue, invoice.KDCurrency);
        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        String paymentLink = invoice.invoiceLink.getText();
        assertion.assertTrue(paymentLink.contains(inputs.getBaseLink()), "pages.Invoice Link Error");

        String amount = driver.findElement(invoice.xpathInvoiceValue).getText().split(" ")[0];
        assertion.assertEquals(Float.parseFloat(amount), Float.parseFloat(invoiceValue), "pages.Invoice Value Error");
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");
        driver.close();

        driver.switchTo().window(newTab.get(0));
        wait.until(ExpectedConditions.presenceOfElementLocated(invoice.xpathInvoiceStatus));
        driver.navigate().refresh();
        String status = driver.findElement(invoice.xpathInvoiceStatus).getText();
        assertion.assertTrue((status.contains("Paid") || status.contains("مدفوعة")), "Status not Changed in MyFatoorah");

        assertion.assertAll();
    }

    @Test
    public void createQuickInvoiceDifferentCurrency() {
        SoftAssert assertion = new SoftAssert();

        home.openQuickInvoicePage();
        invoice.createQuickInvoice(inputs.getInvoiceValue(), invoice.SRCurrency);
        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        String paymentLink = invoice.invoiceLink.getText();
        assertion.assertTrue(paymentLink.contains(inputs.getBaseLink()), "pages.Invoice Link Error");

        String displayAmount = driver.findElement(invoice.xpathDisplayValue).getText().split(" ")[0];
        String baseAmount = driver.findElement(invoice.xpathInvoiceValue).getText().split(" ")[0];
        assertion.assertEquals(Float.parseFloat(displayAmount), Float.parseFloat(inputs.getInvoiceValue()), "pages.Invoice Value");
        String convertedAmount = dfThreeDigits.format(Float.parseFloat(displayAmount) / inputs.getKDtoSR());
        assertion.assertTrue(convertedAmount.equals(baseAmount));
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        wait.until(ExpectedConditions.elementToBeClickable(invoice.classDisplayAmountInvoicePage));
        String displayAmountInvoicePage = driver.findElement(invoice.classDisplayAmountInvoicePage).getText().split(" ")[0];
        assertion.assertTrue(displayAmount.equals(displayAmountInvoicePage));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");
        String paidAmount = driver.findElement(invoice.classDisplayAmountInvoicePage).getText().split(" ")[0];
        assertion.assertTrue(paidAmount.equals(baseAmount));
        driver.close();

        driver.switchTo().window(newTab.get(0));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(invoice.xpathInvoiceStatus));
        String status = driver.findElement(invoice.xpathInvoiceStatus).getText();
        assertion.assertTrue((status.contains("Paid") || status.contains("مدفوعة")), "Status not Changed in MyFatoorah");

        assertion.assertAll();
    }

    @Test
    public void createInvoice() {
        SoftAssert assertion = new SoftAssert();

        home.openCreateInvoicePage();
        invoice.createInvoiceBaseData();
        invoice.product0.sendKeys("Test1");
        invoice.price0.sendKeys(inputs.getInvoiceValue());
        invoice.createInvoice.click();

        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        String paymentLink = invoice.invoiceLink.getText();
        assertion.assertTrue(paymentLink.contains(inputs.getBaseLink()), "pages.Invoice Link");
        String amount = driver.findElement(invoice.xpathInvoiceValue).getText().split(" ")[0];
        assertion.assertEquals(Float.parseFloat(amount), Float.parseFloat(inputs.getInvoiceValue()), "pages.Invoice Value Error");
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");
        driver.close();

        //Check Payment Status Change
        driver.switchTo().window(newTab.get(0));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(invoice.xpathInvoiceStatus));
        String status = driver.findElement(invoice.xpathInvoiceStatus).getText();
        assertion.assertTrue((status.contains("Paid") || status.contains("مدفوعة")), "Status not Changed in MyFatoorah");

        assertion.assertAll();
    }

    @Test(dataProvider = "invoiceValues")
    public void createInvoiceDifferentCurrency(String invoiceValue) {
        SoftAssert assertion = new SoftAssert();

        home.openCreateInvoicePage();
        invoice.createInvoiceBaseData();
        wait.until(ExpectedConditions.elementToBeClickable(invoice.xpathDisplayCurrencyOption));
        invoice.displayCurrencyButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Saudi Arabia (SR)")));
        invoice.SRCurrency.click();
        invoice.product0.sendKeys("Test1");
        invoice.price0.sendKeys(invoiceValue);
        invoice.createInvoice.click();

        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        String paymentLink = invoice.invoiceLink.getText();
        assertion.assertTrue(paymentLink.contains(inputs.getBaseLink()), "pages.Invoice Link");

        String displayAmount = driver.findElement(invoice.xpathDisplayValue).getText().split(" ")[0];
        String baseAmount = driver.findElement(invoice.xpathInvoiceValue).getText().split(" ")[0];
        String convertedAmount = dfTwoDigits.format(Float.parseFloat(displayAmount) / inputs.getKDtoSR()) + "0";
        assertion.assertEquals(Float.parseFloat(displayAmount), Float.parseFloat(invoiceValue), "pages.Invoice Value");
        assertion.assertTrue(convertedAmount.equals(baseAmount));
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        wait.until(ExpectedConditions.elementToBeClickable(invoice.classDisplayAmountInvoicePage));
        String displayAmountInvoicePage = driver.findElement(invoice.classDisplayAmountInvoicePage).getText().split(" ")[0];
        assertion.assertTrue(displayAmountInvoicePage.equals(displayAmount));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");
        String paidAmount = driver.findElement(invoice.classDisplayAmountInvoicePage).getText().split(" ")[0];
        assertion.assertTrue(paidAmount.equals(baseAmount));
        driver.close();

        driver.switchTo().window(newTab.get(0));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(invoice.xpathInvoiceStatus));
        String status = driver.findElement(invoice.xpathInvoiceStatus).getText();
        assertion.assertTrue((status.contains("Paid") || status.contains("مدفوعة")), "Status not Changed in MyFatoorah");


        assertion.assertAll();
    }

    @Test
    public void createInvoiceOpenAmount() {
        SoftAssert assertion = new SoftAssert();

        home.openCreateInvoicePage();
        invoice.createInvoiceBaseData();
        wait.until(ExpectedConditions.elementToBeClickable(invoice.xpathDisplayCurrencyOption));
        invoice.displayCurrencyButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Saudi Arabia (SR)")));
        invoice.SRCurrency.click();
        invoice.openAmount.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("MinimumAmount")));
        invoice.maximumAmount.sendKeys(inputs.getMaximumAmount());
        invoice.minimumAmount.sendKeys("1");
        invoice.product0.sendKeys("Test1");
        invoice.price0.sendKeys(inputs.getInvoiceValue());
        invoice.createInvoice.click();


        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        String paymentLink = invoice.invoiceLink.getText();
        assertion.assertTrue(paymentLink.contains(inputs.getBaseLink()), "pages.Invoice Link");

        String displayAmount = driver.findElement(invoice.xpathDisplayValue).getText().split(" ")[0];
        String baseAmount = driver.findElement(invoice.xpathInvoiceValue).getText().split(" ")[0];
        String convertedAmount = dfTwoDigits.format(Float.parseFloat(displayAmount) / inputs.getKDtoSR()) + "0";
        assertion.assertEquals(Float.parseFloat(displayAmount), Float.parseFloat(inputs.getInvoiceValue()), "pages.Invoice Value");
        assertion.assertTrue(convertedAmount.equals(baseAmount));
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        wait.until(ExpectedConditions.elementToBeClickable(invoice.idEnterInvoiceValue));
        invoice.enterInvoiceValue.clear();
        invoice.enterInvoiceValue.sendKeys(inputs.getOpenAmount());

        driver.findElement(By.xpath("//button[@paymentgatewayid = '4']")).click();
        assertion.assertEquals(Float.parseFloat(inputs.getOpenAmount()), driver.findElement(By.className("form-cont")).getText().split(" ")[0]);
        driver.close();
        driver.switchTo().window(newTab.get(0));
        driver.navigate().refresh();

        assertion.assertAll();
    }

    @Test
    public void createInvoiceWithPercentageDiscount() {
        SoftAssert assertion = new SoftAssert();

        home.openCreateInvoicePage();
        invoice.createInvoiceBaseData();
        wait.until(ExpectedConditions.elementToBeClickable(invoice.xpathDisplayCurrencyOption));
        invoice.displayCurrencyButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Saudi Arabia (SR)")));
        invoice.SRCurrency.click();
        invoice.rateDiscount.click();
        invoice.discountValue.sendKeys(inputs.getDiscountPercentage());
        invoice.product0.sendKeys("Test1");
        invoice.price0.sendKeys(inputs.getInvoiceValue());
        String invoiceLocalValue = invoice.invoiceLocalValue.getText();
        invoice.createInvoice.click();

        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        String displayAmount = driver.findElement(invoice.xpathDisplayValue).getText().split(" ")[0];
        String baseAmount = driver.findElement(invoice.xpathInvoiceValue).getText().split(" ")[0];
        Float remainingPercentage = 1 - Float.parseFloat(inputs.getDiscountPercentage()) / 100;
        assertion.assertTrue(Float.parseFloat(invoiceLocalValue) * remainingPercentage - Float.parseFloat(baseAmount) <= 0.001);

        invoice.invoiceLink.click();
        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        wait.until(ExpectedConditions.elementToBeClickable(invoice.classDisplayAmountInvoicePage));
        String invoiceDisplayAmount = driver.findElement(invoice.classDisplayAmountInvoicePage).getText().split(" ")[0];
        assertion.assertTrue(Float.parseFloat(displayAmount) * remainingPercentage == Float.parseFloat(invoiceDisplayAmount));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");

        String paidAmount = driver.findElement(invoice.classDisplayAmountInvoicePage).getText().split(" ")[0];
        assertion.assertTrue(paidAmount.equals(baseAmount));
        driver.close();
        driver.switchTo().window(newTab.get(0));

        assertion.assertAll();
    }

    @Test
    public void createInvoiceWithValueDiscount() {
        SoftAssert assertion = new SoftAssert();

        home.openCreateInvoicePage();
        invoice.createInvoiceBaseData();
        wait.until(ExpectedConditions.elementToBeClickable(invoice.xpathDisplayCurrencyOption));
        invoice.displayCurrencyButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Saudi Arabia (SR)")));
        invoice.SRCurrency.click();
        invoice.discountValue.sendKeys(inputs.getDiscountAmount());
        invoice.product0.sendKeys("Test1");
        invoice.price0.sendKeys(inputs.getInvoiceValue());
        String invoiceLocalValue = invoice.invoiceLocalValue.getText();
        invoice.createInvoice.click();

        String invoiceReference = invoice.getInvoiceReference();
        invoice.openInvoiceWithReference(invoiceReference);
        String displayAmount = driver.findElement(invoice.xpathDisplayValue).getText().split(" ")[0];
        String baseAmount = driver.findElement(invoice.xpathInvoiceValue).getText().split(" ")[0];
        Float discountValue = Float.parseFloat(inputs.getDiscountAmount());
        assertion.assertTrue(Float.parseFloat(inputs.getInvoiceValue()) - Float.parseFloat(displayAmount) - (discountValue * inputs.getKDtoSR()) <= 0.001, "pages.Invoice Value");
        assertion.assertTrue(Float.parseFloat(invoiceLocalValue) - discountValue - Float.parseFloat(baseAmount) <= 0.01);
        invoice.invoiceLink.click();

        ArrayList<String> newTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        wait.until(ExpectedConditions.elementToBeClickable(invoice.classDisplayAmountInvoicePage));
        String invoiceDisplayAmount = driver.findElement(invoice.classDisplayAmountInvoicePage).getText().split(" ")[0];
        assertion.assertTrue(Float.parseFloat(displayAmount) == Float.parseFloat(invoiceDisplayAmount));
        invoice.payWithKNET();
        String result = invoice.getInvoiceStatus();
        assertion.assertTrue((result.contains("PAID") || result.contains("مدفوع")), "Link not Paid");
        String paidAmount = driver.findElement(invoice.classDisplayAmountInvoicePage).getText().split(" ")[0];
        assertion.assertTrue(paidAmount.equals(baseAmount));
        driver.close();
        driver.switchTo().window(newTab.get(0));

        assertion.assertAll();
    }

}
