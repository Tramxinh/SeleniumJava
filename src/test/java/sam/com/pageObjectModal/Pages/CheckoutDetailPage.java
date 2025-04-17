package sam.com.pageObjectModal.Pages;

import org.openqa.selenium.By;
import sam.com.constants.keywords.WebUI;

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
        WebUI.clickElement(continueToPaymentButton, 2);

    }

    public void completePayment() {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(agreeCheckbox, 2);
        WebUI.clickElement(completeOrderButton, 4);
    }

    public void checkCompleteOrderSuccess() {
        WebUI.waitForPageLoaded();
        WebUI.getText(completeOrderSuccess);
        WebUI.getText(orderCode);
    }

}
