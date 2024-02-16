package tests.setup;

import com.microsoft.playwright.Page;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestResult;
import org.testng.annotations.*;
import tests.listeners.TestListeners;
import tests.pages.MainPage;

import static tests.setup.BaseDriver.*;

@Log4j2
@Listeners(TestListeners.class)
public class BaseTest {

    public Page page;
    public MainPage mainPage;

    @BeforeClass(alwaysRun = true)
    public void init() {
        page = initDriver();
        mainPage = new MainPage(getPage());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            takeScreenshot();
            page.onConsoleMessage(msg -> {
                if ("log - error".equals(msg.type()))
                    log.info("log - Error text: " + msg.text());
            });
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        getPage().context().browser().close();
    }

    @AfterSuite
    public void tearDownSuite() {
        BaseDriver.cleanPlaywright();
    }
}