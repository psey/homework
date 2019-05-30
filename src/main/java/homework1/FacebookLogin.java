package homework1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;


public class FacebookLogin {


    public static void main(String[] args){

        String facebookUrl = "https://www.facebook.com/";
        By loginEmailXPath = By.xpath("//input[@id = 'email']");
        By loginPwdXPath = By.xpath("//input[@id = 'pass']");
        By loginBtnXPath = By.xpath("//label[@id = 'loginbutton']");
        By messangerBtnXPath = By.xpath("//a[@name = 'mercurymessages']");
        By lastMsgXPath = By.xpath("//div[@class = 'content']/div[2]");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        driver.get(facebookUrl);
        WebElement loginEmail = waitUntil(driver, loginEmailXPath);
        loginEmail.sendKeys(Variables.LOGIN);

        WebElement loginPwd = waitUntil(driver, loginPwdXPath);
        loginPwd.sendKeys(Variables.PASSWORD);

        WebElement loginBtn = waitUntil(driver, loginBtnXPath);
        loginBtn.click();

        WebElement messangerBtn = waitUntil(driver, messangerBtnXPath);
        messangerBtn.click();

        WebElement getLastMessage = waitUntil(driver, lastMsgXPath);

        System.out.println("***************************************************");
        System.out.println(getLastMessage.getAttribute("textContent"));


        driver.quit();

    }
    public static WebElement waitUntil(WebDriver driver, By elem){
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(elem));

    }


}
