package com.otaviotarelho.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.otaviotarelho.tests.TestAlerts;
import com.otaviotarelho.tests.TestCadastro;
import com.otaviotarelho.tests.TestCampoTreinamento;
import com.otaviotarelho.tests.TestFrame;
import com.otaviotarelho.tests.TestWindow;

@RunWith(Suite.class)
@SuiteClasses({
	TestCadastro.class,
	TestCampoTreinamento.class,
	TestAlerts.class,
	TestFrame.class,
	TestWindow.class
})
public class TestSuite {

}
