package cursoSelenium;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCampoTreinamento {
	
	
	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver","/Users/otaviortbarros/Developer/Selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///"+ System.getProperty("user.dir") +"/src/main/resources/componentes.html");
		
		//driver.quit();
	}
}
