package com.otaviotarelho.tests;
import static com.otaviotarelho.core.DriverFactory.getDriver;
import static com.otaviotarelho.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.otaviotarelho.core.DSL;


public class AjaxTest {
	
	private DSL dsl;
	
	@Before
	public void init() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		
		dsl = new DSL();
	}
	
	@After
	public void destroy() {
		killDriver();
	}
	
	@Test
	public void testAjax() {
		dsl.write("j_idt116:name", "teste");
		dsl.click("j_idt116:j_idt119");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt116:display"), "teste"));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("")));
		Assert.assertEquals("teste", dsl.getText("j_idt116:display"));
	}
	
}
