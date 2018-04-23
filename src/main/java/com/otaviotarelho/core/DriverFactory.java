package com.otaviotarelho.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	
	
	private static WebDriver driver;
	
	private DriverFactory() {
		
	}
	
	public static WebDriver getDriver() {
		if(driver == null) {
			
			switch(Properties.browser) {
				case FIREFOX:
					break;
				case CHROME:
					System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
					driver = new ChromeDriver();
					break;
			}
			
			driver.manage().window().maximize();			
		}
		
		return driver;
	}
	
	public static void killDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;			
		}
	}
}
