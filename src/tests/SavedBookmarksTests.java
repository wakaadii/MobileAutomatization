package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SavedListsPageObject;
import lib.ui.SearchPageObject;
import lib.ui.WelcomeScreenPageObject;
import org.junit.Test;

public class SavedBookmarksTests extends CoreTestCase {
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
        ArticlePageObject.openSavedLists();
        SavedListsPageObject.openListOfBookmarks(folderName);
        SavedListsPageObject.deleteBookmarkFromList(ArticleTitle);

    }
}
