package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class OrdersPageObject extends BasePage {
	public OrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriver driver;
}
