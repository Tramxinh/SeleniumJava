package com.sam.cms.tests;


import org.testng.annotations.Test;

import com.sam.base.BaseTest;
import com.sam.cms.Pages.LoginPage;
import com.sam.core.config.Configuration;
import com.sam.core.helpers.CaptureHelper;
import com.sam.core.helpers.ExcelHelper;

public class LoginTest extends BaseTest {
    //khởi tạo đối tương cho  page login
    LoginPage loginPage;


    @Test()
    public void loginSuccess() {
        loginPage = new LoginPage();
        loginPage.clickPopup();
        loginPage.loginCMS(Configuration.EMAIL, Configuration.PASSWORD);
        loginPage.verifyLoginCMSSuccess();
    }

    @Test()
    public void loginFailWithEmailInvalid() {
        CaptureHelper.startRecord("loginFailWithEmailInvalid");
        ExcelHelper.getExcelHelper("Personal_Info");
        loginPage = new LoginPage();
        loginPage.clickPopup();
        loginPage.loginCMS(ExcelHelper.getExcelHelper("Personal_Info").getCellData("Email", 1), ExcelHelper.getExcelHelper("Personal_Info").getCellData("Password", 2));
        loginPage.verifyLoginCMSFail();
        CaptureHelper.stopRecord();
    }


    @Test()
    public void loginFailWithPasswordInvalid() {
        CaptureHelper.startRecord("loginFailWithPasswordInvalid");
        ExcelHelper.getExcelHelper("Personal_Info");
        loginPage = new LoginPage();
        loginPage.clickPopup();
        loginPage.loginCMS(Configuration.EMAIL, ExcelHelper.getExcelHelper("Personal_Info").getCellData("Password", 1));
        loginPage.verifyLoginCMSFail();
        CaptureHelper.stopRecord();
    }

}
