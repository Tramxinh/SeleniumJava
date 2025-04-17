package sam.com.pageObjectModal.Testcases;

import org.testng.annotations.Test;
import sam.com.common.BaseTest;
import sam.com.constants.constants.ConfigData;
import sam.com.pageObjectModal.Pages.*;

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
        dashboardPage = loginPage.loginCmsToDashboard(ConfigData.EMAIL, ConfigData.PASSWORD);
        checkoutDetailPage = cartDetailPage.viewCart();
        checkoutDetailPage.clickContinueToShippingButton();
        checkoutDetailPage.completeShippingInfo();
        checkoutDetailPage.completePayment();
        checkoutDetailPage.checkCompleteOrderSuccess();

    }

}
