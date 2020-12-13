package com.busyqa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtility {
	static DriverUtility driverUtility;
	WebDriver driver;

	private DriverUtility() { //Implementation of Singleton class 
		super();
	}

	public static DriverUtility getInstance() { // Will help to have only one object of this class
		if (driverUtility == null) {
			driverUtility = new DriverUtility();
		}
		return driverUtility;
	}

	/*Depending on the driverConfig Properties file the driver details will be returned*/
	
	public WebDriver getDriver() {
		
		String driverInConfigFile = "";
		Properties config = new Properties();
		try {
			config.load(new FileInputStream(System.getProperty("user.dir")+"\\resources\\properties\\driverConfig.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String key : config.stringPropertyNames()) {
			if (key.equals("browser")) {
				driverInConfigFile = config.getProperty("browser");
			}
		}
		switch (driverInConfigFile) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\resources\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\resources\\executables\\edgedriver.exe");
			driver = new EdgeDriver();
			break;
		}		
		return driver;
	}

}
