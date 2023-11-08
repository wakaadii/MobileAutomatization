package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLIE_ID = "pcs-edit-section-title-description",
        FOOTER_ELEMENT_XPATH = "//android.view.View[@content-desc='View article in browser']",
        SAVE_BUTTON_ID = "org.wikipedia:id/page_save",
        ADD_TO_LIST_BUTTON_ID = "org.wikipedia:id/snackbar_action",
        NAME_OF_BOOKMARKS_LIST_XPATH = "//android.widget.EditText[@resource-id = 'org.wikipedia:id/text_input' and @text = 'Name of this list']",
        CLOSE_BOOKMARKS_POPUP_BUTTON_XPATH = "//*[@text = 'OK']",
        CLOSE_ARTICLE_BUTTON_XPATH = "//android.widget.ImageButton[@content-desc='Navigate up']",
        SAVED_LISTS_OF_BOOKMARKS_ID = "org.wikipedia:id/nav_tab_reading_lists";

    public ArticlePageObject(AppiumDriver driver) { super(driver); }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresents(By.id(TITLIE_ID), "Can't find article title");
    }
    public String getArticleTitle () {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("contentDescription");
    }

    public void swipeToFooter() {
        this.swipeUpToElement(By.xpath(FOOTER_ELEMENT_XPATH), "can't find the end of article", 30);
    }

    public void addArticleToList(String nameOfFolder) {

        this.waitForElementAndClick(
                By.id(SAVE_BUTTON_ID),
                "Can't find 'save' button"
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON_ID),
                "can't add to bookmarks",
                10
        );

        this.waitForElementAndClick(
                By.xpath(NAME_OF_BOOKMARKS_LIST_XPATH),
                "can't find list's add");

        this.waitForElementAndSend(
                By.xpath(NAME_OF_BOOKMARKS_LIST_XPATH),
                nameOfFolder,
                "can't send name for list of bookmarks"
        );

        this.waitForElementAndClick(
                By.xpath(CLOSE_BOOKMARKS_POPUP_BUTTON_XPATH),
                "can't create list of bookmarks"
        );
    }

    public void closePage() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON_XPATH),
                "can't find <- arrow on page"
        );
    }

    public void openSavedLists () {
        this.waitForElementAndClick(
                By.id(SAVED_LISTS_OF_BOOKMARKS_ID),
                "Can't find saved bookmarks button"
        );
    }
}
