package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject (AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresents(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresents(By by, String errorMessage) {
        return waitForElementPresents(by, errorMessage,5);
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresents(by, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndClick(By by, String errorMessage){
        WebElement element = waitForElementPresents(by, errorMessage, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSend(By by, String message, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresents(by, errorMessage, timeOutInSeconds);
        element.sendKeys(message);
        return element;
    }

    public WebElement waitForElementAndSend(By by, String message, String errorMessage){
        WebElement element = waitForElementPresents(by, errorMessage, 5);
        element.sendKeys(message);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresents(by, errorMessage, 15);
        element.clear();
        return element;
    }

//    public void swipeUp(int timeOfSwipe) {
//        TouchAction action = new TouchAction(driver);
//        Dimension size = driver.manage().window().getSize();
//        int x = size.width / 2;
//        int start_y = (int) (size.height * 0.8);
//        int end_y = (int) (size.height * 0.2);
//        System.out.println(x + " " + start_y + " " + " " +end_y);
//        action
//                .press(PointOption.point(x, start_y))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
//                .moveTo(PointOption.point(x, end_y))
//                .release()
//                .perform();
//    }

    public void swipeUp(int timeInMills){
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);
        int centerX = size.width / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence swipe = new Sequence(finger,1);

        //Двигаем палец на начальную позицию
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(),centerX,(int)startY));
        //Палец прикасается к экрану
        swipe.addAction(finger.createPointerDown(0));

        //Палец двигается к конечной точке
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(timeInMills),
                PointerInput.Origin.viewport(),centerX,(int)endY));

        //Убираем палец с экрана
        swipe.addAction(finger.createPointerUp(0));

        //Выполняем действия
        driver.perform(Arrays.asList(swipe));
    }

    protected void swipeUpQuick () {
        swipeUp(200);
    }

    public void swipeUpToElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;

        while (driver.findElements(by).size() == 0){
            if (alreadySwiped > maxSwipes){
                waitForElementPresents(by, "Can't find element by swiping \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
        swipeUpQuick();

    }

//    public void swipeElementLeft(By by, String errorMessage) {
//        WebElement element = waitForElementPresents(
//                by,
//                errorMessage,
//                10);
//        int leftX = element.getLocation().getX();
//        int rightX = leftX + element.getSize().getWidth();
//        int upperY = element.getLocation().getY();
//        int lowerY = upperY + element.getSize().getHeight();
//        int middleY = (upperY + lowerY)/2;
//
//        TouchAction action = new TouchAction(driver);
//        action
//                .press(PointOption.point(rightX - 10, middleY))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
//                .moveTo(PointOption.point(leftX + 10, middleY))
//                .release()
//                .perform();
//    }

    protected void swipeElementToLeft(By by, String error_message) {
        RemoteWebElement carousel = (RemoteWebElement) waitForElementPresents(
                by,
                error_message,
                10);
        driver.executeScript("gesture: swipe", Map.of("elementId", carousel.getId(), "percentage", 50, "direction", "left"));
    }

    public int getAmountOfElements(By by)    {
        List elementsCount = driver.findElements(by);
        return elementsCount.size();
    }

    private void assertElementNotPresent (By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + by.toString() + "supposed to not presented";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertNoSearchResults(By byResultLocator, By byGetText, String text, String errorMessage) {
        int amountOfElements = getAmountOfElements(byResultLocator);
        String textOfElement = driver.findElement(byGetText).getText();
        if (!textOfElement.equals("No results") & (amountOfElements == 1)){
            String defaultMessage = "An element '" + byResultLocator.toString() + "supposed to not presented";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresents(by, errorMessage, timeoutInSeconds);
        System.out.println(element.getAttribute(attribute));
        return element.getAttribute(attribute);
    }

    public void assertElementHasText(By by, String text, String errorMessage){
        WebElement element = waitForElementPresents(by, errorMessage, 15);
        Assert.assertEquals(
                errorMessage,
                text,
                element.getText()
        );
    }

    public void assertElementContainText(By by, String text, String errorMessage) {
        WebElement element = waitForElementPresents(
                by,
                errorMessage,
                10
        );
        Assert.assertTrue(element.getText().contains(text));
    }

}
