package com.busyqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObject {
	
	static WebDriver driver;
	public LoginPageObject(WebDriver driver)
	{
		
		LoginPageObject.driver=driver;
	}

	
	public WebElement getUsernameTextbox() {
		return driver.findElement(By.id("email"));
	}
	public WebElement getPasswordTextbox() {
		return driver.findElement(By.xpath("//input[@id='passwd']"));
	}
	public WebElement getForgotPasswordHyperlink() {
		return driver.findElement(By.linkText("Forgot your password?"));
	}
	public WebElement getSignInButton() {
		return driver.findElement(By.cssSelector("button#SubmitLogin"));
	}
	
}
