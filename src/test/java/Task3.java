import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;

/**
 * Created by polina.kozhemiako on 10/25/2017.
 */
public class Task3 extends TestBase{

    @Test
    public void verifyHeadersArePresentOnPages(){
        BasicOperations.loginAsAdmin(driver);
        List<WebElement> menuItems = driver.findElements(By.cssSelector("#app-"));
        for(int i=0; i < menuItems.size(); i++){
            driver.findElement(By.xpath("//*[@id='app-']["+(i+1)+"]")).click();
            Boolean isHeaderPresent = driver.findElements(By.cssSelector("#content h1")).size()>0;

            Assert.assertTrue("H1 header is not present on the menu page.", isHeaderPresent);
            List<WebElement> subItems = driver.findElements(By.cssSelector("#app- ul.docs li"));
            for (int j=0; j < subItems.size(); j++) {
                driver.findElements(By.cssSelector("#app- ul.docs li")).get(j).click();
                isHeaderPresent = driver.findElements(By.cssSelector("#content h1")).size()>0;

                Assert.assertTrue("H1 header is not present on the submenu page.", isHeaderPresent);
            }
        }
    }
}
