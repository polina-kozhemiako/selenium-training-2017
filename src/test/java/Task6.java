import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by polina.kozhemiako on 11/1/2017.
 */
public class Task6 extends TestBase{
    WebDriverWait wait;

    public Task6(){
       wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void verifyCartOperations(){
        BasicOperations.openHomePage(driver);
        Integer startQuantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
        Integer n = 3;
        for (int i = 0; i < n; i++){
            addProductToCart();
        }
        String addedQuantity = driver.findElement(By.cssSelector("span.quantity")).getText();

        Assert.assertEquals("Number of products in the cart differs from expected.", Integer.toString(startQuantity + n), addedQuantity);

        driver.findElement(By.xpath("//*[@id='cart']/a[3]")).click();
        while (driver.findElements(By.cssSelector("tr.header")).size()!=0){
            removeProductFromCart();
        }
        BasicOperations.openHomePage(driver);
        String removedQuantity = driver.findElement(By.cssSelector("span.quantity")).getText();

        Assert.assertEquals("Not all products have been removed from the cart.", "0", removedQuantity);
    }

    private void addProductToCart(){
        driver.findElements(By.cssSelector("li.product.column.shadow.hover-light")).get(0).click();
        Integer quantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
        WebElement elQuantity = driver.findElement(By.cssSelector("span.quantity"));
        String addedQuantity = Integer.toString(quantity+1);
        if (driver.findElements(By.name("options[Size]")).size()!= 0){
            Select dropdown = new Select(driver.findElement(By.name("options[Size]")));
            dropdown.selectByIndex(1);
        }
        driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(elQuantity, addedQuantity));
        driver.navigate().back();
    }

    private void removeProductFromCart(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//*[@id='box-checkout-cart']//button")).get(1)));
        driver.findElements(By.xpath("//*[@id='box-checkout-cart']//button")).get(1).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("tr.header"))));
    }
}
