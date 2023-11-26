package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomeScreenPageObject;
import lib.ui.factory.WelcomeScreenPageObjectFactory;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class CoreTestCase extends TestCase {


    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomeScreen();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void moveToBackground(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private void skipWelcomeScreen () {
        WelcomeScreenPageObject WelcomeScreenPageObject = WelcomeScreenPageObjectFactory.get(driver);
        WelcomeScreenPageObject.clickSkip();
    }


}
