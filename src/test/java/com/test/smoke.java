package com.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
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
import com.utilities.pom;
import com.utilities.screenshot;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class smoke extends homePage {
	Logger logger = Logger.getLogger(smoke.class);
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest(alwaysRun=true)
	public void invokeBrowser() throws IOException, InterruptedException {
		logger.info("================ Launching Browser ==================");
		launchBrowser();
	}
	
	@Test(priority=0)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify webpage title")
	@Story("Story Name: To check automationpractice page title")
	
	public void smoke_verifyTitle() {
		extent = new ExtentReports("C:\\Users\\ADMIN\\eclipse-workspace\\Selenium_Project\\test-output\\Extent_SmokeTestReport.html",true);
		test = extent.startTest("Verify Title for smoke testing");
		test.log(LogStatus.INFO,"smoke_verifyTitle test is initiated");
		
		logger.info("==============Verifying title for smoke testing==================");
		String actual = driver.getTitle();
		String expected = "My Store";
		Assert.assertEquals(actual, expected,"The webpage is not Correct");
		test.log(LogStatus.PASS, "The title of the webpage is verified");
	}
	
	@Test(priority=1)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if SignIn button is enabled")
	@Story("Story Name: To check SignIn button")
	
	public void smoke0() {
		test = extent.startTest("Verify SignIn button for smokeTest");
		test.log(LogStatus.INFO,"smoke0 test is initiated");
		
		logger.info("============== Checking the SignIn button ==================");
		Assert.assertTrue(driver.findElement(pom.signIn).isEnabled(),"The SignIn button is not enabled");
		if(driver.findElement(pom.signIn).isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(pom.signIn));
		}
		System.out.println();
		System.out.println("Is SignIn button enabled? "+driver.findElement(pom.signIn).isEnabled());
		System.out.println();
		test.log(LogStatus.PASS, "The SignIn button is verified");
		smokeSignIn();
	}
	
	@Test(priority=2)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if email textbox is enabled")
	@Story("Story Name: To check email textbox")
	
	public void smoke1() {
		test = extent.startTest("Verify email textbox for smokeTest");
		test.log(LogStatus.INFO,"smoke1 test is initiated");
		
		logger.info("============== Checking the email textbox ==================");
		Assert.assertTrue(driver.findElement(pom.email).isEnabled(),"The email textbox is not enabled");
		if(driver.findElement(pom.email).isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(pom.email));
		}
		System.out.println();
		System.out.println("Is email textbox enabled? "+driver.findElement(pom.email).isEnabled());
		System.out.println();
		test.log(LogStatus.PASS, "The email textbox is verified");
	}
	
	@Test(priority=3)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Checking if password textbox is enabled")
	@Story("Story Name: To check password textbox")
	
	public void smoke2() throws InterruptedException, IOException {
		test = extent.startTest("Verify password textbox for smokeTest");
		test.log(LogStatus.INFO,"smoke2 test is initiated");
		
		logger.info("============== Checking the password textbox ==================");
		Assert.assertTrue(driver.findElement(pom.pwd).isEnabled(),"The password textbox is not enabled");
		if(driver.findElement(pom.pwd).isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid green'",driver.findElement(pom.pwd));
		  }
		Thread.sleep(2000);
		screenshot ss = new screenshot();
		String imagePath = ss.screen(driver);
		System.out.println();
		System.out.println("Is password textbox enabled? "+driver.findElement(pom.pwd).isEnabled());
		System.out.println();
		test.log(LogStatus.PASS, "The password textbox is verified");
		test.log(LogStatus.PASS, test.addScreenCapture(imagePath));
	}
	
	@AfterMethod(alwaysRun=true)
	public void flush(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
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
