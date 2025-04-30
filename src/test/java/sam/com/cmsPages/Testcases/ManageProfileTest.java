package sam.com.cmsPages.Testcases;

import org.testng.annotations.Test;
import sam.com.cmsPages.Pages.DashboardPage;
import sam.com.cmsPages.Pages.LoginPage;
import sam.com.cmsPages.Pages.ManageProfilePage;
import sam.com.common.BaseTest;
import sam.com.constants.constants.ConfigData;

public class ManageProfileTest extends BaseTest {
    ManageProfilePage manageProfilePage;
    DashboardPage dashboardPage;
    LoginPage loginPage;

    @Test
    public void updateManageProfile() {
        loginPage = new LoginPage();
        loginPage.clickPopup();
        manageProfilePage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);
        manageProfilePage.clickManageProfileTab();
        manageProfilePage.inputBasicInfo();

    }
}
