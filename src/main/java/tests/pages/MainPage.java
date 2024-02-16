package tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import tests.setup.PageObject;

public class MainPage extends PageObject {
    public MainPage(Page page) {
        super(page);
    }

    public final PageElement getIbTouchButton = new PageElement(page, "//div[@class='header-wrapper']//div[@class='header-button']//a");
    public final Locator financeAndEsgTab = page.locator("//div[@class='flex-col hide-for-medium flex-right']//li[@id='menu-item-29979']");
    public final Locator emailInput = page.frameLocator("#hs-form-iframe-0").locator("//input[@name='email']");
    public final Locator submitButton = page.frameLocator("#hs-form-iframe-0").locator("//div[@class='actions']/input[@class='hs-button primary large']");
    public final Locator invalidEmailNotification = page.frameLocator("#hs-form-iframe-0").locator("//label[contains(text(),'Email must be formatted correctly.')]");

    public void chooseDropdown(Locator dropdownName, String filterName) {
        dropdownName.click();
        page.click("//span[contains(text(),'" + filterName + "')]");
    }
}