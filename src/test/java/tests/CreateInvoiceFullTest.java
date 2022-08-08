package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateInvoiceFullTest extends BaseTest {

    public CreateInvoiceFullTest(){
        super("FireFox", "2");
    }

    @DataProvider
    public Object[][] createQuickInvoiceData(){
        Object[][] data = new Object[5][10];

        data[0][0] = "Case1";       data[0][1] = "Link";                 data[0][2]="";        data[0][3]="";
        data[0][4] = "Case123";     data[0][5] = invoice.KDCurrency;     data[0][6]= "10";     data[0][7]="Ar";
        data[0][8] = true;          data[0][9] = new String[] {"", "", "", "", ""};

        data[1][0] = "Test";        data[1][1] = "SMS";                  data[1][2]="123";      data[1][3]="";
        data[1][4] = "Case123";     data[1][5] = invoice.SRCurrency;     data[1][6]= "123";     data[1][7]="EN";
        data[1][8] = true;          data[1][9] = new String[] {"", "", "", "", ""};

        data[2][0] = "123";         data[2][1] = "Email";                data[2][2]="";         data[2][3]="T@t.com";
        data[2][4] = "Case123";     data[2][5] = invoice.KDCurrency;     data[2][6]= "test";    data[2][7]="Ar";
        data[2][8] = false;         data[2][9] = new String[] {"", "", "", "", "must be a number."};

        data[3][0] = "+";           data[3][1] = "SMSEmail";             data[3][2]="+";        data[3][3]="test";
        data[3][4] = "Case123";     data[3][5] = invoice.KDCurrency;     data[3][6]= "test";    data[3][7]="Ar";
        data[3][8] = false;         data[3][9] = new String[] {"not accept special characters", "must be a number.", "must be a email.", "", "must be a number."};

        data[4][0] = "Case2";       data[4][1] = "Link";                  data[4][2]="";        data[4][3]="";
        data[4][4] = "Case123";     data[4][5] = invoice.USDCurrency;     data[4][6]= "10";     data[4][7]="En";
        data[4][8] = true;          data[4][9] = new String[] {"", "", "", "", ""};

        return data;
    }

    @Test (dataProvider = "createQuickInvoiceData")
    public void createQuickInvoiceTest(String name, String notification, String mobile, String email,
                                       String reference, WebElement currency, String amount, String invoiceLanguage,
                                       boolean success, String[] messages) {
        SoftAssert assertion = new SoftAssert();
        home.openQuickInvoicePage();
        invoice.checkQuickInvoiceCreation(name, notification, mobile, email,
                reference, currency, amount, invoiceLanguage);
        WebElement [] validationError = {invoice.customerNameError, invoice.customerMobileError,
                invoice.customerEmailError, invoice.customerReferenceError, invoice.invoiceValueError};

        if (success){
            invoice.createInvoice.click();
            String invoiceReference = invoice.getInvoiceReference();
            invoice.openInvoiceWithReference(invoiceReference);
            String paymentLink = invoice.invoiceLink.getText();
            assertion.assertTrue(paymentLink.contains(inputs.getBaseLink()), "pages.Invoice Link Error");
            String baseAmount = driver.findElement(invoice.xpathInvoiceValue).getText().split(" ")[0];

            if (currency == invoice.KDCurrency){
                assertion.assertEquals(Float.parseFloat(baseAmount), Float.parseFloat(amount), "pages.Invoice Value Error");
            }
            else if (currency == invoice.SRCurrency) {
                String convertedAmount = dfThreeDigits.format(Float.parseFloat(amount) / inputs.getKDtoSR());
                assertion.assertEquals(Float.parseFloat(convertedAmount), Float.parseFloat(baseAmount));
            }
            else if (currency == invoice.USDCurrency){
                String convertedAmount = dfThreeDigits.format(Float.parseFloat(amount) / inputs.getKDtoUSD());
                assertion.assertEquals(Float.parseFloat(convertedAmount), Float.parseFloat(baseAmount));
            }
        } else {
            for(int i = 0; i < messages.length; i++ ){
                if (!messages[i].equals("")){
                    assertion.assertTrue(validationError[i].getText().contains(messages[i]));
                }
            }
        }
        assertion.assertAll();
    }
}
