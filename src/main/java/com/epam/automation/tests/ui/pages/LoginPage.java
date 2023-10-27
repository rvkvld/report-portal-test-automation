package com.epam.automation.tests.ui.pages;

import api.item.ApiItem;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import lombok.extern.slf4j.Slf4j;
import webservices.config.ReportPortalApiConfig;

@Slf4j
public class LoginPage {
    private final Page page;
    private final Locator locSubmitButton;
    private final String loginUrl;
    private final String selInputLogin;
    private final String selInputPass;
    private final String selSubmitBtn;


    public LoginPage(Page page) {
        this.page = page;
        String baseUrl = ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal().getBaseUrl();
        this.loginUrl = baseUrl + "/ui/#login";
        this.selInputLogin ="xpath=//input[@placeholder='Login']";
        this.selInputPass = "xpath=//input[@placeholder='Password']";
        this.selSubmitBtn = "xpath=//button[@type='submit']";
        this.locSubmitButton = page.locator(selSubmitBtn);
    }

    public LoginPage open() {
        log.info("INFO: Navigate to: {}", loginUrl);
        page.navigate(loginUrl);
        return this;
    }
    public LoginPage enterUserName(String userName){
        log.info("INFO: Enter userName: {}", userName);
        page.fill( selInputLogin, userName);
        return this;
    }
    public LoginPage enterPassword(String password){
        log.info("INFO: Enter password {}", password);
        page.fill( selInputPass, password);
        return this;
    }
    public void clickSubmit(){
        log.info("INFO: Click Submit button {}", locSubmitButton);
        page.locator(selSubmitBtn).click();
    }

}
