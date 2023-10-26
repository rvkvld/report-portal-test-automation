package api;

import api.model.ApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public abstract class BaseApi {
    private final ApiConfig config;
    public BaseApi (ApiConfig config) {this.config = config;}

    protected abstract ApiConfig getConfig();

    protected RequestSpecification init (ApiConfig config){
        RequestSpecification spec = this.getApiSpec();
        spec.header("correlation-id", "test_ " + UUID.randomUUID(), new Object[0]);
        if(config == null){
            return spec;
        }else {
            if (config.getUserName() != null){
                spec.header("USER-NAME", config.getUserName(), new Object[0]);
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
}
