package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://*[@value = 'Object-oriented programming language']";
        TITLE_TPL = "xpath://*[@value = '{TEXT}']";
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Search";
        SAVED_LISTS_OF_BOOKMARKS = "xpath://XCUIElementTypeButton[@name='Saved']";

    }
    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
