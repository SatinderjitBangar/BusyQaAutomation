package com.busyqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObject {
	
	static WebDriver driver;
	public HomePageObject(WebDriver driver)
	{
		
		HomePageObject.driver=driver;
	}

	
	public WebElement getSignInLink() {
		return driver.findElement(By.partialLinkText("Sign in"));
	}
	public WebElement getContactUsLink() {
		return driver.findElement(By.linkText("Contact us"));
	}
	
	
}
