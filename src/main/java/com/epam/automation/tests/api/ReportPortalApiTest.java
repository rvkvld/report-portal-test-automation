package com.epam.automation.tests.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import webservices.reportportal.ReportPortalApiConfigFactory;
import static org.apache.http.HttpStatus.SC_OK;
public class ReportPortalApiTest {
    private static final String I_AM_HEALTHY = "{\"status\":\"UP\"}\n";

    @Test
    void testServiceHealthIsOk(){
        Response reportPortalResponse = ReportPortalApiConfigFactory.asValidUser().getHealth();
        assertEquals(SC_OK, reportPortalResponse.getStatusCode());
        assertEquals(I_AM_HEALTHY, reportPortalResponse.getBody().print());

    }
}
