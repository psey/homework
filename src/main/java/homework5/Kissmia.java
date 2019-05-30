package homework5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class Kissmia {
    public WebDriver driver;
    public WebDriverWait wait;
    public String emailUrl = "https://tempail.com/en/";
    public String kissmiaUrl = "https://kismia.com/";
    public String email;
    public String name = "Николай";
    public String pwd = "Qwe123";
    //paltaramla@desoz.com

    @Test
    public void userRegistration() throws InterruptedException {
        System.out.println(email);
        driver.get(kissmiaUrl);
        driver.findElement(By.xpath("//div[@class = 'screen_picture']//a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[contains(@class, 'js_signUpForm')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'js_firstStep']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'js_firstStep']//input[@name = 'gender']")));

        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class = 'js_firstStep']//input[@name = 'gender']")).click();
        driver.findElement(By.xpath("//input[@name = 'name']")).sendKeys(name);
        driver.findElement(By.xpath("//a[@data-second-step-text = 'Зарегистрироваться']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'js_secondStep']")));

        driver.findElement(By.xpath("//div[@class = 'js_secondStep']//input[@name = 'email']")).sendKeys(email);
        driver.findElement(By.xpath("//div[@class = 'js_secondStep']//input[@name = 'password']")).sendKeys(pwd);
        driver.findElement(By.xpath("//a[@data-second-step-text = 'Зарегистрироваться']")).click();
        String curUrl = driver.getCurrentUrl();

        if (curUrl.equals("https://kismia.com/sign/application")) System.out.println("kk");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[class= 'sign-application-loader']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id = 'regdata_birth_date']")));
        driver.findElement(By.id("bday_day")).click();
        driver.findElement(By.xpath("//*[@value= '03']")).click();
      //  Thread.sleep(2000);
        driver.findElement(By.id("bday_month")).click();
        driver.findElement(By.xpath("//*[@id = 'bday_month']/*[@value = '03']")).click();
        driver.findElement(By.id("bday_year")).click();
        driver.findElement(By.xpath("//*[@value= '1991']")).click();
        driver.findElement(By.xpath("//*[@type= 'submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("js-skip-upload-photo-step"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'reg-over']")));
        driver.findElement(By.xpath("//*[@class = 'reg-over']"));

        // проверить что урла https://kismia.com/sign/done

        driver.get(emailUrl);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@alt = 'temp mail']")));
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'mail active']/a")));
      //  Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@class = 'mail ']/a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'button-container']/a")));
        driver.findElement(By.xpath("//div[@class = 'button-container']/a")).click();
        ArrayList tabs2 = new ArrayList (driver.getWindowHandles());//Получение списка табов
        driver.switchTo().window(String.valueOf(tabs2.get(1)));//Переключение на второй таб в браузере
        // driver.switchTo().window((String) tabs2.get(0));//Переключение на первый таб в браузере

        }




    @BeforeTest
    public void setupTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
      //  driver.get(emailUrl);
       // email = driver.findElement(By.xpath("//*[@id = 'eposta_adres']")).getAttribute("defaultValue");
    }

  //  @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
