package Keywords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeywords extends ValidationKeywords{
	
	
	public ApplicationKeywords() throws FileNotFoundException
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

	public void login()
	{
		
	}
	
	public void selectDateFromCalender()
	{
		
	}
	public void verifyStockAdded()
	{
		
	}
	
	//method to provide facility to use logging in every available function of application level keyword
	
	public void setReport(ExtentTest extentTest)
	{
		
		//initialises test reference of ExtentTest class from Generic keyword which help to initialize report in Applicationkeyword class
		this.extentTest = extentTest;
		
		
	}
	
	
	
	
	
	

}
