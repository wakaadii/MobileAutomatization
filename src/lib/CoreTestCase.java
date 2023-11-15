package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
//        capabilities.setCapability("app", "C:/programming/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

public class CoreTestCase extends TestCase {

    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android";


    protected AndroidDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception{

        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
//        String platform = System.getenv("PLATFORM");
        String platform = "android";
        if (platform.equals(PLATFORM_ANDROID)) {
            AndroidDriver driver = new AndroidDriver(new URL(appiumURL), capabilities);
        } else if (platform.equals(PLATFORM_ANDROID)) {
            IOSDriver driver = new IOSDriver(new URL(appiumURL), capabilities);
        }
//        driver = new AndroidDriver(new URL(appiumURL), capabilities);
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

    private DesiredCapabilities getCapabilitiesByPlatformEnv () throws Exception {
//        String platform = System.getenv("PLATFORM");
//        заглушка, потому что ни хрена не понятно, где ставить default environment variables
        String platform = "android";
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("appium:platformName", "Android");
            capabilities.setCapability("appium:deviceName", "amt");
            capabilities.setCapability("appium:platformVersion", "8.1");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("appium:appPackage", "org.wikipedia");
            capabilities.setCapability("appium:appActivity", ".main.MainActivity");
            capabilities.setCapability("appium:app", "/Users/shanti/IdeaProjects/MobileAutomatization/apks/org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "amt");
            capabilities.setCapability("platformVersion", "14.1");
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("app", "/Users/shanti/IdeaProjects/MobileAutomatization/apks/org.wikipedia.ios.app");
        } else {
            throw new Exception("can't get run platform from env variable. Platform value = " + platform);
        }

        return capabilities;
    }

    public AppiumDriver setDriverByPlatformEnv (DesiredCapabilities capabilities) throws Exception {
//        String platform = System.getenv("PLATFORM");
//        заглушка, потому что ни хрена не понятно, где ставить default environment variables
        String platform = "android";
        AndroidDriver driver1 = new AndroidDriver(new URL(appiumURL), capabilities);
        IOSDriver driver2 = new IOSDriver(new URL(appiumURL), capabilities);
        if (platform.equals(PLATFORM_ANDROID)) {
            return driver1;
        } else if (platform.equals(PLATFORM_IOS)) {
            return driver2;
        } else {
            throw new Exception("can't get run platform from env variable. Platform value = " + platform);
        }
    }
}
