package cursoSelenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteGoogle {
	
	@Test
	public void teste() {
		System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		//Aula 21
			// driver.manage().window().setPosition(new Point(100, 100)); window position
			//driver.manage().window().setSize(new Dimension(1200, 765)); window size
			driver.manage().window().maximize();
		//
		driver.get("http://www.google.com");
		
		Assert.assertEquals("Google", driver.getTitle());
		
		//driver.close(); fecha apenas o browser e não a instancia do chrome driver
		driver.quit();
	}
}
