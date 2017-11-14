import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


/**
 * Created by polina.kozhemiako on 10/31/2017.
 */

@RunWith(Parameterized.class)
public class Task1 extends TestBaseParameterized{

    @Test
    public void GoogleTest(){
        driver.get("https://google.com");
        driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys("qwerty" + Keys.ENTER);
    }

    @After
    public void stop(){
        driver.close();
    }
}
