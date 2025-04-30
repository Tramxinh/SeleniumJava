package sam.com.cmsPages.Pages;

import org.openqa.selenium.By;
import sam.com.constants.keywords.WebUI;

public class CommonPage {

    public CommonPage() {
    }

    public final By searchBox = By.xpath("//input[@id='search']");
    public final By iconSearch = By.xpath("(//button[@type='submit'])[1]");
    public final By searchResult = By.xpath("//form[@id='search-form']/div//div/h3/a");
    public final By iConCart = By.xpath("//div[@id='cart_items']");
    public final By viewCartButton = By.xpath("//a[normalize-space()='View cart']");


    public void searchProduct(String value) {
        WebUI.clickElement(searchBox, 2);
        WebUI.waitForPageLoaded();
        WebUI.setText(searchBox, value);
        WebUI.clickElement(iconSearch, 2);
        WebUI.clickElement(searchResult, 3);
    }

}

