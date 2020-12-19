package com.busyqa.testScripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.busyqa.core.BaseClass;
import com.busyqa.pageFactory.MyntraHomeFactory;
import com.busyqa.pageObjects.MyntraProductPage;
import com.relevantcodes.extentreports.LogStatus;

public class TC_AddToCart_003 extends BaseClass{
	MyntraHomeFactory mhomeObj;
	List<String> addedProductDesc=new ArrayList<String>();
	List<String> cartProductDesc=new ArrayList<String>();
	@Test
	public void addToCart()
	{
		mhomeObj=new MyntraHomeFactory(driver);
		action.moveToElement(mhomeObj.getMenLink()).perform();
		logObject.info("Hovering Mouse to Men link in the menu");
		extentObject.log(LogStatus.INFO, "Moving Mouse to the Men link in the menu ");
	
		mhomeObj.getCasualShirtLink().click();
		logObject.info("Selecting Casual Shirts from dropdown");
		extentObject.log(LogStatus.INFO, "Selecting Casual Shirts from dropdown");
		
	
		
		String defaultWindowHandle= driver.getWindowHandle();
		MyntraProductPage.getPricesOfProductsDisplayed(driver).get(3).click();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		logObject.info("Selecting Shirt number 3 from list and switching window");
		extentObject.log(LogStatus.INFO, "Selecting Shirt number 3 from list and switching window");
		

		
		driver.findElement(By.xpath("//*[contains(text(),'ADD TO BAG')]")).click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Please select a size')]")));
		logObject.info("Clicking add to cart without selecting size and waiting for error to show up");
		extentObject.log(LogStatus.INFO, "Clicking add to cart without selecting size and waiting for error to show up");
		
		Assert.assertTrue((driver.findElement(By.xpath("//span[contains(text(),'Please select a size')]")).getText().equalsIgnoreCase("Please select a size")));
		
		int availableNumberOfSizes=driver.findElements(By.xpath("//button[@class='size-buttons-size-button size-buttons-size-button-default']//span[@class='size-buttons-size-strike-hide']//..")).size();
		driver.findElements(By.xpath("//button[@class='size-buttons-size-button size-buttons-size-button-default']//span[@class='size-buttons-size-strike-hide']//.."))
		.get((new Random()).nextInt(availableNumberOfSizes)).click();
		logObject.info("Clicking on a Random available size");
		extentObject.log(LogStatus.INFO, "Clicking on a Random available size");
		
		
		addedProductDesc.add(driver.findElement(By.className("pdp-name")).getText()); 
		driver.findElement(By.xpath("//*[contains(text(),'ADD TO BAG')]")).click();
		logObject.info("Clicking on add to cart button and saving the product desc in a variable");
		extentObject.log(LogStatus.INFO, "Clicking on add to cart button and saving the product desc in a variable");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("GO TO BAG")));
		driver.findElement(By.partialLinkText("GO TO BAG")).click();
		logObject.info("Explicitly waiting for button test to change to GO TO BAG and then opening the bag");
		extentObject.log(LogStatus.INFO, "Explicitly waiting for button test to change to GO TO BAG and then opening the bag");
		
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.myntra.com/checkout/cart"));
		logObject.debug("Checking if control redirected to the cart page");
		extentObject.log(LogStatus.INFO, "Checking if control redirected to the cart page");
		
		for(String desc:addedProductDesc)
		Assert.assertTrue(driver.findElement(By.linkText(desc)).isDisplayed());
		
	}
}
