package com.busyqa.testScripts;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.busyqa.core.BaseClass;
import com.busyqa.pageObjects.MyntraProductPage;
import com.busyqa.utils.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class TC_FilterProducts_001 extends BaseClass{
	List <WebElement> selectedPriceValues; /*Holds the displayed corresponding value to price range filter*/
	List<WebElement> productprices; /*Holds the list of the product prices displayed on the page*/
	WebElement filterDisplayDiv; /*Holds the div that displays the selected filters*/
	int random;
	
	@Test
	public void priceFilterTest() throws InterruptedException
	{		
		driver.get("https://www.myntra.com/men-casual-shirts?plaEnabled=false");
		random=(new Random()).nextInt(MyntraProductPage.getNumberOfPriceListFilters(driver)-1); //Generate a random number from 0 to (number of pricefilters)-1		
		MyntraProductPage.getListOfPriceFilterCheckbox(driver).get(random).click(); 
		selectedPriceValues = MyntraProductPage.getDisplayedTextOnPriceFilters(driver);
		filterDisplayDiv=MyntraProductPage.getDivShowingSelectedFilters(driver); 
		String selectedFilter=(selectedPriceValues.get(random).getText().toUpperCase());
		String displayedFilter=filterDisplayDiv.getText().toUpperCase();		
		
			logObject.info("Selecting a random price range");
			extentObject.log(LogStatus.INFO, "Selected Random price range" +selectedFilter);
		
		Assert.assertTrue(selectedFilter.contains(displayedFilter)); 		
			
			logObject.info("Checking if the displayed filter is same as selected price filter");
			extentObject.log(LogStatus.INFO, "Checking if the displayed filter is same as selected price filter");
		
		Thread.sleep(5000);
		boolean asrt=Utilities.verifyPriceFilter(MyntraProductPage.getPricesOfProductsDisplayed(driver),Utilities.lowerSelectedVal(selectedFilter),Utilities.higherSelectedVal(selectedFilter));
			logObject.info("Checking if the displayed products are withing selected price range");
			extentObject.log(LogStatus.INFO, "Checking if the displayed products are withing selected price range");
		
		Assert.assertTrue(asrt);
	}
}
	

