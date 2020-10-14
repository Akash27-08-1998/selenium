package com.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.utilities.pom;

import io.qameta.allure.Step;

public class homePage extends landingPage {

	@Step("Selecting the dress step....")
	public void selectDress() throws InterruptedException {
		WebElement dress_tab = driver.findElement(pom.dress);
		Actions ac = new Actions(driver);
		ac.moveToElement(dress_tab).perform();
		
		WebElement eve_dress = driver.findElement(pom.eveDress);
		ac.moveToElement(eve_dress).click().perform();
		driver.findElement(pom.image).click();
		Thread.sleep(3000);
	}
	
	@Step("Selecting the Size of dress step....")
	public void selectSize() {
		driver.switchTo().frame(0);
		WebElement drop_down = driver.findElement(pom.dropdown);
		Select s = new Select(drop_down);
		s.selectByVisibleText("M");
	}
	
	@Step("Add the item to the cart step....")
	public void addToCart() {
		driver.findElement(pom.addToCart).click();
		driver.switchTo().defaultContent();
	}
	
	@Step("Verifying checkout page step....")
	public void checkout() throws InterruptedException {
		
		    driver.findElement(pom.checkout).click();
		    WebElement summ = driver.findElement(pom.scroll);
		     JavascriptExecutor jss = (JavascriptExecutor) driver;
			jss.executeScript("arguments[0].scrollIntoView()",summ);
			Thread.sleep(2000);
		}
	
	@Step("Signing out from my account")
	public void signOut() {
		WebElement sign_out = driver.findElement(pom.signOut);
		JavascriptExecutor jss = (JavascriptExecutor) driver;
		jss.executeScript("arguments[0].scrollIntoView()",sign_out);
		jss.executeScript("arguments[0].click()", sign_out);
	}
}
