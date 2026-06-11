package api.utils;

import api.clients.AuthClient;
import api.requests.LoginRequest;
import com.microsoft.playwright.APIRequestContext;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import ui.utils.ConfigReader;

public class TokenManager {

    public static String getToken(APIRequestContext request) throws JsonProcessingException {
        AuthClient authClient=new AuthClient(request);
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername(ConfigReader.getProperty("api.username"));
        loginRequest.setPassword(ConfigReader.getProperty("api.password"));
        ObjectMapper mapper=new ObjectMapper();
        String body=mapper.writeValueAsString(loginRequest);
        return authClient.getToken(body);
    }
}
