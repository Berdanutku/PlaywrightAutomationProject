package api.clients;

import api.responses.LoginResponse;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import ui.utils.ConfigReader;

public class AuthClient {

    private final APIRequestContext request;

    private final String baseUrl= ConfigReader.getProperty("api.base.url");

    public AuthClient(APIRequestContext request) {
        this.request = request;
    }

    public APIResponse login(String body){
        return request.post(
                baseUrl + "/auth/login",
                RequestOptions.create().setHeader("Content-Type","application/json").setData(body)
        );
    }

    public String getToken(String body) throws JsonProcessingException {
        APIResponse response=login(body);
        ObjectMapper mapper=new ObjectMapper();
        LoginResponse loginResponse=mapper.readValue(response.text(), LoginResponse.class);
        return loginResponse.getToken();
    }
}
