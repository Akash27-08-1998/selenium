package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.page.homePage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.screenshot;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class negative extends homePage {
	Logger logger = Logger.getLogger(smoke.class);
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest(alwaysRun=true)
	public void invokeBrowser() throws IOException, InterruptedException {
		logger.info("================ Launching Browser ==================");
		launchBrowser();
	}
	
	@DataProvider
	public Iterator<Object[]> getData() throws IOException {
		ArrayList<Object[]> data = com.utilities.getData.fetchData("NegativeTest.xlsx", "Sheet1");
		return data.iterator();
	}
	
	@Test(dataProvider="getData",priority=0)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify SigningIn")
	@Story("Story Name: To check user has signed in")
	public void signIn(String email,String password) {
		extent = new ExtentReports("C:\\Users\\ADMIN\\eclipse-workspace\\Selenium_Project\\test-output\\Extent_NegativeTestReport.html",true);
		test = extent.startTest("Verify SignIn with unregistered emil ID");
		test.log(LogStatus.INFO,"signIn test is initiated");
		
		logger.info("================ Signing In ==================");
		NegativeSignIn(email,password);
		String actual = driver.getTitle();
		String expected = "My account - My Store";
		Assert.assertEquals(actual, expected,"The webpage is not Correct");
		test.log(LogStatus.PASS, "The user Signed in into the account");
	}
	
		
	@AfterMethod(alwaysRun=true)
	public void flush(ITestResult result) throws IOException {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			screenshot ss = new screenshot();
			String imagePath = ss.screen(driver);
			test.log(LogStatus.FAIL, test.addScreenCapture(imagePath));
			test.log(LogStatus.FAIL,"The test case "+result.getName()+" is failed");
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(LogStatus.SKIP,"The test case "+result.getName()+" is skipped");
		}
		
		extent.endTest(test);
		extent.flush();
	}
	
	@AfterTest(alwaysRun=true)
	public void closing() throws InterruptedException {
		logger.info("================ Closing Browser ==================");
		driver.quit();
	}
}
