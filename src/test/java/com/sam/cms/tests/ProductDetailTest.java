package com.sam.cms.tests;

import org.testng.annotations.Test;

import com.sam.base.BaseTest;
import com.sam.cms.Pages.DashboardPage;
import com.sam.cms.Pages.LoginPage;
import com.sam.cms.Pages.ProductDetailPage;
import com.sam.core.config.Configuration;

public class ProductDetailTest extends BaseTest {

    LoginPage loginPage;
    ProductDetailPage productDetailPage;
    DashboardPage dashboardPage;

    @Test
    public void getProductInfoImportToExel() {
        productDetailPage = new ProductDetailPage();
        loginPage = new LoginPage();
        loginPage.clickPopup();
        dashboardPage = loginPage.loginCmsToDashboard(Configuration.EMAIL, Configuration.PASSWORD);
        productDetailPage.resetDataInSheet();
        String[] listProduct = new String[]{"Hoodie", "Shirt"};
        for (int index = 0; index < listProduct.length; index++) {
            String productName = listProduct[index];
            productDetailPage.searchAndSelectProduct(productName);
            productDetailPage.getInfoProductSetToExcel(index + 1);
        }

    }

}
