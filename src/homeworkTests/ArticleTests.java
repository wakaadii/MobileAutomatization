package homeworkTests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.WelcomeScreenPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    //ex6
    //тест должен падать - не успевает прогрузиться страница перед ArticlePageObject.assertElementPresent()
    @Test
    public void testAssertElementPresent() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String textSavedPage = "Java (programming language)";
        String searchLine = "java";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForSearchResult(textSavedPage);
        SearchPageObject.clickByArticleWithSubstring(textSavedPage);

        ArticlePageObject.assertElementPresent();
    }
}
