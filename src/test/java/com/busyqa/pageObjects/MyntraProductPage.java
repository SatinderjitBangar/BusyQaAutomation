package com.busyqa.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyntraProductPage {
	static WebDriver driver;
	public MyntraProductPage(WebDriver driver)
	{
		
		MyntraProductPage.driver=driver;
	}
	
	/*Number of price filters displayed on the page*/
	public int getNumberOfPriceListFilters()
	{
		return (driver.findElements(By.xpath("//ul[@class='price-list']/li"))).size();
	}
	
	/*Return the clickable checkbox webelement under the price filter*/
	public List<WebElement> getListOfPriceFilterCheckbox()
	{
		return (driver.findElements(By.xpath("//ul[@class='price-list']/li/label[1]/div[1]")));
	}
	
	/*Return the labels containing value corresponding to the price filters displayed on the page*/
	
	public List<WebElement> getDisplayedTextOnPriceFilters()
	{
		return (driver.findElements(By.xpath("//ul[@class='price-list']/li/label[1]")));
	}
	
	/*Returns the div containing price of the products displayed on the page*/
	public List<WebElement> getPricesOfProductsDisplayed()
	{
		return (driver.findElements(By.xpath("//div[@class='product-price']")));
	}
	
	/*Returns the elements of the div that shows selected filters*/
	public WebElement getDivShowingSelectedFilters()
	{
		return (driver.findElement(By.xpath("//div[@class='filter-summary-filter']")));
	}
	
}
