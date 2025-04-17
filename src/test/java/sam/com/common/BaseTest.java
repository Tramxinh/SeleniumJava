package sam.com.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import sam.com.constants.constants.ConfigData;
import sam.com.constants.constants.drivers.DriverManager;
import sam.com.constants.helpers.CaptureHelper;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void createDrive(String browser) {
        if (ConfigData.BROWSER != null && !ConfigData.BROWSER.isEmpty()) {
            driver = setUpBrowser(ConfigData.BROWSER);
        } else {
            driver = setUpBrowser(browser);
        }
        DriverManager.setDriver(driver);
    }

    public WebDriver setUpBrowser(String browserName) {
        switch (browserName.trim().toLowerCase()) {
            case "safari":
                driver = initSafariDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            default:
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        // Disable notifications
        options.addArguments("--disable-notifications");
        // Disable password saving and filling
        options.addArguments("--password-store=basic");
        // Disable Chrome's built-in password manager
        options.addArguments("--disable-features=PasswordManager");
        // Optional: Use incognito mode
        options.addArguments("--incognito");
        // Optional: Disable all extensions
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initSafariDriver() {
        driver = new SafariDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }


    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            CaptureHelper.captureScreenshot("Case Fail " + result.getName());
        }
    }

    @AfterMethod
    public void closeDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }

};

