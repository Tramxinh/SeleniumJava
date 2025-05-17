package com.sam.cms.Pages.common;

import com.sam.core.config.TimeoutConstants;
import com.sam.ui.keywords.WebUI;
import org.openqa.selenium.By;

public class CommonPage {

    public CommonPage() {
    }

    public final By searchBox = By.xpath("//input[@id='search']");
    public final By iconSearch = By.xpath("(//button[@type='submit'])[1]");
    public final By searchResult = By.xpath("//form[@id='search-form']/div//div/h3/a");
    public final By iConCart = By.xpath("//div[@id='cart_items']");
    public final By viewCartButton = By.xpath("//a[normalize-space()='View cart']");
    public final By cookieAcceptButton = By.xpath("//button[contains(text(),'Ok. I Understood')]");

    public void searchAndSelectProduct(String value) {
        WebUI.clickElement(searchBox, TimeoutConstants.SHORT_TIMEOUT);
        WebUI.waitForPageLoaded();
        WebUI.setText(searchBox, value);
        WebUI.clickElement(iconSearch, TimeoutConstants.SHORT_TIMEOUT);
        WebUI.clickElement(searchResult, TimeoutConstants.MEDIUM_TIMEOUT);
    }

    public void acceptCookieAlert() {
        try {
            if (WebUI.checkElementExist(cookieAcceptButton)) {
                WebUI.clickElement(cookieAcceptButton, TimeoutConstants.VERY_SHORT_TIMEOUT);
            }
        } catch (Exception e) {
            System.out.println("Cookie alert not displayed");
        }
    }

}

