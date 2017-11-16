package gl.pageobjectapproach.page;

import gl.pageobjectapproach.data.PageBase;
import gl.pageobjectapproach.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends PageBase {

    @FindBy(css = "span.quantity")
    WebElement quantityInCart;

    @FindBy(css = "li.product.column.shadow.hover-light")
    List<WebElement> popularItems;

    @FindBy(xpath = "//*[@id='cart']/a[3]")
    public WebElement checkOutLink;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void open(){
        driver.get(TestData.homePageUrl);
    }

    public String getQuantityInCart(){
        return quantityInCart.getText();
    }

    public WebElement findPopularItemWithIndex(int i) {
        return popularItems.get(i);
    }

    public void clickPopularItemWithIndex(int i){
        findPopularItemWithIndex(i).click();
    }
}
