package com.nopcommerce.myacount;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.AddressesPageObject;
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class Nopcommerce_01_MyAccount extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private AddressesPageObject addressesPage;
	private ChangePasswordPageObject changePasswordPage;
	private String firstName, lastName, emailAdress, password, confirmPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		emailAdress = "afc" + generateFakeNumber() + "@gmail.com";
		password = "123456789";
		confirmPassword = "123456789";

		// Register a account
		registerPage = homePage.clickToRegisterLink();
		registerPage.registerAccount(driver, firstName, lastName, emailAdress, password, confirmPassword);
		
		//Login a account
		loginPage = homePage.clickToLoginLink();
		loginPage.loginAccount(driver, emailAdress, password);
	}

	@Test
	public void MyAccount_01_Update_Customer_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isYourPersonalDetailsDisplayed());
		customerInfoPage.checkToFemaleCheckboxRadio("Female");
		customerInfoPage.inputToFirstNameTextbox("Automation");
		customerInfoPage.inputToLastNameTextbox("FC");
		customerInfoPage.selectDay("1");
		customerInfoPage.selectMonth("January");
		customerInfoPage.selectYear("1990");
		customerInfoPage.inputToEmailAddress("automationfc@gmail.com");
		customerInfoPage.inputToCompanyName("AutomationFC");
		customerInfoPage.clickToSaveButton();
		Assert.assertEquals(customerInfoPage.getSuccessMessageUpdateInfor(), "The customer info has been updated successfully.");
		Assert.assertTrue(customerInfoPage.isFemaleSelected());
		Assert.assertEquals(customerInfoPage.getInforFirstNameTextbox(), "Automation");
		Assert.assertEquals(customerInfoPage.getInforLastNameTextbox(), "FC");
		Assert.assertEquals(customerInfoPage.getInforDate(), "1");
		Assert.assertEquals(customerInfoPage.getInforMonth(), "January");
		Assert.assertEquals(customerInfoPage.getInforYear(), "1990");
		Assert.assertEquals(customerInfoPage.getInforEmail(), "automationfc@gmail.com");
		Assert.assertEquals(customerInfoPage.getInforCompanyName(), "AutomationFC");
	}

	@Test
	public void MyAccount_02_Add_Addresses() {
		addressesPage = customerInfoPage.openAddressesPage(driver);
		addressesPage.clickToAddNewButton();
		addressesPage.inputFirstNameTextbox("Automation");
		addressesPage.inputLastNameTextbox("FC");
		addressesPage.inputEmailAddressTextbox("automationfc.vn@gmail.com");
		addressesPage.inputCompanyTextbox("Automation FC");
		addressesPage.selectACountry("VietNam");
		addressesPage.selectAState("Other");
		addressesPage.inputCityTextbox("Da Nang");
		addressesPage.inputAddress1Textbox("123/04 Le Lai"); 
		addressesPage.inputAddress2Textbox("234/05 Hai Phong"); 
		addressesPage.inputZipTextbox("550000");
		addressesPage.inputPhoneNumberTextbox("0123456789");
		addressesPage.inputFaxNumberTextbox("0987654321");
		addressesPage.clickToSaveButton();
		Assert.assertEquals(addressesPage.getSuccessMessageUpdateAddress(), "The new address has been added successfully.");
		Assert.assertEquals(addressesPage.getNameValue, "Automation FC");
		Assert.assertEquals(addressesPage.getEmailValue, "automationfc.vn@gmail.com");
		Assert.assertEquals(addressesPage.getPhoneValue, "0123456789");
		Assert.assertEquals(addressesPage.getFaxValue, "0987654321");
		Assert.assertEquals(addressesPage.getCompanyValue, "Automation FC");
		Assert.assertEquals(addressesPage.getAddress1Value, "123/04 Le Lai");
		Assert.assertEquals(addressesPage.getAddress2Value, "234/05 Hai Phong");
		Assert.assertEquals(addressesPage.getStateAndZipValue, "Ha Noi, 550000");
		Assert.assertEquals(addressesPage.getCountryValue, "Viet Nam");
	}

	@Test
	public void MyAccount_03_Change_Password() {
	changePasswordPage = addressesPage.openChangePasswordPage(driver);
	
	}

	@Test
	public void MyAccount_04_My_Product_Review() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
