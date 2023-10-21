
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "amt");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/programming/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {

        waitForElementByXpathAndClick(
                "//*[@text='Skip']",
                "can't skip welcome screen"
                );

        waitForElementByXpathAndClick(
                "//*[@text='Search Wikipedia']",
                "search bar is not founded",
                5
        );

        waitForElementByXpathAndSend(
                "//*[@text='Search Wikipedia']",
                "Java",
                "can't find search element"
        );

        waitForElementByXpathAndClick(
                "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']",
                "There is no searched text is server answer",
                15
        );


    }

    private WebElement waitForElementPresents (String xpath, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresents (String xpath, String errorMessage) {
        return waitForElementPresents(xpath, errorMessage,5);
    }
    private WebElement waitForElementByXpathAndClick(String xpath, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresents(xpath, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String errorMessage){
        WebElement element = waitForElementPresents(xpath, errorMessage, 5);
        element.click();
        return element;
    }
    private WebElement waitForElementByXpathAndSend(String xpath, String message, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresents(xpath, errorMessage, timeOutInSeconds);
        element.sendKeys(message);
        return element;
    }

    private WebElement waitForElementByXpathAndSend(String xpath, String message, String errorMessage){
        WebElement element = waitForElementPresents(xpath, errorMessage, 5);
        element.sendKeys(message);
        return element;
    }
}