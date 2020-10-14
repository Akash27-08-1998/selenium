package com.page;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.base.browser;
import com.utilities.pom;
import com.utilities.reading;

import io.qameta.allure.Step;

public class landingPage extends browser {
	public static reading r = new reading();
	
	@Step("Signing In into my account with a registered email ID")
	public void signIn() throws IOException {
		driver.findElement(pom.signIn).click();
		WebElement sign_in = driver.findElement(pom.singInBox);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",sign_in);
		
		String fileName= prop.getProperty("filename");
		String sheetName = prop.getProperty("sheetname");
		String email = r.read(fileName, sheetName, 1, 0);
		String pwd = r.read(fileName, sheetName, 1, 1);
		driver.findElement(pom.email).sendKeys(email);
		driver.findElement(pom.pwd).sendKeys(pwd);
		driver.findElement(pom.login).click();
		
	}
	
	@Step("Checking signIn functionalities")
	public void smokeSignIn() {
		driver.findElement(pom.signIn).click();
		WebElement sign_in = driver.findElement(pom.singInBox);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",sign_in);
		
	}
	
	@Step("Signing In into my account with an unregistered email ID")
	public void NegativeSignIn(String email, String pwd) {
		driver.findElement(pom.signIn).click();
		WebElement sign_in = driver.findElement(pom.singInBox);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",sign_in);
		
		driver.findElement(pom.email).sendKeys(email);
		driver.findElement(pom.pwd).sendKeys(pwd);
		driver.findElement(pom.login).click();
		
		
	}
	
}
