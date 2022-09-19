package tests;

import helperObjects.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import utilities.SeleniumDriver;

public class TC1 extends TestBase {
    @BeforeTest
    public void prepareTest(){
        this.prepare();
    }

    @Test()
    public class testetsng {
        @Test()
        public void test() {
            LandingPage landingPage = new LandingPage(seleniumDriver);
            landingPage.navigate();
            landingPage.selectPesornalLoad();
        }
    }

    @AfterTest
    public void endTest()
    {
        this.closeBrowser();
    }
}
