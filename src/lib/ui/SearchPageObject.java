package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[@text='Search Wikipedia']",
            SEARCH_INPUT = "//*[@text='Search Wikipedia']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_FIELD = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_LOCATOR_XPATH = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/{SUBSTRING}",
            SEARCH_RESULTS_LIST_ID = "//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']";

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
        this.waitForElementPresents(By.xpath(SEARCH_INIT_ELEMENT), "can't find search bar and click");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "search bar is not founded");
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSend(By.xpath(SEARCH_INPUT), searchLine, "Can't find and type Durationo search input");
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresents(By.xpath(searchResultXpath), "There is no text '" + substring + "' in server answer");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresents(By.id(SEARCH_CANCEL_BUTTON), "Can't find 'cancel search' cross");
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Button 'cancel search' is presented", 10);
    }

    public void clearSearchField () {
        this.waitForElementAndClear(By.id(SEARCH_FIELD), "field for clear does not present", 10);
    }

    public void clickCancelSearch () {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Can't click on 'cancel search' button");
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXpath), "can't click result with substring text'" + substring + "'");
    }

    public int countNumberOfLines() {
        String countElementsLocator = getSearchResultLocator( "android.view.ViewGroup");
        this.waitForElementPresents(
                By.xpath(countElementsLocator),
                "incorrect search locator " + countElementsLocator
        );
        return getAmountOfElements(By.xpath(countElementsLocator));
    }

    public String getTextOfLine(int index) {
        String textElement = getSearchResultLocator("android.view.ViewGroup[@index = " + index + "]/android.widget.TextView");
        return driver.findElement(By.xpath(textElement)).getText();
    }

    public void searchResultIsNotEmpty() {
        Assert.assertTrue("too few results", ((countNumberOfLines() > 1) || !(getTextOfLine(0).equals("No results"))));
    }

    public void noSearchResult() {
        Assert.assertTrue("Some results are founded", ((countNumberOfLines() == 1) & getTextOfLine(0).equals("No results")));
    }
    public void searchResultIsEmpty() {
        waitForElementNotPresent(By.xpath(SEARCH_RESULTS_LIST_ID), "Element is presented", 15);
    }


}
