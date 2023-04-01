package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private long longTimeout = 30;
	private long shortTimeout = 5;
	
	// Web Browser Function
		protected void openPageUrl(WebDriver driver, String pageUrl) {
			driver.get(pageUrl);
		}

		protected String getPageTitle(WebDriver driver) {
			return driver.getTitle();
		}

		protected String getPageUrl(WebDriver driver) {
			return driver.getCurrentUrl();
		}

		protected String getPageSourceCode(WebDriver driver) {
			return driver.getPageSource();
		}

		protected void backToPage(WebDriver driver) {
			driver.navigate().back();
		}

		protected void forwardToPage(WebDriver driver) {
			driver.navigate().forward();
		}

		protected void refreshToPage(WebDriver driver) {
			driver.navigate().refresh();
		}

		protected Alert waitForAlertPresence(WebDriver driver) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			return explicitWait.until(ExpectedConditions.alertIsPresent());
		}

		protected void acceptAlert(WebDriver driver) {
			waitForAlertPresence(driver).accept();
		}

		protected void cancelAlert(WebDriver driver) {
			waitForAlertPresence(driver).dismiss();
		}

		protected String getAlertText(WebDriver driver) {
			return waitForAlertPresence(driver).getText();
		}

		protected void sendkeyToAlert(WebDriver driver, String textValue) {
			waitForAlertPresence(driver).sendKeys(textValue);
		}

		protected void switchToWindowByID(WebDriver driver, String parentID) {
			Set<String> allWindows = driver.getWindowHandles();
			for (String runWindow : allWindows) {
				if (!runWindow.equals(parentID)) {
					driver.switchTo().window(runWindow);
					break;
				}
			}
		}

		protected void switchToWindowByTitle(WebDriver driver, String title) {
			Set<String> allWindows = driver.getWindowHandles();
			for (String runWindows : allWindows) {
				driver.switchTo().window(runWindows);
				String currentWin = driver.getTitle();
				if (currentWin.equals(title)) {
					break;
				}
			}
		}

		protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
			Set<String> allWindows = driver.getWindowHandles();
			for (String runWindows : allWindows) {
				if (!runWindows.equals(parentID)) {
					driver.switchTo().window(runWindows);
					driver.close();
				}
			}
			driver.switchTo().window(parentID);
		}

		// Web Element Function
		private By getByXpath(String xpathLocator) {
			return By.xpath(xpathLocator);
		}

		private WebElement getWebElement(WebDriver driver, String xpathLocator) {
			return driver.findElement(getByXpath(xpathLocator));
		}

		private List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
			return driver.findElements(getByXpath(xpathLocator));
		}

		protected void clickToElement(WebDriver driver, String xpathLocator) {
			getWebElement(driver, xpathLocator).click();
		}

		protected void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
			WebElement element = getWebElement(driver, xpathLocator);
			element.clear();
			element.sendKeys(textValue);
		}

		protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
			Select select = new Select(getWebElement(driver, xpathLocator));
			select.selectByValue(textItem);
		}

		protected String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
			Select select = new Select(getWebElement(driver, xpathLocator));
			return select.getFirstSelectedOption().getText();
		}

		protected boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
			Select select = new Select(getWebElement(driver, xpathLocator));
			return select.isMultiple();
		}

		protected void selectItemInCustomDropdown(WebDriver driver, String xpathLocator, String childXpathLocator,
				String expectedItem) {
			getWebElement(driver, xpathLocator).click();
			sleepInSecond(1);
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			List<WebElement> allItems = explicitWait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpathLocator)));
			for (WebElement item : allItems) {
				if (item.getText().trim().equals(expectedItem)) {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);

					item.click();
					sleepInSecond(1);
					break;
				}
			}
		}

		protected void sleepInSecond(long timeInSecond) {
			try {
				Thread.sleep(timeInSecond * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
			return getWebElement(driver, xpathLocator).getAttribute(attributeName);
		}

		protected String getElementText(WebDriver driver, String xpathLocator) {
			return getWebElement(driver, xpathLocator).getText();
		}

		protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
			return getWebElement(driver, xpathLocator).getCssValue(propertyName);
		}

		protected String getHexaColorFromRGBA(String rgbaValue) {
			return Color.fromString(rgbaValue).asHex();
		}

		protected int getElementSize(WebDriver driver, String xpathLocator) {
			return getListWebElement(driver, xpathLocator).size();
		}

		protected void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
			WebElement element = getWebElement(driver, xpathLocator);
			if (!element.isSelected()) {
				element.click();
			}
		}

		protected void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
			WebElement element = getWebElement(driver, xpathLocator);
			if (element.isSelected()) {
				element.click();
			}
		}

		protected boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
			return getWebElement(driver, xpathLocator).isDisplayed();
		}

		protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
			return getWebElement(driver, xpathLocator).isEnabled();
		}

		protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
			return getWebElement(driver, xpathLocator).isSelected();
		}

		protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
			driver.switchTo().frame(getWebElement(driver, xpathLocator));
		}

		protected void switchToDefaultContent(WebDriver driver) {
			driver.switchTo().defaultContent();
		}

		protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
			Actions action = new Actions(driver);
			action.moveToElement(getWebElement(driver, xpathLocator)).perform();
		}

		protected void scrollToBottomPage(WebDriver driver) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		protected void highlightElement(WebDriver driver, String xpathLocator) {
			WebElement element = getWebElement(driver, xpathLocator);
			String originalStyle = element.getAttribute("style");
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
					"style", "border: 2px solid red; border-style: dashed;");
			sleepInSecond(1);
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
					"style", originalStyle);
		}

		protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
		}

		protected void scrollToElement(WebDriver driver, String xpathLocator) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					getWebElement(driver, xpathLocator));
		}

		protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
					getWebElement(driver, xpathLocator));
		}

		protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
					} catch (Exception e) {
						return true;
					}
				}
			};
			ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};
			return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
		}

		protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
			return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
					getWebElement(driver, xpathLocator));
		}

		protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
			boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					getWebElement(driver, xpathLocator));
			if (status) {
				return true;
			} else {
				return false;
			}
		}
		
		protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
		}
		
		protected void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
		}
		
		protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		}
		
		protected void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
		}
		
		protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
		}
}
