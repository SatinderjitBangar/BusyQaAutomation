package com.busyqa.testScripts;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import com.busyqa.core.BaseClass;
import com.busyqa.pageObjects.MyntraProductPage;
import com.busyqa.utils.Utilities;

public class TC_SortProducts_002 extends BaseClass{
	WebDriver driver = null;
	MyntraProductPage mpObj;
	
	@BeforeTest
	public void init() {
		driver.get("https://www.myntra.com/men-casual-shirts?plaEnabled=false");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		mpObj = new MyntraProductPage(driver);
		action = new Actions(driver);
	}

	@Test
	public void highToLow() throws InterruptedException
	{
		action.moveToElement(driver.findElement(By.xpath("//div[@class='sort-sortBy']"))).click().build().perform();
		driver.findElement(By.xpath("//ul[@class='sort-list']//li[4]//label[1]")).click();
		driver.findElement(By.xpath("//span[@class='header-title']")).click();
		Thread.sleep(5000);
		Assert.assertTrue(Utilities.verifyOrder(mpObj.getPricesOfProductsDisplayed(),"desc"));
	}
	
	@Test public void lowtoHigh() throws InterruptedException {
		action.moveToElement(driver.findElement(By.xpath("//div[@class='sort-sortBy']"))).click().build().perform();
		driver.findElement(By.xpath("//ul[@class='sort-list']//li[5]//label[1]")).click();
		driver.findElement(By.xpath("//span[@class='header-title']")).click();
		Thread.sleep(5000);
		Assert.assertTrue(Utilities.verifyOrder(mpObj.getPricesOfProductsDisplayed(),"asc"));
	  }

}
