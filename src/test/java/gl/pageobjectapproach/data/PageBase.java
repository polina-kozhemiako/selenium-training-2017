package gl.pageobjectapproach.data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by polina.kozhemiako on 11/16/2017.
 */
public class PageBase {
    public WebDriver driver;
    public WebDriverWait wait;

    public PageBase(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }
}
