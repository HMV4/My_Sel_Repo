package Keywords;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.reports.*;


public class GenericKeywords extends extentReport{

	public WebDriver driver = null;
	public Properties prop;
	public Properties envProp;
	public ExtentTest extentTest;
	SoftAssert SoftAssertion;
	
	
	// WebDriverWait wait =new WebDriverWait(driver,30);

	public void OpenBrowser(String browserName) {
		System.out.println("Opening Browser.....");
		log(browserName);
		
		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
			// System.setProperty("ywebdriver.gecko.driver","F:\\geckodriver.exe");
			ChromeOptions crop = new ChromeOptions();
			// crop.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			// crop.addArguments("--disable-notifications");
			crop.addArguments("--start-maximized");
			driver = new ChromeDriver(crop);

		} else if (browserName.equals("fireFox")) {
			System.setProperty("webdriver.gecko.driver", "F:\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		}

		// Implicit wait to stabilize page load after opening browser
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		
	}

	// URL entering and opening function
	public void navigate(String urlKey) {

		System.out.println("Navigating to URL....");
		log("Navigating to URL"+urlKey);
		driver.get(prop.getProperty(urlKey));
		Takescreenshot();

	}

	// Click function
	public void click(String locatorKey) throws InterruptedException {
		System.out.println("Clicking on Locator....");
		log("Clicking on Locator...."+locatorKey);
		Thread.sleep(2000);;
		// calls to get locators xpath ,id ,css,etc
		getElement(locatorKey).click();
		Takescreenshot();
		// Thread.sleep(2000);

	}

	// Send Keys functions

	public void type(String locatorKey, String data) {
		System.out.println("Entering... " + locatorKey + "...Data" + data);
		log("Sending or typing" + data);
		getElement(locatorKey).sendKeys(prop.getProperty(data));
		Takescreenshot();

	}

	public void select(String locatorname, String data) {

	}

	public void get(String locatorname) {

	}

	// Locator identifier functions
	public WebElement getElement(String locatorKey) {

		if (!isElementpresent(locatorKey)) {
			// Report Failure;
			System.out.println("Element not present with locator" + locatorKey);
			log("Element not present with locator" + locatorKey);
		}
		if (!isElementVisible(locatorKey)) {
			// Report Failure;
			System.out.println("Element not visible with locator" + locatorKey);
			log("Element not visible with locator" + locatorKey);
		}

		// Here making locator generic to fine element base on any locator
		WebElement we = null;
		try {
			we = driver.findElement(objectlocator(locatorKey));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		return we;

	}

	// Element visibility check function
	public boolean isElementVisible(String locatorKey) {
		System.out.println("Checking Visibilility of element");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(objectlocator(locatorKey)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}

		return true;

	}

	// Element presence check function
	public boolean isElementpresent(String locatorkey) {
		System.out.println("Checking presence of element");
		WebDriverWait wait = new WebDriverWait(driver, 3);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(objectlocator(locatorkey)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;

	}

	// Locator identifier functions
	public By objectlocator(String locaterKey) throws IOException {
		if (locaterKey.endsWith("_id")) {
			return By.id(prop.getProperty(locaterKey));
		} else if (locaterKey.endsWith("_xpath")) {
			return By.xpath(prop.getProperty(locaterKey));
		} else if (locaterKey.endsWith("_Css")) {
			return By.cssSelector(prop.getProperty(locaterKey));
		} else if (locaterKey.endsWith("_tag")) {
			return By.tagName(prop.getProperty(locaterKey));
		} else
			return By.name(prop.getProperty(locaterKey));

	}
	
	// Closing browser
	public void closeBrowser()
	{

		driver.close();
		System.out.println(" Browser Closed");
		
		
	}
	
	// Generic logging function
	public void log(String msg)
	{
	    System.out.println(msg);	
	    extentTest.log(Status.INFO, msg);
	    
		
		
		
	}

	// report failure function
	
	public void reportFailure(String failmsg ,boolean stopOnFailure)
	{
		
		  //System.out.println(failmsg);
		  
		  //Prints failure in Extent report 
		extentTest.log(Status.FAIL, failmsg);
		Takescreenshot();
		
		  //Prints failure in Testng report SoftAssertion.fail(failmsg);
		 if(stopOnFailure) { assertAll(); }
		 
		
	}
	
	// Assert All function mandatory to print failure in report
	public void assertAll()
	{
		
		  //This is TestNG builtIn Reporter class
		Reporter.getCurrentTestResult().getTestContext().
		  setAttribute("criticalFailure","Y"); 
		  SoftAssertion.assertAll();
	}
	
	//Snapshot function to keep results
	public  void Takescreenshot() 
	{
		Date date = new Date();
		
		//Stores actual image
		String actualSnapImage = date.toString().replace(":", "_").replace(",", "_")+".png";
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			//Helps to create Dynamic folder using new file() 
			FileUtils.copyFile(srcfile, new File(extentReport.snapshotFolder+"//"+actualSnapImage));
			
			// Puts snap in folder using addScreenCaptureFromPath()
			extentTest.log(Status.INFO, "snapshot>>"+extentTest.addScreenCaptureFromPath(extentReport.snapshotFolder+"//"+actualSnapImage));
		} catch (IOException e) {			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
}
