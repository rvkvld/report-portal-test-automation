package webservices.reportportal;

import api.item.ApiItem;
import webservices.config.ReportPortalApiConfig;

public class ReportPortalApiConfigFactory {
    private static final ReportPortalApiConfig USER = ApiItem.instance().getConfig(ReportPortalApiConfig.class);
    private ReportPortalApiConfigFactory(){}

    public static ReportPortalApi asValidUser() {return new ReportPortalApi(USER.getReportPortal());}
}
