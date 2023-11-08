package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.WelcomeScreenPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearhResult("Java (programming language)");
    }

    @Test
    public void testCancelSearch() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
//        Use for manual clearing field
//        SearchPageObject.clearSearchField();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmounOfNotEmptySearch() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "Linkin park discography";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.searchResultIsNotEmpty();
    }

    @Test
    public void testAmounOfEmptySearch() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "82drjv";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.searchResultIsEmpty();
    }
}
