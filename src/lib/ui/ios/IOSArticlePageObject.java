package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Search";
        SAVED_LISTS_OF_BOOKMARKS = "id:saved";
        CLOSE_SYNC_POPUP = "xpath://XCUIElementTypeButton[@name='Close']";
    }
    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
