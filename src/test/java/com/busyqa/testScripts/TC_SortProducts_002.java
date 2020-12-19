package com.busyqa.testScripts;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import com.busyqa.core.BaseClass;
import com.busyqa.pageFactory.MyntraHomeFactory;
import com.busyqa.pageObjects.MyntraProductPage;
import com.busyqa.utils.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class TC_SortProducts_002 extends BaseClass{

	MyntraHomeFactory mhomeObj;
	@Test
	public void highToLow() throws InterruptedException
	{
		mhomeObj=new MyntraHomeFactory(driver);

		
		action.moveToElement(mhomeObj.getMenLink()).perform();
			logObject.info("Hovering Mouse to Men link in the menu");
			extentObject.log(LogStatus.INFO, "Moving Mouse to the Men link in the menu ");
		
		mhomeObj.getCasualShirtLink().click();
			logObject.info("Selecting Casual Shirts from dropdown");
			extentObject.log(LogStatus.INFO, "Selecting Casual Shirts from dropdown");
		
		action.moveToElement(MyntraProductPage.getSortDropdown(driver)).click().build().perform();
			logObject.info("Clicking over the sort filter dropdown");
			extentObject.log(LogStatus.INFO, "Clicking over the sort filter dropdown");
			
		MyntraProductPage.getHighToLowDropdown(driver).click();
			logObject.info("Clicking on High to Low sort filter from dropdown");
			extentObject.log(LogStatus.INFO, "Clicking on High to Low sort filter from dropdown");
		
		driver.findElement(By.xpath("//span[@class='header-title']")).click();
		Thread.sleep(5000);
		
			logObject.info("Getting all the prices on the page and ensuring if its in desc order");
			extentObject.log(LogStatus.INFO, "Getting all the prices on the page and ensuring if its in desc order");
		Assert.assertTrue(Utilities.verifyOrder(MyntraProductPage.getPricesOfProductsDisplayed(driver),"desc"));
			
	}
	
	@Test (dependsOnMethods = {"highToLow"})
	public void lowtoHigh() throws InterruptedException {
		
		action.moveToElement(MyntraProductPage.getSortDropdown(driver)).click().build().perform();
			logObject.info("Clicking over the sort filter dropdown");
			extentObject.log(LogStatus.INFO, "Clicking over the sort filter dropdown");
		
		MyntraProductPage.getLowToHighDropdown(driver).click();
			logObject.info("Clicking on Low to High sort filter from dropdown");
			extentObject.log(LogStatus.INFO, "Clicking on Low to High sort filter from dropdown");
		
		driver.findElement(By.xpath("//span[@class='header-title']")).click();
		Thread.sleep(4000);
		
			logObject.info("Getting all the prices on the page and ensuring if its in asc order");
			extentObject.log(LogStatus.INFO, "Getting all the prices on the page and ensuring if its in asc order");
		Assert.assertTrue(Utilities.verifyOrder(MyntraProductPage.getPricesOfProductsDisplayed(driver),"asc"));
			
	  }

}
