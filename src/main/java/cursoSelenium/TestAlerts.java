package cursoSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAlerts {
	
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
	public void shouldInteractWithSimpleAlert() {
	
		dsl.click("alert");
		
		Alert alert = dsl.switchToAlert();
		
		String AlertText = alert.getText();
		
		Assert.assertEquals("Alert Simples", AlertText);
		
		alert.dismiss();
		
		dsl.escreve("elementosForm:nome", AlertText);
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
