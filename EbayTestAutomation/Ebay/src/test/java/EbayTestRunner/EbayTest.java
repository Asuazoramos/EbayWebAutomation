package EbayTestRunner;



import homepage.EbayRunner;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reporting.TestLogger;

import java.io.IOException;
import java.util.List;



public class EbayTest extends EbayRunner {
    EbayRunner ebayRunner;

    @BeforeMethod
    public void initializePageObjects(){

       ebayRunner = PageFactory.initElements(driver, EbayRunner.class);
    }
    @Test
    public void getAllDropDownItemsTest(){
        List<String> allDropDownItems = ebayRunner.getAllDropDownItems();
    }
    @Test
    public void searchTest(){
        features("samsung cables");
    }

    @Test
    public void searchItemsFromExcelTest() throws IOException {
        ebayRunner.searchItemsFromExcelFiles();
    }


    @Test
    public void getTitleTest() {
        String expectedTitle = "Electronics, Cars, Fashion, Collectibles & More | eBay";
        String actualTitle = getTitle(driver);
        System.out.println(actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);
    }
    @Test
    public void signLineTest(){
        getSignLine();
    }
    @Test
    public void shopCategoryTest()  {
        getShopCategory();
    }

    @Test
    public void typeSearchBox(){
        search("apple watch");
    }
    @Test
    public void searchBox(){
        search("computers");
    }
    @Test
    public void searchID(){
        searchConteners("comic book");
    }
    @Test
    public void searchVolume(){
        searchControl("black & decker");
    }
    @Test
    public void searchFeatures(){
        features("beauty face");
    }


//    @Test
//    public void searchItemsWithExcelDataTest(){
//        ebayRunner.searchItemsWithExcelData();
//    }
    @Test
    public void totalLinks(){
        getNumberOfLinks();
    }

}
