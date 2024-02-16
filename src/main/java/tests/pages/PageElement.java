package tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public record PageElement(Page page, String xpath) {
    public Locator toLocator() {
        return page.locator(xpath);
    }
}