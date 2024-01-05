package com.qa.pages;


import com.google.common.collect.ImmutableList;
import com.qa.engine.DriverManager;
import com.qa.engine.GlobalParams;
import com.qa.utils.TestUtils;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.Point;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static com.qa.engine.ProjectBase.logError;
import static com.qa.engine.ProjectBase.logInfo;

public abstract class BasePage {
    private final WebDriver driver;
    protected TestUtils utils = new TestUtils();
    public static RequestSpecification reqSpec;
    public static Response response;

    public BasePage() {
        this.driver = new DriverManager().getDriver();
        try {
            if (new GlobalParams().getPlatformName().equalsIgnoreCase("ANDROID") || new GlobalParams().getPlatformName().equalsIgnoreCase("IOS"))
                PageFactory.initElements(new AppiumFieldDecorator(this.driver, Duration.ofSeconds(30)), this);
            else
                PageFactory.initElements(this.driver, this);

        } catch (Exception e) {
            logInfo("Enter correct Domain " + e);
        }
    }

    protected abstract void waitForAppToLoad();

    public WebDriver getDriver() {
        return this.driver;
    }

    public void waitForVisibility(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void waitForVisibility(WebElement webElement, long time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void waitForVisibility(WebElement webElement, long time, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            logInfo(message);
            e.printStackTrace();
        }
    }

    public void waitForVisibility(By webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void clear(WebElement webElement) {
        waitForVisibility(webElement);
        try {
            webElement.clear();
            logInfo("Text has been cleared");
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void clear(WebElement webElement, String message) {
        waitForVisibility(webElement);
        try {
            webElement.clear();
            logInfo(message);
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void click(WebElement webElement) {
        try {
            webElement.click();
            logInfo("Clicking Element");
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void click(WebElement webElement, String msg) {
        try {
            webElement.click();
            logInfo(msg);
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void click(By webElement, String msg) {
        waitForVisibility(webElement);
        try {
            driver.findElement(webElement).click();
            logInfo(msg);
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void click(By webElement) {
        waitForVisibility(webElement);
        try {
            driver.findElement(webElement).click();
            logInfo("Clicking Element");
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void sendKeys(WebElement webElement, String txt) {
        waitForVisibility(webElement);
        try {
            webElement.sendKeys(txt);
            logInfo("Sending Text");
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void clickAndSendKeys(WebElement webElement, String txt) {
        webElement.click();
        try {
            webElement.sendKeys(txt);
            logInfo("Sending Text");
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void clickAndSendKeys(WebElement webElement, String txt, String message) {
        webElement.click();
        try {
            webElement.sendKeys(txt);
            logInfo(message);
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void clickAndSendKeys(By webElement, String txt) {
        try {
            getDriver().findElement(webElement).click();
            getDriver().findElement(webElement).sendKeys(txt);
            logInfo("Sending Text");
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void clickAndSendKeys(By webElement, String txt, String msg) {
        try {
            getDriver().findElement(webElement).click();
            getDriver().findElement(webElement).sendKeys(txt);
            logInfo(msg);
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public void sendKeys(WebElement webElement, String txt, String msg) {
        try {
            waitForVisibility(webElement);
            webElement.sendKeys(txt);
            logInfo(msg);
        } catch (Exception e) {
            logInfo("Element not found");
            e.printStackTrace();
        }
    }

    public String getAttribute(WebElement webElement, String attribute) {
        waitForVisibility(webElement);
        return webElement.getAttribute(attribute);
    }

    public String getAttribute(By webElement, String attribute) {
        return driver.findElement(webElement).getAttribute(attribute);
    }

    public String getTextWithAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public void scroll(String direction, double scrollRatio) {
        if (scrollRatio < 0 || scrollRatio > 1)
            throw new Error("Scroll ratio must be between 0 to 1");

        Dimension size = getDriver().manage().window().getSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));

        int bottom = (int) (midPoint.y - (size.height * scrollRatio) * 0.5);
        int top = (int) (midPoint.y + (size.height * scrollRatio) * 0.5);
        int right = (int) (midPoint.x - (size.width * scrollRatio) * 0.5);
        int left = (int) (midPoint.x + (size.width * scrollRatio) * 0.5);

        logInfo("Swiping to " + direction.toUpperCase());

        switch (direction.toUpperCase()) {
            case "UP" -> swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom));
            case "DOWN" -> swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top));
            case "LEFT" -> swipe(new Point(left, midPoint.y), new Point(right, midPoint.y));
            case "RIGHT" -> swipe(new Point(right, midPoint.y), new Point(left, midPoint.y));
        }
    }

    public void scroll(String direction) {
        double scrollRatio = 0.75;

        Dimension size = getDriver().manage().window().getSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));

        int bottom = (int) (midPoint.y - (size.height * scrollRatio) * 0.5);
        int top = (int) (midPoint.y + (size.height * scrollRatio) * 0.5);
        int right = (int) (midPoint.x - (size.width * scrollRatio) * 0.5);
        int left = (int) (midPoint.x + (size.width * scrollRatio) * 0.5);

        logInfo("Swiping to " + direction.toUpperCase());

        switch (direction.toUpperCase()) {
            case "UP" -> swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom));
            case "DOWN" -> swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top));
            case "LEFT" -> swipe(new Point(left, midPoint.y), new Point(right, midPoint.y));
            case "RIGHT" -> swipe(new Point(right, midPoint.y), new Point(left, midPoint.y));
        }
    }

    public void scrollWithCoordinates(String direction, double scrollRatio, int x, int y) {
        if (scrollRatio < 0 || scrollRatio > 1)
            throw new Error("Scroll ratio must be between 0 to 1");

        Dimension size = getDriver().manage().window().getSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));

        midPoint.x = x;
        midPoint.y = y;

        int bottom = (int) (midPoint.y - (size.height * scrollRatio) * 0.5);
        int top = (int) (midPoint.y + (size.height * scrollRatio) * 0.5);
        int right = (int) (midPoint.x - (size.width * scrollRatio) * 0.5);
        int left = (int) (midPoint.x + (size.width * scrollRatio) * 0.5);

        logInfo("Swiping to " + direction.toUpperCase());

        switch (direction.toUpperCase()) {
            case "UP" -> swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom));
            case "DOWN" -> swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top));
            case "LEFT" -> swipe(new Point(left, midPoint.y), new Point(right, midPoint.y));
            case "RIGHT" -> swipe(new Point(right, midPoint.y), new Point(left, midPoint.y));
        }
    }

    private void swipe(Point source, Point target) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence dragNDrop = new Sequence(finger, 0);
        dragNDrop.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), source.x, source.y));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), target.x, target.y));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((RemoteWebDriver) driver).perform(ImmutableList.of(dragNDrop));
    }

    public void tapOnPosition(int pointX, int pointY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence clickPosition = new Sequence(finger, 1);
        clickPosition.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), pointX, pointY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((RemoteWebDriver) driver).perform(List.of(clickPosition));
    }

    public void scrollUntilElementPresent(By xpath, String direction, double ratio) {
        Instant timeout = Instant.now().plusSeconds(60);

        while (getDriver().findElements(xpath).size() == 0) {
            scroll(direction, ratio);

            if (getDriver().findElements(xpath).size() == 1)
                break;

            if (Instant.now().isAfter(timeout))
                throw new TimeoutException("Element is not present in the page");
        }
    }

    public void scrollUntilElementDisplayed(By xpath, String direction, double ratio) {
        Instant timeout = Instant.now().plusSeconds(60);

        while (!getDriver().findElement(xpath).isDisplayed()) {
            scroll(direction, ratio);

            if (getDriver().findElement(xpath).isDisplayed())
                break;

            if (Instant.now().isAfter(timeout))
                throw new TimeoutException("Element is not present in the page");
        }
    }

    public void scrollUntilElementPresent(By xpath, String direction, double ratio, long seconds) {
        Instant timeout = Instant.now().plusSeconds(seconds);

        while (getDriver().findElements(xpath).size() == 0) {
            scroll(direction, ratio);

            if (getDriver().findElements(xpath).size() == 1)
                break;

            if (Instant.now().isAfter(timeout))
                throw new TimeoutException("Element is not present in the page");
        }
    }
}