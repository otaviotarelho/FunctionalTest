import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.otaviotarelho.helpers.DSL;


public class AjaxTest {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		
		dsl = new DSL(driver);
	}
	
	@After
	public void destroy() {
		driver.quit();
	}
	
	@Test
	public void testAjax() {
		dsl.write("j_idt116:name", "teste");
		dsl.click("j_idt116:j_idt119");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt116:display"), "teste"));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("")));
		Assert.assertEquals("teste", dsl.getText("j_idt116:display"));
	}
	
}
