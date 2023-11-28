package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SavedListsPageObject;
import lib.ui.android.AndroidSavedListsPageObject;
import lib.ui.ios.IOSSavedListsPageObject;

public class SavedListsPageObjectFactory {
    public static SavedListsPageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSavedListsPageObject(driver);
        } else {
            return new IOSSavedListsPageObject(driver);
        }
    }


}
