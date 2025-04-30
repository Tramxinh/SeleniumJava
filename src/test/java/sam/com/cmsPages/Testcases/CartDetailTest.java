package sam.com.cmsPages.Testcases;

import org.testng.annotations.Test;
import sam.com.cmsPages.Pages.*;
import sam.com.common.BaseTest;
import sam.com.constants.constants.ConfigData;

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
        dashboardPage = loginPage.loginCmsToDashboard(ConfigData.EMAIL, ConfigData.PASSWORD);
        String[] listProductName = new String[]{"Shirt", "Hoodie"};
        for (String productName : listProductName) {
            productDetailPage.searchAndSelectProduct(productName);
            productDetailPage.addProductToCart();
        }
        cartDetailPage.viewCart();
        cartDetailPage.checkProductsInCart_WithTotalCalculation();
    }


}
