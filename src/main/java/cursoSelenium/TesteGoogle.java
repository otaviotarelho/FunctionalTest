package cursoSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteGoogle {
private WebDriver driver;
	
	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@After
	public void destroy() {
		driver.quit();
	}
	
	@Test
	public void teste() {

		//Aula 21
			// driver.manage().window().setPosition(new Point(100, 100)); window position
			//driver.manage().window().setSize(new Dimension(1200, 765)); window size
		//
		driver.get("http://www.google.com");
		
		Assert.assertEquals("Google", driver.getTitle());
		
		//driver.close(); fecha apenas o browser e não a instancia do chrome driver
		driver.quit();
	}
}
