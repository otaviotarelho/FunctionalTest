package com.otaviotarelho.tests;

import static com.otaviotarelho.core.DriverFactory.getDriver;
import static com.otaviotarelho.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.otaviotarelho.core.DSL;

public class SyncTests {
	private DSL dsl;
	
	@Before
	public void init() {
		getDriver().get("file:///"+ System.getProperty("user.dir") +"/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void destroy() {
		killDriver();
	}
	
	@Test
	public void shouldIntectWithLazyLoadingElement_Fixed() throws InterruptedException {
		dsl.click("buttonDelay");
		Thread.sleep(5000);
		dsl.write("novoCampo", "Teste");
	}
	
	@Test
	public void shouldIntectWithLazyLoadingElement_Implicit(){
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.click("buttonDelay");
		dsl.write("novoCampo", "Teste");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void shouldIntectWithLazyLoadingElement_Explict(){
		dsl.click("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.write("novoCampo", "Teste");
	}
	
}
