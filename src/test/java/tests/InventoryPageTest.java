package tests;

import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ScreenshotUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryPageTest extends BaseTest{

    @Test
    void verifyProductsLoaded(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.navigate();
        InventoryPage inventoryPage=loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
        int productCount=inventoryPage.getProductCount();
        System.out.println(productCount);

        assertTrue(productCount>0);
    }

    @Test
    void addProductToCartTest(){
        try{
            LoginPage loginPage=new LoginPage(page);
            loginPage.navigate();
            InventoryPage inventoryPage=loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
            inventoryPage.addBackpackToCart();

            assertEquals("2",inventoryPage.getCartBadgeCount());
        }
        catch (AssertionError e){
            ScreenshotUtil.attachScreenshot(page);
            throw e;
        }

    }

    @Test
    void logoutTest(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.navigate();
        InventoryPage inventoryPage=loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
        assertTrue(inventoryPage.logout());
    }
}
