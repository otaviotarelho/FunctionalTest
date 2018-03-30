package com.otaviotarelho.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.otaviotarelho.helpers.DSL;
import com.otaviotarelho.pages.CampoTreinamentoPage;


public class TestCadastro {
	
	private static final String ESCOLARIDADE_MESTRADO = "Mestrado";
	private static final String SOBRENOME = "Tarelho";
	private static final String NOME = "Otavio";
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa(){
		System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}

	@Test
	public void deveRealizarCadastroComSucesso(){
		page.setNome(NOME);
		page.setSobrenome(SOBRENOME);
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade(ESCOLARIDADE_MESTRADO);
		page.setEsporte("Natacao");
		page.signup();
		
		Assert.assertTrue(page.getResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.getRegistredName().endsWith(NOME));
		Assert.assertEquals("Sobrenome: Tarelho", page.getRegistredSobrenome());
		Assert.assertEquals("Sexo: Masculino", page.getRegistredSexo());
		Assert.assertEquals("Comida: Pizza", page.getRegistredComida());
		Assert.assertEquals("Escolaridade: mestrado", page.getRegistredEscolaridade());
		Assert.assertEquals("Esportes: Natacao", page.getRegistredEsporte());
	}
	
	@Test
	public void deveValidarNomeObrigatorio(){
		page.signup();
		Alert alert = dsl.switchToAlert();
		String text = alert.getText();
		Assert.assertEquals("Nome eh obrigatorio", text);
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio(){
		page.setNome("Nome qualquer");
		page.signup();
		
		
		
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertAceptAndReturnText());
	}
	
	@Test
	public void deveValidarSexoObrigatorio(){
		page.setNome(NOME);
		page.setSobrenome(SOBRENOME);
		page.signup();
		
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertAceptAndReturnText());
	}
	
	@Test
	public void deveValidarComidaVegetariana(){
		page.setNome(NOME);
		page.setSobrenome(SOBRENOME);
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.signup();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertAceptAndReturnText());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso(){
		page.setNome(NOME);
		page.setSobrenome(SOBRENOME);
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?");
		page.signup();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertAceptAndReturnText());
	}
}
