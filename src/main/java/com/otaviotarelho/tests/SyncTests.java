package com.otaviotarelho.tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.otaviotarelho.helpers.DSL;

public class SyncTests {
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
	public void shouldIntectWithLazyLoadingElement_Fixed() throws InterruptedException {
		dsl.click("buttonDelay");
		Thread.sleep(5000);
		dsl.write("novoCampo", "Teste");
	}
	
	@Test
	public void shouldIntectWithLazyLoadingElement_Implicit(){
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.click("buttonDelay");
		dsl.write("novoCampo", "Teste");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void shouldIntectWithLazyLoadingElement_Explict(){
		dsl.click("buttonDelay");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.write("novoCampo", "Teste");
	}
	
}
