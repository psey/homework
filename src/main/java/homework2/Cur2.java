package homework2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class Cur2 {
    public static void main(String[] args) {
        Map <String, Double> pokupka;
        Map <String, Double> prodaj;

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, 15);


        driver.manage().window().maximize();
        By privatCurXPath = By.xpath("//div[@class = 'section']/div[2]");
        driver.get("https://www.privat24.ua");

        wait.until(ExpectedConditions.visibilityOfElementLocated(privatCurXPath));
        WebElement val = driver.findElement(privatCurXPath);
        String value = val.getText();
        System.out.println(value);
        double privatPokupka = Double.parseDouble(getWord(value,0));
        double privatProdaj = Double.parseDouble(getWord(value,1));

        System.out.println(privatPokupka);
        System.out.println(privatProdaj);

        driver.get("https://my.ukrsibbank.com/ru/personal/operations/currency_exchange/");
        By ukrSibPokupkaXPath = By.xpath("(//tr)[2]/td[2]");
        By ukrSibProdajXPath = By.xpath("(//tr)[2]/td[3]");

        wait.until(ExpectedConditions.presenceOfElementLocated(ukrSibPokupkaXPath));
        WebElement pokupka2 = driver.findElement(ukrSibPokupkaXPath);
        WebElement prodaj2 = driver.findElement(ukrSibProdajXPath);

        double ukrPokupka = Double.parseDouble(pokupka2.getText());
        double ukrProdaj = Double.parseDouble(prodaj2.getText());

        System.out.println(ukrPokupka);
        System.out.println(ukrProdaj);


        driver.get("https://www.universalbank.com.ua/");

        By universalPokupkaXPath = By.xpath("(//tr)[2]/td[2]");
        By universalProdajXPath = By.xpath("(//tr)[2]/td[3]");

        wait.until(ExpectedConditions.presenceOfElementLocated(universalPokupkaXPath));
        WebElement pokupka3 = driver.findElement(universalPokupkaXPath);
        WebElement prodaj3 = driver.findElement(universalProdajXPath);

        double universalPokupka = Double.parseDouble(pokupka3.getText());
        double universalProdaj = Double.parseDouble(prodaj3.getText());

        System.out.println(universalPokupka);
        System.out.println(universalProdaj);

        driver.get("https://www.oschadbank.ua/ua");
        By oschadPokupkaXPath = By.xpath("//*[@class = 'buy-USD']");
        By oschadProdajXPath = By.xpath("//*[@class = 'sell-USD']");

        wait.until(ExpectedConditions.presenceOfElementLocated(oschadPokupkaXPath));
        WebElement pokupka4 = driver.findElement(oschadPokupkaXPath);
        WebElement prodaj4 = driver.findElement(oschadProdajXPath);

        double oschadPokupka = Double.parseDouble(pokupka4.getText());
        double oschadProdaj = Double.parseDouble(prodaj4.getText());

        System.out.println(oschadPokupka);
        System.out.println(oschadProdaj);

        driver.get("https://www.bank.gov.ua/control/uk/curmetal/detail/currency?period=daily");
        By nacPokupkaXPath = By.xpath("//*[contains(text(), 'USD')]/following-sibling::*[3]");
        By nacCountXPath = By.xpath("//*[contains(text(), 'USD')]/following-sibling::*[1]");

        wait.until(ExpectedConditions.presenceOfElementLocated(nacPokupkaXPath));
        WebElement pokupka5 = driver.findElement(nacPokupkaXPath);
        WebElement forWichCount1 = driver.findElement(nacCountXPath);

        double nacPokupka = Double.parseDouble(pokupka5.getText());
        double forWhatCount = Double.parseDouble(forWichCount1.getText());
        double nacBankCurrency = nacPokupka / forWhatCount;


        System.out.println(nacBankCurrency);
        System.out.println(nacPokupka);
        System.out.println(forWhatCount);
        //*[contains(text(), 'USD')]





        driver.quit();


    }
    public static String getWord(String s, int n){
        String str = (s+" ").split("/")[n];
        String str2 = str.replaceAll(" ", "");
        return str2;
    }
}
