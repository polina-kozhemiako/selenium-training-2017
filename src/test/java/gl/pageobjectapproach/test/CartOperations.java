package gl.pageobjectapproach.test;
import gl.pageobjectapproach.data.TestBase;
import gl.pageobjectapproach.data.Application;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by polina.kozhemiako on 11/16/2017.
 */
public class CartOperations extends TestBase{

    Application app;

    public CartOperations(){
        app = new Application(driver);
    }

    @Test
    public void verifyAddingToCart(){
        app.mainPage.open();
        int startQuantity = Integer.parseInt(app.mainPage.getQuantityInCart());
        int n = 3;
        addSeveralProductsToCart(n);
        int addedQuantity = Integer.parseInt(app.mainPage.getQuantityInCart());

        Assert.assertEquals("Product hasn't been added to the cart.\nNumber of products in the cart differs from expected.", (startQuantity + n), addedQuantity);
    }

    @Test
    public void verifyRemovingFromCart(){
        app.mainPage.open();
        addSeveralProductsToCart(3);
        app.productPage.checkOutLink.click();
        app.cartPage.removeAllProductsFromCart();
        app.mainPage.open();
        String removedQuantity = app.mainPage.getQuantityInCart();

        Assert.assertEquals("Not all products have been removed from the cart.", "0", removedQuantity);
    }

    private void addSeveralProductsToCart(int n){
        for (int i = 0; i < n; i++){
            app.mainPage.clickPopularItemWithIndex(0);
            app.productPage.addProductToCart();
            app.goBack();
        }
    }
}
