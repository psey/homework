package lesson5;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DriverFindElement extends BaseTest {

    @Test
    public void pageLoadWait(){
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); // работает только на get page
        // по дефолту стоит в хроме, потому типа не ставим
    }
    @Test
    public void elementWait(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS); // ожидание скрипта. Никто не юзает


    }
}
