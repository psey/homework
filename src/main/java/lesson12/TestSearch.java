package lesson12;

import core.BrowserFactory;
import org.testng.annotations.Test;

import java.util.List;

public class TestSearch extends BrowserFactory {

    @Test
    public void search(){
        SearchForm searchForm = new GoogleSearch();
        List<String> result = searchForm.search("Jack Sparrow");
        System.out.println(result);

        searchForm = new BingSearch();
        result = searchForm.search("Jack Sparrow");
        System.out.println(result);
    }
}
