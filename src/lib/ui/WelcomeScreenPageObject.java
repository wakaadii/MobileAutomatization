package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomeScreenPageObject extends MainPageObject{

    private static final String
            SCIP_WELCOME_SCREEN_BUTTON = "//*[@text='Skip']";

    public WelcomeScreenPageObject(AppiumDriver driver) {
        super(driver);
    }
    public void scipWelcomeScreen (){
        this.waitForElementAndClick(By.xpath(SCIP_WELCOME_SCREEN_BUTTON), "can't skip welcome screen");
    }
}
