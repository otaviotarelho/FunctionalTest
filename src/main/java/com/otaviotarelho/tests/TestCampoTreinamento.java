package com.otaviotarelho.tests;

import static com.otaviotarelho.core.DriverFactory.getDriver;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.otaviotarelho.core.BaseTest;
import com.otaviotarelho.core.DSL;

public class TestCampoTreinamento extends BaseTest{
	
	private DSL dsl;
	
	
	@Before
	public void init() {
		getDriver().get("file:///"+ System.getProperty("user.dir") +"/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@Test
	public void shouldWriteInTextField() {
		dsl.write("elementosForm:nome", "Texto a ser escrito");
		Assert.assertEquals("Texto a ser escrito", dsl.obterValorCampoById("elementosForm:nome"));
		
	}
	
	@Test
	public void shouldWriteInTextArea(){
		dsl.write("elementosForm:sugestoes", "Texto a ser escrito no textArea\n\n outra linha \n\n ultima linha");
		Assert.assertEquals("Texto a ser escrito no textArea\n\n outra linha \n\n ultima linha", dsl.obterValorCampoById("elementosForm:sugestoes"));
		
	}
	
	@Test
	public void shouldInteractWithRadio(){
		
		dsl.click("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioOrCheckSelected("elementosForm:sexo:0"));
		
	}
	

	@Test
	public void shouldInteractWithCheckbox(){
		
		dsl.click("elementosForm:comidaFavorita:3");
		Assert.assertTrue(dsl.isRadioOrCheckSelected("elementosForm:comidaFavorita:3"));
		
	}
	
	@Test
	public void shouldSelectAnOptionInCombobox() {
		
		dsl.selectFromComboBox("elementosForm:escolaridade", "2o grau completo");
		
		Assert.assertEquals("2o grau completo", dsl.getSelectedFromComboBox("elementosForm:escolaridade"));
		
	}
	
	@Test
	public void shouldVerifyAvailableValuesFromCombo() {
		
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		
		Select combo = new Select(element);
		
		List<WebElement> options = combo.getOptions();
		
		Assert.assertEquals(8, options.size()); //get combo size
		
		
		boolean encontrou = false;
		
		for(WebElement option : options) {
			if("Mestrado".equals(option.getText())) {
				encontrou = true;
				break;
			}
		}
		
		Assert.assertTrue(encontrou);
		
		encontrou = false;
		for(WebElement option : options) {
			if("Nova opt".equals(option.getText())) {
				encontrou = true;
				break;
			}
		}
		
		Assert.assertFalse(encontrou);
		
	}
	
	@Test
	public void ShouldSelectMultipleCombo() {

		getDriver().get("file:///"+ System.getProperty("user.dir") +"/src/main/resources/componentes.html");
		
		dsl.selectFromComboBox("elementosForm:esportes", "Natacao");
		dsl.selectFromComboBox("elementosForm:esportes", "Corrida");
		dsl.selectFromComboBox("elementosForm:esportes", "O que eh esporte?");
		
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		
		Assert.assertEquals(2, allSelectedOptions.size());
		
	}
	
	@Test
	public void ShouldClickSomeButtons() {
		
		dsl.click("buttonSimple");
		
		WebElement findElement = getDriver().findElement(By.id("buttonSimple"));
		
		Assert.assertEquals("Obrigado!", findElement.getAttribute("value"));
		
	}
	
	@Test
	public void ShouldInterectWithAnchorWithoutId() {
		
		dsl.clickLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.getText("resultado"));
		
	}
	
	@Test
	public void ShouldGetTextInThePage() {
		
		/*boolean contains = driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento");
		
		Assert.assertTrue(contains);*/

		
		Assert.assertEquals("Campo de Treinamento", dsl.getText(By.tagName("h3")));
		// wrong Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.tagName("span")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getText(By.className("facilAchar")));
		
	}
	
	@Test
	public void testJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		//js.executeScript("alert('testnadoJavascript')");
		
		js.executeScript("document.getElementById('elementosForm:nome').value = 'otavio'");
		
		
		WebElement elemento = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", elemento, "solid 4px red");
		
		//scroll
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executeJavascript("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		
	}
}
