package gl.pageobjectapproach.page;

import gl.pageobjectapproach.data.PageBase;
import gl.pageobjectapproach.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase{

    @FindBy(name="username")
    WebElement inputUsername;

    @FindBy(name="password")
    WebElement inputPassword;

    @FindBy(name="login")
    WebElement buttonLogin;


    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void open(){
        driver.get(TestData.adminPageUrl);
    }

    public void loginAsAdmin(){
        inputUsername.clear();
        inputUsername.sendKeys(TestData.username);
        inputPassword.clear();
        inputPassword.sendKeys(TestData.password);
        buttonLogin.click();
    }
}
