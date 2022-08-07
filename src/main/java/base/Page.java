package base;

import base.InputData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Page {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static InputData inputs = new InputData();

    public Page(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }
}
