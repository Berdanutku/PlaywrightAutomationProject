package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected Page page;
    protected BrowserContext context;

    @BeforeAll
    static void setup(){
        playwright=Playwright.create();
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @AfterAll
    static void teardown(){
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void openPage(){
        context= browser.newContext();
        context.tracing().start(
                new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true)
        );
        page=context.newPage();
    }

    @AfterEach
    void closePage(){
        context.tracing().stop(
                new Tracing.StopOptions().setPath(java.nio.file.Paths.get("trace.zip"))
        );
        page.close();
    }
}

