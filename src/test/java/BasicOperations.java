import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by polina.kozhemiako on 11/14/2017.
 */
public class BasicOperations {
    public static final String adminPageUrl = "http://127.0.0.1:8080/litecart/admin/";
    public static final String homePageUrl = "http://127.0.0.1:8080/litecart";
    private static final String username = "admin";
    private static final String password = "admin";

    public static void openHomePage(WebDriver driver){
        driver.get(homePageUrl);
    }

    public static void loginAsAdmin(WebDriver driver) {
        driver.get(adminPageUrl);
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }
}
