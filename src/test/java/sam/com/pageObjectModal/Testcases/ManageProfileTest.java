package sam.com.pageObjectModal.Testcases;

import org.testng.annotations.Test;
import sam.com.common.BaseTest;
import sam.com.constants.constants.ConfigData;
import sam.com.pageObjectModal.Pages.DashboardPage;
import sam.com.pageObjectModal.Pages.LoginPage;
import sam.com.pageObjectModal.Pages.ManageProfilePage;

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
