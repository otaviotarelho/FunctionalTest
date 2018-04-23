package com.otaviotarelho.pages;

import com.otaviotarelho.core.BasePage;

public class CampoTreinamentoPage extends BasePage {
	
	public void setNome(String nome) {
		dsl.write("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.write("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino() {
		dsl.click("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.click("elementosForm:sexo:1");
	}
	
	public void setComidaCarne() {
		dsl.click("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaPizza() {
		dsl.click("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano() {
		dsl.click("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String value) {
		dsl.selectFromComboBox("elementosForm:escolaridade", value);
	}
	
	public void setEsporte(String... values) {
		for(String value: values) {
			dsl.selectFromComboBox("elementosForm:esportes",value);
		}
	}
	
	public void signup() {
		dsl.click("elementosForm:cadastrar");
	}
	
	public String getResultadoCadastro() {
		return dsl.getText("resultado");
	}
	
	public String getRegistredName() {
		return dsl.getText("descNome");
	}
	
	public String getRegistredSobrenome() {
		return dsl.getText("descSobrenome");
	}
	
	public String getRegistredSexo() {
		return dsl.getText("descSexo");
	}
	
	public String getRegistredComida() {
		return dsl.getText("descComida");
	}
	
	public String getRegistredEscolaridade() {
		return dsl.getText("descEscolaridade");
	}
	
	public String getRegistredEsporte() {
		return dsl.getText("descEsportes");
	}
	
}
