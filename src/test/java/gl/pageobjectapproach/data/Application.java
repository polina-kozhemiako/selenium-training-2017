package gl.pageobjectapproach.data;

import gl.pageobjectapproach.page.CartPage;
import gl.pageobjectapproach.page.LoginPage;
import gl.pageobjectapproach.page.MainPage;
import gl.pageobjectapproach.page.ProductPage;
import org.openqa.selenium.WebDriver;

public class Application {

    WebDriver driver;
    public LoginPage loginPage;
    public MainPage mainPage;
    public ProductPage productPage;
    public CartPage cartPage;

    public Application(WebDriver driver){
        this.driver = driver;
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void goBack(){
        driver.navigate().back();
    }
}
