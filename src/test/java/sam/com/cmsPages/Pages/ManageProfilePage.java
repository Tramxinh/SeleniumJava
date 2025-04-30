package sam.com.cmsPages.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import sam.com.constants.constants.ConfigData;
import sam.com.constants.constants.TimeoutConstants;
import sam.com.constants.constants.drivers.DriverManager;
import sam.com.constants.helpers.CaptureHelper;
import sam.com.constants.helpers.ExcelHelper;
import sam.com.constants.keywords.WebUI;
import sam.com.constants.utils.LogUtils;

public class ManageProfilePage extends CommonPage {
    // Constants
    private static final String SHEET_NAME = "Personal_Info";
    private static final String IMAGE_PATH = "/src/test/resources/FileImage/samcuptest1.png";

    // Locators
    private final By manageProfileTab = By.xpath("(//div[@class='sidemnenu mb-3']/ul/li)[7]");
    private final By yourNameInfo = By.xpath("(//div[@class='card-body']/div[1])/div/input");
    private final By yourPhoneInfo = By.xpath("(//div[@class='card-body']/div[2])/div/input");
    private final By photoInfo = By.xpath("(//div[@class='card-body']/div[3])//div[contains(text(),'Browse')]");
    private final By passwordInfo = By.xpath("(//div[@class='card-body']/div[4])/div/input");
    private final By passwordConfirmInfo = By.xpath("(//div[@class='card-body']/div[5])/div/input");
    private final By updateButton = By.xpath("//div/button[normalize-space()='Update Profile']");
    private final By uploadNewButton = By.xpath("//div[@id='aizUploaderModal']//a[normalize-space()='Upload New']");
    private final By uploadFile = By.xpath("//input[@class='uppy-Dashboard-input']");
    private final By searchFile = By.xpath("//input[@placeholder='Search your files']");
    private final By selectFile = By.xpath("(//div[@class='aiz-file-box'])[1]");
    private final By addFileButton = By.xpath("//div[@id='aizUploaderModal']//div/div[3]/button");

    @Step("Click Manage Profile Tab")
    public void clickManageProfileTab() {
        WebUI.clickElement(manageProfileTab, TimeoutConstants.MEDIUM_TIMEOUT);
        WebUI.waitForPageLoaded();
    }

    @Step("Update Profile Info")
    public void inputBasicInfo() {
        CaptureHelper.captureScreenshot("Update profile Page");
        try {
            ExcelHelper excelHelper = ExcelHelper.getExcelHelper(SHEET_NAME);

            // Set name
            clearAndSetText(yourNameInfo, excelHelper.getCellData("Name", 1));

            // Set phone
            clearAndSetText(yourPhoneInfo, excelHelper.getCellData("Phone", 1));

            // Upload and select photo
            uploadPhoto();
            selectPhoto();

            // Set password fields
            WebUI.setText(passwordInfo, ConfigData.PASSWORD);
            WebUI.setText(passwordConfirmInfo, ConfigData.PASSWORD);

            // Update profile
            WebUI.clickElement(updateButton, TimeoutConstants.SHORT_TIMEOUT);
        } catch (Exception e) {
            LogUtils.error("Error in inputBasicInfo: " + e.getMessage());
        }
    }

    private void clearAndSetText(By locator, String text) {
        WebUI.clickElement(locator, TimeoutConstants.VERY_SHORT_TIMEOUT);
        Actions action = new Actions(DriverManager.getDriver());
        action.keyDown(WebUI.getWebElement(locator), Keys.COMMAND)
                .sendKeys("A")
                .keyUp(Keys.COMMAND)
                .sendKeys(text)
                .build()
                .perform();
    }

    @Step("select Photo")
    public void selectPhoto() {
        try {
            CaptureHelper.startRecord("loginFailWithEmailInvalid");
            ExcelHelper excelHelper = ExcelHelper.getExcelHelper(SHEET_NAME);

            WebUI.clickElement(photoInfo, TimeoutConstants.VERY_SHORT_TIMEOUT);
            WebUI.clickElement(searchFile, TimeoutConstants.SHORT_TIMEOUT);

            String fileName = excelHelper.getCellData("File Name", 1);
            WebUI.setText(searchFile, fileName);
            WebUI.waitForElementClickable(selectFile, TimeoutConstants.SHORT_TIMEOUT);

            handleFileIsSelected();
        } catch (Exception e) {
            LogUtils.error("Error in selectPhoto: " + e.getMessage());
        }
    }

    @Step("Upload photo")
    public void uploadPhoto() {
        try {
            WebUI.clickElement(photoInfo, TimeoutConstants.SHORT_TIMEOUT);
            WebUI.clickElement(uploadNewButton, TimeoutConstants.SHORT_TIMEOUT);

            String fullPath = System.getProperty("user.dir") + IMAGE_PATH;
            WebUI.setText(uploadFile, fullPath);

            WebUI.waitForElementsVisibled(addFileButton, TimeoutConstants.LONG_TIMEOUT);
            WebUI.clickElement(addFileButton, TimeoutConstants.VERY_SHORT_TIMEOUT);
        } catch (Exception e) {
            LogUtils.error("Error in uploadPhoto: " + e.getMessage());
            throw new RuntimeException("Failed to upload photo: " + e.getMessage(), e);
        }
    }

    @Step("Handle File Is Selected")
    public void handleFileIsSelected() {
        try {
            WebElement fileElement = WebUI.getWebElement(selectFile);
            String selectedState = fileElement.getAttribute("data-selected");

            if ("true".equals(selectedState)) {
                WebUI.clickElement(selectFile, TimeoutConstants.VERY_SHORT_TIMEOUT);
            }

            WebUI.clickElement(addFileButton, TimeoutConstants.VERY_SHORT_TIMEOUT);
        } catch (Exception e) {
            LogUtils.error("Error in handleFileIsSelected: " + e.getMessage());
        }
    }
}
