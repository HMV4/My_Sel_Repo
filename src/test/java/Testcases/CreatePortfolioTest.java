package Testcases;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import com.TestBase.Testbase;

import Keywords.ApplicationKeywords;

public class CreatePortfolioTest extends Testbase {
	//ApplicationKeywords app;
	
	@Test
	public void createPortfolioTest() throws InterruptedException, FileNotFoundException
	{
		//app= new ApplicationKeywords();
		app.OpenBrowser("Chrome");
		app.navigate("url");
		app.validateTitle("");
		//app.validateElementPresent("usernameId_xpath");
		app.click("signInLink_xpath");
		app.type("usernameId_xpath","usernameValue");
		app.type("passwordLocator_xpath","passwordValue");
		app.selectDateFromCalender();
		app.reportFailure("invalid name",true);
		app.assertAll();
		app.closeBrowser();
		
		
	}

}
