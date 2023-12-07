package api.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;

@Slf4j
@NoArgsConstructor
@Data
abstract class ApiResponse<T> {
    private Response response;
    private T body;
    private Map<String, Object> errors;

    protected void setBody(Response resonse, Class<T> type){
        try {
            log.info("Converting JSON to: {}", type.getSimpleName());
            this.body = resonse.as(type);
        }catch (Exception var4){
            log.warn("Could not deserialize body to {}", type, var4);
            this.setErrors(resonse);
        }
    }

    protected void setBody (Response response, TypeRef<T> typeRef) {
        try {
            log.info("Converting JSON to: {}", typeRef.getType().getTypeName());
            this.body = response.as(typeRef);
        } catch (Exception var4){
            log.warn("Could not deserialize body to {}", typeRef, var4);
            this.setErrors(response);

        }

    }

    protected void setErrors(Response response){
        try {
            this.errors = (Map) response.as(Map.class);
        }catch (Exception var3){
            log.debug("Could not deserialize body to {}", Map.class, var3);
        }
    }
    public <T> T getErrors(Class<T> cls) { return new ObjectMapper().convertValue(this.errors, cls); }

}
