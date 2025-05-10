package com.sam.cms.tests;

import org.testng.annotations.Test;

import com.sam.base.BaseTest;
import com.sam.cms.Pages.*;
import com.sam.core.config.Configuration;

public class CheckoutDetailTest extends BaseTest {
    ProductDetailPage productDetailPage;
    CartDetailPage cartDetailPage;
    LoginPage loginPage;
    CheckoutDetailPage checkoutDetailPage;
    DashboardPage dashboardPage;

    @Test
    public void viewProductsInCart() {
        productDetailPage = new ProductDetailPage();
        cartDetailPage = new CartDetailPage();
        loginPage = new LoginPage();
        checkoutDetailPage = new CheckoutDetailPage();
        loginPage.clickPopup();
        dashboardPage = loginPage.loginCmsToDashboard(Configuration.EMAIL, Configuration.PASSWORD);
        checkoutDetailPage = cartDetailPage.viewCart();
        checkoutDetailPage.clickContinueToShippingButton();
        checkoutDetailPage.completeShippingInfo();
        checkoutDetailPage.completePayment();
        checkoutDetailPage.checkCompleteOrderSuccess();

    }

}
