package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']",
            SEARCH_INPUT = "xpath://*[@text='Search Wikipedia']",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_FIELD = "id:org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_LOCATOR_XPATH = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/{SUBSTRING}",
            SEARCH_RESULTS_LIST_ID = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']";

    /* template methods*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultLocator(String substring){
        return SEARCH_RESULT_LOCATOR_XPATH.replace("{SUBSTRING}", substring);
    }
    /* template methods*/

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementPresents(SEARCH_INIT_ELEMENT, "can't find search bar and click");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "search bar is not founded");
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSend(SEARCH_INPUT, searchLine, "Can't find and type Durationo search input");
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresents(searchResultXpath, "There is no text '" + substring + "' in server answer");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresents(SEARCH_CANCEL_BUTTON, "Can't find 'cancel search' cross");
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Button 'cancel search' is presented", 10);
    }

    public void clearSearchField () {
        this.waitForElementAndClear(SEARCH_FIELD, "field for clear does not present", 10);
    }

    public void clickCancelSearch () {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Can't click on 'cancel search' button");
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath, "can't click result with substring text'" + substring + "'");
    }

    public int countNumberOfLines() {
        String countElementsLocator = getSearchResultLocator( "android.view.ViewGroup");
        this.waitForElementPresents(
                countElementsLocator,
                "incorrect search locator " + countElementsLocator
        );
        return getAmountOfElements(countElementsLocator);
    }

    public String getTextOfLine(int index) {
        String textElement = getSearchResultLocator("android.view.ViewGroup[@index = " + index + "]/android.widget.TextView");
        return driver.findElement(getLocatorByString(textElement)).getText();
    }

    public void searchResultIsNotEmpty() {
        Assert.assertTrue("too few results", ((countNumberOfLines() > 1) || !(getTextOfLine(0).equals("No results"))));
    }

    public void noSearchResult() {
        Assert.assertTrue("Some results are founded", ((countNumberOfLines() == 1) & getTextOfLine(0).equals("No results")));
    }
    public void searchResultIsEmpty() {
        waitForElementNotPresent(SEARCH_RESULTS_LIST_ID, "Element is presented", 15);
    }


}
