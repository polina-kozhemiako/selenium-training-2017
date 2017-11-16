package gl.pageobjectapproach.page;
import gl.pageobjectapproach.data.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends PageBase {

    @FindBy(css = "span.quantity")
    public WebElement quantityInCart;

    @FindBy(name = "options[Size]")
    public List<WebElement> dropDownSize;

    @FindBy(name = "add_cart_product")
    public WebElement buttonAdd;

    @FindBy(xpath = "//*[@id='cart']/a[3]")
    public WebElement checkOutLink;

    public ProductPage(WebDriver driver){
        super(driver);
    }

    public String getQuantityInCart(){
        return quantityInCart.getText();
    }

    public boolean dropDownSizeIsPresent(){
        return dropDownSize.size()>0;
    }

    private ExpectedCondition<Boolean> quantityIsIncreasedToValue(String expectedQuantity){
        return ExpectedConditions.textToBePresentInElement(quantityInCart, expectedQuantity);
    }

    public void addProductToCart(){
        Integer quantityValue = Integer.parseInt(getQuantityInCart());
        if (dropDownSizeIsPresent()){
            Select dropdown = new Select(dropDownSize.get(0));
            dropdown.selectByIndex(1);
        }
        buttonAdd.click();
        wait.until(quantityIsIncreasedToValue(Integer.toString(quantityValue + 1)));
    }
}
