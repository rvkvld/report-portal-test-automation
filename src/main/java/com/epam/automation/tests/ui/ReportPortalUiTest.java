package com.epam.automation.tests.ui;

import api.item.ApiItem;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import webservices.config.ReportPortalApiConfig;

@Slf4j
public class ReportPortalUiTest {
    private String baseUrl = ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal().getBaseUrl();
    private String loginUrl = baseUrl + "/ui/#login";
    @Test
    public void testReportPortalUiLogin() {
        try(Playwright playwright = Playwright.create()){
            BrowserType browserType = playwright.chromium();
            Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            Page page = browser.newPage();
            page.navigate(loginUrl);
            String password = ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal().getPassword();
            String userName = ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal().getUserName();
            log.info("INFO: Testing string: {}", page.title());
            log.info("INFO: Base url: {}", baseUrl);
            log.info("INFO: Pass: {}", password);
            log.info("INFO: UserName: {}", userName);
        }
    }
}
