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


    protected AppiumDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception{

        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = setDriverByPlatformEnv(capabilities);
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
        String platform = System.getenv("PLATFORM");
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
        String platform = System.getenv("PLATFORM");

        AppiumDriver driver = new AppiumDriver(new URL(appiumURL), capabilities);

        if (platform.equals(PLATFORM_ANDROID)) {
             driver = new AndroidDriver(new URL(appiumURL), capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
             driver = new IOSDriver(new URL(appiumURL), capabilities);
        } else {
            throw new Exception("can't get run platform from env variable. Platform value = " + platform);
        }
        System.out.println(driver);
        return driver;
    }
}
