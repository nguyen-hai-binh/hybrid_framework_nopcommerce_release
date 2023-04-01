package com.nopcommerce.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

public class Nopcommerce_01_Register extends BaseTest {
	private WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
	}

	@Test
	public void Register_01_() {
	}

	@Test
	public void Register_02_() {
	}

	@Test
	public void Register_03_() {
	}

	@Test
	public void Register_04_() {
	}

	@Test
	public void Register_05_() {
	}

	@Test
	public void Register_06_() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
