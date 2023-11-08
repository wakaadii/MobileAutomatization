package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedListsPageObject extends MainPageObject{

    private static final String
            NAME_OF_BOOKMARKS_LIST_TPL = "//android.widget.TextView[@text = '{FOLDER_NAME}']",
            BOOKMARK_TO_DELETE_TPL = "//android.widget.TextView[@text = '{TEXT}']";

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
                By.xpath(xpath),
                "can't find bookmarks list " + folderName
        );
    }

    public void waitForArticleToAppearByTitle(String nameOfBookmark) {
        String xpath = getBookmarkXpath(nameOfBookmark);
        this.waitForElementPresents(
                By.xpath(xpath),
                "Can't find saved Article by title " + nameOfBookmark,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String nameOfBookmark) {
        String xpath = getBookmarkXpath(nameOfBookmark);
        this.waitForElementNotPresent(
                By.xpath(xpath),
                "marked page is in the list",
                15
        );
    }

    public void deleteBookmarkFromList(String nameOfBookmark) {
        this.waitForArticleToAppearByTitle(nameOfBookmark);
        String xpath = getBookmarkXpath(nameOfBookmark);
        this.swipeElementLeft(
                By.xpath(xpath),
                "can't find marked page"
        );
        waitForArticleToDisappearByTitle(nameOfBookmark);
    }




}
