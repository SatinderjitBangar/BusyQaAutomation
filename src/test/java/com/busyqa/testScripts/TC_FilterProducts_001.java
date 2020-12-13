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

import com.busyqa.pageObjects.MyntraProductPage;

public class TC_FilterProducts_001 {
	WebDriver driver=null;
	Random rn;
	MyntraProductPage mpObj;
	List <WebElement> priceFilrtListCheckbox;
	List <WebElement> priceValues;
	List<WebElement> productprices; /*Holds the list of the product prices displayed on the page*/
	WebElement filterDisplayDiv; /*Holds the div that displays the selected filters*/
	
	@BeforeMethod
	public void init()
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\resources\\executables\\chrome\\chromedriver.exe");
		driver= new ChromeDriver ();
		driver.get("https://www.myntra.com/men-casual-shirts?plaEnabled=false");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		mpObj=new MyntraProductPage(driver);
	}
	
	@Test
	public void brandFilterTest() throws InterruptedException
	{		
		priceFilrtListCheckbox = mpObj.getListOfPriceFilterCheckbox();/*Holds the clickable element coressponding to price range filter*/
		priceValues = mpObj.getDisplayedTextOnPriceFilters();/*Holds the displayed corrsponding value to price range filter*/
		int random=(new Random()).nextInt(mpObj.getNumberOfPriceListFilters()-1); //Generate a random number from 0 to (number of pricefilters)-1		
		priceFilrtListCheckbox.get(random).click(); //Clicks on a random pricefilter 
		
		filterDisplayDiv=mpObj.getDivShowingSelectedFilters(); 
		
		String selectedFilter=(priceValues.get(random).getText().toUpperCase());
		String displayedFilter=filterDisplayDiv.getText().toUpperCase();		
		Assert.assertTrue(selectedFilter.contains(displayedFilter));		
		Thread.sleep(5000);
		productprices=mpObj.getPricesOfProductsDisplayed();
		
		int lowerVal=Integer.parseInt((selectedFilter.replaceFirst("RS. ","")).replaceFirst(" TO.*", ""));
		int higherVal= Integer.parseInt(((selectedFilter.substring(selectedFilter.lastIndexOf("RS."),selectedFilter.length())).replaceFirst("RS. ","")).replaceAll("\\(.*", ""));
		
		System.out.println("Lower = "+lowerVal);
		System.out.println("Higher ="+higherVal);
		System.out.println(productprices.size());
		
		for(WebElement e:productprices)
		{
			int temp=Integer.parseInt(((e.getText()).replaceFirst("Rs. ","")).replaceFirst("Rs..*", ""));
			
			if(temp<=higherVal && temp>=lowerVal )
			{
				System.out.println("testpass" + temp);
				
			}
			else
			{
				System.out.println("Testfail"+ temp);
			}
		}	
	}
	@AfterMethod
	public void teardown()
	{
	
	}
	
	
}
	

