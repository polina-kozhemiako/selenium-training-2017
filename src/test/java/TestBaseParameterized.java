import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by polina.kozhemiako on 11/13/2017.
 */

public class TestBaseParameterized {

    @Parameterized.Parameter
    public static WebDriver driver;

    @Parameterized.Parameters
    public static WebDriver[] getDriver() {
        ChromeDriverManager.getInstance().setup();
        FirefoxDriverManager.getInstance().setup();
        InternetExplorerDriverManager.getInstance().arch32().setup();

        return new WebDriver[]{new ChromeDriver(),  new FirefoxDriver(), new InternetExplorerDriver()};
    }

    @AfterClass
    public static void stopTesting() {
        driver.quit();
    }
}
