package homeworkTests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.WelcomeScreenPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    //ex2
    @Test
    public void testComparisonTextOfElement() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    //ex3
    @Test
    public void testCanceledSearch() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "Java";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.searchResultIsNotEmpty();
        SearchPageObject.clearSearchField();
        SearchPageObject.searchResultIsEmpty();
    }
    //ex4
    @Test
    public void testCheckWord() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "Java";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        int counter = SearchPageObject.countNumberOfLines();

        for (int i = 0; i < counter; i++ ) {
            assertTrue(
                    "there is no java in text of " + i + " element",
                    SearchPageObject.getTextOfLine(i).contains(searchLine));
        }
    }
}
