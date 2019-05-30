package trello;

import core.BrowserFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import trello.pages.LoginPage;

import java.io.IOException;

public class LoginTest extends BrowserFactory {


    @Test
    public void login() throws IOException{
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.login("ivanovamarichka+6@gmail.com", "mbA7PyNj4LWYKb5");
        Assert.assertTrue(!driver.findElements(By.cssSelector(".mod-add")).isEmpty(), "Board page isn't opened");
    }

}
