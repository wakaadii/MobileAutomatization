package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SavedListsPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class SavedBookmarksTests extends CoreTestCase {

    private static final String folderName = "first list";
    @Test
    public void testSaveAndDeleteBookmarks(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SavedListsPageObject SavedListsPageObject = new SavedListsPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement();
        String ArticleTitle = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToNewList(folderName);
            ArticlePageObject.closePage();
            ArticlePageObject.closePage();
            ArticlePageObject.openSavedLists();
            SavedListsPageObject.openListOfBookmarks(folderName);
            SavedListsPageObject.deleteBookmarkFromList(ArticleTitle);
        } else {
            ArticlePageObject.saveArticleToDefaultList();
            ArticlePageObject.closePage();
            SearchPageObject.clickCancelSearch();
            ArticlePageObject.openSavedLists();
            SavedListsPageObject.deleteBookmarkFromList(ArticleTitle);
        }
    }
}
