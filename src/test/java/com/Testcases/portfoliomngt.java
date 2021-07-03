package com.Testcases;

import org.testng.annotations.Test;

import com.TestBase.Testbase;

public class portfoliomngt extends Testbase {

	@Test
	public void createPortfolio() {

		System.out.println("====Calling createPortfolio() Test ======");
        app.OpenBrowser("Chrome");
        app.closeBrowser();
	}

	@Test
	public void deletePortfolio() {
		System.out.println("====Calling deletePortfolio() Test =======");
		app.OpenBrowser("Chrome");
		app.closeBrowser();
	}

}
