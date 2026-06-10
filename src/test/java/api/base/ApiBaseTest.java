package api.base;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ApiBaseTest {

    protected Playwright playwright;
    protected APIRequestContext request;

    @BeforeEach
    void setup(){
        playwright=Playwright.create();
        request=playwright.request().newContext();
    }

    @AfterEach
    void tearDown(){
        request.dispose();
        playwright.close();
    }
}
