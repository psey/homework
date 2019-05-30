package homework2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/*****
 * Написать программу которая будет отображать кол-во мест в зале кинотеатра:
 *
 * Кол-во мест в зале
 * Кол-во мест занятых
 * Кол-во мест свободных
 * % Занятых мест от “всего мест в зале”
 * % Свободных мест от “всего мест в зале”
 */

public class Cinema {
    public static void main(String[] args){

        String url = "http://liniakino.com/showtimes/aladdin/";
        By showtimeXPath = By.xpath("./following-sibling::*//li[@class = 'showtime-item']/a");
        By filmNameXPath = By.xpath("//h1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();

        driver.get(url);

        WebElement filmName = wait.until(ExpectedConditions.visibilityOfElementLocated(filmNameXPath));
        List<WebElement> listOfFilms;
        listOfFilms = driver.findElements(filmNameXPath);

        for (int i = 0; i < listOfFilms.size(); i++){
            WebElement filmItem = listOfFilms.get(i);

            if (filmItem.getAttribute("innerText").equals("Дамбо")){
                WebElement firstSeans = filmItem.findElement(showtimeXPath);
                firstSeans.click();
                break;
            } else {
                System.out.println("Фильм в прокате " + filmItem.getAttribute("innerText"));
            }

        }

        WebElement frameID = driver.findElement(By.tagName("iframe"));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameID));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("window")));

        WebElement closeBtn = driver.findElement(By.xpath("//div[@class ='window']/div[contains(@class, 'close')]"));

        Actions action = new Actions(driver);
        action.moveToElement(closeBtn).click().perform();

        WebElement titleInFrame = driver.findElement(By.className("showtime-title"));
        String titleInFrameText = titleInFrame.getAttribute("outerText");

        if (titleInFrameText.equals("Дамбо")) {
            List<WebElement> seatsCount = driver.findElements(By.xpath("//div[contains(@class, 'seat')]"));
            int seatCount = seatsCount.size() - 1;
            List<WebElement> seatsOccupied = driver.findElements(By.xpath("//div[contains(@class, 'seat-occupied')]"));
            int seatOccupied = seatsOccupied.size();
            int freeSeats = seatCount - seatOccupied;
            double percentFree = freeSeats * 100 / seatCount;
            double percentOccupied =  seatOccupied * 100 / seatCount;


            System.out.println("Кол-во мест в зале: " + seatCount);
            System.out.println("Кол-во занятых мест в зале: " + seatOccupied);
            System.out.println("Кол-во свободных мест в зале: " + freeSeats);
            System.out.println("% свободных " + percentFree);
            System.out.println("% занятых " + percentOccupied);

        } else {
            System.out.println("Выбран фильм " + titleInFrameText);
        }
     driver.quit();

    }

}
