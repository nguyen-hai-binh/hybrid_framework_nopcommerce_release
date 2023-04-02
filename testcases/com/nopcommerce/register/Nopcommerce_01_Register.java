package com.nopcommerce.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class Nopcommerce_01_Register extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Register_01_Empty_Data() {
	registerPage = homePage.clickToRegisterLink();
	registerPage.clickToRegisterButton();
	Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
	Assert.assertEquals(registerPage.getErrorMessageLastNameTextbox(), "Last name is required.");
	Assert.assertEquals(registerPage.getErrorMassageEmailTextbox(), "Email is required.");
	Assert.assertEquals(registerPage.getErrorMassagePassswordTextbox(), "Password is required.");
	Assert.assertEquals(registerPage.getErrorMassageConfirmPassword(), "Password is required.");
		
	}

	@Test
	public void Register_02_Invalid_Email() {
	}

	@Test
	public void Register_03_Successfully() {
	}

	@Test
	public void Register_04_Exist_Email() {
	}

	@Test
	public void Register_05_Password_Less_Than_6_Characters() {
	}

	@Test
	public void Register_06_Invalid_Password() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
