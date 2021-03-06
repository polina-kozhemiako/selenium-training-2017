import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by polina.kozhemiako on 10/26/2017.
 */
@RunWith(Parameterized.class)
public class Task4 extends TestBaseParameterized{

    @Before
    public void startTesting() {
        BasicOperations.openHomePage(driver);
        WebElement elCampaigns = driver.findElement(By.cssSelector("#box-campaigns h3"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elCampaigns);
    }

    @Test
    public void LC_01_verifyProductName(){
        String itemName = driver.findElement(By.xpath("//*[@id='box-campaigns']//div[2]")).getText();
        driver.findElement(By.xpath("//*[@id='box-campaigns']//a[1]")).click();
        String productName = driver.findElement(By.cssSelector("h1.title")).getText();

        Assert.assertEquals("Product name on main page is not the same as on product page.", itemName, productName);
    }

    @Test
    public void LC_02_verifyRegularPriceValue(){
        String itemRegPrice = driver.findElement(By.xpath("//*[@id='box-campaigns']//s")).getText();
        driver.findElement(By.xpath("//*[@id='box-campaigns']//a[1]")).click();
        String productRegPrice = driver.findElement(By.cssSelector("s.regular-price")).getText();

        Assert.assertEquals("Regular price value on main page is not the same as on product page.", itemRegPrice, productRegPrice);
    }

    @Test
    public void LC_03_verifySpecialPriceValue(){
        String itemSpecPrice = driver.findElement(By.xpath("//*[@id='box-campaigns']//strong")).getText();
        driver.findElement(By.xpath("//*[@id=\'box-campaigns\']//a[1]")).click();
        String productSpecPrice = driver.findElement(By.cssSelector("strong.campaign-price")).getText();

        Assert.assertEquals("Special price value on main page is not the same as on product page.", itemSpecPrice, productSpecPrice);
    }

    @Test
    public void LC_04_verifyRegularPriceFontOnMainPage(){
        WebElement elItemRegularPrice = driver.findElement(By.xpath("//*[@id='box-campaigns']//s"));
        String itemRegPriceFontColor = elItemRegularPrice.getCssValue("color");
        String itemRegPriceFontStyle = elItemRegularPrice.getCssValue("text-decoration");
        String expected =(driver instanceof FirefoxDriver)?"rgb(119, 119, 119)":"rgba(119, 119, 119, 1)";
        String expectedStyle =(driver instanceof ChromeDriver)?"line-through solid rgb(119, 119, 119)":"line-through";

        Assert.assertEquals("Regular price text color on main page is not gray.", expected, itemRegPriceFontColor );
        Assert.assertEquals("Regular price text style on main page is not strike.", expectedStyle, itemRegPriceFontStyle);
    }

    @Test
    public void LC_05_verifyRegularPriceFontOnProductPage(){
        driver.findElement(By.xpath("//*[@id='box-campaigns']//a[1]")).click();
        WebElement elProductRegularPrice = driver.findElement(By.cssSelector("s.regular-price"));
        String productRegPriceFontColor = elProductRegularPrice.getCssValue("color");
        String productRegPriceFontStyle = elProductRegularPrice.getCssValue("text-decoration");
        String expectedColor =(driver instanceof FirefoxDriver)?"rgb(102, 102, 102)":"rgba(102, 102, 102, 1)";
        String expectedStyle =(driver instanceof ChromeDriver)?"line-through solid rgb(102, 102, 102)":"line-through";

        Assert.assertEquals("Regular price text color on product page is not gray.", expectedColor, productRegPriceFontColor );
        Assert.assertEquals("Regular price text style on product page is not strike.", expectedStyle, productRegPriceFontStyle);
    }

    @Test
    public void LC_06_verifySpecialPriceFontOnMainPage(){
        WebElement elItemSpecialPrice =  driver.findElement(By.xpath("//*[@id='box-campaigns']//strong"));
        String itemSpecPriceFontColor = elItemSpecialPrice.getCssValue("color");
        String itemSpecPriceFontStyle = elItemSpecialPrice.getCssValue("font-weight");
        String expectedColor =(driver instanceof FirefoxDriver)?"rgb(204, 0, 0)":"rgba(204, 0, 0, 1)";
        String expectedStyle =(driver instanceof ChromeDriver)?"700":"900";

        Assert.assertEquals("Special price text color on main page is not red.", expectedColor, itemSpecPriceFontColor );
        Assert.assertEquals("Special price text style on main page is not bold.", expectedStyle, itemSpecPriceFontStyle);
    }

    @Test
    public void LC_07_verifySpecialPriceFontOnProductPage(){
        driver.findElement(By.xpath("//*[@id='box-campaigns']//a[1]")).click();
        WebElement elProductSpecialPrice = driver.findElement(By.cssSelector("strong.campaign-price"));
        String productSpecPriceFontColor = elProductSpecialPrice.getCssValue("color");
        String productSpecPriceFontStyle = elProductSpecialPrice.getCssValue("font-weight");
        String expectedColor =(driver instanceof FirefoxDriver)?"rgb(204, 0, 0)":"rgba(204, 0, 0, 1)";

        Assert.assertEquals("Special price text color on product page is not red.",expectedColor, productSpecPriceFontColor );
        Assert.assertEquals("Special price text style on product page is not bold.", "700", productSpecPriceFontStyle);
    }
}


