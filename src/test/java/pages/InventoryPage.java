package pages;

import com.microsoft.playwright.Page;

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
}
