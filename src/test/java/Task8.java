import com.google.common.io.Files;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.*;
import java.util.List;

/**
 * Created by polina.kozhemiako on 11/13/2017.
 */
public class Task8 {
    private static EventFiringWebDriver driver;

    public Task8(){
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new EventsListener());
    }

    @After
    public void stop(){
        driver.quit();
    }

    @Test
    public void verifyHeadersArePresentOnPages(){
        BasicOperations.loginAsAdmin(driver);
        List<WebElement> menuItems = driver.findElements(By.cssSelector("#app-"));
        for(int i=0; i < menuItems.size(); i++){
            WebElement elMenuItem = driver.findElement(By.xpath("//*[@id='app-']["+(i+1)+"]"));
            String menuItemName = elMenuItem.getText();
            elMenuItem.click();
            Boolean isHeaderPresent = driver.findElements(By.cssSelector("#content h1")).size()>0;

            Assert.assertTrue("H1 header is not present on page " + menuItemName, isHeaderPresent);
            List<WebElement> subItems = driver.findElements(By.cssSelector("#app- ul.docs li"));
            for (int j=0; j < subItems.size(); j++) {
                WebElement elSubMenuItem = driver.findElements(By.cssSelector("#app- ul.docs li")).get(j);
                String subItemName =  elSubMenuItem.getText();
                elSubMenuItem.click();
                isHeaderPresent = driver.findElements(By.cssSelector("#content h1")).size()>0;

                Assert.assertTrue("H1 header is not present on page " + subItemName, isHeaderPresent);
            }
        }
    }

    public static class EventsListener extends AbstractWebDriverEventListener {

        @Override
        public void onException(Throwable throwable, WebDriver driver){
            try {
                FileOutputStream f = new FileOutputStream("log_task8.txt");
                System.setOut(new PrintStream(f));
                System.out.println("Exception: " + throwable);

                File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(tempFile, new File("screenshot.png"));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver){
            System.out.println("Start searching: " + by);
        }

        @Override
        public void afterFindBy (By by, WebElement element, WebDriver driver){
            System.out.println("Element \'" + by + "\'" + " found");
        }
    }
}


