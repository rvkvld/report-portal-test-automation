package com.epam.automation.e2e;

import com.epam.automation.tests.ui.pages.LoginPage;
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

public class StepsDefLoginPage {
    private final String FILTERS = "Filters";
    protected static Playwright playwright;
    protected static Browser browser;
    protected static Page page;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
    }
    @AfterAll
    public static void closeBrowser() {
        playwright.close();
    }
    LoginPage loginPage;

    @Given("^user navigate to login page$")
    public LoginPage navigate() {
        page = browser.newPage();
        loginPage = new LoginPage(page);
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
