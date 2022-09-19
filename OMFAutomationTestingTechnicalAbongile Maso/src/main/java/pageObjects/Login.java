package pageObjects;

import enumerables.Enums.LocatorType;
import helperObjects.TestBase;
import utilities.SeleniumDriver;

public class Login extends TestBase{

	public Login(SeleniumDriver seleniumDriver)
    {
    	super(seleniumDriver);
    }
	private  String signInButtonXpath()
	{
		return "//div[@class=\"header_user_info\"]/a";
	}
	private  String emailTextFieldID()
    {
    	return "email";
    }
	private  String passTextFieldID()
	{
		return "passwd";
	}
	private  String signInButtonID()
    {
    	return "SubmitLogin";
    }

	public  void navigateToLogin()  {
		seleniumDriver.navigateToPage(appConfig.getSelectedEnvironment().urlUnderTest);
	}

	public Login loginUser(String email, String pass)
	{
		this.navigateToLogin();
		seleniumDriver.clickElement(LocatorType.XPATH, signInButtonXpath());
		seleniumDriver.enterText(LocatorType.ID, emailTextFieldID(), email);
		seleniumDriver.enterText(LocatorType.ID, passTextFieldID(), pass);
		seleniumDriver.clickElement(LocatorType.ID, signInButtonID());

		return new Login(seleniumDriver);
	}
    
    
}
