package homeworkTests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SavedListsPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class SavedBookmarksTests extends CoreTestCase {

    //ex5
    private static final String searchLine = "java",
            folderName = "first list",
            textFirstSavedPage = "Java (programming language)",
            textSecondSavedPage = "JavaScript";
    @Test
        public void testSaveAndDeleteBookmarks(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SavedListsPageObject SavedListsPageObject = new SavedListsPageObject(driver);


        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleWithSubstring(textFirstSavedPage);
        ArticlePageObject.waitForTitleElement();
        String FirstArticleTitle = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToNewList(folderName);
        ArticlePageObject.closePage();
        SearchPageObject.clickByArticleWithSubstring(textSecondSavedPage);
        ArticlePageObject.waitForTitleElement();
        String SecondArticleTitle = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToList(folderName);
        ArticlePageObject.closePage();
        ArticlePageObject.closePage();
        ArticlePageObject.openSavedLists();
        SavedListsPageObject.openListOfBookmarks(folderName);
        SavedListsPageObject.deleteBookmarkFromList(FirstArticleTitle);
        SavedListsPageObject.waitForArticleToAppearByTitle(SecondArticleTitle);
    }

}
