package pageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMessageAtFirstNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_FIRST_NAME);
		return getElementText(driver, RegisterPageUI.ERROR_MESSAGE_FIRST_NAME);
	}

	public String getErrorMessageLastNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_LAST_NAME);
		return getElementText(driver, RegisterPageUI.ERROR_MESSAGE_LAST_NAME);
	}

	public String getErrorMassageEmailTextbox() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_ADDRESS);
		return getElementText(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_ADDRESS);
	}

	public String getErrorMassagePassswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_PASSWORD);
		return getElementText(driver, RegisterPageUI.ERROR_MESSAGE_PASSWORD);
	}

	public String getErrorMassageConfirmPassword() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_CONFIRM_PASSWORD);
		return getElementText(driver, RegisterPageUI.ERROR_MESSAGE_CONFIRM_PASSWORD);
	}
	
	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_EXISTING_EMAIL);
		return getElementText(driver, RegisterPageUI.ERROR_MESSAGE_EXISTING_EMAIL);
	}
	
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE_REGISTER);
		return getElementText(driver, RegisterPageUI.SUCCESS_MESSAGE_REGISTER);
	}	

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	public HomePageObject clickToContinueButton() {
		waitForElementClickable(driver,RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}
	
	public HomePageObject registerAccount(WebDriver driver, String firstName, String lastName, String emailAddress, String password, String confirmPassword) {
		inputToFirstNameTextbox(firstName);
		inputToLastNameTextbox(lastName);
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(password);
		inputToConfirmPasswordTextbox(confirmPassword);
		clickToRegisterButton();
		return clickToContinueButton();
	}
}
