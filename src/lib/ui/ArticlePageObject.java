package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE,
        FOOTER_ELEMENT,
        SAVE_BUTTON,
        ADD_TO_LIST_BUTTON,
        NAME_OF_BOOKMARKS_LIST,
        CLOSE_BOOKMARKS_POPUP_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        SAVED_LISTS_OF_BOOKMARKS,
        CLOSE_SYNC_POPUP,
        NAME_OF_BOOKMARKS_LIST_TPL;

    public ArticlePageObject(AppiumDriver driver) { super(driver); }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresents(TITLE, "Can't find article title");
    }

    public String getArticleTitle () {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("contentDescription");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToElement(FOOTER_ELEMENT, "can't find the end of article", 30);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "can't find the end of article", 40);
        }
    }

    public void addArticleToNewList(String nameOfFolder) {

        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Can't find 'save' button"
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "can't add to bookmarks",
                10
        );

        this.waitForElementAndClick(
                NAME_OF_BOOKMARKS_LIST,
                "can't find list's add");

        this.waitForElementAndSend(
                NAME_OF_BOOKMARKS_LIST,
                nameOfFolder,
                "can't send name for list of bookmarks"
        );

        this.waitForElementAndClick(
                CLOSE_BOOKMARKS_POPUP_BUTTON,
                "can't create list of bookmarks"
        );
    }

    public void saveArticleToDefaultList() {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Can't find 'save' button"
        );
    }


    private static String getNameOfBookmarksListXpathByName (String folderName) {
        return NAME_OF_BOOKMARKS_LIST_TPL.replace("{TEXT}", folderName);
    }

    public void addArticleToList(String folderName) {
        String folderXpath = getNameOfBookmarksListXpathByName(folderName);

        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Can't find 'save' button"
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "can't add to bookmarks",
                10
        );

        this.waitForElementAndClick(
                folderXpath,
                "can't find list's add");

    }

    public void closePage() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "can't find <- arrow on page"
        );
    }

    public void openSavedLists () {
        this.waitForElementAndClick(
                SAVED_LISTS_OF_BOOKMARKS,
                "Can't find saved bookmarks button"
        );
    }

    public void assertElementPresent() {
        Assert.assertTrue(driver.findElement(this.getLocatorByString(TITLE)).isDisplayed());
    }

}
