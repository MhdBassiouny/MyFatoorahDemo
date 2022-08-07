package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;

import java.text.DecimalFormat;
import java.time.Duration;


public class BaseTestFirefox {
    protected static WebDriver driver;
    protected static Login login;
    protected static Home home;
    protected static Register register;
    protected static Invoice invoice;
    protected static Refund refund;
    protected static InputData inputs = new InputData();
    protected static WebDriverWait wait;
    protected static DecimalFormat dfThreeDigits = new DecimalFormat("#.###");
    protected static DecimalFormat dfTwoDigits = new DecimalFormat("#.##");

    @BeforeClass
    public static void initiateDriver() {
        String geckoPath = System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", geckoPath);
        driver = new FirefoxDriver();

        login = new Login(driver);
        home = new Home(driver);
        register = new Register(driver);
        invoice = new Invoice(driver);
        refund = new Refund(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        driver.manage().window().maximize();
        driver.navigate().to(inputs.getLogin());
        driver.manage().addCookie(new Cookie("ApplicationGatewayAffinity", "61939aeb6b7c5f38617144d210b01e24"));
        driver.manage().addCookie(new Cookie("ApplicationGatewayAffinityCORS", "61939aeb6b7c5f38617144d210b01e24"));
        login.loginData(inputs.getCorrectEmail(), inputs.getCorrectPassword());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("header-title")));
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
