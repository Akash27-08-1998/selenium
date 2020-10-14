package com.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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

public class regression extends homePage {
	
	Logger logger = Logger.getLogger(regression.class);
	ExtentReports extent;
	ExtentTest Test;
	
	@BeforeTest(alwaysRun=true)
	public void invokeBrowser() throws IOException, InterruptedException {
		logger.info("================ Launching Browser ==================");
		launchBrowser();
	}
	
	@Test(priority=0)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify webpage title")
	@Story("Story Name: To check automationpractice page title")
	
	public void regression_verifyTitle() {
		extent = new ExtentReports("C:\\Users\\ADMIN\\eclipse-workspace\\Selenium_Project\\test-output\\Extent_RegressionReport.html",true);
		Test = extent.startTest("Verify Title for regression testing");
		Test.log(LogStatus.INFO,"regression_verifyTitle test is initiated");
		
		logger.info("==============Verifying title for regression testing==================");
		String actual = driver.getTitle();
		String expected = "My Store";
		Assert.assertEquals(actual, expected,"The webpage is not Correct");
		Test.log(LogStatus.PASS, "The title of the webpage is verified");
	}
	
	@Test(priority=1)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Signing In")
	@Story("Story Name: To LogIn into my account")
	
	public void step1() throws IOException {
		Test = extent.startTest("Signing In to my Account");
		Test.log(LogStatus.INFO,"step1 test is initiated");
		
		logger.info("================ Signing In ==================");
		signIn();
		Test.log(LogStatus.PASS, "Logged In into my account");
	}
	
	@Test(priority=2)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Selecting a Dress")
	@Story("Story Name: To Select an evening dress")
	
	public void step2() throws InterruptedException {
		Test = extent.startTest("Selecting the Dress");
		Test.log(LogStatus.INFO,"step2 test is initiated");
		
		logger.info("================ Selecting the Dress ==================");
		selectDress();
		Test.log(LogStatus.PASS, "Evening dress selected");
	}
	
	@Test(priority=3)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Selecting the size")
	@Story("Story Name: To Select the size of the dress")
	
	public void step3() {
		Test = extent.startTest("Selecting the Size");
		Test.log(LogStatus.INFO,"step3 test is initiated");
		
		logger.info("================ Selecting the Size ==================");
		selectSize();
		Test.log(LogStatus.PASS, "Evening dress size is selected");
	}
	
	@Test(priority=4)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Adding to the cart")
	@Story("Story Name: To add the item to the cart")
	
	public void step4() {
		Test = extent.startTest("Adding to the Cart");
		Test.log(LogStatus.INFO,"step4 test is initiated");
		
		logger.info("================ Adding to the Cart ==================");
		addToCart();
		Test.log(LogStatus.PASS, "Item added to the cart");
	}
	
	@Test(priority=5)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Checkout page")
	@Story("Story Name: To check the details of the product on the checkout page")
	
	public void step5() throws InterruptedException, IOException {
		Test = extent.startTest("Checking Out from cart");
		Test.log(LogStatus.INFO,"step5 test is initiated");
		
		logger.info("================ Checking out from Cart ==================");
		checkout();
		screenshot ss = new screenshot();
		String imagePath = ss.screen(driver);
		Test.log(LogStatus.PASS, "Checkout Successful");
		Test.log(LogStatus.PASS, Test.addScreenCapture(imagePath));
	}
	
	@Test(priority=6)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Signing out")
	@Story("Story Name: To LogOut from my account")
	
	public void step6() {
		Test = extent.startTest("Signing out");
		Test.log(LogStatus.INFO,"step6 test is initiated");
		
		logger.info("================ Signing Out ==================");
		signOut();
		Test.log(LogStatus.PASS, "Sign out Successful");
	}
	
	@AfterMethod(alwaysRun=true)
	public void flush(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			Test.log(LogStatus.FAIL,"The test case "+result.getName()+" is failed");
			Test.log(LogStatus.FAIL, result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			Test.log(LogStatus.SKIP,"The test case "+result.getName()+" is skipped");
		}
		
		extent.endTest(Test);
		extent.flush();
	}
		
	@AfterTest(alwaysRun=true)
	public void closing() throws InterruptedException {
		logger.info("================ Closing Browser ==================");
		driver.quit();
	}
}
