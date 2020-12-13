package com.busyqa.core;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import com.busyqa.utils.DriverUtility;
import com.busyqa.utils.ExcelUtility;
import com.busyqa.utils.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import utilities.ExtentManager;
//import utilities.Xls_Reader;

public class BaseClass {
	public static Properties config = new Properties();
	public static WebDriver driver = null;
	public static ExcelUtility excelUtility=null;
	String log4jConfPath = System.getProperty("user.dir")+"\\resources\\properties\\log4j.properties";
	public static Logger logObject = Logger.getLogger("qaLogger");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest extentObject;
	public Actions action;
	
	@BeforeSuite
	public void init()
	{
		PropertyConfigurator.configure(log4jConfPath);
		driver=DriverUtility.getInstance().getDriver();
		//driver.get("http://automationpractice.com/index.php");
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		excelUtility=new ExcelUtility(System.getProperty("user.dir")+"\\resources\\excel\\TestData.xlsx");
		// Global implicit Wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void teardown()
	{
		if(driver!=null)
			driver.quit();
	}
	
	
}
