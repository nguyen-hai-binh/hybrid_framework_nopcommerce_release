package pageUIs;

public class RegisterPageUI {
	public final static String FIRST_NAME_TEXTBOX = "//input[@id='FirstName']";
	public final static String LAST_NAME_TEXTBOX = "//input[@id='LastName']";
	public final static String EMAIL_ADDRESS_TEXTBOX= "//input[@id='Email']";
	public final static String PASSWORD_TEXTBOX= "//input[@id='Password']";
	public final static String CONFIRM_PASSWORD_TEXTBOX= "//input[@id='ConfirmPassword']";
	public final static String REGISTER_BUTTON = "//button[@id='register-button']";
	public final static String REGISTER_SUCCESS_MESSAGE = "//div[@class='result']";
	public final static String ERROR_MESSAGE_FIRST_NAME ="//span[@id='FirstName-error']";
	public final static String ERROR_MESSAGE_LAST_NAME ="//span[@id='LastName-error']";
	public final static String ERROR_MESSAGE_EMAIL_ADDRESS ="//span[@id='Email-error']";
	public final static String ERROR_MESSAGE_PASSWORD ="//span[@id='Password-error']";
	public final static String ERROR_MESSAGE_CONFIRM_PASSWORD ="//span[@id='ConfirmPassword-error']";
	public final static String ERROR_MESSAGE_EXISTING_EMAIL ="//div[@class='message-error validation-summary-errors']";
	public final static String SUCCESS_MESSAGE_REGISTER ="//div[@class='result']";
	public final static String CONTINUE_BUTTON ="//a[contains(@class,'register-continue-button')]";

}
