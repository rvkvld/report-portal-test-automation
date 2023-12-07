package com.epam.automation.e2e.step_definitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.epam.automation.tests.ui.pages.LoginPage;

public class LoginPageSteps {
    private final String FILTERS = "Filters";
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private LoginPage loginPage;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        page = browser.newPage();
    }
    @AfterAll
    public static void closeBrowser() {
        playwright.close();
    }

    @Given("^user navigate to login page$")
    public com.epam.automation.tests.ui.pages.LoginPage navigate() {
        loginPage = new com.epam.automation.tests.ui.pages.LoginPage(page);
        return loginPage.open();
    }

    @When("user login into application with {string} and password {string}")
    public void enterUserName(String userName, String password) {
        loginPage.enterUserName(userName)
                .enterPassword(password)
                .clickSubmit();
    }

    @Then("^FILTERS page appears$")
    public void validate() {
        assertEquals(FILTERS, page.getByText(FILTERS).textContent());
    }

}
