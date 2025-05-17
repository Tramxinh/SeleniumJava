package com.sam.cms.Pages;

import com.sam.core.config.TimeoutConstants;
import com.sam.ui.keywords.WebUI;
import org.openqa.selenium.By;

public class CheckoutDetailPage extends CartDetailPage {

    private final By addressInforRadio = By.xpath("(//section[@class='mb-4 gry-bg']//div[@class='shadow-sm bg-white p-4 rounded mb-4']/div/div)[1]");
    private final By continueToDeliveryInfo = By.xpath("//section[@class='mb-4 gry-bg']//div[@class='col-md-6 text-center text-md-right']/button");
    private final By continueToPaymentButton = By.xpath("(//section[@class='py-4 gry-bg']//form//div[2])/button");
    private final By agreeCheckbox = By.xpath("//input[@id='agree_checkbox']/following-sibling::span[1]");
    private final By completeOrderButton = By.xpath("//div[@class='row align-items-center pt-3']//div[2]/button");
    private final By completeOrderSuccess = By.xpath("//section[@class='py-4']//div/h1");
    private final By orderCode = By.xpath("(//div[@class='card-body']/div[1])/h2/span");

    public void completeShippingInfo() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(addressInforRadio, 1);
        WebUI.clickElement(continueToDeliveryInfo, 1);
        WebUI.clickElement(continueToPaymentButton, TimeoutConstants.SHORT_TIMEOUT);

    }

    public void completePayment() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(agreeCheckbox, TimeoutConstants.SHORT_TIMEOUT);
        WebUI.clickElement(completeOrderButton, TimeoutConstants.LONG_TIMEOUT);
    }

    public void checkCompleteOrderSuccess() {
        WebUI.waitForPageLoaded();
        this.acceptCookieAlert();
        WebUI.getText(completeOrderSuccess);
        WebUI.getText(orderCode);
    }

}
