package gl.pageobjectapproach.page;
import gl.pageobjectapproach.data.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends PageBase {

    @FindBy(css = "tr.header")
    private List<WebElement> productTable;

    @FindBy(name = "remove_cart_item")
    WebElement buttonRemove;

    public CartPage(WebDriver driver){
        super(driver);
    }

    private ExpectedCondition<WebElement> buttonRemoveIsPresent(){
        return ExpectedConditions.visibilityOf( buttonRemove) ;
    }

    private ExpectedCondition<Boolean> productTableIsUpdated(WebElement element){
        return ExpectedConditions.stalenessOf(element);
    }

    public Boolean productTableIsPresent(){
        return productTable.size()>0;
    }

    public void removeOneProductFromCart(){
        wait.until(buttonRemoveIsPresent());
        WebElement table = productTable.get(0);
        buttonRemove.click();
        wait.until(productTableIsUpdated(table));
    }

    public void removeAllProductsFromCart(){
        while (productTableIsPresent()){
            removeOneProductFromCart();
        }
    }
}
