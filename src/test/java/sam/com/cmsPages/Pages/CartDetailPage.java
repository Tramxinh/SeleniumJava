package sam.com.cmsPages.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import sam.com.constants.helpers.ExcelHelper;
import sam.com.constants.keywords.WebUI;
import sam.com.constants.reports.AllureManager;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CartDetailPage extends CommonPage {

    private final By continueToShippingButton = By.xpath("//section[@id='cart-summary']//div[@class='col-md-6 text-center text-md-right']/a");
    private final By productInTable = By.xpath("//section[@id='cart-summary']//child::div[@class='mb-4']/ul/li");
    private final By productName = By.xpath(".//span[contains(@class, 'fs-14')]");
    private final By productPrice = By.xpath(".//span[contains(@class, 'fw-600') and contains(text(), '$')]");
    private final By productQuantity = By.xpath("..//input[contains(@class, 'input-number')]");
    private final By totalPrice = By.xpath(".//span[contains(@class, 'text-primary')]");

    @Step("View cart and proceed to checkout")
    public CheckoutDetailPage viewCart() {
        WebUI.clickElement(iConCart, 2);
        WebUI.clickElement(viewCartButton, 1);
        return new CheckoutDetailPage();
    }

    @Step("Continue to shipping")
    public void clickContinueToShippingButton() {
        WebUI.clickElement(continueToShippingButton, 1);
    }

    @Step("Verify products in cart with total calculation")
    public void checkProductsInCart_WithTotalCalculation() {
        WebUI.checkElementExist(productInTable);
        AllureManager.saveScreenshotPNG();

        List<WebElement> items = WebUI.getWebElements(productInTable);

        // Load Excel data
        ExcelHelper.getExcelHelper("Personal_Info");
        List<Map<String, String>> excelDataList = ExcelHelper.getAllData();

        for (WebElement item : items) {
            verifyProductDetails(item, excelDataList);
        }
    }

    private void verifyProductDetails(WebElement item, List<Map<String, String>> excelDataList) {
        // Extract product information
        ProductInfo productInfo = extractProductInfo(item);

        // Find matching Excel row
        Map<String, String> matchingRow = findMatchingExcelRow(productInfo.name, excelDataList);

        // Verify product details
        verifyProductAttributes(productInfo, matchingRow);

        // Verify total calculation
        verifyTotalCalculation(productInfo);
    }

    private ProductInfo extractProductInfo(WebElement item) {
        ProductInfo info = new ProductInfo();

        // Extract product name, color, and size
        String productInfoText = item.findElement(productName).getText();
        String[] productInfoArr = productInfoText.split("-");
        info.name = productInfoArr.length > 0 ? productInfoArr[0].trim() : "";
        info.color = productInfoArr.length > 1 ? productInfoArr[1].trim() : "";
        info.size = productInfoArr.length > 2 ? productInfoArr[2].trim() : "";

        // Extract price, quantity, and total
        List<WebElement> prices = item.findElements(productPrice);
        info.price = prices.size() > 0 ? prices.get(0).getText() : "N/A";
        info.quantity = item.findElement(productQuantity).getAttribute("value");
        info.total = item.findElement(totalPrice).getText();

        return info;
    }

    private Map<String, String> findMatchingExcelRow(String productName, List<Map<String, String>> excelDataList) {
        return excelDataList.stream()
                .filter(row -> productName.equalsIgnoreCase(row.get("Product Name")))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Product not found in Excel: " + productName));
    }

    private void verifyProductAttributes(ProductInfo info, Map<String, String> matchingRow) {
        Assert.assertEquals(info.color, matchingRow.get("Color"), "Product color is not correct");
        Assert.assertEquals(info.size, matchingRow.get("Size"), "Product size is not correct");
        Assert.assertEquals(info.price, matchingRow.get("Prices"), "Product price is not correct");
        Assert.assertEquals(info.quantity, "1", "Quantity is not correct");
    }

    private void verifyTotalCalculation(ProductInfo info) {
        double price = parseCurrency(info.price);
        int quantity = Integer.parseInt(info.quantity);
        double expectedTotal = price * quantity;
        double actualTotal = parseCurrency(info.total);

        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Product Total is not correct");
    }

    private double parseCurrency(String currencyText) {
        try {
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
            Number number = format.parse(currencyText);
            return number.doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException("Cannot parse currency: " + currencyText, e);
        }
    }

    // Helper class to organize product information
    private static class ProductInfo {
        String name;
        String color;
        String size;
        String price;
        String quantity;
        String total;
    }
}
