import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homework extends CoreTestCase {

    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testComparisonTextOfElement() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen",
                10
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@class = 'android.widget.TextView']"),
                "Can't find article title",
                15
        );

        MainPageObject.assertElementHasText(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@class = 'android.widget.TextView']"), "Search Wikipedia", "Text of element is unexpected");
    }

    @Test
    public void testCanceledSearch() throws InterruptedException {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "can't skip welcome screen"
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "search bar is not founded"
        );

        MainPageObject.waitForElementAndSend(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "can't find search bar or can't send query"
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']"),
                "There are no current results for this query",
                15);


        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "field for clear is not founded",
                15
        );

        MainPageObject.WaitForElementNotPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']"),
                "clearing 'search' field didn't work",
                15
        );
    }

    @Test
    public void testCheckWord() {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "can't skip welcome screen"
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "search bar is not founded"
        );

        MainPageObject.waitForElementAndSend(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "can't find search bar or can't send query"
        );

        WebElement lastElementOnScreen = MainPageObject.waitForElementPresents(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[last()]"),
                "There is no elements for searching",
                10
        );

        MainPageObject.assertElementContainText(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@instance = 1]/android.widget.TextView[@index = 0]"),
                "Java",
                "there is no java in text of 1st element"
        );

        MainPageObject.assertElementContainText(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@instance = 2]/android.widget.TextView[@index = 0]"),
                "Java",
                "there is no java in text of 2nd element"
        );

        MainPageObject.assertElementContainText(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@instance = 3]/android.widget.TextView[@index = 0]"),
                "Java",
                "there is no java in text of 3rd element"
        );

        MainPageObject.assertElementContainText(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@instance = 4]/android.widget.TextView[@index = 0]"),
                "Java",
                "there is no java in text of 4th element"
        );
    }

    @Test
    public void testSaveAndDeleteBookmarks(){

            String textFirstSasvedPage = "Java (programming language)";
            String textSecondSavedPage = "JavaScript";
            String nameOfList = "programming languages";

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        MainPageObject.waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Java",
                "can't find search bar or can't send query"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='" + textFirstSasvedPage + "']"),
                "There is no searched text " + textFirstSasvedPage + " is server answer",
                15
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@content-desc='"+ textFirstSasvedPage + "']"),
                "Can't find article "+ textFirstSasvedPage + " title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Can't find 'save' button"
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "can't add to bookmarks",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.EditText[@resource-id = 'org.wikipedia:id/text_input' and @text = 'Name of this list']"),
                "can't find list's add");

        MainPageObject.waitForElementAndSend(
                By.xpath("//android.widget.EditText[@resource-id = 'org.wikipedia:id/text_input' and @text = 'Name of this list']"),
                nameOfList,
                "can't send name for list of bookmarks"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "can't create list of bookmarks"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "can't find <- arrow on first page"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='" + textSecondSavedPage + "']"),
                "There is no searched text " + textSecondSavedPage + " is server answer",
                15
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@content-desc='" + textSecondSavedPage + "']"),
                "Can't find article " + textSecondSavedPage + " title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Can't find 'save' button"
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "can't add to bookmarks",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text = '" + nameOfList + "']"),
                "can't find list of bookmarks"
        );



        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "can't find <- arrow on second page"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "can't find <- arrow on search"
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Can't find saved bookmarks button"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index = 1]"),
                "can't find bookmarks list"
        );

        MainPageObject.swipeElementLeft(
                By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '" + textFirstSasvedPage + "']"),
                "can't find marked page"
        );

        MainPageObject.WaitForElementNotPresent(
                By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '" + textFirstSasvedPage + "']"),
                "Test can't delete page " + textFirstSasvedPage,
                15
        );

        MainPageObject.waitForElementPresents(
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
