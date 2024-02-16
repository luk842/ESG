package tests.setup;

import com.microsoft.playwright.Page;

public abstract class PageObject {
    protected Page page;

    public PageObject(Page page) {
        this.page = page;
    }
}