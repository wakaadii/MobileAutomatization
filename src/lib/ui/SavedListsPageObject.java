package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SavedListsPageObject extends MainPageObject{

    private static final String
            NAME_OF_BOOKMARKS_LIST_TPL = "xpath://android.widget.TextView[@text = '{FOLDER_NAME}']",
            BOOKMARK_TO_DELETE_TPL = "xpath://android.widget.TextView[@text = '{TEXT}']";

    private static String getFolderXpathByName (String folderName) {
        return NAME_OF_BOOKMARKS_LIST_TPL.replace("{FOLDER_NAME}", folderName);
    }

    private static String getBookmarkXpath(String bookmarkName) {
        return BOOKMARK_TO_DELETE_TPL.replace("{TEXT}", bookmarkName);
    }

    public SavedListsPageObject(AppiumDriver driver) { super(driver); }

    public void openListOfBookmarks(String folderName) {
        String xpath = getFolderXpathByName(folderName);
        this.waitForElementAndClick(
                xpath,
                "can't find bookmarks list " + folderName
        );
    }

    public void waitForArticleToAppearByTitle(String nameOfBookmark) {
        String xpath = getBookmarkXpath(nameOfBookmark);
        this.waitForElementPresents(
                xpath,
                "Can't find saved Article by title " + nameOfBookmark,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String nameOfBookmark) {
        String xpath = getBookmarkXpath(nameOfBookmark);
        this.waitForElementNotPresent(
                xpath,
                "marked page is in the list",
                15
        );
    }

    public void deleteBookmarkFromList(String nameOfBookmark) {
        this.waitForArticleToAppearByTitle(nameOfBookmark);
        String xpath = getBookmarkXpath(nameOfBookmark);
        this.swipeElementToLeft(
                xpath,
                "can't find marked page"
        );
        waitForArticleToDisappearByTitle(nameOfBookmark);
    }




}
