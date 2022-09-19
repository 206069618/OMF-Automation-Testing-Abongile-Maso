package utilities;

import enumerables.Enums.BrowserTypes;
import enumerables.Enums.LocatorType;

import helperObjects.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.notification.StoppedByUserException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Iterator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import javax.swing.text.html.HTMLDocument.Iterator;

public class SeleniumDriver extends TestBase {
	private WebDriver webDriver;

	public SeleniumDriver(BrowserTypes browserType) {
		switch (browserType) {
		case Chrome:
			WebDriverManager.chromedriver().setup();
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability("build", "[Java] How to Deal with Element is not clickable at point Exception");
			cap.setCapability("name", "[Java] How to Deal with Element is not clickable at point Exception");
			cap.setCapability("platform", "Windows 10");
			cap.setCapability("browserName", "Chrome");
			cap.setCapability("version", "latest");
			cap.setCapability("tunnel", false);
			cap.setCapability("network", true);
			cap.setCapability("console", true);
			cap.setCapability("visual", true);
			this.webDriver = new ChromeDriver();
			break;
		case FireFox:
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities cap1 = DesiredCapabilities.firefox();
			cap1.setCapability("marionette", true);
			this.webDriver = new FirefoxDriver(cap1);
			break;
		default:

		}

		this.webDriver.manage().timeouts().implicitlyWait(appConfig.getWaitTimeout(), TimeUnit.SECONDS);
		this.webDriver.manage().timeouts().pageLoadTimeout(appConfig.getPageLoadTimeout(), TimeUnit.SECONDS);
		this.webDriver.manage().window().maximize();
	}

	public boolean navigateToPage(String url) {
		try {
			webDriver.navigate().to(url);
			System.out.println("Home Page title is : " + webDriver.getTitle());
			return true;
		} catch (StaleElementReferenceException | IllegalStateException | NoSuchElementException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	public String getTextFromElement(LocatorType locatorType, String value) {
		try {

			By locator = LocatorValue(locatorType, value);
			WebElement element = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));

			String text = element.getText();
			return text;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean enterText(LocatorType locatorType, String value, String text) {
		try {
			By locator = LocatorValue(locatorType, value);

			WebElement element = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));

			element.clear();
			element.sendKeys(text);

			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean selectOption(LocatorType locatorType, String value, String text) {
		try {
			By locator = LocatorValue(locatorType, value);
			WebElement element = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));
			Select dropdown = new Select(element);
			element.click();
			dropdown.selectByVisibleText(text);
			return true;
		}

		catch (Exception e) {
			throw e;
		}
	}

	public int returnNUmberOfElements(LocatorType locatorType, String value) {
		try {
			int size = 0;
			By locator = LocatorValue(locatorType, value);
			List<WebElement> element = webDriver.findElements(locator);
			size = element.size();
			return size;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean clickElement(LocatorType locatorType, String value) {
		try {
			By locator = LocatorValue(locatorType, value);
			WebElement element = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));
			element.click();
			return true;
		} catch (WebDriverException e) {
			throw e;
		}

	}

	public boolean MoveToElementAndClick(LocatorType locatorType, String value)
    {
		try {
		By locator = LocatorValue(locatorType, value);
		Actions a = new Actions(webDriver);
		JavascriptExecutor executor = (JavascriptExecutor)a;
		executor.executeScript("arguments[0].click();", locator);
		return true;
		} catch (WebDriverException e) {
			throw e;
		}
    }

	public boolean hoverOverMenuAndClickSubItem(LocatorType locatorType, String menu, String subMenu) {
		try {
			Actions a = new Actions(webDriver);
			By locator = LocatorValue(locatorType, menu);
			WebElement element = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));
			a.moveToElement(element).build().perform();

			locator = LocatorValue(locatorType, subMenu);
			element = webDriver.findElement(locator);

			element.click();

			return true;
		} catch (WebDriverException e) {
			throw e;
		}
	}

	public void hoverOverMenuAndClickSubItemIfExist(LocatorType locatorType, String menu) {
		try {
			Actions a = new Actions(webDriver);
			By locator = LocatorValue(locatorType, menu);
			WebElement element = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));
			a.moveToElement(element).build().perform();

			element = element.findElement(By.xpath("./ul/li/a"));

			if (element != null)
				element.click();

		} catch (WebDriverException e) {
			throw e;
		}
	}

	public void switchToIframe(LocatorType locatorType, String value) {
		By locator = LocatorValue(locatorType, value);
		WebElement element = (new WebDriverWait(webDriver, 20))
				.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));
		webDriver.switchTo().frame(element);
	}

	public static By LocatorValue(LocatorType locatorType, String value) {
		By by;
		switch (locatorType) {
		case ID:
			by = By.id(value);
			break;
		case NAME:
			by = By.name(value);
			break;
		case XPATH:
			by = By.xpath(value);
			break;
		case CSS:
			by = By.cssSelector(value);
			break;
		case LINK_TEXT:
			by = By.linkText(value);
			break;
		case PARTIAL_LINK_TEXT:
			by = By.partialLinkText(value);
			break;
		case CLASS_NAME:
			by = By.className(value);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}

	public String getPageTitle() {
		return this.webDriver.getTitle();
	}

	public WebDriver getWebDriver() {
		try {
			return webDriver;
		} catch (Exception e) {
			throw e;
		}
	}

	public void closeBrowser() {
		if (webDriver != null) {
			webDriver.close();
			webDriver.quit();
		}
	}

	public void SwitchTabs() {
		Actions a = new Actions(webDriver);
		a.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();

		String currentTab = webDriver.getWindowHandle();

		for (String tab : webDriver.getWindowHandles()) {

			if (!tab.equals(currentTab)) {

				webDriver.switchTo().window(tab);
				
				System.out.println("Tab" + currentTab);
				System.out.println("Tab2" + tab);
				System.out.println("Page title is : " + webDriver.getTitle());

				// Perform operation on new Tab

			}

		}
	}

	public void SwitchWindows(String url) {
		webDriver.get(url);

		// It will return the parent window name as a String
		String parent = webDriver.getWindowHandle();

		// This will return the number of windows opened by Webdriver and will return
		// Set of St//rings
		Set<String> s1 = webDriver.getWindowHandles();

		// Now we will iterate using Iterator
		Iterator<String> I1 = s1.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			// Here we will compare if parent window is not equal to child window then we
			// will close

			if (!parent.equals(child_window)) {
				webDriver.switchTo().window(child_window);

				System.out.println(webDriver.switchTo().window(child_window).getTitle());

				webDriver.close();
			}

		}
		// once all pop up closed now switch to parent window
		webDriver.switchTo().window(parent);

	}

}
