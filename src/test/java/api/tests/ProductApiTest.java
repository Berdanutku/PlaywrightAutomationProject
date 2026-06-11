package api.tests;

import api.base.ApiBaseTest;
import api.clients.ProductApiClient;
import api.requests.ProductRequest;
import api.responses.ProductResponse;
import api.utils.ApiAllureUtil;
import com.microsoft.playwright.APIResponse;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void getSingleProductTest() throws JsonProcessingException {
            ProductApiClient productApi=new ProductApiClient(request);
            APIResponse response=productApi.getProduct(1);
            ApiAllureUtil.attachRequest("GET /products/1");
            ApiAllureUtil.attcahStatusCode(response.status());
            ApiAllureUtil.attachResponse(response.text());
            assertEquals(200,response.status());

            ObjectMapper mapper=new ObjectMapper();
            ProductResponse product=mapper.readValue(response.text(), ProductResponse.class);
            assertEquals(1,product.getId());
            assertNotNull(product.getTitle());
            assertTrue(product.getPrice()>0);
    }

    @Test
    void createProductTest() throws JsonProcessingException {

        ProductRequest requestBody=new ProductRequest();
        requestBody.setTitle("Playwright Product");
        requestBody.setPrice(99.99);
        requestBody.setCategory("electronics");

        ObjectMapper mapper=new ObjectMapper();
        String jsonBody=mapper.writeValueAsString(requestBody);

        ProductApiClient productApi=new ProductApiClient(request);
        ApiAllureUtil.attachRequest(jsonBody);
        APIResponse response= productApi.createProduct(jsonBody);
        ApiAllureUtil.attachResponse(jsonBody);
        ProductResponse product=mapper.readValue(response.text(),ProductResponse.class);
        assertEquals(201,response.status());
        assertEquals("Playwright Product",product.getTitle());
        System.out.println(response.text());
    }

    @Test
    void updateProductTest() throws JsonProcessingException {

            ProductRequest requestBody=new ProductRequest();
            requestBody.setTitle("Playwright Product 2");
            requestBody.setPrice(11.11);
            requestBody.setCategory("electronics2");

            ObjectMapper mapper=new ObjectMapper();
            String jsonBody=mapper.writeValueAsString(requestBody);

            ProductApiClient productApi=new ProductApiClient(request);
            APIResponse response=productApi.updateProduct(1,jsonBody);
            assertEquals(200,response.status());

            ProductResponse product=mapper.readValue(response.text(), ProductResponse.class);
            assertEquals("Playwright Product 2",product.getTitle());
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
