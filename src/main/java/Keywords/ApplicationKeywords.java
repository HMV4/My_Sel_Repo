package Keywords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeywords extends ValidationKeywords{
	
	
	public ApplicationKeywords(ExtentTest extentTest) throws FileNotFoundException
	{
		
		prop = new Properties();
		envProp = new Properties();
		String path ="F:\\TestNgDemo\\Framework\\src\\test\\resources\\uat.properties";
		FileInputStream filereads = new FileInputStream(path);
		try {
			prop.load(filereads);
			
			
			  //Setting dyanamic prop file as per env String
				/*
				 * String env=envProp.getProperty("env")+".properties";
				 * path=System.getProperty("user.dir")+"\\src\\test\\resources\\"+env;
				 * filereads=new FileInputStream(path); envProp.load(filereads);
				 */
			 
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Initialize soft assert in this constructor 
		SoftAssertion = new SoftAssert();
		
		
	}

	public void defaultLogin() throws InterruptedException
	{
		navigate("url");
		System.out.println("Default login......................");
		waitForPageToLoad();
		type("usernameId_xpath","usernameValue");
		type("passwordLocator_xpath","passwordValue");
		Thread.sleep(2000);
		click("signInButton_xpath");
		
	}
	
	public void selectDateFromCalender()
	{
		
	}
	public void verifyStockAdded()
	{
		
	}
	
	
	public void verifyPortfolioAdded(String locatorKey,String data)
	{
		//selectByvisibletext("portfolioDropdown_xpath","portfolioValue");
		List<WebElement> PortfolioNames= new Select(getElement(locatorKey)).getOptions();
		for(WebElement PortfolioOptions :PortfolioNames )
		{
			if(PortfolioOptions.getText().equals(prop.getProperty(data)))
			{
				
				System.out.println("Portfolio created successfully" +prop.getProperty(data));
			}
			
		}
		
		
	

	}
	
	//method to provide facility to use logging in every available function of application level keyword
	
	public void setReport(ExtentTest extentTest)
	{
		
		//initialises test reference of ExtentTest class from Generic keyword which help to initialize report in Applicationkeyword class
		this.extentTest = extentTest;
		
		
	}
	
	
	
	
	
	

}
