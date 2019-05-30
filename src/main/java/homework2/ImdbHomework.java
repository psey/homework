package homework2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/*****
 *  Написать программу которая выведет на экран информацию о фильме.
 * Ссылка на страницу фильма: https://www.imdb.com/title/tt0111161/
 *
 * 1. Название фильма
 * 2. Дата выхода
 * 3. Продолжительность фильма в минутах. Продолжительность фильма в секундах.
 * 4. Рейтинг фильма
 * 5. Жанр
 * 6. Ссылку на трейлер фильма
 * 7. Ссылку на постер фильма
 * 8. Директора фильма
 * 9. 5 Актеров фильма
 * 10. Рейтинг Metascore
 * 11. Кол-во ревью (отдельно пользовательских, отдельно критиков, сумму пользовательских и критиков)
 * 12. Названия 3х "похожих" фильмов
 */

public class ImdbHomework {
    public static void main(String[] args) {

        String filmUrl = "https://www.imdb.com/title/tt0111161/";
        By filmNameXPath = By.xpath("//div[@class= 'title_wrapper']/h1");
        By releaseDateXPath = By.xpath("//a[contains(@title,'See more')]");
        By filmGenreXPath = By.xpath("//div[@class = 'subtext']/a");
        By posterXPath = By.xpath("//div[@class = 'poster']//img");
        By  trailerXPath = By.xpath("//div[@class = 'slate']/a");
        By directorXPath = By.xpath("//*[@class = 'credit_summary_item']");
        By ratingClassName = By.className("ratingValue");
        By metaScoreXpath = By.xpath("//*[contains(@class,'metacriticScore')]");
        By userReviewXPath = By.xpath("//span/*[contains(@href,'reviews')]");
        By criticReviewXPath = By.xpath("//span/*[contains(@href,'externalreviews')]");
        By filmDurationXPAth = By.xpath("//time");
        By actorsXpath = By.xpath("//a[contains(@href, 'fullcredits')]");
        By listOfActorsXPath = By.xpath("//*[@class = 'cast_list']//tr//td[2]//a[contains(@href, 'name')]");
        By similarFilmXPAth = By.xpath("//*[@class = 'list_name']");
        List<WebElement> listOfActors;
        List <WebElement> listOfSimilarFilms;


        WebDriver driver = new ChromeDriver();

        driver.get(filmUrl);

        WebElement filmName = wait(driver, filmNameXPath);
        WebElement releaseDate = driver.findElement(releaseDateXPath);
        WebElement filmGenre = driver.findElement(filmGenreXPath);
        WebElement posterLink = driver.findElement(posterXPath);
        WebElement trailerLink = driver.findElement(trailerXPath);
        WebElement director = driver.findElement(directorXPath);
        WebElement rating = driver.findElement(ratingClassName);
        WebElement metaScore = driver.findElement(metaScoreXpath);
        WebElement userReview = driver.findElement(userReviewXPath);
        WebElement criticReview = driver.findElement(criticReviewXPath);
        WebElement filmDuration = driver.findElement(filmDurationXPAth);

        String filmDuration2 = filmDuration.getAttribute("dateTime");
        String durationImMin = filmDuration2.replaceAll("[^0-9]","");
        int durationInSec = Integer.parseInt(durationImMin) * 60;


        String userReviewText = getFirstWordWithoutComa(userReview.getText());
        int countOfUserReview = Integer.parseInt(userReviewText);
        String criticsReviewText = getFirstWordWithoutComa(criticReview.getText());
        int countOfCriticsReview = Integer.parseInt(criticsReviewText);
        int amountReview = + countOfCriticsReview + countOfUserReview;


        System.out.println("Name " + filmName.getText());
        System.out.println("Release date " + releaseDate.getText());
        System.out.println("Film duration in min " + durationImMin + "\nDuration in seconds: " + durationInSec);
        System.out.println("Rating " + rating.getAttribute("outerText"));
        System.out.println("Genre " + filmGenre.getAttribute("innerHTML"));
        System.out.println("Poster Link " + posterLink.getAttribute("currentSrc"));
        System.out.println("Trailer link " + trailerLink.getAttribute("href"));
        System.out.println(director.getAttribute("outerText"));
        System.out.println("Metascorerating " + metaScore.getAttribute("innerText"));
        System.out.println("User review: " + countOfUserReview + "\nCritics review: " + countOfCriticsReview +
                "\nAmount count of reviews: " + amountReview );


        WebElement clickOnCast= driver.findElement(actorsXpath);
        clickOnCast.click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement castManuTable =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'cast_list']")));

        listOfActors = driver.findElements(listOfActorsXPath);
        System.out.println("\nFive film actors: ");
        for (int i = 0; i < 5; i++){
            WebElement elem = listOfActors.get(i);
            String n = elem.getAttribute("innerText");
            System.out.println(i + 1 + " " + n);
        }

        listOfSimilarFilms = driver.findElements(similarFilmXPAth);
        System.out.println("\nSimilar films are");

        for (int i = 0; i < 3; i++){
            WebElement elem = listOfSimilarFilms.get(i);
            String n = elem.getAttribute("innerText");

            System.out.println(i + 1 + " " + n);
        }



        driver.quit();

    }
    public static WebElement wait(WebDriver driver, By elem){
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(elem));

    }
    public static String getFirstWordWithoutComa(String s){
        String str = (s+" ").split(" ")[0];
        String str2 = str.replaceAll(",", "");
        return str2;
    }


}
