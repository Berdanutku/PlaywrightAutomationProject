package api.tests;

import api.base.ApiBaseTest;
import api.clients.ProductApiClient;
import api.utils.ApiAllureUtil;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductApiTest extends ApiBaseTest {
    @Test
    void getAllProductsTest(){
            ProductApiClient productApi=new ProductApiClient(request);
            APIResponse response=productApi.getAllProducts();
            assertEquals(200,response.status());

            String body=response.text();
            System.out.println(body);
            assertTrue(body.contains("title"));

    }

    @Test
    void getSingleProductTest(){
            ProductApiClient productApi=new ProductApiClient(request);
            APIResponse response=productApi.getProduct(1);
            ApiAllureUtil.attachRequest("GET /products/1");
            ApiAllureUtil.attcahStatusCode(response.status());
            ApiAllureUtil.attachResponse(response.text());
            assertEquals(200,response.status());

            //json
    }

    @Test
    void createProductTest(){
            String requestBody= """
                    {
                    "title":"Playwright Product",
                    "price":"99.99",
                    "category":"electronics"
                    }""";
            ProductApiClient productApi=new ProductApiClient(request);
            ApiAllureUtil.attachRequest(requestBody);
            APIResponse response= productApi.createProduct(requestBody);
            ApiAllureUtil.attachResponse(response.text());
            assertEquals(201,response.status());
            System.out.println(response.text());

            //json

    }

    @Test
    void updateProductTest(){
            String requestBody= """
                    {
                    "title":"Playwright Product2",
                    "price":"991.99",
                    "category":"electronics2"
                    }""";
            ProductApiClient productApi=new ProductApiClient(request);
            APIResponse response=productApi.updateProduct(1,requestBody);
            assertEquals(200,response.status());

            //json

    }

    @Test
    void deleteProductTest(){
            ProductApiClient productApi= new ProductApiClient(request);
            APIResponse response= productApi.deleteProduct(1);
            assertEquals(200,response.status());
            System.out.println(response.text());


    }

    @Test
    void productNotFoundTest(){
            ProductApiClient productApi= new ProductApiClient(request);
            APIResponse response=productApi.getProduct(99999);

            assertEquals(404,response.status());

    }
}
