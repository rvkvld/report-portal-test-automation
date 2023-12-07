package webservices.reportportal;

import api.BaseApi;
import api.item.ApiItem;
import api.model.ApiConfig;
import api.response.ModelResponse;
import lombok.extern.slf4j.Slf4j;
import webservices.config.ReportPortalApiConfig;
import io.restassured.response.Response;

import static webservices.enums.ReportPortalEndpoint.*;

@Slf4j
public class ReportPortalApi extends BaseApi {
    private static final String LAUNCHER_ID = "launchId";

    public ReportPortalApi(ApiConfig config) {super(config);}
    public ApiConfig getConfig() {
        return ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal();
    }

    public Response getHealth(){
        return init (getConfig()).accept("").get(GET_HEALTH.getEndpoint());
    }

    public Response getAllLaunches(){
        return init (getConfig()).accept("").get(GET_LAUNCHERS.getEndpoint());
    }

    public ModelResponse<Object> getLaunchById(String launchId){
        return ModelResponse.wrap(init(getUser()).pathParams(LAUNCHER_ID, launchId)
                .get(GET_LAUNCHER_BY_ID.getEndpoint()), Object.class);
    }
}