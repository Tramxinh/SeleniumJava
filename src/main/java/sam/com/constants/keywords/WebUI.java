package sam.com.constants.keywords;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import sam.com.constants.constants.ConfigData;
import sam.com.constants.constants.TimeoutConstants;
import sam.com.constants.constants.drivers.DriverManager;
import sam.com.constants.reports.AllureManager;
import sam.com.constants.utils.LogUtils;

import java.time.Duration;
import java.util.List;


public class WebUI {

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigData.PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    @Step("waitForElementsVisibled {1} for element {0}")
    public static void waitForElementsVisibled(By by, int second) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static Boolean checkElementExist(By by) {
        List<WebElement> listElement = getWebElements(by);
        if (listElement.size() > 0) {
            LogUtils.info("Element " + by + " existing.");
            return true;
        } else {
            LogUtils.info("Element " + by + " NOT exist.");
            return false;
        }
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(TimeoutConstants.MEDIUM_TIMEOUT);
    }

    @Step("waitForElementClickable {0} with timeout {1}")
    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.elementToBeClickable(by)); // Use 'by' directly instead of 'getWebElement(by)'
        } catch (Exception e) {
            logConsole("Timeout waiting for the element to be clickable: " + by.toString());
            Assert.fail("Timeout waiting for the element to be clickable: " + by.toString());
        }
    }

    public static void logConsole(Object message) {
        LogUtils.info(message);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    @Step("click Element {0} with timeout is {1}")
    public static void clickElement(By by, int timeout) {
        waitForElementClickable(by, timeout);
        getWebElement(by).click();
        logConsole("Click on element " + by);
    }

    @Step("setText for {0} with value is {1}")
    public static void setText(By by, String value) {
        getWebElement(by).sendKeys(value);
        AllureManager.saveTextLog("Set text " + value + " on element " + by);
        AllureManager.saveScreenshotPNG();
        logConsole("Set text " + value + " on element " + by);
    }

    @Step("getText for {0}")
    public static String getText(By by) {
        String value = getWebElement(by).getText();
        AllureManager.saveTextLog("Get text " + value + " on element " + by);
        AllureManager.saveScreenshotPNG();
        logConsole("Get text " + value + " on element " + by);
        return value;

    }

    public static String getAttribute(By by, String attribute) {
        String value = getWebElement(by).getDomAttribute(attribute);
        logConsole("Get Attribute " + value + " on element " + by);
        return value;

    }
}
