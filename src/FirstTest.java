import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;
import java.util.List;

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

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "field for clear does not present",
                15
        );

        WaitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "close cross is presented",
                10
        );
    }

    @Test
    public void testCompireArticleTitle() {

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

//        String article = title_element.getAttribute("content-desc");
// it's not working on junit 4.1.22 + selenium 3.4.0
//
//        Assert.assertEquals(
//                "this is not expected title",
//                "Java (programming language)",
//                article
//        );

    }

    @Test
    public void testSwipeArticle() {

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
                "Appium",
                "can't find search bar or can't send query"
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Automation for Apps']"),
                "There is no searched text is server answer",
                15
        );

        swipeUpToElement(
                By.xpath("//*[@content-desc = 'View article in browser']"),
                "can't find footer element",
                20
        );
        swipeUp(200);

    }

    @Test
    public void saveAndDeleteBookmarks (){
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

        waitForElementPresents(
                By.xpath("//*[@content-desc='Object-oriented programming language']"),
                "Can't find article title",
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
                "first list",
                "can't send name for list of bookmarks"
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "can't create list of bookmarks"
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "can't find <- arrow on page"
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
                By.xpath("//android.view.ViewGroup[@resource-id = 'org.wikipedia:id/page_list_item_container' and @index = 0]"),
                "can't find marked page"
        );

        WaitForElementNotPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container' and @index = 0]"),
                "marked page is in the list",
                15
        );
    }

    @Test
    public void testAmounOfNotEmptySearch() {

        waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
        );


        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        String searchLine = "Linkin park discography";

        waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                searchLine,
                "can't find search bar or can't send query"
        );

        String searchResultLocator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@index = 0]";
        waitForElementPresents(
                By.xpath(searchResultLocator),
                "there is no search results by " + searchResultLocator
        );

        int amountOfSearchedElements = getAmountOfElements(
                By.xpath(searchResultLocator)
        );

        Assert.assertTrue(
                "too few results for searhed string",
                amountOfSearchedElements > 0
        );

    }

    @Test
    public void testAmounOfEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        String searchLine = "82drjv";

        waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                searchLine,
                "can't find search bar or can't send query"
        );

        String searchResultLocator = "//*[@resource-id = 'org.wikipedia:id/results_text']/android.view.ViewGroup[@index = 0]";
        String emptyResultLabel = "//android.widget.TextView[@resource-id = 'org.wikipedia:id/results_text']";

        waitForElementPresents(
                By.xpath(emptyResultLabel),
                "there is no empty result label by request " + searchLine
        );

        assertElementNotPresent(
                By.xpath(searchResultLocator),
                By.xpath(emptyResultLabel),
                "We've found some results by request " + searchLine,
                emptyResultLabel
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

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresents(by, errorMessage, 15);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick () {
        swipeUp(200);
    }

    protected void swipeUpToElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;

        while (driver.findElements(by).size() == 0){
            if (alreadySwiped > maxSwipes){
                waitForElementPresents(by, "Can't find element by swiping \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
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

    private int getAmountOfElements(By by)    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent (By byCountElements, By byTextOfElement, String errorMessage, String emptyResultsMessage) {
        int amountOfElements = getAmountOfElements(byCountElements);
        WebElement element = waitForElementPresents(byTextOfElement, " element by Xpath " + byTextOfElement + "not found");
        if ((amountOfElements > 1) + element.getText() != emptyResultsMessage) {
            String defaultMessage = "An element '" + byCountElements.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

}