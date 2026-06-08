package tests;

import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuccessfulLoginTest extends BaseTest{

    @Test
    void successfulLoginTest(){
        LoginPage loginPage=new LoginPage(page);

        loginPage.navigate();
        InventoryPage inventoryPage=loginPage.login("standard_user","secret_sauce");

        assertTrue(inventoryPage.isProductPageDisplayed());
    }

}
