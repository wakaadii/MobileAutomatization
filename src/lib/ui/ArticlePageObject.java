package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLIE_XPATH = "xpath://*[@resource-id = 'pcs-edit-section-title-description']",
        FOOTER_ELEMENT_XPATH = "xpath://android.view.View[@content-desc='View article in browser']",
        SAVE_BUTTON_ID = "id:org.wikipedia:id/page_save",
        ADD_TO_LIST_BUTTON_ID = "id:org.wikipedia:id/snackbar_action",
        NAME_OF_BOOKMARKS_LIST_XPATH = "xpath://android.widget.EditText[@resource-id = 'org.wikipedia:id/text_input' and @text = 'Name of this list']",
        CLOSE_BOOKMARKS_POPUP_BUTTON_XPATH = "xpath://*[@text = 'OK']",
        CLOSE_ARTICLE_BUTTON_XPATH = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        SAVED_LISTS_OF_BOOKMARKS_ID = "id:org.wikipedia:id/nav_tab_reading_lists",
        NAME_OF_BOOKMARKS_LIST_XPATH_TPL = "xpath://android.widget.TextView[@text = '{TEXT}']";

    public ArticlePageObject(AppiumDriver driver) { super(driver); }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresents(TITLIE_XPATH, "Can't find article title");
    }

    public String getArticleTitle () {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("contentDescription");
    }

    public void swipeToFooter() {
        this.swipeUpToElement(FOOTER_ELEMENT_XPATH, "can't find the end of article", 30);
    }

    public void addArticleToNewList(String nameOfFolder) {

        this.waitForElementAndClick(
                SAVE_BUTTON_ID,
                "Can't find 'save' button"
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON_ID,
                "can't add to bookmarks",
                10
        );

        this.waitForElementAndClick(
                NAME_OF_BOOKMARKS_LIST_XPATH,
                "can't find list's add");

        this.waitForElementAndSend(
                NAME_OF_BOOKMARKS_LIST_XPATH,
                nameOfFolder,
                "can't send name for list of bookmarks"
        );

        this.waitForElementAndClick(
                CLOSE_BOOKMARKS_POPUP_BUTTON_XPATH,
                "can't create list of bookmarks"
        );
    }


    private static String getNameOfBookmarksListXpathByName (String folderName) {
        return NAME_OF_BOOKMARKS_LIST_XPATH_TPL.replace("{TEXT}", folderName);
    }

    public void addArticleToList(String folderName) {
        String folderXpath = getNameOfBookmarksListXpathByName(folderName);

        this.waitForElementAndClick(
                SAVE_BUTTON_ID,
                "Can't find 'save' button"
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON_ID,
                "can't add to bookmarks",
                10
        );

        this.waitForElementAndClick(
                folderXpath,
                "can't find list's add");

    }

    public void closePage() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON_XPATH,
                "can't find <- arrow on page"
        );
    }

    public void openSavedLists () {
        this.waitForElementAndClick(
                SAVED_LISTS_OF_BOOKMARKS_ID,
                "Can't find saved bookmarks button"
        );
    }

    public void assertElementPresent() {
        Assert.assertTrue(driver.findElement(this.getLocatorByString(TITLIE_XPATH)).isDisplayed());
    }

}
