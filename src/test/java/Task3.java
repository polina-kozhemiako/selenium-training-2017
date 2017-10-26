import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

/**
 * Created by polina.kozhemiako on 10/25/2017.
 */
public class Task3 {

    @Before
    public void start(){
        FirefoxDriverManager.getInstance().setup();
    }


    @Test
    public void VerifyHeadersForPages(){
        WebDriver driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8080/litecart/admin");
        // log in
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath(".//*[@id='box-login']/form/div[2]/button")).click();
        // get menu items
        List<WebElement> menuItems = driver.findElements(By.cssSelector("#app-"));
        for(int i=0; i < menuItems.size(); i++){
            WebElement menuItem = driver.findElements(By.cssSelector("#app-")).get(i);
            menuItem.click();
            // get subitems
            List<WebElement> subItems = driver.findElements(By.cssSelector("#app- ul[class=docs] li"));
            if (subItems.size()>0)
            {
                for (int j=0; j < subItems.size(); j++) {
                    WebElement subItem = driver.findElements(By.cssSelector("#app- ul[class=docs] li")).get(j);
                    subItem.click();
                    Boolean isHeaderPresent = driver.findElements(By.cssSelector("#content h1")).size()>0;

                    Assert.assertTrue("H1 header is not present on the page.", isHeaderPresent);
                }
            }
        }

        driver.close();
    }

}
