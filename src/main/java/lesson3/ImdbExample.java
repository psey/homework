package lesson3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ImdbExample {
    public WebDriver driver;

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
    }

    @Test
    public void myTest(){
        driver.get("http://www.imdb.com/chart/top");
        List <WebElement> titles = driver.findElements(By.xpath("//td[@class = 'titleColumn']"));
        for (WebElement element: titles){
            System.out.println(element.getAttribute("innerText"));
        //    element.getAttribute("attrName");

        }
        List <WebElement> links = driver.findElements(By.xpath("//td[@class = 'titleColumn']/a"));
        for (WebElement element: links){
            System.out.println(element.getAttribute("href"));
            //    element.getAttribute("attrName");

        }
        List <WebElement> rating = driver.findElements(By.xpath("//*[@class = 'ratingColumn imdbRating']"));
        for (WebElement element: rating){
            System.out.println(element.getAttribute("outerText"));

        }
//TODO SLIDE 31
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
