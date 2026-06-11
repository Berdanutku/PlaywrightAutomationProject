package ui.tests;

import org.junit.jupiter.api.Test;
import ui.pages.CartPage;
import ui.pages.InventoryPage;
import ui.pages.LoginPage;
import ui.utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartPageTest extends BaseTest{

    @Test
    void verifyProductsAddedToCartTest(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.navigate();
        InventoryPage inventoryPage=loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
        inventoryPage.addBackpackToCart();
        CartPage cartPage=new CartPage(page);
        cartPage.openCartPage();

        assertTrue(cartPage.isProductDisplayedInCart());
    }

    @Test
    void removeProductFromCartTest(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.navigate();
        InventoryPage inventoryPage=loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
        inventoryPage.addBackpackToCart();
        CartPage cartPage=new CartPage(page);
        cartPage.openCartPage();
        cartPage.removeProductFromCart();

        assertTrue(cartPage.isProductRemoved());
    }
}
