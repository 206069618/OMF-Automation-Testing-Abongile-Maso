package pageObjects;

import enumerables.Enums.LocatorType;
import helperObjects.TestBase;
import utilities.SeleniumDriver;

public class LandingPage extends TestBase {

	public LandingPage(SeleniumDriver seleniumDriver) {
		super(seleniumDriver);
	}

	private String unitPriceStr;
	private String totalPriceStr;
// ABongile 

	private String OldMutualFinanceWebsiteTitle() {
		return "VerifyTitle";
	}

	private String OurSolutionMenuItemXpath() {
		return "/html[1]/body[1]/div[1]/div[1]/om-wc-config[1]/div[1]/om-page[1]/div[1]/div[1]/header[1]/om-header-with-breadcrumbs[1]/div[1]/om-main-navigation[1]/div[1]/nav[1]/div[1]/div[2]/om-main-navigation-menu[1]/ul[1]/li[1]/ul[1]/li[3]/a[1]";
	}

	private String PesonalLoanCss() {
		return "/html[1]/body[1]/div[1]/div[1]/om-wc-config[1]/div[1]/om-page[1]/div[1]/div[1]/header[1]/om-header-with-breadcrumbs[1]/div[1]/om-main-navigation[1]/div[1]/nav[1]/div[1]/div[2]/om-main-navigation-menu[1]/ul[1]/li[1]/ul[1]/li[3]/ul[1]/li[1]/ul[1]/li[2]/a[1]";
	}

	private String CalculateLoanCss() {
		return "om-wc-config.hydrated om-page.hydrated div.theme-default.om-page-container om-header-with-breadcrumbs.hydrated div.header-with-breadcrumbs-container om-hero-banner.hydrated:nth-child(3) div.hero-banner-container div.banner:nth-child(3) om-hero-banner-content.hydrated span.theme-default.om-hero-banner-content-container om-button.hydrated:nth-child(4) a.theme-default.secondary-large > span.om-button-text";
	}

	private String HowMuchDoYouNeedDrpXpath() {
		return "/html[1]/body[1]/div[1]/div[1]/om-wc-config[1]/div[1]/om-page[1]/div[1]/div[1]/main[1]/om-application-container[1]/div[1]/om-1-col-layout[1]/div[1]/om-section[1]/div[1]/div[1]/div[2]/div[1]/om-personal-loans-calculator[1]/div[1]/div[1]/form[1]/div[1]/om-form-dropdown-field-wrapper[1]/div[1]/div[1]/div[1]/om-form-dropdown-field[1]/div[1]/div[1]/span[1]";
		//return "//input[@placeholder='How long do you need to repay it?']";
		}
	private String HowMuchDoYouNeedValueXpath() {
		return "//*[@id=\"R50000\"]";
	}

	private String searchInputFieldID() {
		return "search_query_top";
	}

	private String searchButtonNameID() {
		return "submit_search";
	}

	private String itemsGridXpath() {
		return "//*[@id='center_column']/ul/li";
	}

	private String menuItemXpath() {
		return "(//div[@id=\"block_top_menu\"]/ul/li)[1]";
	}

	private String menuItemGenericXpath(int index) {
		return "(//div[@id=\"block_top_menu\"]/ul/li)[" + index + "]";
	}

	private String subMenuItemsXpath() {
		return "";
	}

	private String tshirtsSubMenuItemXpath() {
		return "(//a[@title=\"T-shirts\"])[1]";
	}

	private String itemXpath() {
		return "//div[@class=\"product-image-container\"]";
	}

	private String unitPriceLabelID() {
		return "our_price_display";
	}

	private String totaLabelXpath() {
		return "//span[@class=\"ajax_block_products_total\"]";
	}

	private String quantityInputID() {
		return "quantity_wanted";
	}

	private String addToCartButtonID() {
		return "add_to_cart";
	}

	private String popUpIframeXpath() {
		return "//iframe[@class=\"fancybox-iframe\"]";
	}

	public void navigate() {
		seleniumDriver.navigateToPage(appConfig.getSelectedEnvironment().urlUnderTest);
	}

	public String getUnitPrice() {
		return unitPriceStr;
	}

	public String getTotalPrice() {
		return totalPriceStr;
	}

	public LandingPage search(String search_text) {
		this.navigate();
		seleniumDriver.enterText(LocatorType.ID, searchInputFieldID(), search_text);
		seleniumDriver.clickElement(LocatorType.NAME, searchButtonNameID());

		return new LandingPage(seleniumDriver);
	}

	public int getItemsCount() {
		return seleniumDriver.returnNUmberOfElements(LocatorType.XPATH, itemsGridXpath());
	}

	public LandingPage selectPesornalLoad() {

		seleniumDriver.hoverOverMenuAndClickSubItem(LocatorType.XPATH, OurSolutionMenuItemXpath(), PesonalLoanCss());
		seleniumDriver.clickElement(LocatorType.CSS, CalculateLoanCss());
		seleniumDriver.SwitchTabs();
		seleniumDriver.clickElement(LocatorType.XPATH, HowMuchDoYouNeedDrpXpath());
		seleniumDriver.clickElement(LocatorType.XPATH, HowMuchDoYouNeedValueXpath());
		//seleniumDriver.selectOption(LocatorType.CSS, HowMuchDoYouNeedDrpXpath(), "R50 000");
		// seleniumDriver.clickElement(LocatorType.CSS, HowMuchDoYouNeedValueCss());
		return new LandingPage(seleniumDriver);
	}

	public LandingPage addItemsToCart(int qty) {
		seleniumDriver.hoverOverMenuAndClickSubItem(LocatorType.XPATH, OurSolutionMenuItemXpath(), PesonalLoanCss());
//		seleniumDriver.clickElement(LocatorType.XPATH, itemXpath());
//		seleniumDriver.switchToIframe(LocatorType.XPATH, popUpIframeXpath());
//		unitPriceStr = seleniumDriver.getTextFromElement(LocatorType.ID, unitPriceLabelID());
//		seleniumDriver.enterText(LocatorType.ID, quantityInputID(), String.valueOf(qty));
//		seleniumDriver.clickElement(LocatorType.ID, addToCartButtonID());
//		seleniumDriver.getWebDriver().switchTo().defaultContent();
//		totalPriceStr = seleniumDriver.getTextFromElement(LocatorType.XPATH, totaLabelXpath());

		return new LandingPage(seleniumDriver);
	}

	public LandingPage selectMenuAndSubMenu(int index) {

		seleniumDriver.hoverOverMenuAndClickSubItemIfExist(LocatorType.XPATH, menuItemGenericXpath(index));
		return new LandingPage(seleniumDriver);
	}

}
