package com.nopcommerce.login;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.AddressesPageObject;
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewsPageObject;
import pageObjects.RegisterPageObject;

public class Nopcommerce_MyAccount extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private AddressesPageObject addressesPage;
	private ChangePasswordPageObject changePasswordPage;
	private MyProductReviewsPageObject myProductReviewPage;
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

		// Login a account
		loginPage = homePage.clickToLoginLink();
		loginPage.loginAccount(driver, emailAdress, password);
	}

	@Test
	public void MyAccount_01_Update_Customer_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isYourPersonalDetailsDisplayed());
		customerInfoPage.selectToFemaleRadiobox("Female");
		customerInfoPage.enterFirstNameTextbox("Automation");
		customerInfoPage.enterLastNameTextbox("FC");
		customerInfoPage.selectDay("1");
		customerInfoPage.selectMonth("January");
		customerInfoPage.selectYear("1990");
		customerInfoPage.enterEmailAddress("automationfc@gmail.com");
		customerInfoPage.enterCompanyName("AutomationFC");
		customerInfoPage.clickToSaveButton();
		Assert.assertEquals(customerInfoPage.getNotificationSuccessMessage(), "The customer info has been updated successfully.");
		Assert.assertTrue(customerInfoPage.isFemaleSelected());
		Assert.assertEquals(customerInfoPage.getFirstNameAttributeValue(), "Automation");
		Assert.assertEquals(customerInfoPage.getLastNameAttributeValue(), "FC");
		Assert.assertEquals(customerInfoPage.getDateAttributeValue(), "1");
		Assert.assertEquals(customerInfoPage.getMonthAttributeValue(), "January");
		Assert.assertEquals(customerInfoPage.getYearAttributeValue(), "1990");
		Assert.assertEquals(customerInfoPage.getEmailAttributeValue(), "automationfc@gmail.com");
		Assert.assertEquals(customerInfoPage.getCompanyNameAttributeValue(), "AutomationFC");
	}

	@Test
	public void MyAccount_02_Add_Addresses() {
		addressesPage = customerInfoPage.openAddressesPage(driver);
		addressesPage.clickToAddNewButton();
		addressesPage.enterFirstNameTextbox("Automation");
		addressesPage.enterLastNameTextbox("FC");
		addressesPage.enterEmailAddressTextbox("automationfcvn@gmail.com");
		addressesPage.enterCompanyTextbox("Automation FC");
		addressesPage.selectACountry("VietNam");
		addressesPage.selectAState("Other");
		addressesPage.enterCityTextbox("Da Nang");
		addressesPage.enterAddress1Textbox("123/04 Le Lai");
		addressesPage.enterAddress2Textbox("234/05 Hai Phong");
		addressesPage.enterZipTextbox("550000");
		addressesPage.enterPhoneNumberTextbox("0123456789");
		addressesPage.enterFaxNumberTextbox("0987654321");
		addressesPage.clickToSaveButton();
		Assert.assertEquals(addressesPage.getNotificationSuccessMessage(), "The new address has been added successfully.");
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
		changePasswordPage.enterOldPasswordTextbox(password);
		changePasswordPage.enterNewPasswordTextbox("987654321");
		changePasswordPage.enterConfirmPasswordTextbox("987654321");
		changePasswordPage.clickToChangePasswordButton();
		Assert.assertEquals(changePasswordPage.getNotificationSuccessMessage(), "Password was changed");
		homePage = changePasswordPage.clickToLogoNopCommerce();
		loginPage = homePage.clickToLoginLink();
		
		// Login with old password
		loginPage.enterEmailTextbox("automationfc@gmail.com");
		loginPage.enterPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
		// Login with new password
		loginPage.enterEmailTextbox("automationfc@gmail.com");
		loginPage.enterPasswordTextbox("987654321");
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertEqual(customerInfoPage.isCustomerInfoPageDisplayed(), "My account - Customer info");
	}

	@Test
	public void MyAccount_04_My_Product_Review() {
		homePage = customerInfoPage.clickToLogoNopCommerce();
		
		myProductReviewPage = customerInfoPage.openMyProductReviewsPage(driver);
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
