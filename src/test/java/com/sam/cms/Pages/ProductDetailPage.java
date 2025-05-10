package com.sam.cms.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import com.sam.cms.Pages.common.CommonPage;
import com.sam.core.config.TimeoutConstants;
import com.sam.core.helpers.ExcelHelper;
import com.sam.report.AllureManager;
import com.sam.ui.keywords.WebUI;

public class ProductDetailPage extends CommonPage {

    private final By productName = By.xpath("//section[@class='mb-4 pt-3']//div[@class='col-xl-7 col-lg-6']//h1[@class='mb-2 fs-20 fw-600']");
    private final By price = By.xpath("//div[@class='product-price']/strong[@id='chosen_price']");
    private final By size = By.xpath("//input[@name='attribute_id_1']/following-sibling::span");
    private final By color = By.xpath("//input[@name='color']");
    private final By quantity = By.xpath("//div[@class='avialable-amount opacity-60']/span[@id='available-quantity']");
    private final By addToCartButton = By.xpath("(//button[@class='btn btn-soft-primary mr-2 add-to-cart fw-600'])[1]");
    private final By popupSuccess = By.xpath("//div[@id='addToCart-modal-body']");
    private final By closePopup = By.xpath("//div[@id='modal-size']/div/button[@aria-label='Close']");

    private static final String SHEET_NAME = "Product_Info";

    @Step("Get product information and save to Excel at row {rowIndex}")
    public void getInfoProductSetToExcel(int rowIndex) {
        // Get Excel helper once and reuse
        ExcelHelper excelHelper = ExcelHelper.getExcelHelper(SHEET_NAME);

        // Capture product details
        String productNameText = WebUI.getText(productName);
        String priceText = WebUI.getText(price);
        String quantityText = WebUI.getText(quantity);

        // Save screenshot for verification
        AllureManager.saveScreenshotPNG();

        // Set data to Excel
        excelHelper.setCellData(productNameText, "Product Name", rowIndex);
        excelHelper.setCellData(priceText, "Prices", rowIndex);

        if (WebUI.checkElementExist(size)) {
            excelHelper.setCellData(WebUI.getText(size), "Size", rowIndex);
        }

        if (WebUI.checkElementExist(color)) {
            excelHelper.setCellData(WebUI.getAttribute(color, "value"), "Color", rowIndex);
        }

        excelHelper.setCellData(quantityText, "Quantity", rowIndex);
    }

    @Step("Reset data in Excel sheet")
    public void resetDataInSheet() {
        ExcelHelper excelHelper = ExcelHelper.getExcelHelper(SHEET_NAME);

        // Use a loop to make code more maintainable
        String[] columns = {"Product Name", "Prices", "Size", "Color", "Quantity"};
        for (String column : columns) {
            excelHelper.setCellData("", column, 1);
        }
    }

    @Step("Add product to cart")
    public void addProductToCart() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(addToCartButton, TimeoutConstants.SHORT_TIMEOUT);
        WebUI.waitForElementsVisibled(popupSuccess, TimeoutConstants.SHORT_TIMEOUT);
        WebUI.clickElement(closePopup, 1);

        // Add screenshot for verification
        AllureManager.saveScreenshotPNG();
    }

}
