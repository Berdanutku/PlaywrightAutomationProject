package pages;

import com.microsoft.playwright.Page;
import utils.ConfigReader;

public class InventoryPage {

    private final Page page;

    public InventoryPage(Page page) {
        this.page = page;
    }

    private final String productsTitle=".title";

    public String getPageTitle(){
        return page.locator(productsTitle).textContent();
    }

    public Boolean isProductPageDisplayed(){
        return page.locator(productsTitle).isVisible();
    }

    public int getProductCount(){
        return page.locator(".inventory_item").count();
    }

    public void addBackpackToCart(){
        page.locator("#add-to-cart-sauce-labs-backpack").click();
    }

    public String getCartBadgeCount(){
        return page.locator(".shopping_cart_badge").textContent();
    }

    public Boolean logout(){
        page.locator("#react-burger-menu-btn").click();
        page.locator("#logout_sidebar_link").click();
        //page.waitForURL("https://www.saucedemo.com/");
        return page.url().equals(ConfigReader.getProperty("baseUrl"));
    }
}
