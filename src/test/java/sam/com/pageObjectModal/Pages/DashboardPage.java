package sam.com.pageObjectModal.Pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import sam.com.constants.constants.drivers.DriverManager;
import sam.com.constants.keywords.WebUI;

public class DashboardPage extends CommonPage {

    public DashboardPage() {
    }

    private final By dashboardMenu = By.xpath("(//div[@class='sidemnenu mb-3']/ul/li)[1]");

    public void verifyDashboardMenu() {
        WebUI.waitForElementsVisibled(dashboardMenu, 2);
        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://cms.anhtester.com/dashboard", "Fail.The current Url not matching");
        Assert.assertTrue(WebUI.getWebElement(dashboardMenu).isDisplayed(), "Fail.The Dashboard is not displayed");
    }
}

