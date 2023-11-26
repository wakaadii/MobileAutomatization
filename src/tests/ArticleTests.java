package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompireArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String articleTitle = ArticlePageObject.getArticleTitle();

        assertEquals(
                "this is not expected title",
                "Object-oriented programming language",
                articleTitle
        );

    }
    @Test
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }
}
