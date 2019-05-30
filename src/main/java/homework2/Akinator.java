package homework2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Scanner;

public class Akinator {
    public static void main(String[] args) throws InterruptedException {
        String url = "https://ru.akinator.com/";
        By question = By.className("question-text");
        By loaderAk = By.id("div-overlay");

        WebDriver driver = new ChromeDriver();

        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement playBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href = '/game']")));

        playBtn.click();
        System.out.println("Да - 1, Нет - 2, Я не знаю - 3, Возможно Частично - 4, Скорее нет Не совсем - 5 ");

        Scanner sc = new Scanner(System.in);

        while (true) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(question));

            System.out.println(driver.findElement(question).getText());

             if (!sc.hasNextInt()) {
                System.out.println("Вы вводите не число");
                sc.next();

            } else {
                int num = sc.nextInt();
                if (num >= 1 && num <= 5){
                        WebElement elemInRow = driver.findElement(By.xpath("//*[contains(@class, 'database-selection')]//li["+num+"]"));
                        elemInRow.click();
                        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderAk));
                        try {
                            driver.findElement(question);
                        } catch (Exception e){
                            WebElement headerP = driver.findElement(By.className("sub-bubble-propose"));
                            WebElement contentP = driver.findElement(By.className("proposal-title"));
                            WebElement subTitle = driver.findElement(By.className("proposal-subtitle"));
                            System.out.println(headerP.getText() + " " + contentP.getText() + " " + subTitle.getText());
                            break;

                        }

                    } else {
                        System.out.println("Введите число от 1 до 5");
                }
                }
            }
            driver.quit();
        }
    }



