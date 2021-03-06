package com.busyqa.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import com.busyqa.core.BaseClass;
import com.busyqa.pageObjects.MyntraProductPage;

public class Utilities extends BaseClass{
	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));

	}
	
	/******************************************//******************************************/
	/******************************************//******************************************/
	/******************************************//******************************************/
									/* SORT PRODUCT UTILITIES*/
	/******************************************//******************************************/
	/******************************************//******************************************/
	/******************************************//******************************************/
	
	public static int convertPriceToInt(String priceDivValue)
	{
		return Integer.parseInt((priceDivValue.replaceFirst("Rs. ","")).replaceFirst("Rs..*", ""));
	}
	
	
	public static boolean verifyOrder(List<WebElement> productprices, String order) throws InterruptedException
	{
		int temp2,temp;
		if(order=="desc")
		{
			temp2=-1;
			for (WebElement e: productprices)
			{
				temp=Utilities.convertPriceToInt(e.getText());
				if(temp2<0)
				{
					temp2=temp;
					continue;
				}else
				{
					if(!(temp2>=temp))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);window.scrollBy(0,-300);", e);
						return false;
					}
					temp2=temp;
				}
			}
		}
		else if(order=="asc")
		{ 
			temp2=-1;
			for (WebElement e: productprices)
			{
				temp=Utilities.convertPriceToInt(e.getText());
				if(temp2<0)
				{
					temp2=temp;
					continue;
				}else
				{
					if(!(temp2<=temp))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);window.scrollBy(0,-300);", e);
						return false;
					}
					temp2=temp;
				}
			}
		}

		return true;
	}
	/******************************************//******************************************/
	/******************************************//******************************************/
	/******************************************//******************************************/
									/* FILTER PRODUCT UTILITIES*/
	/******************************************//******************************************/
	/******************************************//******************************************/
	/******************************************//******************************************/
	
	public static int lowerSelectedVal(String selectedFilter)
	{
		
		return Integer.parseInt((selectedFilter.replaceFirst("RS. ","")).replaceFirst(" TO.*", ""));
	}
	
	public static int higherSelectedVal(String selectedFilter)
	{
		
		return Integer.parseInt(((selectedFilter.substring(selectedFilter.lastIndexOf("RS."),selectedFilter.length())).replaceFirst("RS. ","")).replaceAll("\\(.*", ""));
	}
	
	public static boolean verifyPriceFilter(List<WebElement> filter,int lowerVal,int higherVal)
	{
		for(WebElement e:filter)
		{
			int temp=Integer.parseInt(((e.getText()).replaceFirst("Rs. ","")).replaceFirst("Rs..*", ""));
			
			if(temp<=higherVal && temp>=lowerVal )
			{
				
			}
			else
			{
				return false;
			}
		}	
		return true;
	}
	
	/******************************************//******************************************/
	/******************************************//******************************************/
	/******************************************//******************************************/
									/* DATA PROVIDERS*/
	/******************************************//******************************************/
	/******************************************//******************************************/
	/******************************************//******************************************/
	@DataProvider (name="validUserPass")
	public Object[][] credentialsProvider() throws IOException
	{
		Object credentials[][]=excelUtility.getValidData();
		return credentials;
	}
	@DataProvider (name="invalidUserPass")
	public Object[][] credentialsProvider2() throws IOException
	{
		Object credentials[][]=excelUtility.getInvalidData();
		return credentials;
	}
	
	
	
}
