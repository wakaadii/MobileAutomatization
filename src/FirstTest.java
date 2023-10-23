import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
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
    public void findJavaTest() {

        waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
                );

        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Java",
                "can't find search bar or can't send query"
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "There is no searched text is server answer",
                15
        );


    }

    @Test
    public void CancelSearchTest() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "can't skip welcome screen"
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "search bar is not founded"
        );

        waitForElementAndSend(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "can't find search bar or can't send query"
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find 'cancel search' cross"
        );

        WaitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "close cross is presented",
                10
        );
    }

    @Test
    public void testCompirArticleTitle() {

        waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Java",
                "can't find search bar or can't send query"
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "There is no searched text is server answer",
                15
        );

        WebElement title_element = waitForElementPresents(
                By.xpath("//*[@content-desc='Object-oriented programming language']"),
                "Can't find article title",
                15
        );

        String article = title_element.getText();

        Assert.assertEquals(
                "this is not expected title",
                "Java (programming language)",
                article
        );

    }

    private WebElement waitForElementPresents(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresents(By by, String errorMessage) {
        return waitForElementPresents(by, errorMessage,5);
    }
    private WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresents(by, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndClick(By by, String errorMessage){
        WebElement element = waitForElementPresents(by, errorMessage, 5);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSend(By by, String message, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresents(by, errorMessage, timeOutInSeconds);
        element.sendKeys(message);
        return element;
    }

    private WebElement waitForElementAndSend(By by, String message, String errorMessage){
        WebElement element = waitForElementPresents(by, errorMessage, 5);
        element.sendKeys(message);
        return element;
    }

    private boolean WaitForElementNotPresent(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


}