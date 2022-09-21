package tests;

import helperObjects.TestBase;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pageObjects.LandingPage;
import utilities.SeleniumDriver;

public class TC1 extends TestBase {
	ExtentHtmlReporter htmlReporter;
	 
    ExtentReports extent;
    //helps to generate the logs in the test report.
    ExtentTest test;
	//private static String fileName = System.getProperty("file.separator");
    @BeforeTest
    public void prepareTest(){
        this.prepare();
        //his.startReport() 
        
    }
    @BeforeTest
    public void startReport() {
        // initialize the HtmlReporter
     //   htmlReporter = new ExtentHtmlReporter("./target/reports/");
    //	"./target/screenshots/
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"./target/screenshots/test.html");
        System.out.println(htmlReporter);
        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
 
 
        //configuration items to change the look and feel
        //add content, manage tests etc
     //  htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
     //   htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
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
    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getTestName());
        }
        else {
            test.log(Status.SKIP, result.getTestName());
        }
    }

    @AfterTest
    public void endTest()
    {
        this.closeBrowser();
    }
    @AfterTest
    public void tearDown() {
        //to write or update test information to reporter
        extent.flush();
    }
}
