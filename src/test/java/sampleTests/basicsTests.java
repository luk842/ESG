package sampleTests;

import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.setup.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static tests.properties.Environment.URL;

public class basicsTests extends BaseTest {

    @Description("Test - 1")
    @Test
    public void buttonHasProperColour() {
        // GIVEN: User is on main page
        page.waitForLoadState(LoadState.NETWORKIDLE);
        // THEN: Verify if title page contains correct name
        assertThat(page).hasTitle("SAP Fioneer | World-class software solutions for financial services");
        // AND: Verify if button has proper colour
        assertThat(mainPage.getIbTouchButton.toLocator()).isVisible();
        assertThat(mainPage.getIbTouchButton.toLocator()).hasCSS("background-color", "rgb(255, 212, 60)"); // rgb(255, 212, 60) = yellow
    }

    @Description("Test - 2")
    @Test
    public void validRedirectionAfterChoseGivenTabFromBookmark() {
        // GIVEN: User is on main page
        page.waitForLoadState(LoadState.NETWORKIDLE);
        // WHEN: Choose given page from bookmark
        mainPage.chooseDropdown(mainPage.financeAndEsgTab, "ESG KPI Engine");
        // THEN: Verify if title page contains correct name
        assertThat(page).hasURL((URL + "finance-esg/esg-kpi-engine/"));
        assertThat(page).hasTitle("Stay audit-ready with the ESG KPI Engine | SAP Fioneer");
    }

    @Description("Test - 3")
    @Test
    public void validateIncorrectEmailMessage() {
        // GIVEN: User is on main page
        page.waitForLoadState(LoadState.NETWORKIDLE);
        // WHEN: Click "Get in touch" button
        page.click(mainPage.getIbTouchButton.xpath());
        // THEN: Verify if title page contains correct name
        assertThat(page).hasURL(URL + "contact/");
        assertThat(page).hasTitle("SAP Fioneer | Contact | Get in touch!");
        // AND: Input incorrect email
        mainPage.emailInput.fill("342323");
        mainPage.submitButton.click();
        // AND: Invalid email notification appear
        assertThat(mainPage.invalidEmailNotification).isVisible();
    }
}