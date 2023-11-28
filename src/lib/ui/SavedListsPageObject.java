package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class SavedListsPageObject extends MainPageObject{

    protected static String
            NAME_OF_BOOKMARKS_LIST_TPL,
            BOOKMARK_TO_DELETE_TPL,
            CLOSE_SYNC_POPUP,
            BUTTON_TO_DELETE;

    private static String getFolderByName(String folderName) {
        return NAME_OF_BOOKMARKS_LIST_TPL.replace("{FOLDER_NAME}", folderName);
    }

    private static String getBookmark(String bookmarkName) {
        return BOOKMARK_TO_DELETE_TPL.replace("{TEXT}", bookmarkName);
    }

    public SavedListsPageObject(AppiumDriver driver) { super(driver); }

    public void openListOfBookmarks(String folderName) {
        String xpath = getFolderByName(folderName);
        this.waitForElementAndClick(
                xpath,
                "can't find bookmarks list " + folderName
        );
    }

    public void waitForArticleToAppearByTitle(String nameOfBookmark) {
        String xpath = getBookmark(nameOfBookmark);
        this.waitForElementPresents(
                xpath,
                "Can't find saved Article by title " + nameOfBookmark,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String nameOfBookmark) {
        String xpath = getBookmark(nameOfBookmark);
        this.waitForElementNotPresent(
                xpath,
                "marked page is in the list",
                15
        );
    }

    public void deleteBookmarkFromList(String nameOfBookmark) {
        if (Platform.getInstance().isAndroid()) {
            this.waitForArticleToAppearByTitle(nameOfBookmark);
            String xpath = getBookmark(nameOfBookmark);
            this.swipeElementToLeft(
                    xpath,
                    "can't find marked page"
            );
            waitForArticleToDisappearByTitle(nameOfBookmark);
        } else {
            waitForElementAndClick(CLOSE_SYNC_POPUP, "Can't close sync popup");
            this.waitForArticleToAppearByTitle(nameOfBookmark);
            String xpath = getBookmark(nameOfBookmark);
            this.swipeElementToLeft(
                    xpath,
                    "can't find marked page"
            );
            this.clickElementToTheRightUpCorner(BUTTON_TO_DELETE, "Can't click on delete bookmark button");
            waitForArticleToDisappearByTitle(nameOfBookmark);
        }
    }




}
