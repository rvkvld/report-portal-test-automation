package com.epam.automation.tests.ui;

import api.item.ApiItem;
import com.epam.automation.tests.ui.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import webservices.config.ReportPortalApiConfig;

public class LoginPageUITests extends BaseUITest {
    private String password = ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal().getPassword();
    private String userName = ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal().getUserName();
    private final String FILTERS = "Filters";
    private final String WELCOME = "Welcome,login to your account";

    @Test
    public void shouldLoginReportPortalUi() {
        page = browser.newPage();
        LoginPage loginPage = new LoginPage(page);
        loginPage.open()
                .enterUserName(userName)
                .enterPassword(password)
                .clickSubmit();
        assertEquals(FILTERS, page.getByText(FILTERS).textContent());
    }
    @ParameterizedTest()
    @CsvFileSource(resources = "/test-data-ui/reportPortalUiLoginNegative.csv", numLinesToSkip = 1)
    public void shouldNotLoginReportPortalUi(String userName, String password) {
        page = browser.newPage();
        LoginPage loginPage = new LoginPage(page);
        loginPage.open()
                .enterUserName(userName)
                .enterPassword(password)
                .clickSubmit();
        assertEquals(WELCOME, page.getByText(WELCOME).textContent());
        }
}
