package pages;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import utils.ConfigReader;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate(){
        page.navigate(ConfigReader.getProperty("baseUrl"));
    }

    public void enterUsername(String username){
        page.locator("#user-name").fill(username);
    }

    public void enterPassword(String password){
        page.locator("#password").fill(password);
    }

    public void clickLoginButton(){
        page.locator("#login-button").click();
    }

    @Step("Login with username: {username}")
    public InventoryPage login(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        return new InventoryPage(page);
    }
}
