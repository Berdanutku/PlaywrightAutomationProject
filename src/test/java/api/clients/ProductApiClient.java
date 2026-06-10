package api.clients;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import utils.ConfigReader;

public class ProductApiClient {
    private final APIRequestContext request;
    private final String baseUrl= ConfigReader.getProperty("api.base.url");

    public ProductApiClient(APIRequestContext request) {
        this.request = request;
    }

    public APIResponse getProduct(int id){
        return request.get(baseUrl+"/products/"+ id);
    }

    public APIResponse getAllProducts(){
        return request.get(baseUrl+"/products");
    }

    public APIResponse createProduct(String body){
        return request.post(baseUrl+"/products", RequestOptions.create().setHeader("Content-Type","application/json").setData(body));
    }
    public APIResponse updateProduct(int id,String body){
        return request.put(baseUrl+"/products/"+ id,RequestOptions.create().setHeader("Content-Type", "application/json").setData(body));
    }
    public APIResponse deleteProduct(int id){
        return request.delete(baseUrl+"/products/"+ id);
    }
}
