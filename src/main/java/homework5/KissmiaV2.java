package homework5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class KissmiaV2 {
    public static String userEmail = "bastibigne@desoz.com";
    public static String height = "180";
    public WebDriver driver;
    public WebDriverWait wait;
    public String url = "https://kismia.com/";
    public String userPassword = "Qwe123";
    public int whoInList = 2;
    public String messageToUser = "Привет, красавица! Как Ваши дела?";
    By loginXPath = By.xpath("//*[contains(@class, 'js_signInForm')]//*[@name = 'email']");
    By passwordXPath = By.xpath("//*[contains(@class, 'js_signInForm')]//*[@name = 'password']");
    By submitFormBtnXPath = By.xpath("//*[contains(@class, 'js_signInForm')]//a[contains(@class, 'js_submit')]");

//2. Тест на отправку сообщения

    @Test
    public void loginTest(){
        boolean isMatch;
        SoftAssert softAssert = new SoftAssert();
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginXPath));
        driver.findElement(loginXPath).sendKeys(userEmail);
        driver.findElement(passwordXPath).sendKeys(userPassword);
        driver.findElement(submitFormBtnXPath).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'new-header__wrapper']")));
        String currUrl = driver.getCurrentUrl();
        if (currUrl.contains("matches") ){
            isMatch = true;
        } else {
            isMatch = false;
        }
        softAssert.assertEquals(isMatch, true);

    }
    @Test (dependsOnMethods = "loginTest")
    public void editProfileHeight(){
        SoftAssert softAssert = new SoftAssert();

        driver.findElement(By.xpath("//ul[@class = 'new-header-sub-menu']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class = 'new-header-sub-menu']/li[contains(@class, 'active')]")));
        driver.findElement(By.xpath("//li[@class = 'new-header-subs__elem'][1]/a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'js-edit-profile')]")));
        driver.findElement(By.xpath("//*[contains(@class, 'js-edit-profile')]")).click();
        driver.findElement(By.xpath("//*[@data-field =  'height'][@type = 'number']")).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), height);
        driver.findElement(By.xpath("//*[contains(@class, 'save-profile-changes')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h6[@class = 'profile-info__field-header' and text()=\"Рост\"])")));
        String heightInfo = driver.findElement(By.xpath("(//h6[@class = 'profile-info__field-header' and text()=\"Рост\"])/following-sibling::p")).getText();
        heightInfo = heightInfo.substring(0,4);
        softAssert.assertEquals(heightInfo, height);

    }

    @Test (dependsOnMethods = "loginTest")
    public void editProfileAge() {
        SoftAssert softAssert = new SoftAssert();

        driver.findElement(By.xpath("//ul[@class = 'new-header-sub-menu']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class = 'new-header-sub-menu']/li[contains(@class, 'active')]")));
        driver.findElement(By.xpath("//li[@class = 'new-header-subs__elem'][2]/a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class ='new-content-title new' and text() = 'Мои настройки']")));
        driver.findElement(By.xpath("//*[@class ='settings-nav']/div[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class ='settings-nav']/div[2][contains(@class,'active')]")));
        String currYear = driver.findElement(By.xpath("//*[@name = 'year']")).getAttribute("value");

        driver.findElement(By.xpath("//*[@name = 'year']")).click();
        driver.findElement(By.xpath("//*[@value= '1991']")).click();
        driver.findElement(By.xpath("//*[contains(@class,'js_save')]")).click();
        String updYear = driver.findElement(By.xpath("//*[@name = 'year']")).getAttribute("value");

        softAssert.assertNotEquals(currYear, updYear);
    }

    @Test (dependsOnMethods = "loginTest")
    public void sendMessage() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.xpath("//*[@class = 'right-side-menu__item']/a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@class, 'js_moreOptionsTrigger')])")));
        try {
            driver.findElement(By.className("cookies-popup__btn")).click();
        } catch (Exception e){

        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//*[contains(@class, 'js_moreOptionsTrigger')])["+whoInList+"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[contains(@class, 'js_moreOptions')])[2]//li")));
        driver.findElement(By.xpath("(//ul[contains(@class, 'js_moreOptions')])[2]//li")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name = 'message']")));
        driver.findElement(By.xpath("//*[@name = 'message']")).sendKeys(messageToUser);
        driver.findElement(By.xpath("//*[contains(@class, 'js_formComposeSubmit')]")).click();
        driver.findElement(By.className("threads-limit"));

    }


    @BeforeTest
    public void setupTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


}

