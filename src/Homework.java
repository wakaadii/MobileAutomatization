import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
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
            driver.rotate(ScreenOrientation.PORTRAIT);
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

            WebElement lastElementOnScreen = waitForElementPresents(
                    By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[last()]"),
                    "There is no elements for searching",
                    10
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

        @Test
        public void saveAndDeleteBookmarks (){

                String textFirstSasvedPage = "Java (programming language)";
                String textSecondSavedPage = "JavaScript";
                String nameOfList = "programming languages";

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
                    By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='" + textFirstSasvedPage + "']"),
                    "There is no searched text " + textFirstSasvedPage + " is server answer",
                    15
            );

            waitForElementPresents(
                    By.xpath("//*[@content-desc='"+ textFirstSasvedPage + "']"),
                    "Can't find article "+ textFirstSasvedPage + " title",
                    15
            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/page_save"),
                    "Can't find 'save' button"
            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/snackbar_action"),
                    "can't add to bookmarks",
                    10
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.EditText[@resource-id = 'org.wikipedia:id/text_input' and @text = 'Name of this list']"),
                    "can't find list's add");

            waitForElementAndSend(
                    By.xpath("//android.widget.EditText[@resource-id = 'org.wikipedia:id/text_input' and @text = 'Name of this list']"),
                    nameOfList,
                    "can't send name for list of bookmarks"
            );

            waitForElementAndClick(
                    By.xpath("//*[@text = 'OK']"),
                    "can't create list of bookmarks"
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "can't find <- arrow on first page"
            );

            waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='" + textSecondSavedPage + "']"),
                    "There is no searched text " + textSecondSavedPage + " is server answer",
                    15
            );

            waitForElementPresents(
                    By.xpath("//*[@content-desc='" + textSecondSavedPage + "']"),
                    "Can't find article " + textSecondSavedPage + " title",
                    15
            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/page_save"),
                    "Can't find 'save' button"
            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/snackbar_action"),
                    "can't add to bookmarks",
                    10
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@text = '" + nameOfList + "']"),
                    "can't find list of bookmarks"
            );



            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "can't find <- arrow on second page"
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "can't find <- arrow on search"
            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/nav_tab_reading_lists"),
                    "Can't find saved bookmarks button"
            );

            waitForElementAndClick(
                    By.xpath("//android.view.ViewGroup[@index = 1]"),
                    "can't find bookmarks list"
            );

            swipeElementLeft(
                    By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '" + textFirstSasvedPage + "']"),
                    "can't find marked page"
            );

            WaitForElementNotPresent(
                    By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '" + textFirstSasvedPage + "']"),
                    "Test can't delete page " + textFirstSasvedPage,
                    15
            );

            waitForElementPresents(
                    By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '" + textSecondSavedPage + "']"),
                    textSecondSavedPage + " is not in the list",
                    15
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

    protected void swipeElementLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresents(
                by,
                errorMessage,
                10);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        System.out.println(leftX + " " + rightX);
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY)/2;
        System.out.println(upperY + " " + lowerY + " " + middleY);

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX - 10, middleY)
                .waitAction(300)
                .moveTo(leftX + 10, middleY)
                .release()
                .perform();
    }
}
