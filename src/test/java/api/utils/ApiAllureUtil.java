package api.utils;

import io.qameta.allure.Allure;

public class ApiAllureUtil {

    public static void attachRequest(String request){
        Allure.addAttachment("Request","application/json",request);
    }

    public static void attachResponse(String response){
        Allure.addAttachment("Response","application/json",response);
    }
    public static void attcahStatusCode(int statusCode){
        Allure.addAttachment("Status Code",String.valueOf(statusCode));
    }
}
