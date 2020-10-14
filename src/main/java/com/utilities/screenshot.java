package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenshot {
	public String screen(WebDriver driver) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ScrObj = (TakesScreenshot) driver;
		File CaptureImg = ScrObj.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(CaptureImg, new File("C:\\Users\\ADMIN\\eclipse-workspace\\Selenium_Project\\ScreenShots\\"+timeStamp+"_pic.png"));
		
		String screenshot ="C:\\Users\\ADMIN\\eclipse-workspace\\Selenium_Project\\ScreenShots\\"+timeStamp+"_pic.png" ;
		return screenshot;
	}
}
