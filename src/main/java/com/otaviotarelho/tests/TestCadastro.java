package com.otaviotarelho.tests;

import static com.otaviotarelho.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;

import com.otaviotarelho.core.BaseTest;
import com.otaviotarelho.core.DSL;
import com.otaviotarelho.pages.CampoTreinamentoPage;


public class TestCadastro extends BaseTest{
	
	private static final String ESCOLARIDADE_MESTRADO = "Mestrado";
	private static final String SOBRENOME = "Tarelho";
	private static final String NOME = "Otavio";
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa(){
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
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
