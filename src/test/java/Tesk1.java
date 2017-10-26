import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by polina.kozhemiako on 10/23/2017.
 */
public class Tesk1 {

    @Test
    public void GoogleTest(){
        //FirefoxDriverManager.getInstance().setup();
        InternetExplorerDriverManager.getInstance().arch32().setup();
        //WebDriver driver = new FirefoxDriver();
        WebDriver driver = new InternetExplorerDriver();

        driver.get("https://google.com");
        driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys("qwerty");

        driver.close();
    }
}
