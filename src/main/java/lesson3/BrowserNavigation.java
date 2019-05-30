package lesson3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrowserNavigation {
    WebDriver driver;

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
    }


    @Test
    public void testBrowserNavigation(){
        driver.get("https://google.com");
        driver.get("https://facebook.com");
        System.out.println("CURRENT URL: "+driver.getCurrentUrl());
        driver.navigate().back();
        System.out.println("AFTER .back() URL: "+driver.getCurrentUrl());
        driver.navigate().forward();
        System.out.println("AFTER .forward() URL: "+driver.getCurrentUrl());
        driver.quit();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
