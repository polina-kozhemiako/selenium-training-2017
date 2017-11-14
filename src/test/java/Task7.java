import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * Created by polina.kozhemiako on 11/7/2017.
 */
public class Task7 extends TestBase {

    @Test
    public void verifyNewWindowOpening(){
        BasicOperations.loginAsAdmin(driver);
        driver.findElement(By.xpath("//*[@id='app-'][3]")).click();
        WebElement elCanada = driver.findElement(By.xpath("//td/a[contains(text(), 'Canada')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elCanada);
        elCanada.click();

        List<WebElement> elLinks = driver.findElements(By.cssSelector("i[class='fa fa-external-link']"));
        for(int i = 0; i < elLinks.size(); i++){
            String originalWindow = driver.getWindowHandle();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            Set<String> existWindows = driver.getWindowHandles();
            clickExternalLink(i);
            String newWindow = wait.until(anyWindowOtherThan(existWindows));
            driver.switchTo().window(newWindow);

            Assert.assertTrue("New window was not opened at link # "+(i+1)+".", (newWindow != originalWindow));
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }

    private void clickExternalLink(int i){
        driver.findElements(By.xpath("//*[@class='fa fa-external-link']")).get(i).click();
    }

    private ExpectedCondition<String> anyWindowOtherThan(Set<String> windows){
        return new ExpectedCondition<String>() {
            public String apply(@Nullable WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
}
