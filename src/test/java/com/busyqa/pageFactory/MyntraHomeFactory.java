package com.busyqa.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyntraHomeFactory {
	WebDriver driver;
	public MyntraHomeFactory(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//a[text()='Men'][@href='/shop/men']")
	static WebElement menLink;
	
	@FindBy(linkText="Casual Shirts")
	static WebElement casualShirtLink;
	
	
	public  WebElement getMenLink() {
		return menLink;
	}

	public  WebElement getCasualShirtLink() {
		return casualShirtLink;
	}
	
}
