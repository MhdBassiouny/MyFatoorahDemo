package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;

import java.text.DecimalFormat;
import java.time.Duration;


public class BaseTest {

    protected static String browserType;
    protected static String loadBalancer;
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

    public BaseTest(String browserType, String loadBalance){
        this.browserType = browserType;
        this.loadBalancer = loadBalance;
    }
    public BaseTest(){
    }

    @BeforeClass
    public static void initiateDriver() {
        if (browserType.equals("FireFox")) {
            String geckoPath = System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", geckoPath);
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else {
            String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromePath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }

        login = new Login(driver);
        home = new Home(driver);
        register = new Register(driver);
        invoice = new Invoice(driver);
        refund = new Refund(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.navigate().to(inputs.getLogin());
        if (loadBalancer.equals("1")){
            driver.manage().addCookie(new Cookie("ApplicationGatewayAffinity", "3ef0c0508ad415fb05a4ff3f87fb97da"));
            driver.manage().addCookie(new Cookie("ApplicationGatewayAffinityCORS", "3ef0c0508ad415fb05a4ff3f87fb97da"));
        } else if (loadBalancer.equals("2")){
            driver.manage().addCookie(new Cookie("ApplicationGatewayAffinity", "61939aeb6b7c5f38617144d210b01e24"));
            driver.manage().addCookie(new Cookie("ApplicationGatewayAffinityCORS", "61939aeb6b7c5f38617144d210b01e24"));
        }
        login.loginData(inputs.getCorrectEmail(), inputs.getCorrectPassword());
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
