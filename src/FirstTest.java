import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void tesrSearch() {

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
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "There is no searched text is server answer",
                15
        );


    }

    @Test
    public void testCancelSearch() {
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

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find 'cancel search' cross"
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "field for clear does not present",
                15
        );

        MainPageObject.WaitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "close cross is presented",
                10
        );
    }

    @Test
    public void testCompireArticleTitle() {

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
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "There is no searched text is server answer",
                15
        );

        WebElement title_element = MainPageObject.waitForElementPresents(
                By.xpath("//*[@content-desc='Object-oriented programming language']"),
                "Can't find article title",
                15
        );

        String article = title_element.getAttribute("content-desc");
 //     it's not working on junit 4.1.22 + selenium 3.4.0

        Assert.assertEquals(
                "this is not expected title",
                "Java (programming language)",
                article
        );

    }

    @Test
    public void testSwipeArticle() {

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
                "Appium",
                "can't find search bar or can't send query"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Automation for Apps']"),
                "There is no searched text is server answer",
                15
        );

        MainPageObject.swipeUpToElement(
                By.xpath("//*[@content-desc = 'View article in browser']"),
                "can't find footer element",
                20
        );
        MainPageObject.swipeUp(200);

    }

    @Test
    public void testSaveAndDeleteBookmarks(){
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
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "There is no searched text is server answer",
                15
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@content-desc='Object-oriented programming language']"),
                "Can't find article title",
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
                "first list",
                "can't send name for list of bookmarks"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "can't create list of bookmarks"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "can't find <- arrow on page"
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
                By.xpath("//android.view.ViewGroup[@resource-id = 'org.wikipedia:id/page_list_item_container' and @index = 0]"),
                "can't find marked page"
        );

        MainPageObject.WaitForElementNotPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container' and @index = 0]"),
                "marked page is in the list",
                15
        );
    }

    @Test
    public void testAmounOfNotEmptySearch() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        String searchLine = "Linkin park discography";

        MainPageObject.waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                searchLine,
                "can't find search bar or can't send query"
        );

        String searchResultLocator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@index = 0]";
        MainPageObject.waitForElementPresents(
                By.xpath(searchResultLocator),
                "there is no search results by " + searchResultLocator
        );

        int amountOfSearchedElements = MainPageObject.getAmountOfElements(
                By.xpath(searchResultLocator)
        );

        Assert.assertTrue(
                "too few results for searhed string",
                amountOfSearchedElements > 0
        );

    }

    @Test
    public void testAmounOfEmptySearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        String searchLine = "82drjv";

        MainPageObject.waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                searchLine,
                "can't find search bar or can't send query"
        );

        String searchResultLocator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        String emptyResultLabel = "//android.widget.TextView[@resource-id = 'org.wikipedia:id/results_text']";

        MainPageObject.waitForElementPresents(
                By.xpath(emptyResultLabel),
                "there is no empty result label by request " + searchLine
        );

        MainPageObject.assertNoSearchResults(
                By.xpath(searchResultLocator),
                By.xpath(emptyResultLabel),
                "No results",
                "test_error");


    }

    @Test
    public void testRotationOfScreenSearchResults() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        String searchLine = "Java";

        MainPageObject.waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                searchLine,
                "can't find search bar or can't send query"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Can't find text 'Object-oriented programming language' in searching by text '" + searchLine + "'",
                15
        );

        String titleBeforeRotating = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "contentDescription",
                "Cannot find title of article after screen rotation",
                15
        );

        System.out.println(titleBeforeRotating);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotating = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "contentDescription",
                "Cannot find title of article after screen rotation",
                15
        );

        Assert.assertEquals(
                "Article subtitle have been changed after rotation",
                titleBeforeRotating,
                titleAfterRotating
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotating = MainPageObject.waitForElementAndGetAttribute(
                By.id("pcs-edit-section-title-description"),
                "contentDescription",
                "Cannot find title of article after screen rotation",
                15
        );

        Assert.assertEquals(
                "Article subtitle have been changed after second rotation",
                titleBeforeRotating,
                titleAfterSecondRotating
        );
    }

    @Test
    public void testSearchResultsInBackground () {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        String searchLine = "Java";

        MainPageObject.waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                searchLine,
                "can't find search bar or can't send query"
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Can't find text 'Object-oriented programming language' in searching by text '" + searchLine + "'",
                15
        );

        driver.runAppInBackground(5);

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Can't find article 'Object-oriented programming language' after background",
                15
        );
    }



}