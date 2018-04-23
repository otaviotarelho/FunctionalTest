package com.otaviotarelho.core;

import static com.otaviotarelho.core.DriverFactory.getDriver;
import static com.otaviotarelho.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();
	
	@After
	public void destroy() throws IOException {
		
		TakesScreenshot ss = (TakesScreenshot) getDriver();
		File arq = ss.getScreenshotAs(OutputType.FILE);
		
		StringBuilder screenshotName = fileName();
		FileUtils.copyFile(arq, new File(screenshotName.toString()));
		
		if(Properties.FECHAR_BROWSER) {
			killDriver();
		}
	}

	private StringBuilder fileName() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("target");
		stringBuilder.append(File.pathSeparator);
		stringBuilder.append("screenshot");
		stringBuilder.append(File.pathSeparator);
		stringBuilder.append(testName.getMethodName());
		stringBuilder.append(".jpg");
		return stringBuilder;
	}
}
