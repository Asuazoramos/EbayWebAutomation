package homepage;

import base.CommonAPI;

import database.ConnectToExcelFiles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;


public class EbayRunner extends CommonAPI {
    @FindBy(id = "gh-ac") public static WebElement searchProducts;
    @FindBy(id = "gh-ac") public static WebElement searchBar;
    @FindBy(id = "gh-cat") public static WebElement allCategoriesDropDown;
    @FindBy(id = "gh-shop-a") public static WebElement shopCategory;
    @FindBy(id = "gh-ug") public static WebElement sighLine;
    @FindBy(xpath = "//h1") public static WebElement searchResultsHeader;
    @FindBy(id = "gh-ac") public static WebElement searchTools;
    @FindBy(id = "gh-ac") public static WebElement searchNext;
    @FindBy(id = "gh-ac") public static WebElement searchCharger;
    @FindBy(xpath = "//*[@id=\"gh-ac\"]") public static WebElement searchLine;
    @FindBy(xpath = "//*[@id=\"gh-ac\"]") public static WebElement searchOpinion;
    @FindBy(xpath = "//*[@id=\"gh-ac\"]") public static WebElement searchGet;
    @FindBy(xpath = "//*[@id=\"gh-ac\"]") public static WebElement searchBranch;
    @FindBy(xpath = "//*[@id=\"gh-ac\"]") public static WebElement searchImport;
    @FindBy(id = "gh-shop-a" ) public static WebElement category;
    @FindBy(id = "gh-ug") public static WebElement lines;
    @FindBy(id = "gh-eb-My") public static WebElement myEbay;
    @FindBy(id = "gh-p-2") public static WebElement count;

    public void Counting()
    {
        count.click();
    }

    public void ebay() {
        myEbay.click();
    }

    public void signIn(){
        lines.click();
    }

    public void shop(){
        category.click();

    }

    public void connect(String Connect){
        searchImport.click();
        typeOnElement(searchImport,Connect);
        ExplicitlyWait(10).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(Connect));
    }
    public void set(String Select){
        searchBranch.click();
        typeOnElement(searchBranch,Select);
        ExplicitlyWait(10).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(Select));
    }

    public void Gets(String get){
        searchGet.click();
        typeOnElement(searchGet,get);
        ExplicitlyWait(10).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(get));
    }

    public void Options(String option){
        searchOpinion.click();
        typeOnElement(searchOpinion,option);
        ExplicitlyWait(10).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(option));
    }

    public void line(String Lines){
        searchLine.click();
        typeOnElement(searchLine,Lines);
        ExplicitlyWait(15).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(Lines));
    }

    public void technology(String Tech){
        searchCharger.click();
        typeOnElement(searchCharger,Tech);
        ExplicitlyWait(15).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(Tech));

    }

    public void features(String products){
        searchProducts.click();
        typeOnElement(searchProducts,products);
        ExplicitlyWait(15).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(products));

    }
    public void search(String typedValueInSearch){
        searchBar.click();
        typeOnElement(searchBar,typedValueInSearch);
        ExplicitlyWait(15).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(typedValueInSearch));
    }
    public void searchConteners(String ValueInSearch){
        searchTools.click();
        typeOnElement(searchTools,ValueInSearch);
        ExplicitlyWait(15).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(ValueInSearch));
    }

    public void getSignLine(){
        sighLine.click();
    }

    public void searchControl(String toolValue){
        searchNext.click();
        typeOnElement(searchNext,toolValue);
        ExplicitlyWait(15).until(ExpectedConditions.visibilityOf(searchResultsHeader));
        Assert.assertTrue(searchResultsHeader.getText().contains(toolValue));
    }

    public List<String> getAllDropDownItems(){
        List<WebElement> webElements = SelectList(allCategoriesDropDown);
        getListOfStringText(webElements, "all categories drop down");
        return null;
    }

    public void getShopCategory(){
        shopCategory.click();
    }
    public void getNumberOfLinks(){
        numberOfLinks();
    }
    public String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    public void searchItemsFromExcelFiles() throws IOException {
        String[] items =  ConnectToExcelFiles.getCellValuesFromSheet("/Users/siembrahielotrucho/Documents/EbayTestAutomation/Ebay/data/Ebay-items-for-search.xlsx");
        for (String str : items){
            System.out.println("searchForItems" + str + "fromTheExcelFiles");
            searchBar.click();
            searchBar.sendKeys(str);
            ExplicitlyWait(15).until(ExpectedConditions.visibilityOf(searchResultsHeader));
            searchBar.clear();
        }
    }


}
