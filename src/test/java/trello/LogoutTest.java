package trello;

import org.testng.Assert;
import org.testng.annotations.Test;
import trello.pages.BoardPage;

public class LogoutTest extends LoginTest {

    @Test
    public void logout(){
        try {
            login();
            BoardPage boardPage = new BoardPage();
            boardPage.logout();
            Assert.assertTrue(driver.getCurrentUrl().contains("logged-out"),"Урла не равняется логаут");
        } catch (Exception e){
            System.out.println(e);
        }

    }
}
