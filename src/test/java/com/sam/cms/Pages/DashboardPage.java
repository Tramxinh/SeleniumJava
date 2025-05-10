package com.sam.cms.Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.sam.cms.Pages.common.CommonPage;
import com.sam.core.config.TimeoutConstants;
import com.sam.core.driver.DriverManager;
import com.sam.ui.keywords.WebUI;

public class DashboardPage extends CommonPage {

    public DashboardPage() {
    }

    private final By dashboardMenu = By.xpath("(//span[@class='aiz-side-nav-text'][normalize-space()='Dashboard'])[1]");

    public void verifyDashboardMenu() {
        WebUI.waitForElementsVisibled(dashboardMenu, TimeoutConstants.SHORT_TIMEOUT);
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://cms.anhtester.com/dashboard", "Fail.The current Url not matching");
        Assert.assertTrue(WebUI.getWebElement(dashboardMenu).isDisplayed(), "Fail.The Dashboard is not displayed");
    }
}

