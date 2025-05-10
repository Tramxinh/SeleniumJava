package com.sam.cms.tests;

import org.testng.annotations.Test;

import com.sam.base.BaseTest;
import com.sam.cms.Pages.DashboardPage;
import com.sam.cms.Pages.LoginPage;
import com.sam.cms.Pages.ManageProfilePage;
import com.sam.core.config.Configuration;

public class ManageProfileTest extends BaseTest {
    ManageProfilePage manageProfilePage;
    DashboardPage dashboardPage;
    LoginPage loginPage;

    @Test
    public void updateManageProfile() {
        loginPage = new LoginPage();
        loginPage.clickPopup();
        manageProfilePage = loginPage.loginCMS(Configuration.EMAIL, Configuration.PASSWORD);
        manageProfilePage.clickManageProfileTab();
        manageProfilePage.inputBasicInfo();

    }
}
