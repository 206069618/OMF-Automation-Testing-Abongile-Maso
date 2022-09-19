package utilities;

import enumerables.Enums.BrowserTypes;
import enumerables.Enums.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class AppConfig
{
    public Properties properties = new Properties();
    private File appConfig = new File("config.properties");
    private int pageLoadTimeout;
    private int waitTimeout;
    private List<BrowserTypes> browserTypes;
    private String environment;
    public Environment environmentEnum;

    public int getWaitTimeout()
    {
        return waitTimeout;
    }

    public int getPageLoadTimeout()
    {
        return pageLoadTimeout;
    }

    public List<BrowserTypes> getSelectedBrowsers()
    {
        return browserTypes;
    }
    public Environment getSelectedEnvironment()
    {
        return environmentEnum;
    }

    public AppConfig()
    {
        try
        {
            this.loadExistingConfigurationFile();
            this.loadConfigurationSettings();
        }
        catch(Exception e)
        {
            System.out.println("AppConfig method failed");
        }

    }

    private void loadConfigurationSettings()
    {
        try
        {
            //Get properties from configuration file
            pageLoadTimeout = Integer.parseInt(properties.getProperty("PageLoadTimeout"));
            waitTimeout = Integer.parseInt(properties.getProperty("waitTimeout"));
            browserTypes = resolveBrowserType();
            environment = properties.getProperty("Environment");

            //Determine run environment to execute tests against
            environmentEnum = resolveEnvironment();
        }
        catch(Exception e)
        {
            System.out.println("loadConfigurationSettings method failed");
        }
    }

    private Environment resolveEnvironment()
    {

        if ("DEV".equalsIgnoreCase(environment)) {
            return Environment.Dev;
        } else if ("UAT".equalsIgnoreCase(environment)) {
            return Environment.UAT;
        } else if ("PROD".equalsIgnoreCase(environment)) {
            return Environment.Prod;
        }
        return Environment.Dev;
    }

    private List<BrowserTypes> resolveBrowserType()
    {
        String[] configBrowserTypes = properties.getProperty("BrowserType").split(":");
        List<BrowserTypes> browsers = new ArrayList<BrowserTypes>();

        for(String browser : configBrowserTypes)
        {
            if ("CHROME".equalsIgnoreCase(browser)) {
                browsers.add(BrowserTypes.Chrome);
            } else if ("FIREFOX".equalsIgnoreCase(browser)) {
                browsers.add(BrowserTypes.FireFox);
            } else {
                browsers.add(BrowserTypes.Chrome);
            }
        }

        return browsers;
    }

    private boolean loadExistingConfigurationFile()
    {
        try
        {
            if(properties == null)
            {
                properties = new Properties();
            }

            properties.load(new FileInputStream(appConfig.getAbsolutePath()));
            return true;
        }
        catch(IOException e)
        {
            System.out.println("loadExistingConfigurationFile method failed");
            return false;
        }
    }



}


