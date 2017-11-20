package gl.pageobjectapproach.test;
import com.google.common.base.Verify;
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
    public void verifyAddingRemovingFromCart(){
        app.mainPage.open();
        int startQuantity = Integer.parseInt(app.mainPage.getQuantityInCart());
        int n = 3;
        app.addSeveralProductsToCart(n);
        int addedQuantity = Integer.parseInt(app.mainPage.getQuantityInCart());

        Assert.assertEquals("Product hasn't been added to the cart.\nNumber of products in the cart differs from expected.", (startQuantity + n), addedQuantity);

        app.productPage.checkOutLink.click();
        app.cartPage.removeAllProductsFromCart();
        app.mainPage.open();
        String removedQuantity = app.mainPage.getQuantityInCart();

        Assert.assertEquals("Not all products have been removed from the cart.", "0", removedQuantity);
    }
}
