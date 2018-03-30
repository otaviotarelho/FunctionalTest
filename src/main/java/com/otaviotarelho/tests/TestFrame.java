package com.otaviotarelho.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.otaviotarelho.helpers.DSL;



public class TestFrame {
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///"+ System.getProperty("user.dir") +"/src/main/resources/componentes.html");
		
		dsl = new DSL(driver);
	}
	
	@After
	public void destroy() {
		driver.quit();
	}
	
	@Test
	public void shouldInteractWithFrame() {
		
		dsl.switchToFrame("frame1");
		dsl.click("frameButton");
		
		Alert alert = dsl.switchToAlert();
		
		String text = alert.getText();
		Assert.assertEquals("Frame OK!", text);
		alert.accept();
		
		dsl.switchToDefaultContent();
		dsl.write("elementosForm:nome", text);
		
	}
}
