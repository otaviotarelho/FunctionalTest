package cursoSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestWindow {
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
