import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

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
    public void testCompireArticleTitle() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String articleTitle = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "this is not expected title",
                "Object-oriented programming language",
                articleTitle
        );

    }

    @Test
    public void testSwipeArticle() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }

    @Test
    public void testSaveAndDeleteBookmarks(){

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SavedListsPageObject SavedListsPageObject = new SavedListsPageObject(driver);
        String folderName = "first list";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement();
        String ArticleTitle = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToList(folderName);
        ArticlePageObject.closePage();
        ArticlePageObject.closePage();
        MainPageObject.openSavedLists();
        SavedListsPageObject.openListOfBookmarks(folderName);
        SavedListsPageObject.deleteBookmarkFromList(ArticleTitle);

    }

    @Test
    public void testAmounOfNotEmptySearch() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "Linkin park discography";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);

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

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "82drjv";
        String searchResultLocator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        String emptyResultLabel = "//android.widget.TextView[@resource-id = 'org.wikipedia:id/results_text']";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);



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

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "Java";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);

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

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "Java";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForSearhResult("Object-oriented programming language");

        driver.runAppInBackground(5);

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Can't find article 'Object-oriented programming language' after background",
                15
        );
    }



}