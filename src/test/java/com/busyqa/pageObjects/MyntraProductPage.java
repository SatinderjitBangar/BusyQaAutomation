package com.busyqa.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyntraProductPage {
	
	/*Number of price filters displayed on the page*/
	public static int getNumberOfPriceListFilters(WebDriver driver)
	{
		return (driver.findElements(By.xpath("//ul[@class='price-list']/li"))).size();
	}
	
	/*Return the clickable checkbox webelement under the price filter*/
	public static List<WebElement> getListOfPriceFilterCheckbox(WebDriver driver)
	{
		return (driver.findElements(By.xpath("//ul[@class='price-list']/li/label[1]/div[1]")));
	}
	
	/*Return the labels containing value corresponding to the price filters displayed on the page*/
	
	public static List<WebElement> getDisplayedTextOnPriceFilters(WebDriver driver)
	{
		return (driver.findElements(By.xpath("//ul[@class='price-list']/li/label[1]")));
	}
	
	/*Returns the div containing price of the products displayed on the page*/
	public static List<WebElement> getPricesOfProductsDisplayed(WebDriver driver)
	{
		return (driver.findElements(By.xpath("//div[@class='product-price']")));
	}
	
	/*Returns the elements of the div that shows selected filters*/
	public static WebElement getDivShowingSelectedFilters(WebDriver driver)
	{
		return (driver.findElement(By.xpath("//div[@class='filter-summary-filter']")));
	}

	/*Return the drop down to manage the sort criteria*/
	public static WebElement getSortDropdown(WebDriver driver)
	{
		return (driver.findElement(By.xpath("//div[@class='sort-sortBy']")));
	}
	
	/*Return the high to low element from sort dropdown*/
	public static WebElement getHighToLowDropdown(WebDriver driver)
	{
		return (driver.findElement(By.xpath("//ul[@class='sort-list']//li[4]//label[1]")));
	}
	/*Return the low to high element from sort dropdown*/
	public static WebElement getLowToHighDropdown(WebDriver driver)
	{
		return (driver.findElement(By.xpath("//ul[@class='sort-list']//li[5]//label[1]")));
	}
	
}
