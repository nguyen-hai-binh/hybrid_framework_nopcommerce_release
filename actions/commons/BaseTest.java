package commons;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {
	private WebDriver driver;

	protected WebDriver getBrowserDriver(String browserName) {
//		if (browserName.equals("chrome")) { // chrome
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//		} else if (browserName.equals("h_chrome")) { // headless chrome
//			WebDriverManager.chromedriver().setup();
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("-headless");
//			options.addArguments("window-size=1920x1080");
//			driver = new ChromeDriver(options);
//		} else if (browserName.equals("firefox")) { // firefox
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//		} else if (browserName.equals("h_firefox")) { // headless firefox
//			WebDriverManager.firefoxdriver().setup();
//			FirefoxOptions options = new FirefoxOptions();
//			options.addArguments("--headless");
//			options.addArguments("window-size=1920x1080");
//			driver = new FirefoxDriver(options);
//		} else if (browserName.equals("edge")) { // edge
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
//		} else if (browserName.equals("brave")) { // brave
//			WebDriverManager.chromedriver().driverVersion("110.0.5481.77").setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
//			driver = new ChromeDriver();
//		} else if (browserName.equals("opera")) { // opera
//			WebDriverManager.operadriver().setup();
//			driver = new OperaDriver();
//		} else if (browserName.equals("coccoc")) { // coccoc
//			WebDriverManager.chromedriver().driverVersion("	109.0.5414.74").setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
//			driver = new ChromeDriver(options);
//		} else {
//			throw new RuntimeException("Browser name invalid");
//		}

		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
			driver = new ChromeDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Browser name is not valid.");
		}

		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;
	}

	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
