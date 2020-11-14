package EbayTestRunner;

import homepage.EbayRunner;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class EbaySearchBar  extends EbayRunner {
    EbayRunner ebayRunner;

    @BeforeMethod
    public void initializePageObjects(){

        ebayRunner = PageFactory.initElements(driver, EbayRunner.class);
    }

    @Test
    public void searchTime(){
        line("support book");
    }
    @Test
    public void searchChoose(){
        Options("history book");
    }
    @Test
    public void searchGlass(){
        Gets("books");
    }
    @Test
    public void searchBrowser(){
        set("ray-ban");
    }
    @Test
    public void searchCut(){
        connect("apple cables");
    }
    @Test
    public void cat(){
        shop();
    }
    @Test
    public void signing(){
        signIn();
    }
    @Test
    public void contain(){
        ebay();
    }
    @Test
    public void contact(){
        Counting();
    }



}
