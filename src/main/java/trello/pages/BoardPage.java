package trello.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.BrowserFactory.driver;

public class BoardPage {
    private By menuBtn = By.cssSelector(".member");
    private By menu = By.cssSelector((".no-back"));
    private By logoutBtn = By.cssSelector(".js-logout");


    public void logout(){
        driver.findElement(menuBtn).click();

        String name = driver.findElement(menu).getAttribute("textContent");
        if (name.contains("Rina")){
            System.out.println("Rina is present");
        }

        driver.findElement(logoutBtn).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://trello.com/logged-out"));
    }
}
