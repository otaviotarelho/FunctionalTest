package cursoSelenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TestFrame {
	
	@Test
	public void shouldInteractWithFrame() {
		System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///"+ System.getProperty("user.dir") +"/src/main/resources/componentes.html");
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		
		String text = alert.getText();
		Assert.assertEquals("Frame OK!", text);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(text);
		
		driver.quit();
	}
}
