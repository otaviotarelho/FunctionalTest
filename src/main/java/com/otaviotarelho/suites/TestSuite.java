package com.otaviotarelho.suites;

import static com.otaviotarelho.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.otaviotarelho.tests.TestCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TestCadastro.class
})
public class TestSuite {
	
	@AfterClass
	public static void destroy() {
		killDriver();
	}
}
