import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by polina.kozhemiako on 10/31/2017.
 */
public class Task5 extends TestBase {

    @Test
    public void verifyAddingOfNewProduct(){
        BasicOperations.loginAsAdmin(driver);
        driver.findElement(By.xpath("//*[@id='app-'][2]")).click();
        WebElement elButtonAdd = driver.findElement(By.xpath("//*[@id='content']//a[2]"));
        elButtonAdd.click();
        String productName = generateUniqueProductName();
        fillInDataForNewProduct(productName);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.name("save")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.dataTable")));
        Boolean isProductAdded = driver.findElements(By.xpath("//td/a[contains(text(), '"+ productName +"')]")).size()>0;

        Assert.assertTrue("A new product has not been added to the Catalog.", isProductAdded);
    }

    private String generateUniqueProductName(){
        return "Mermaid Duck_" + System.currentTimeMillis();
    }

    private void fillInDataForNewProduct(String productName){
        driver.findElements(By.name("status")).get(0).click();
        driver.findElement(By.name("name[en]")).sendKeys(productName);
        driver.findElements(By.name("product_groups[]")).get(0).click();
        driver.findElements(By.name("product_groups[]")).get(2).click();
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("1.00");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("mermaid_yellow_duck.jpg").getFile());
        driver.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());
        // Information tab
        driver.findElements(By.cssSelector("ul.index li")).get(1).click();
        Select elManufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        elManufacturer.selectByIndex(1);
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("This beautiful rubber Mermaid Duck will bring you joy and happiness.");
        driver.findElement(By.name("head_title[en]")).sendKeys("Mermaid Rubber Duck");
        // Prices tab
        driver.findElements(By.cssSelector("ul.index li")).get(3).click();
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("30.00");
        Select elCurrency = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        elCurrency.selectByIndex(1);
        driver.findElement(By.name("prices[USD]")).sendKeys("24");
        driver.findElement(By.name("prices[EUR]")).sendKeys("22");
    }
}
