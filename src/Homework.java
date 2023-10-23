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

import java.net.URL;

public class Homework {

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

        private void assertElementHasText(By by, String text, String errorMessage){
            WebElement element = waitForElementPresents(by, errorMessage, 15);
            Assert.assertEquals(
                    errorMessage,
                    text,
                    element.getText()
            );
        }
}
