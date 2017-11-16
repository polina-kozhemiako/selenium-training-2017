package gl.pageobjectapproach.data;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by polina.kozhemiako on 11/16/2017.
 */
public class TestBase {
    public static WebDriver driver;

    @BeforeClass
    public static void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }
}
