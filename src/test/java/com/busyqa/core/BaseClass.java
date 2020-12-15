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
	static String log4jConfPath = System.getProperty("user.dir")+"\\resources\\properties\\log4j.properties";
	public static Logger logObject = Logger.getLogger("qaLogger");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest extentObject;
	public static Actions action;

	@BeforeSuite
	public static void init()
	{
		PropertyConfigurator.configure(log4jConfPath);
		driver=DriverUtility.getInstance().getDriver();
		excelUtility=new ExcelUtility(System.getProperty("user.dir")+"\\resources\\excel\\TestData.xlsx");
		action = new Actions(driver);
		driver.get("https://www.myntra.com/");
		// Global implicit Wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}
	
	@AfterSuite
	public static void teardown()
	{
		if(driver!=null)
			driver.quit();
	}
	
	
}
