package com.sam.cms.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.sam.core.config.Configuration;
import com.sam.core.config.TimeoutConstants;
import com.sam.core.driver.DriverManager;
import com.sam.core.helpers.CaptureHelper;
import com.sam.report.AllureManager;
import com.sam.ui.keywords.WebUI;


public class LoginPage extends DashboardPage {
    public LoginPage() {
    }

    private final By inputEmail = By.xpath("//input[@id='email']");
    private final By inputPassword = By.xpath("//input[@id='password']");
    private final By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private final By errorMessage = By.xpath("//div/span[normalize-space()='Invalid login credentials']");
    private final By popup = By.xpath("//div[@class='modal-content position-relative border-0 rounded-0']/button");


    public void enterEmail(String email) {
        WebUI.setText(inputEmail, email);


    }

    public void enterPassword(String password) {
        WebUI.setText(inputPassword, password);

    }

    public void clickLoginButton() {
        WebUI.clickElement(buttonLogin, 1);
    }

    public void clickPopup() {
        DriverManager.getDriver().get(Configuration.URL);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(popup, 1);
    }

    public ManageProfilePage loginCMS(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        return new ManageProfilePage();
    }

    public DashboardPage loginCmsToDashboard(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        return new DashboardPage();
    }

    public void verifyLoginCMSSuccess() {
        DashboardPage dashboardPage = new DashboardPage();
        WebUI.waitForPageLoaded();
        dashboardPage.verifyDashboardMenu();
    }

    @Step("verify Login CMSFail ")
    public void verifyLoginCMSFail() {
        WebUI.waitForElementsVisibled(errorMessage, TimeoutConstants.SHORT_TIMEOUT);
        AllureManager.saveScreenshotPNG();
        CaptureHelper.captureScreenshot("loginCMSFail");
        Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Fail.The error message is not displayed");
    }

}
