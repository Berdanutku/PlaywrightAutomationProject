package pages;

import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    private final String cart=".shopping_cart_link";

    public void openCartPage(){
        page.locator(cart).click();
    }
    public Boolean isProductDisplayedInCart(){
        return page.locator(".cart_item").count()>0;
    }
    public void removeProductFromCart(){
        page.locator("#remove-sauce-labs-backpack").click();
    }

    public Boolean isProductRemoved(){
        return page.locator(".cart_item").count()==0;
    }


}
