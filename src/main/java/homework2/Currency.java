package homework2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/***
 * Написать программу которая будет:
 *
 * 1. Заходить на сайты банков
 * 2. Записывать 2 переменные типа "double" - курс покупки\продажи валют (гривна\доллар)
 * 3. Посчитать средний курс валют, отдельно, покупки и продажи между всеми банками (вывести в консоль)
 * 4. Вывести в консоль банк с самым низким курсом покупки доллара
 * 5. Вывести в консоль банк с самым высоким курсом продажи
 */

public class Currency {

    public WebDriver driver;
    public Map<String, Double> buyCurrMap = new HashMap<String, Double>();
    public Map<String, Double> sellCurrMap = new HashMap<String, Double>();
    public WebDriverWait wait;

    public static String getWord(String s, int n) {
        String str = (s + " ").split("/")[n];
        String str2 = str.replaceAll(" ", "");
        return str2;
    }

    public static double averageCurrency(Map<String, Double> someMap) {
        double sum = 0;
        for (double value : someMap.values()) {
            sum += value;
            System.out.println("Value: " + value);
        }
        return sum / someMap.size();

    }

    public static Double minValue(Map<String, Double> someMap) {
        double min = Double.MAX_VALUE;
        for (double value : someMap.values()) {
            if (value <= min) {
                min = value;
            }
        }

        return min;
    }

    public static Double greatestValue(Map<String, Double> someMap) {
        double max = Double.MIN_VALUE;
        for (double value : someMap.values()) {
            if (value >= max) {
                max = value;
            }
        }

        return max;

    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }


    @DataProvider(name = "testParams")
    public Object[][] provideUrls() {
        return new Object[][]{{"privat", "https://www.privat24.ua", By.xpath("//div[@class = 'section']/div[2]"),
                By.xpath("//div[@class = 'section']/div[2]")},
                {"ukrSib", "https://my.ukrsibbank.com/ru/personal/operations/currency_exchange/",
                        By.xpath("(//tr)[2]/td[2]"), By.xpath("(//tr)[2]/td[3]")},
                {"universal", "https://www.universalbank.com.ua/", By.xpath("(//tr)[2]/td[2]"),
                        By.xpath("(//tr)[2]/td[3]")},
                {"oschadbank", "https://www.oschadbank.ua/ua", By.xpath("//*[@class = 'buy-USD']"),
                        By.xpath("//*[@class = 'sell-USD']")},
                {"nationalBank", "https://www.bank.gov.ua/control/uk/curmetal/detail/currency?period=daily",
                        By.xpath("//*[contains(text(), 'USD')]/following-sibling::*[3]"),
                        By.xpath("//*[contains(text(), 'USD')]/following-sibling::*[1]")}
        };
    }

    @BeforeTest
    public void setUp() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "testParams")
    public void getCurrency(String name, String url, By buyCurrency, By sellCurrency) {
        String bankName = name;
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buyCurrency));
        WebElement buyVal = driver.findElement(buyCurrency);
        WebElement sellVal = driver.findElement(sellCurrency);

        String valueBuy = buyVal.getText();
        String valueSell = sellVal.getText();

        if (bankName.equals("privat")) {
            double privatBuyRate = Double.parseDouble(getWord(valueBuy, 0));
            double privatSellRate = Double.parseDouble(getWord(valueBuy, 1));
            //  System.out.println(name + " buy currency " + privatBuyRate);
            //  System.out.println(name + " sell currency " + privatSellRate);
            buyCurrMap.put(name, privatBuyRate);
            sellCurrMap.put(name, privatSellRate);

        } else if (bankName.equals("nationalBank")) {
            double nationalbankRate = Double.parseDouble(valueBuy);
            double forWhatCount = Double.parseDouble(valueSell);
            double nationalBankCurrency = nationalbankRate / forWhatCount;
            buyCurrMap.put(name, nationalBankCurrency);
            sellCurrMap.put(name, nationalBankCurrency);


            // System.out.println(name + " currency rate is " + nationalBankCurrency);

        } else {
            double bankCurrencyBuy = Double.parseDouble(valueBuy);
            double bankCurrencySell = Double.parseDouble(valueSell);
            //   System.out.println(name + " buy currency " + bankCurrencyBuy);
            //   System.out.println(name + " sell currency " + bankCurrencySell);
            buyCurrMap.put(name, bankCurrencyBuy);
            sellCurrMap.put(name, bankCurrencySell);

        }


    }

    @Test(dependsOnMethods = "getCurrency")
    public void getAvarageCurrency() {
        System.out.println("Avarage curreny buy " + averageCurrency(buyCurrMap));
        System.out.println("Avarage curreny sell " + averageCurrency(sellCurrMap));
    }

    @Test(dependsOnMethods = "getCurrency")
    public void getCourse() {
        double m = minValue(buyCurrMap);
        double s = greatestValue(sellCurrMap);
        System.out.println("min " + m + " bank " + getKeyFromValue(buyCurrMap, m));
        System.out.println("max " + s + " bank " + getKeyFromValue(sellCurrMap, s));

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
