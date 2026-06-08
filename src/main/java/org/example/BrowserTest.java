package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class BrowserTest {

    public static void main(String[] args) {
        try(Playwright playwright=Playwright.create()){
            Browser browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            Page page = browser.newPage();


            page.navigate("https://www.saucedemo.com");
            System.out.println(page.title());

            page.locator("#user-name").fill("standard_user");
            page.locator("#password").fill("secret_sauce");
            page.locator("#login-button").click();
            assert page.url().contains("inventory");
        }
    }
}
