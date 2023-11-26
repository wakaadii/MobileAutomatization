package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

     static {
         SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']";
         SEARCH_INPUT = "xpath://*[@text='Search Wikipedia']";
         SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
         SEARCH_FIELD = "id:org.wikipedia:id/search_src_text";
         SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']";
         SEARCH_RESULT_LOCATOR_XPATH = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/{SUBSTRING}";
         SEARCH_RESULTS_LIST_ID = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']";
     }
    public AndroidSearchPageObject (AppiumDriver driver) {
        super(driver);
    }
}
