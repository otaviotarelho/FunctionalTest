package com.otaviotarelho.tests;

import static com.otaviotarelho.core.DriverFactory.getDriver;
import static com.otaviotarelho.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;

import com.otaviotarelho.core.DSL;

public class TestAlerts {
	
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
	public void shouldInteractWithSimpleAlert() {
	
		dsl.click("alert");
		
		Alert alert = dsl.switchToAlert();
		
		String AlertText = alert.getText();
		
		Assert.assertEquals("Alert Simples", AlertText);
		
		alert.dismiss();
		
		dsl.write("elementosForm:nome", AlertText);
	}
	
	@Test
	public void shouldInteractWithConfirmAlert() {
		
		dsl.click("confirm");
		
		Alert alert = dsl.switchToAlert();
		
		Assert.assertEquals("Confirm Simples", alert.getText());
		
		alert.accept();
		
		Assert.assertEquals("Confirmado", alert.getText());
		
		alert.accept();
		
		dsl.click("confirm");
		
		alert = dsl.switchToAlert();
		
		Assert.assertEquals("Confirm Simples", alert.getText());
		
		alert.dismiss();
		
		Assert.assertEquals("Negado", alert.getText());
		
		alert.accept();
		
	}
	
	@Test
	public void shouldInteractWithPromptAlert() {

		dsl.click("prompt");
		
		Alert alert = dsl.switchToAlert();
		
		Assert.assertEquals("Digite um numero", alert.getText());
		
		alert.sendKeys("12");
		
		alert.accept();
		
		Assert.assertEquals("Era 12?", alert.getText());
		
		alert.accept();
		
		Assert.assertEquals(":D", alert.getText());
		
		alert.accept();
		
	}
}
