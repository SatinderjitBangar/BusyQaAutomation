package com.busyqa.testScripts;

import org.testng.Assert;
import org.testng.annotations.*;
import com.busyqa.core.BaseClass;
import com.busyqa.pageFactory.LoginPageFactory;
import com.busyqa.pageObjects.HomePageObject;
import com.busyqa.utils.Utilities;
import com.relevantcodes.extentreports.LogStatus;;

/*
 * Test verify all the valid login creds are working on the sign in page
 */
public class TS_LoginPage_001 extends BaseClass{
	
	LoginPageFactory loginObject;
	HomePageObject homeObject;
	@BeforeClass
	public void loginInit()
	{
		driver.get("http://automationpractice.com/index.php");
		homeObject=new HomePageObject(driver);
		homeObject.getSignInLink().click();
			logObject.debug("Home page Webelements intantiated");
		loginObject= new LoginPageFactory(driver);
			logObject.debug("login Page Webelements instantiated");
	}
	
	//Negative Test Case
	@Test(dataProvider = "invalidUserPass",dataProviderClass = Utilities.class)
	public void test_login_1(String username, String password) {
		extentObject.log(LogStatus.INFO, "Login Test started with username "+username+" and password "+password);
		doLogin(username,password);
		String actualTitle=driver.getTitle();
		Assert.assertNotEquals(actualTitle,"My account - My Store");
	}
	
	@AfterClass
	public void loginTeardown()
	{
		driver.get("https://www.myntra.com/men-casual-shirts?plaEnabled=false");
	}
	
	@Test(dataProvider = "validUserPass",dataProviderClass = Utilities.class)
	public void test_login_2(String username, String password) {
		extentObject.log(LogStatus.INFO, "Login Test started with username "+username+" and password "+password);
		doLogin(username,password);
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle,"My account - My Store");
	}
	
	public void doLogin(String username, String password)
	{
		
		loginObject.getUsernameTextbox().clear();
		loginObject.getUsernameTextbox().sendKeys(username);
		extentObject.log(LogStatus.INFO, "Entered Username");
			logObject.info("Entering Username " + username);
		loginObject.getPasswordTextbox().clear();
		loginObject.getPasswordTextbox().sendKeys(password);
		extentObject.log(LogStatus.INFO, "Entered Password");
			logObject.info("Entering Password " + password);
		loginObject.getSignInButton().click();
			logObject.debug("Sign in button clicked");
			extentObject.log(LogStatus.INFO, "Submit Button Clicked");
			
	}
	
}
