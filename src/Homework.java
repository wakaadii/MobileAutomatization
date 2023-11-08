import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.WelcomeScreenPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class Homework extends CoreTestCase {

    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    //ex2
    @Test
    public void testComparisonTextOfElement() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearhResult("Java (programming language)");
    }

    //ex3
    @Test
    public void testCanceledSearch() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "Java";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForSearhResult("Object-oriented programming language");

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "field for clear is not founded",
                15
        );

        MainPageObject.WaitForElementNotPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']"),
                "clearing 'search' field didn't work",
                15
        );
    }

    //ex4
    @Test
    public void testCheckWord() {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String searchLine = "Java";

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForSearhResult("Object-oriented programming language");

        int counter = MainPageObject.getAmountOfElements(By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup"));

        for (int i = 1; i <= counter; i++ ) {
            MainPageObject.assertElementContainText(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']/android.view.ViewGroup[@instance = " + i + "]/android.widget.TextView[@index = 0]"),
                "Java",
                "there is no java in text of " + i + " element"
            );
        }
    }

    //ex5
    @Test
    public void testSaveAndDeleteBookmarks(){

            String textFirstSasvedPage = "Java (programming language)";
            String textSecondSavedPage = "JavaScript";
            String nameOfList = "programming languages";

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Skip']"),
                "can't skip welcome screen"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "search bar is not founded",
                5
        );

        MainPageObject.waitForElementAndSend(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Java",
                "can't find search bar or can't send query"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='" + textFirstSasvedPage + "']"),
                "There is no searched text " + textFirstSasvedPage + " is server answer",
                15
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@content-desc='"+ textFirstSasvedPage + "']"),
                "Can't find article "+ textFirstSasvedPage + " title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Can't find 'save' button"
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "can't add to bookmarks",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.EditText[@resource-id = 'org.wikipedia:id/text_input' and @text = 'Name of this list']"),
                "can't find list's add");

        MainPageObject.waitForElementAndSend(
                By.xpath("//android.widget.EditText[@resource-id = 'org.wikipedia:id/text_input' and @text = 'Name of this list']"),
                nameOfList,
                "can't send name for list of bookmarks"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "can't create list of bookmarks"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "can't find <- arrow on first page"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='" + textSecondSavedPage + "']"),
                "There is no searched text " + textSecondSavedPage + " is server answer",
                15
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[@content-desc='" + textSecondSavedPage + "']"),
                "Can't find article " + textSecondSavedPage + " title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Can't find 'save' button"
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "can't add to bookmarks",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text = '" + nameOfList + "']"),
                "can't find list of bookmarks"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "can't find <- arrow on second page"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "can't find <- arrow on search"
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Can't find saved bookmarks button"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.view.ViewGroup[@index = 1]"),
                "can't find bookmarks list"
        );

        MainPageObject.swipeElementLeft(
                By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '" + textFirstSasvedPage + "']"),
                "can't find marked page"
        );

        MainPageObject.WaitForElementNotPresent(
                By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '" + textFirstSasvedPage + "']"),
                "Test can't delete page " + textFirstSasvedPage,
                15
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//android.widget.TextView[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '" + textSecondSavedPage + "']"),
                textSecondSavedPage + " is not in the list",
                15
        );
    }

    //ex6
    @Test
    public void testAssertElementPresent() throws InterruptedException {

        WelcomeScreenPageObject ScipWelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        ScipWelcomeScreenPageObject.scipWelcomeScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearhResult("Java (programming language)");
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "There is no searched text is server answer",
                15);

        assertElementPresent(By.xpath("//android.view.View[@resource-id = 'pcs']/android.view.View[@index = 0]")
                //, "title of this page is not presented"
                );
    }

    public void assertElementPresent(By by) {
        Assert.assertTrue(driver.findElement(by).isDisplayed());
    }

}

