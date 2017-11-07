import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by polina.kozhemiako on 10/31/2017.
 */
public class Task5 {
    public static WebDriver driver;
    private String adminPageUrl = "http://127.0.0.1:8080/litecart/admin/";

    @Before
    public void start(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        loginAsAdmin("admin", "admin");
    }

    @Test
    public void verifyAddingOfNewProduct(){
        driver.findElement(By.xpath("//*[@id='app-'][2]")).click();
        WebElement elButtonAdd = driver.findElement(By.xpath("//*[@id='content']//a[2]"));
        elButtonAdd.click();
        fillInDataForNewProduct();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.name("save")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.dataTable")));
        Boolean isProductAdded = driver.findElements(By.xpath("//td/a[contains(text(), 'Mermaid Duck')]")).size()>0;

        Assert.assertTrue("A new product has not been added to the Catalog.", isProductAdded);
    }

    @After
    public void stop(){
        driver.quit();
    }

    private void loginAsAdmin(String username, String password){
        driver.get(adminPageUrl);
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    private void fillInDataForNewProduct(){
        driver.findElements(By.name("status")).get(0).click();
        driver.findElement(By.name("name[en]")).sendKeys("Mermaid Duck");
        driver.findElements(By.name("product_groups[]")).get(0).click();
        driver.findElements(By.name("product_groups[]")).get(2).click();
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("1.00");
        driver.findElement(By.name("new_images[]")).sendKeys("D:\\Projects\\mermaid_yellow_duck.jpg");
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
