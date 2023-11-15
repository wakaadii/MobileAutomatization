package tests.ios;

import lib.IosTestCase;
import lib.ui.WelcomeScreenPageObject;
import org.junit.Test;

public class GetStartedTest extends IosTestCase {

    @Test
    public void testPassThroughWelcome() {
        WelcomeScreenPageObject WelcomeScreenPageObject = new WelcomeScreenPageObject(driver);

        WelcomeScreenPageObject.learnMoreWikiLinkIOS();
        WelcomeScreenPageObject.nextWelcomeScreenIOS();

        WelcomeScreenPageObject.waitForNewWaysTextIOS();
        WelcomeScreenPageObject.nextWelcomeScreenIOS();

        WelcomeScreenPageObject.preferredLanguagesLinkIOS();
        WelcomeScreenPageObject.nextWelcomeScreenIOS();

        WelcomeScreenPageObject.learnMoreDataLinkIOS();
        WelcomeScreenPageObject.clickGetStartedButtonIOS();
    }


}
