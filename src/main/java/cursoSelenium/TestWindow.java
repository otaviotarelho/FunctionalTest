package cursoSelenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestWindow {

	@Test
	public void shouldInteractWithWindow() {
		System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///"+ System.getProperty("user.dir") +"/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		
		driver.switchTo().window("Popup");
		
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		
		driver.close();
		
		driver.switchTo().window("");
		
		driver.findElement(By.tagName("textarea")).sendKeys("Fechou janela??");
		
		driver.quit();
	}
	
	@Test
	public void shouldInteractWithWindowWithoutId() {
		System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///"+ System.getProperty("user.dir") +"/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		
		driver.close();
		
		driver.switchTo().window("");
		
		driver.findElement(By.tagName("textarea")).sendKeys("Fechou janela??");
		
		driver.quit();
	}
}
