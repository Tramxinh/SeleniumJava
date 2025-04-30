package sam.com.cmsPages.Testcases;

import org.testng.annotations.Test;
import sam.com.cmsPages.Pages.DashboardPage;
import sam.com.cmsPages.Pages.LoginPage;
import sam.com.cmsPages.Pages.ProductDetailPage;
import sam.com.common.BaseTest;
import sam.com.constants.constants.ConfigData;

public class ProductDetailTest extends BaseTest {

    LoginPage loginPage;
    ProductDetailPage productDetailPage;
    DashboardPage dashboardPage;

    @Test
    public void getProductInfoImportToExel() {
        productDetailPage = new ProductDetailPage();
        loginPage = new LoginPage();
        loginPage.clickPopup();
        dashboardPage = loginPage.loginCmsToDashboard(ConfigData.EMAIL, ConfigData.PASSWORD);
        productDetailPage.resetDataInSheet();
        String[] listProduct = new String[]{"Hoodie", "Shirt"};
        for (int index = 0; index < listProduct.length; index++) {
            String productName = listProduct[index];
            productDetailPage.searchAndSelectProduct(productName);
            productDetailPage.getInfoProductSetToExcel(index + 1);
        }

    }

}
