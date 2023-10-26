package webservices.reportportal;

import api.BaseApi;
import api.item.ApiItem;
import api.model.ApiConfig;
import lombok.extern.slf4j.Slf4j;
import webservices.config.ReportPortalApiConfig;
import io.restassured.response.Response;

import static webservices.enums.ReportPortalEndpoint.GET_HEALTH;

@Slf4j
public class ReportPortalApi extends BaseApi {

    public ReportPortalApi(ApiConfig config) {super(config);}
    public ApiConfig getConfig() {
        return ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal();
    }

    public Response getHealth(){
        return init (getConfig()).accept("").get(GET_HEALTH.getEndpoint());
    }
}