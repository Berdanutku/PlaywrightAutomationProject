package api.utils;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper mapper =new ObjectMapper();
    public static String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
    public static <T> T fromJson(String json,Class<T> classParameter) throws JsonProcessingException {
        return mapper.readValue(json,classParameter);
    }
}
