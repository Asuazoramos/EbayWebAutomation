package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class CommonAPI {
    public static ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutpuDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(driver, result.getName());
        }
        driver.quit();
    }

    public void captureScreenshot(WebDriver driver, String name) {
    }

    @AfterSuite
    public void generateReport() {
        driver.quit();
    }
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();

    }


    public String browserStackUserName = "angelsuazo1";
    public String browserStackAccessKey = "zqkAy9JDsv7zHw9Fszn7";
    public String saucceLabsUserName = "";
    public String sauceLabsAccessKey = "";

    public static WebDriver driver = null;

    public CommonAPI(WebDriver driver) {

    }
    public static WebDriver driver1 = null;

    public CommonAPI() {

    }


    @Parameters({"useCloudEnv","cloudEnvName","url","os","os_version","browserName","browserVersion"})
    @BeforeMethod
    public void setUp(@Optional("false") String useCloudEnv, @Optional("browserstack") String cloudEnvName, @Optional("http://Ebay.com") String url, @Optional("Windows") String os, @Optional("10") String os_version, @Optional("chrome") String browserName,
                      @Optional String browserVersion) throws IOException {

        if (useCloudEnv.equalsIgnoreCase("true")) {
            getCloudDriver(cloudEnvName,browserStackUserName,browserStackAccessKey,os,os_version,browserName,browserVersion);
        } else if (useCloudEnv.equalsIgnoreCase("false")){
            getLocalDriver(os,browserName);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }

    public WebDriver getLocalDriver(String os, String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            if(os.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.chrome.driver", "/Users/siembrahielotrucho/WebAutomationJune2020Group2/driver/chromedriver 9");
                driver = new ChromeDriver();
            }else if(os.equalsIgnoreCase("Windows")){
                System.setProperty("webdriver.chrome.driver", "/Users/siembrahielotrucho/WebAutomationJune2020Group2/driver/chromedriver 9");
                driver = new ChromeDriver();
            }
        }

        else {
            System.out.println("Sorry we don't support that browser.");
        }
        return driver;
    }

    public WebDriver getCloudDriver(String envName, String envUserName, String envAccessKey, String os,
                                    String os_version, String browserName, String browserVersion)throws IOException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browserVersion", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if(envName.equalsIgnoreCase("saucelabs")){
            driver = new RemoteWebDriver(new URL("http://"+envUserName+":"+envAccessKey+
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
        }else if(envName.equalsIgnoreCase("browserstack")) {
            driver = new RemoteWebDriver(new URL("http://" + envUserName + ":" + envAccessKey +
                    "@hub-cloud.browserstack.com/wd/hub"), cap);
        }

        return driver;
    }


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public Wait<WebDriver> ExplicitlyWait(long timeout){
        return new WebDriverWait(driver, timeout);

    }

    public void clickOnElement(WebElement element) {
        element.click();
    }


    public void typeOnElement(WebElement element,String valueToType) {
        element.sendKeys(valueToType, Keys.ENTER);
    }

    public void clickByAttributeValue(List<WebElement> elementList, String attribute, String myAttributeValue) {

        for (WebElement element : elementList) {
            String value = element.getAttribute(attribute);
            if (value.equalsIgnoreCase(myAttributeValue)) {
                element.click();
                break;
            }
        }

    }

    public List<WebElement> SelectList(WebElement element) {

        Select select = new Select(element);
        List<WebElement> selectList = select.getOptions();
        return selectList;
    }

    public List<String> getListOfStringText(List<WebElement> elementList, String nameOfList) {
        List<String> elementTextList = new ArrayList<String>();
        int listSize = elementList.size();
        System.out.println("Total " + nameOfList + " count is " + listSize);
        for (WebElement element : elementList) {
            String elementText = element.getText();
            System.out.println(elementText);
            elementTextList.add(elementText);
        }
        return elementTextList;
    }

    public void selectAValue(List<WebElement> elementList, String mySelectionValue) {
        for (WebElement element : elementList) {
            String selectValue = element.getText();
            if (selectValue.equalsIgnoreCase(mySelectionValue)) {
                element.click();
                break;
            }
        }
    }

    public List ascendingListOfText(List<WebElement> elementList) {
        List actualList = new ArrayList();
        for (WebElement element : elementList) {
            String text = element.getText();
            actualList.add(text);
        }
        List temp = new ArrayList();
        temp.addAll(actualList);
        Collections.sort(temp);
        return temp;
    }

    public List descendingListOfText(List<WebElement> elementList) {
        List actualList = new ArrayList();
        for (WebElement element : elementList) {
            String text = element.getText();
            actualList.add(text);
        }
        List temp = new ArrayList();
        temp.addAll(actualList);
        Collections.sort(temp, Collections.reverseOrder());
        return temp;
    }

    public String acceptAlert() {
        Alert alert = driver.switchTo().alert();
        String actualAlertText = alert.getText();
        alert.accept();
        return actualAlertText;
    }

    public void frameSwitch(WebElement element) {
        driver.switchTo().frame(element);

    }

    public void numberOfLinks() {
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        int linkCount = allLinks.size();
        System.out.println("Total amount of links are " + linkCount);
    }

    public void closePopupWindows() {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        int windowsCount = allWindows.size();
        System.out.println("Total amount of windows are " + windowsCount + ".");
        for (String childWindow : allWindows) {
            if (!parentWindow.equalsIgnoreCase(childWindow)) {
                driver.switchTo().window(childWindow);
                driver.getTitle();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }
    public void clearInputField(WebElement element){
        element.clear();
    }

    public static void navigateBack(){
        driver.navigate().back();
    }

    //Synchronization
//    public static void waitUntilClickAble(By locator) {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//    }
//
//    public static void waitUntilVisible(By locator) {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
//
//    public static void waitUntilSelectable(By locator) {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
//    }

    public static void waitUntilClickAble(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitUntilVisible(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public static void waitUntilSelectable(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

}
