package com.otaviotarelho.tests;

import static com.otaviotarelho.core.DriverFactory.getDriver;
import static com.otaviotarelho.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.otaviotarelho.core.DSL;

public class TestWindow {
	private WebDriver driver;
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
	public void shouldInteractWithWindow() {
		
		dsl.click("buttonPopUpEasy");
		dsl.switchToWindows("Popup");
		dsl.write(By.tagName("textarea"), "Deu certo?");
		
		driver.close();
		
		dsl.switchToWindows("");
		
		dsl.write(By.tagName("textarea"), "Fechou janela??");
		
	}
	
	@Test
	public void shouldInteractWithWindowWithoutId() {

		dsl.click("buttonPopUpHard");
		dsl.switchToWindows(driver.getWindowHandles().toArray()[1].toString());
		dsl.write(By.tagName("textarea"), "Deu certo?");
		
		driver.close();
		
		dsl.switchToWindows("");
		dsl.write(By.tagName("textarea"), "Fechou janela??");
		
	}
}
