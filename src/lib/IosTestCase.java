package lib;

import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
//        capabilities.setCapability("app", "C:/programming/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

public class IosTestCase extends TestCase {

    protected IOSDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception{

        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "amt");
        capabilities.setCapability("platformVersion", "14.1");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/shanti/IdeaProjects/MobileAutomatization/apks/org.wikipedia.ios.app");

        driver = new IOSDriver(new URL(appiumURL), capabilities);
        this.rotateScreenPortrait();
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
}
