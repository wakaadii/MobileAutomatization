package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomeScreenPageObject extends MainPageObject{

    private static final String
            SCIP_WELCOME_SCREEN_BUTTON_XPATH_ANDROID = "//*[@text='Skip']",
            NEXT_IOS_BYUTTON_XPATH_IOS = "//*[@type = 'XCUIElementTypeButton' and @name = 'Next']",
            LEARN_MORE_ABOUT_WIKI_XPATH_IOS = "//*[@name = 'Learn more about Wikipedia']",
            NEW_WAYS_TO_EXPLORE_ID_IOS = "New ways to explore",
            ADD_OR_EDIT_PREFERED_LANGUAGES_XPATH_IOS = "//*[@name = 'Add or edit preferred languages']",
            LEARN_NORE_ABOUT_DATA_COLLECTED_XPATH_IOS = "//*[@type = 'XCUIElementTypeStaticText' and @name = 'Learn more about data collected']",
            GET_STARTED_BUTTON_XPATH_IOS = "//*[@type = 'XCUIElementTypeButton' and @name = 'Get started']";

    public WelcomeScreenPageObject(AppiumDriver driver) {
        super(driver);
    }
    public void scipWelcomeScreen (){
        this.waitForElementAndClick(By.xpath(SCIP_WELCOME_SCREEN_BUTTON_XPATH_ANDROID), "can't skip welcome screen");
    }

    public void nextWelcomeScreenIOS () {
        this.waitForElementAndClick(By.xpath(NEXT_IOS_BYUTTON_XPATH_IOS), "can't find 'next' button");
    }

    public void learnMoreWikiLinkIOS () {
        this.waitForElementPresents(By.xpath(LEARN_MORE_ABOUT_WIKI_XPATH_IOS), "there is no element 'Learn more'");
    }

    public void waitForNewWaysTextIOS () {
        this.waitForElementPresents(By.id(NEW_WAYS_TO_EXPLORE_ID_IOS), "there is no element 'New ways to explore'");
    }

    public void preferredLanguagesLinkIOS () {
        this.waitForElementPresents(By.xpath(ADD_OR_EDIT_PREFERED_LANGUAGES_XPATH_IOS), "there is no element 'Add or edit preferred languages'");
    }

    public void learnMoreDataLinkIOS() {
        waitForElementPresents(By.xpath(LEARN_NORE_ABOUT_DATA_COLLECTED_XPATH_IOS),"there is no element 'Learn more about data collected'");
    }
    public void clickGetStartedButtonIOS() {
        this.waitForElementAndClick(By.xpath(GET_STARTED_BUTTON_XPATH_IOS), "can't find 'Get started' button");

    }
}
