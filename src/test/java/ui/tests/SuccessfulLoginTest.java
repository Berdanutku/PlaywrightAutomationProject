package ui.tests;

import org.junit.jupiter.api.Test;
import ui.pages.InventoryPage;
import ui.pages.LoginPage;
import ui.utils.ConfigReader;
import io.qameta.allure.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuccessfulLoginTest extends BaseTest{

    @Test
    @Description("Verify successful login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Utku")
    @Epic("SauceDemo")
    @Feature("Login")
    @Story("Successful Login")
    void successfulLoginTest(){
        LoginPage loginPage=new LoginPage(page);

        loginPage.navigate();
        InventoryPage inventoryPage=loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));

        assertTrue(inventoryPage.isProductPageDisplayed());
    }

}
