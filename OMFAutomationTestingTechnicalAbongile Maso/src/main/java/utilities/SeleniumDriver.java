package utilities;

import enumerables.Enums.BrowserTypes;
import enumerables.Enums.LocatorType;

import helperObjects.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.junit.runner.notification.StoppedByUserException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import javax.swing.text.html.HTMLDocument.Iterator;

public class SeleniumDriver extends TestBase {
	private WebDriver webDriver;

    ExtentHtmlReporter htmlReporter;
 
    ExtentReports extent;
    //helps to generate the logs in the test report.
    ExtentTest test;
    
	 
	 
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
			  Assert.assertTrue(true);
			return true;
		} catch (StaleElementReferenceException | IllegalStateException | NoSuchElementException e) {
		    Assert.assertTrue(false);
		    
			throw e;
		} catch (Exception e) {
			captureScreen();
			throw e;
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: closeBrowser
	 * @purpose: retrieve text and compare it with datasheet value
	 * @param :
	 * @author Abongile Maso
	 * @return: void
	 * 
	 * 
	 ***/
	public boolean RetriveTitleAndCompare(String value) {

		try {

			String retrievedText = webDriver.getTitle();

			if (!retrievedText.equals(value)) {
				System.out.println("Failed to validate: " + retrievedText + " Againts: " + value);
				return false;
			}
			System.out.println("Text retrieved successfully validate text - " + retrievedText + " with: " + value);
			return true;
		} catch (Exception e) {

			return false;
		}

	}

	
	public String captureScreen() {
	    String path;
	    WebDriver driver = new ChromeDriver();
	    try {
	        WebDriver webDriver = new Augmenter().augment(driver);
	        File source = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
	        path = "./target/screenshots/" + source.getName();
	        FileUtils.copyFile(source, new File(path));
	        System.out.println("source" +source);
	        System.out.println("path" +path);
	    }
	    catch(IOException e) {
	        path = "Failed to capture screenshot: " + e.getMessage();
	    }
	    return path;
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
		    Assert.assertTrue(true);
		    captureScreen();
			return true;
		} catch (WebDriverException e) {
		    Assert.assertTrue(false);
			throw e;
		}

	}

	public boolean clickElement2(LocatorType locatorType, String value, LocatorType locatorType2, String value2) {
		try {
			By locator = LocatorValue(locatorType, value);
			By locator2 = LocatorValue(locatorType2, value2);
			WebElement element = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));
			element.click();
			WebElement element2 = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator2)));
			element2.click();
			return true;
		} catch (WebDriverException e) {
			throw e;
		}

	}

	public boolean ScrolToElementAndClick(LocatorType locatorType, String value) {
		try {
			By locator = LocatorValue(locatorType, value);

			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("window.scrollBy(0,380)", "");

			webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			WebElement element = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(locator)));
			element.click();
		    Assert.assertTrue(true);
			return true;
		} catch (WebDriverException e) {
		    Assert.assertTrue(false);
			throw e;
		}

	}

	public boolean retryingFindClick(LocatorType locatorType, String value) {
		By locator = LocatorValue(locatorType, value);
		boolean result = false;
		try {
			WebElement button = webDriver.findElement(locator);
			button.click();
			result = true;
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			WebElement button = webDriver.findElement(locator);
			button.click();
			result = true;
		}
		return result;
	}

	public void waitForElementElementToApear(LocatorType locatorType, String value) {
		try {
			By locator = LocatorValue(locatorType, value);
		} catch (Exception e) {
			String lines[] = e.getMessage().split("\\r?\\n");
		}
	}

	public void SearchDroDown(LocatorType locatorType, String value, String SearchFor) {

		By locator = LocatorValue(locatorType, value);
		String searchText = SearchFor;

		WebElement dropdown = webDriver.findElement(locator);
		clickElement(LocatorType.XPATH, value);
		List<WebElement> options = dropdown.findElements(By.className("dropdown-options-list"));
		for (WebElement option : options) {
			if (option.getText().equals(searchText)) {
				option.click(); // click the desired option
				break;
			}
		}
	}

	public boolean MoveToElementAndClick(LocatorType locatorType, String value) {
		try {
			By locator = LocatorValue(locatorType, value);

			// JavascriptExecutor executor = (JavascriptExecutor)webDriver;

			JavascriptExecutor jse2 = (JavascriptExecutor) webDriver;
			jse2.executeScript("arguments[0].scrollIntoView()", locator);

			return true;
		} catch (WebDriverException e) {
			throw e;
		}
	}

	public boolean WaitforElement(LocatorType locatorType, String value) {
		try {
			By locator = LocatorValue(locatorType, value);

			WebDriverWait wait3 = new WebDriverWait(webDriver, 20);
			wait3.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		    Assert.assertTrue(true);
			return true;
		} catch (WebDriverException e) {
		    Assert.assertTrue(false);
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
			captureScreen();
		    Assert.assertTrue(true);
			return true;
		} catch (WebDriverException e) {
		    Assert.assertTrue(false);
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

		for (String winHandle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(winHandle);
		}

//
//		for (String tab : webDriver.getWindowHandles()) {
//
//			if (!tab.equals(currentTab)) {
//
//				//webDriver.switchTo().window(tab);
//				((JavascriptExecutor)webDriver).executeScript("window.open()");
//				ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
//				webDriver.switchTo().window(tabs.get(1));
//				System.out.println("Tab" + currentTab);
//				System.out.println("Tab2" + tab);
//				System.out.println("Page title is : " + webDriver.getTitle());

		// Perform operation on new Tab

	}

	// }
//	}

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
