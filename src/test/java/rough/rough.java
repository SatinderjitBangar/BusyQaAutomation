package rough;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.busyqa.pageFactory.MyntraHomeFactory;
import com.busyqa.pageObjects.MyntraProductPage;

public class rough {
WebDriver driver;
MyntraHomeFactory mp;
List<String> addedProductName=new ArrayList<String>();
List<String> addedProductDesc=new ArrayList<String>();
List<String> cartProductDesc=new ArrayList<String>();
	@Test
	public void click()
	{
		System.setProperty("webdriver.chrome.driver","D:\\EclipseJavaIDE\\BusyQA\\automation_project\\resources\\executables\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.myntra.com/men-casual-shirts");
		String defaultWindowHandle= driver.getWindowHandle();
		MyntraProductPage.getPricesOfProductsDisplayed(driver).get(3).click();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		driver.findElement(By.xpath("//div[contains(text(),'ADD TO BAG')]")).click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Please select a size')]")));

		Assert.assertTrue((driver.findElement(By.xpath("//span[contains(text(),'Please select a size')]")).getText().equalsIgnoreCase("Please select a size")));
		
		int size=driver.findElements(By.xpath("//button[@class='size-buttons-size-button size-buttons-size-button-default']//span[@class='size-buttons-size-strike-hide']//..")).size();
		driver.findElements(By.xpath("//button[@class='size-buttons-size-button size-buttons-size-button-default']//span[@class='size-buttons-size-strike-hide']//.."))
		.get((new Random()).nextInt(size)).click();
		
		addToCart();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("GO TO BAG")));
		driver.findElement(By.partialLinkText("GO TO BAG")).click();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.myntra.com/checkout/cart"));
		driver.findElement(By.linkText("Men Brown & Blue Regular Fit Checked Pure Cotton Casual Shirt")).getText();
		
		
	}
	
	public void addToCart()
	{
		addedProductName.add(driver.findElement(By.className("pdp-title")).getText());
		addedProductDesc.add(driver.findElement(By.className("pdp-name")).getText());
		driver.findElement(By.xpath("//div[contains(text(),'ADD TO BAG')]")).click();
	}
	
}
