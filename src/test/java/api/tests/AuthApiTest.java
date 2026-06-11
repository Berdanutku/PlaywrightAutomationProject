package api.tests;

import api.base.ApiBaseTest;
import api.clients.AuthClient;
import api.requests.LoginRequest;
import api.responses.LoginResponse;
import api.utils.JsonUtils;
import api.utils.TokenManager;
import com.microsoft.playwright.APIResponse;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthApiTest extends ApiBaseTest {

    private static final Logger log = LoggerFactory.getLogger(AuthApiTest.class);

    @Test
    void loginTest() throws JsonProcessingException {
        AuthClient authClient=new AuthClient(request);
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername("mor_2314");
        loginRequest.setPassword("83r5^_");

        String body = JsonUtils.toJson(loginRequest);

        APIResponse response=authClient.login(body);
        System.out.println(response.text());
        assertEquals(201,response.status());

        LoginResponse loginResponse=JsonUtils.fromJson(response.text(), LoginResponse.class);
        assertNotNull(loginResponse.getToken());
    }

     @Test
    void loginWithInvalidPassword() throws JsonProcessingException {
        AuthClient authClient=new AuthClient(request);
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername("mor_2314");
        loginRequest.setPassword("wrong");


        String body=JsonUtils.toJson(loginRequest);
        APIResponse response=authClient.login(body);
         System.out.println(response.text());
         assertEquals(401,response.status());
     }

     @Test
    void getTokenTest() throws JsonProcessingException {
         String token= TokenManager.getToken(request);
         System.out.println(token);
         assertNotNull(token);
     }
}
