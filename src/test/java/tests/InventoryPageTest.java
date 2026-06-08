package tests;

import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryPageTest extends BaseTest{

    @Test
    void verifyProductsLoaded(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.navigate();
        InventoryPage inventoryPage=loginPage.login("standard_user","secret_sauce");
        int productCount=inventoryPage.getProductCount();
        System.out.println(productCount);

        assertTrue(productCount>0);
    }

    @Test
    void addProductToCartTest(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.navigate();
        InventoryPage inventoryPage=loginPage.login("standard_user","secret_sauce");
        inventoryPage.addBackpackToCart();

        assertEquals("1",inventoryPage.getCartBadgeCount());
    }
}
