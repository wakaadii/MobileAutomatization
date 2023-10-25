import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;

public class Homework {

        private AndroidDriver driver;

        @Before
        public void setUp() throws Exception {
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
        public void comparisonTextOfElement() {
            waitForElementAndClick(
                    By.xpath("//*[@text='Skip']"),
                    "can't skip welcome screen",
                    10
            );

            waitForElementPresents(
                    By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@class = 'android.widget.TextView']"),
                    "Can't find article title",
                    15
            );

            assertElementHasText(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@class = 'android.widget.TextView']"), "Search Wikipedia", "Text of element is unexpected");
        }

        @Test
        public void canceledSearch () throws InterruptedException {

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

            waitForElementPresents(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']"),
                    "There are no current results for this query",
                    15);


            waitForElementAndClear(
                    By.id("org.wikipedia:id/search_src_text"),
                    "field for clear is not founded",
                    15
            );

            WaitForElementNotPresent(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']"),
                    "clearing 'search' field didn't work",
                    15
            );
        }

        @Test
        public void checkWord() {

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

            assertElementContainText(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@instance = 1]/android.widget.TextView[@index = 0]"),
                    "Java",
                    "there is no java in text of 1st element"
            );

            assertElementContainText(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@instance = 2]/android.widget.TextView[@index = 0]"),
                    "Java",
                    "there is no java in text of 2nd element"
            );

            assertElementContainText(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@instance = 3]/android.widget.TextView[@index = 0]"),
                    "Java",
                    "there is no java in text of 3rd element"
            );

            assertElementContainText(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@instance = 4]/android.widget.TextView[@index = 0]"),
                    "Java",
                    "there is no java in text of 4th element"
            );
        }



        private WebElement waitForElementPresents(By by, String errorMessage, long timeOutInSeconds) {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.withMessage(errorMessage + "\n");
            return wait.until(
                    ExpectedConditions.presenceOfElementLocated(by)
            );
        }

        private WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds){
            WebElement element = waitForElementPresents(by, errorMessage, timeOutInSeconds);
            element.click();
            return element;
        }

        private WebElement waitForElementAndSend(By by, String message, String errorMessage){
            WebElement element = waitForElementPresents(by, errorMessage, 5);
            element.sendKeys(message);
            return element;
        }

        private WebElement waitForElementAndClick(By by, String errorMessage){
            WebElement element = waitForElementPresents(by, errorMessage, 5);
            element.click();
            return element;
        }

        private void assertElementHasText(By by, String text, String errorMessage){
            WebElement element = waitForElementPresents(by, errorMessage, 15);
            Assert.assertEquals(
                    errorMessage,
                    text,
                    element.getText()
            );
        }

        private void assertElementContainText(By by, String text, String errorMessage) {
            WebElement element = waitForElementPresents(
                    by,
                    errorMessage,
                    10
            );
            Assert.assertTrue(element.getText().contains(text));
        }

        private boolean WaitForElementNotPresent(By by, String errorMessage, long timeOutInSeconds) {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.withMessage(errorMessage + "\n");
            return wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(by)
            );
        }

        private WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds) {
            WebElement element = waitForElementPresents(by, errorMessage, 15);
            element.clear();
            return element;
        }
}
