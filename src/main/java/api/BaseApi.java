package api;

import api.item.ApiItem;
import api.model.ApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import webservices.config.ReportPortalApiConfig;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public abstract class BaseApi {
    private final ApiConfig config;
    private String password = ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal().getPassword();
    private String userName = ApiItem.instance().getConfig(ReportPortalApiConfig.class).getReportPortal().getUserName();
    public BaseApi (ApiConfig config) {this.config = config;}

    protected abstract ApiConfig getConfig();
    public ApiConfig getUser(){
        return this.config;
    }

    protected RequestSpecification init (ApiConfig config){
        RequestSpecification spec = this.getApiSpec();
        String token = getToken();
        if(config == null && token != null){
            return spec;
        }else {
            if (config.getUserName() != null){
                spec.header("USER-NAME", config.getUserName());
                spec.header("Authorization", "Bearer " +  token);
            }
            return spec;
        }
    }

    private RequestSpecification getApiSpec(){
        RestAssuredConfig config = (new RestAssuredConfig()).objectMapperConfig(ObjectMapperConfig
                .objectMapperConfig().jackson2ObjectMapperFactory((clazz, charset)-> {
                    return (new ObjectMapper()).registerModule(new JavaTimeModule())
                            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                }));
        return RestAssured.given().baseUri(this.getConfig().getBaseUrl()).accept(ContentType.JSON)
                .contentType("application/json").filter(new RequestLoggingFilter()).config(config);
    }

    private String getToken() {
        String basicToken = "Basic dWk6dWltYW4=";
        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", basicToken)
                .formParam("grant_type", "password")
                .formParam("username", userName)
                .formParam("password", password)
                .when().post("http://localhost:8080/uat/sso/oauth/token");

        assertEquals(response.statusCode(), 200);
        String token = response.body().jsonPath().getString("access_token");
        assertNotNull(token, "ERROR: Data is retrieved successfully");
        response.prettyPrint();
        return token;
    }
}
