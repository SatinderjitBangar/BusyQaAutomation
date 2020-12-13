package com.busyqa.pageFactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
	
	WebDriver driver;
	public LoginPageFactory(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")
	static WebElement usernameTextbox;
	
	@FindBy(id="passwd")
	static WebElement passwordTextbox;
	
	@FindBy(linkText="Forgot your password?")
	static WebElement forgotPasswordHyperlink;
	
	@FindBy(css="button#SubmitLogin")
	static WebElement signInButton;
	
	public  WebElement getUsernameTextbox() {
		return usernameTextbox;
	}
	public  WebElement getPasswordTextbox() {
		return passwordTextbox;
	}
	public  WebElement getForgotPasswordHyperlink() {
		return forgotPasswordHyperlink;
	}
	public  WebElement getSignInButton() {
		return signInButton;
	}
	
}
