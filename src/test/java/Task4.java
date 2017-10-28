import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/**
 * Created by polina.kozhemiako on 10/26/2017.
 */
public class Task4 {
    public static WebDriver driver;

    public Task4(){
        driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        //driver = new ChromeDriver();
    }

    @BeforeClass
    public static void startTesting(){
        FirefoxDriverManager.getInstance().setup();
        //InternetExplorerDriverManager.getInstance().arch32().setup();
        //ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void start() {
        driver.navigate().to("http://127.0.0.1:8080/litecart");
        WebElement elCampaigns = driver.findElement(By.cssSelector("#box-campaigns h3"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elCampaigns);
    }

    @Test
    public void verifyProductName(){
        WebElement elItemName = driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[2]"));
        String itemName = elItemName.getText();
        WebElement yellowDuckItem= driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]"));
        yellowDuckItem.click();
        WebElement elProductName = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[1]/h1"));
        String productName = elProductName.getText();

        Assert.assertEquals("Product name on main page is not the same as on product page.", itemName, productName);
    }

    @Test
    public void verifyRegularPriceValue(){
        WebElement elItemRegularPrice =  driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/s"));
        String itemRegPrice = elItemRegularPrice.getText();
        WebElement yellowDuckItem= driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]"));
        yellowDuckItem.click();
        WebElement elProductRegularPrice = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/s"));
        String productRegPrice = elProductRegularPrice.getText();

        Assert.assertEquals("Regular price value on main page is not the same as on product page.", itemRegPrice, productRegPrice);
    }

    @Test
    public void verifySpecialPriceValue(){
        WebElement elItemSpecialPrice =  driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/strong"));
        String itemSpecPrice = elItemSpecialPrice.getText();
        WebElement yellowDuckItem= driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]"));
        yellowDuckItem.click();
        WebElement elProductSpecialPrice = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/strong"));
        String productSpecPrice = elProductSpecialPrice.getText();

        Assert.assertEquals("Special price value on main page is not the same as on product page.", itemSpecPrice, productSpecPrice);
    }

    // will fail in IE: rgba(119, 119, 119, 1)
    @Test
    public void verifyRegularPriceFontOnMainPage(){
        WebElement elItemRegularPrice = driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/s"));
        String itemRegPriceFontColor = elItemRegularPrice.getCssValue("color");
        String itemRegPriceFontStyle = elItemRegularPrice.getCssValue("text-decoration");

        Assert.assertEquals("Regular price text color on main page is not gray.","rgb(119, 119, 119)", itemRegPriceFontColor );
        Assert.assertEquals("Regular price text style on main page is not strike.", "line-through", itemRegPriceFontStyle);
    }

    // will fail in IE: rgba(102, 102, 102, 1)
    @Test
    public void verifyRegularPriceFontOnProductPage(){
        WebElement yellowDuckItem = driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]"));
        yellowDuckItem.click();
        WebElement elProductRegularPrice = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/s"));
        String productRegPriceFontColor = elProductRegularPrice.getCssValue("color");
        String productRegPriceFontStyle = elProductRegularPrice.getCssValue("text-decoration");

        Assert.assertEquals("Regular price text color on product page is not gray.","rgb(102, 102, 102)", productRegPriceFontColor );
        Assert.assertEquals("Regular price text style on product page is not strike.", "line-through", productRegPriceFontStyle);
    }

    // will fail in FF: font-weight=900
    // will fail in IE: rgba(204, 0, 0, 1)
    @Test
    public void verifySpecialPriceFontOnMainPage(){
        WebElement elItemSpecialPrice =  driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]/div[4]/strong"));
        String itemSpecPriceFontColor = elItemSpecialPrice.getCssValue("color");
        String itemSpecPriceFontStyle = elItemSpecialPrice.getCssValue("font-weight");

        Assert.assertEquals("Special price text color on main page is not red.","rgb(204, 0, 0)", itemSpecPriceFontColor );
        Assert.assertEquals("Special price text style on main page is not bold.", "bold", itemSpecPriceFontStyle);
    }

    // will fail in FF: font-weight=700
    // will fail in IE: rgba(204, 0, 0, 1)
    @Test
    public void verifySpecialPriceFontOnProductPage(){
        WebElement yellowDuckItem = driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]"));
        yellowDuckItem.click();
        WebElement elProductSpecialPrice = driver.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[2]/div[2]/strong"));
        String productSpecPriceFontColor = elProductSpecialPrice.getCssValue("color");
        String productSpecPriceFontStyle = elProductSpecialPrice.getCssValue("font-weight");

        Assert.assertEquals("Special price text color on product page is not red.","rgb(204, 0, 0)", productSpecPriceFontColor );
        Assert.assertEquals("Special price text style on product page is not bold.", "bold", productSpecPriceFontStyle);
    }


    @AfterClass
    public static void stopTesting(){
        driver.quit();
    }
}


