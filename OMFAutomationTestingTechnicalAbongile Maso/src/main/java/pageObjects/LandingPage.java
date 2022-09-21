package pageObjects;

import enumerables.Enums.LocatorType;
import helperObjects.TestBase;
import utilities.SeleniumDriver;

public class LandingPage extends TestBase {

	public LandingPage(SeleniumDriver seleniumDriver) {
		super(seleniumDriver);
	} 
	/**
	 * 
	 * @name: WebLocators
	 * @param :
	 * @author Abongile Maso
	 ***/
	private String PersonalLoanPageP_LINK_TEXT() {
		return "personal loans";
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

	private String HowMuchDoYouNeedDrpCss() {
		return "om-wc-config.hydrated om-page.hydrated div.theme-default.om-page-container om-application-container.hydrated:nth-child(2) div.application-container-calculator-layout om-1-col-layout.hydrated div.om-1-col-layout om-section.hydrated div.theme-default.om-section div.section-slots-wrapper div.section__section-wrapper om-personal-loans-calculator.hydrated div.theme-default.om-personal-loans-calculator om-form-dropdown-field-wrapper.hydrated div.om-form-dropdown-field-container div.om-form-dropdown-field-wrapper.left div.om-form-dropdown-field-inner-wrapper om-form-dropdown-field.hydrated div.theme-default.om-form-dropdown-component.native-mobile > div.dark-grey-border";
	}

	private String HowMuchDoYouNeedValueXpath() {
		return "//li[text()='R50 000']";
	}

	private String NextButtonXpath() {
		return "/html[1]/body[1]/div[1]/div[1]/om-wc-config[1]/div[1]/om-page[1]/div[1]/div[1]/main[1]/om-application-container[1]/div[1]/om-1-col-layout[1]/div[1]/om-section[1]/div[1]/div[1]/div[2]/div[1]/om-personal-loans-calculator[1]/div[1]/div[2]/div[1]/div[1]/om-button[1]";
	}

	private String HowLongDoYouNeedToRepayDrpCss() {
		return "om-wc-config.hydrated om-page.hydrated div.theme-default.om-page-container om-application-container.hydrated:nth-child(2) div.application-container-calculator-layout om-1-col-layout.hydrated div.om-1-col-layout om-section.hydrated div.theme-default.om-section div.section-slots-wrapper div.section__section-wrapper om-personal-loans-calculator.hydrated div.theme-default.om-personal-loans-calculator om-form-dropdown-field-wrapper.hydrated div.om-form-dropdown-field-container div.om-form-dropdown-field-wrapper.left div.om-form-dropdown-field-inner-wrapper om-form-dropdown-field.hydrated div.theme-default.om-form-dropdown-component.native-mobile > div.dark-grey-border";
	}

	private String MonthsXpath() {
		return "//li[@id='60 Months']";
	}

	private String CalculateBtnCss() {
		return "om-wc-config.hydrated om-page.hydrated div.theme-default.om-page-container om-application-container.hydrated:nth-child(2) div.application-container-calculator-layout om-1-col-layout.hydrated div.om-1-col-layout om-section.hydrated div.theme-default.om-section div.section-slots-wrapper div.section__section-wrapper om-personal-loans-calculator.hydrated div.theme-default.om-personal-loans-calculator div.navigation-button-result-wrapper:nth-child(2) div.navigation-button-wrapper:nth-child(1) div:nth-child(1) div.continue-section om-button.hydrated > button.theme-default.primary-large";
	}

	
	/**
	 * 
	 * @name: Scenario
	 * @purpose: Test Scenario
	 * @param :
	 * @author Abongile Maso
	 ***/
	public void navigate() {
		// Browse to Old Mutual Finance Web site (https://www.oldmutualfinance.co.za/)
		// and
		seleniumDriver.navigateToPage(appConfig.getSelectedEnvironment().urlUnderTest);
		// verify that the title is Bank and Borrow Solutions | Old Mutual.
		seleniumDriver.RetriveTitleAndCompare("Bank and Borrow Solutions | Old Mutual");
	}

	public LandingPage selectPesornalLoad() {
		// Navigate to PERSONAL LOANS page
		seleniumDriver.hoverOverMenuAndClickSubItem(LocatorType.XPATH, OurSolutionMenuItemXpath(), PesonalLoanCss());
		// verify that you are on the correct page.
		seleniumDriver.WaitforElement(LocatorType.XPATH, PersonalLoanPageP_LINK_TEXT());

		// Navigate to CALCULATE, 
		seleniumDriver.clickElement(LocatorType.CSS, CalculateLoanCss());
		
		
		// select R50 000 from the dropdown then click next
		seleniumDriver.SwitchTabs();
		seleniumDriver.ScrolToElementAndClick(LocatorType.CSS, HowMuchDoYouNeedDrpCss());
		seleniumDriver.ScrolToElementAndClick(LocatorType.XPATH, HowMuchDoYouNeedValueXpath());
		seleniumDriver.clickElement(LocatorType.XPATH, NextButtonXpath());
		
		//Select 60 MONTHS from the “How long do you need to repay it?” dropdown and click CALCULATE.
		seleniumDriver.SwitchTabs();
		seleniumDriver.clickElement(LocatorType.CSS, HowLongDoYouNeedToRepayDrpCss());
		seleniumDriver.clickElement(LocatorType.XPATH, MonthsXpath());
		seleniumDriver.clickElement(LocatorType.CSS, CalculateBtnCss());
		
		
		//Validate the amounts R1656.43 and R1810.05 under the RESULT SUMMARY section.
		
		return new LandingPage(seleniumDriver);
	}

	
}
