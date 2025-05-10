package com.sam.cms.tests;

import org.testng.annotations.Test;

import com.sam.base.BaseTest;
import com.sam.cms.Pages.*;
import com.sam.core.config.Configuration;

public class CartDetailTest extends BaseTest {
    ProductDetailPage productDetailPage;
    CartDetailPage cartDetailPage;
    LoginPage loginPage;
    ManageProfilePage manageProfilePage;
    DashboardPage dashboardPage;

    @Test
    public void viewProductsInCart() {
        productDetailPage = new ProductDetailPage();
        cartDetailPage = new CartDetailPage();
        loginPage = new LoginPage();
        loginPage.clickPopup();
        dashboardPage = loginPage.loginCmsToDashboard(Configuration.EMAIL, Configuration.PASSWORD);
        String[] listProductName = new String[]{"Shirt", "Hoodie"};
        for (String productName : listProductName) {
            productDetailPage.searchAndSelectProduct(productName);
            productDetailPage.addProductToCart();
        }
        cartDetailPage.viewCart();
        cartDetailPage.verifyProductsInCartWithTotalCalculation();
    }


}
