package base;

public class InputData {
    private int sleepTime = 1200;
    private String login = "https://demo.myfatoorah.com/En/All/Account/LogIn";
    private String home = "https://demo.myfatoorah.com/En/KWT/";
    private String baseLink = "https://demo.MyFatoorah.com/";

    // pages.Register Data
    private String BusinessName = "Teat123";
    private String maximumAmount = "20";
    private  String openAmount = "10";
    private    String registerEmail = "teat@test.com";

    private String invoiceValue = "11.500";
    private  float KDtoSR = 12.300f;
    private  String discountPercentage = "10";
    private  String discountAmount = ".5";
    private  String partialRefundAmount = "3";
    private String wireTransfereAmount = "15";

    // pages.Login Data
    private String correctEmail = "test@testing.com";
    private  String correctPassword = "A@qw1234";
    private  String incorrectEmail = "mbassiouny94@gmail.com";
    private  String incorrectPassword = "A@qw12345";


    //Links Demo
    private String quickInvoiceLink = home + "QuickInvoices/Create";
    private String createInvoiceLink = home + "Invoices/Create";
    private String InvoiceListLink = home + "Invoices";
    private String refundLink = home + "Refunds/Transactions";
    private String wireTransferLink = home + "Refunds/Create";



    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }


    public String getBaseLink() {
        return baseLink;
    }

    public void setBaseLink(String baseLink) {
        this.baseLink = baseLink;
    }


    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }


    public String getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(String maximumAmount) {
        this.maximumAmount = maximumAmount;
    }


    public String getOpenAmount() {
        return openAmount;
    }

    public void setOpenAmount(String openAmount) {
        this.openAmount = openAmount;
    }


    public String getRegisterEmail() {
        return registerEmail;
    }

    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }


    public String getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(String invoiceValue) {
        this.invoiceValue = invoiceValue;
    }


    public float getKDtoSR() {
        return KDtoSR;
    }

    public void setKDtoSR(float KDtoSR) {
        this.KDtoSR = KDtoSR;
    }


    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }


    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }


    public String getPartialRefundAmount() {
        return partialRefundAmount;
    }

    public void setPartialRefundAmount(String partialRefundAmount) {
        this.partialRefundAmount = partialRefundAmount;
    }


    public String getWireTransfereAmount() {
        return wireTransfereAmount;
    }

    public void setWireTransfereAmount(String wireTransfereAmount) {
        this.wireTransfereAmount = wireTransfereAmount;
    }


    public String getCorrectEmail() {
        return correctEmail;
    }

    public void setCorrectEmail(String correctEmail) {
        this.correctEmail = correctEmail;
    }


    public String getCorrectPassword() {
        return correctPassword;
    }

    public void setCorrectPassword(String correctPassword) {
        this.correctPassword = correctPassword;
    }


    public String getIncorrectEmail() {
        return incorrectEmail;
    }

    public void setIncorrectEmail(String incorrectEmail) {
        this.incorrectEmail = incorrectEmail;
    }


    public String getIncorrectPassword() {
        return incorrectPassword;
    }

    public void setIncorrectPassword(String incorrectPassword) {
        this.incorrectPassword = incorrectPassword;
    }


    public String getQuickInvoiceLink() {
        return quickInvoiceLink;
    }

    public void setQuickInvoiceLink(String quickInvoiceLink) {
        this.quickInvoiceLink = quickInvoiceLink;
    }


    public String getCreateInvoiceLink() {
        return createInvoiceLink;
    }

    public void setCreateInvoiceLink(String createInvoiceLink) {
        this.createInvoiceLink = createInvoiceLink;
    }


    public String getInvoiceListLink() {
        return InvoiceListLink;
    }

    public void setInvoiceListLink(String invoiceListLink) {
        InvoiceListLink = invoiceListLink;
    }


    public String getRefundLink() {
        return refundLink;
    }

    public void setRefundLink(String refundLink) {
        this.refundLink = refundLink;
    }


    public String getWireTransferLink() {
        return wireTransferLink;
    }

    public void setWireTransferLink(String wireTransferLink) {
        this.wireTransferLink = wireTransferLink;
    }
}
