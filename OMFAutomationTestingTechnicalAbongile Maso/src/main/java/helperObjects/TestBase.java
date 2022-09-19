package helperObjects;

import enumerables.Enums.BrowserTypes;
import utilities.AppConfig;
import utilities.SeleniumDriver;

import java.util.ArrayList;
import java.util.List;

public abstract class TestBase
{
    protected static SeleniumDriver seleniumDriver;
    protected static AppConfig  appConfig = new AppConfig();;

    public TestBase ()
    {

    }

    public TestBase (SeleniumDriver seleniumDriver)
    {

        TestBase.seleniumDriver = seleniumDriver;
    }

    public void prepare()
    {
        try
        {
            BrowserTypes browserType = appConfig.getSelectedBrowsers().get(0);

            switch(browserType)
            {
                case Chrome:
                case FireFox:
                    seleniumDriver = new SeleniumDriver( browserType);
                    break;

                default:
            }

        }
        catch(Exception e)
        {
            throw(e);
        }
    }

    public void closeBrowser()
    {
        seleniumDriver.closeBrowser();
    }
}

