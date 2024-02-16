package tests.setup;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Attachment;

import java.nio.file.Paths;

import static tests.properties.Environment.HEADLESS;
import static tests.properties.Environment.URL;

public class BaseDriver {

    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public static Page initDriver() {
        tlPlaywright.set(Playwright.create());
        tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(HEADLESS)));
        tlPage.set(tlBrowser.get().newPage());
        getPage().navigate(URL);
        getPage().setViewportSize(1920, 1080);
        getPage().setDefaultTimeout(10000);
        return getPage();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        String path = "./target/site" + "/screenshot/" + System.currentTimeMillis() + ".png";
        return getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));
    }

    public static void cleanPlaywright() {
        tlPlaywright.remove();
        tlBrowser.remove();
        tlPage.remove();
    }
}