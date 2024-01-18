package api.response;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

public class ModelResponse<T> extends ApiResponse {
    public ModelResponse(){
    }

    public static <T> ModelResponse<T> wrap(Response response, Class<T> type){
        ModelResponse<T> modelResponse = new ModelResponse();
        modelResponse.setResponse(response);
        modelResponse.setBody(response, type);
        return modelResponse;
    }

    public static <T> ModelResponse<T> wrap(Response response, TypeRef<T> typeRef){
        ModelResponse<T> modelResponse = new ModelResponse();
        modelResponse.setResponse(response);
        modelResponse.setBody(response, typeRef);
        return modelResponse;
    }

}
