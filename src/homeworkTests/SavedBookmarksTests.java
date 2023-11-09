package homeworkTests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SavedListsPageObject;
import lib.ui.SearchPageObject;
import lib.ui.WelcomeScreenPageObject;
import org.junit.Test;

public class SavedBookmarksTests extends CoreTestCase {

    //ex5
    @Test
    public void testSaveAndDeleteBookmarks(){

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SavedListsPageObject SavedListsPageObject = new SavedListsPageObject(driver);
        String searchLine = "java";
        String folderName = "first list";
        String textFirstSavedPage = "Java (programming language)";
        String textSecondSavedPage = "JavaScript";


        ScipWelcomeScreenPageObject.scipWelcomeScreen();
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
