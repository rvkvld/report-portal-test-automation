package com.epam.automation.tests.api;

import api.response.ModelResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import webservices.reportportal.ReportPortalApiConfigFactory;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReportPortalApiTest {
    private static final String I_AM_HEALTHY = "{\"status\":\"UP\"}\n";

    @Test
    void testServiceHealthIsOk(){
        Response reportPortalResponse = ReportPortalApiConfigFactory.asValidUser().getHealth();
        assertEquals(SC_OK, reportPortalResponse.getStatusCode());
        assertEquals(I_AM_HEALTHY, reportPortalResponse.getBody().print());

    }
    @Test
    void testShouldReturnAllLaunchers(){
        Response reportPortalResponse = ReportPortalApiConfigFactory.asValidUser().getAllLaunches();
        assertEquals(SC_OK, reportPortalResponse.getStatusCode());

    }
    @Test
    void testShouldReturnLaunchById(){
        ModelResponse<Object> reportPortalResponse = ReportPortalApiConfigFactory.asValidUser().getLaunchById("1");
        assertEquals(SC_OK, reportPortalResponse.getResponse().getStatusCode());

    }
    @Test
    public void getToken() {
        String basicToken = "Basic dWk6dWltYW4=";
        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", basicToken)
                .formParam("grant_type", "password")
                .formParam("username", "default")
                .formParam("password", "1q2w3e")
                .when().post("http://localhost:8080/uat/sso/oauth/token");

        assertEquals(response.statusCode(), 200);
        String token = response.body().jsonPath().getString("access_token");
        assertNotNull(token, "ERROR: Data is retrieved successfully");
        response.prettyPrint();
    }
}
