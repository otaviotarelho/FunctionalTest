package com.otaviotarelho.tests;

import static com.otaviotarelho.core.DriverFactory.getDriver;
import static com.otaviotarelho.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;

import com.otaviotarelho.core.DSL;



public class TestFrame {

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
