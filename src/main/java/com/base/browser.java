package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.qameta.allure.Step;

public class browser {
	
	public static WebDriver driver;
	protected static Properties prop;
	static FileInputStream fis;
	public static String hubURL = "http://localhost:4444/wd/hub";
	
	@Step("Launching the browser step....")
	public static void launchBrowser() throws IOException, InterruptedException  {
		
	try {
			fis=new FileInputStream("C:\\Users\\ADMIN\\eclipse-workspace\\Selenium_Project\\config-file\\config.properties");
			prop=new Properties();
			prop.load(fis);
		}
		catch(FileNotFoundException e) {
			
			System.out.println(e.getMessage());
		}
	
	//running the script in selenium grid with firefox browser
	if(prop.getProperty("mode").equalsIgnoreCase("grid")) {
		if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("marionette", true);
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(hubURL),cap);
		 }
	
		//running the script in selenium grid with chrome browser
		else if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);
			
			ChromeOptions op = new ChromeOptions();
			op.merge(cap);
			
			driver = new RemoteWebDriver(new URL(hubURL),op);
			
		  }
		
		//running the script in selenium grid with IE browser
		else if(prop.getProperty("browser").equalsIgnoreCase("IE")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("internet explorer");
			cap.setPlatform(Platform.WINDOWS);
						
			driver = new RemoteWebDriver(new URL(hubURL),cap);
			
		 }
		
		else {
			   System.out.println("Please choose a supported Browser");
			 }
	
	   }
	
	//running the script in selenium with firefox browser
	else if(prop.getProperty("mode").equalsIgnoreCase("local")) {
	
		if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", prop.getProperty("driverpath")+"geckodriver.exe");
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("marionette", true);
			driver=new FirefoxDriver();
		}
	
		//running the script in selenium with chrome browser
		else if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", prop.getProperty("driverpath")+"chromedriver.exe");
			driver=new ChromeDriver();
	       }
		
		//running the script in selenium with IE browser
		else if(prop.getProperty("browser").equalsIgnoreCase("IE")) {
			
			System.setProperty("webdriver.ie.driver", prop.getProperty("driverpath")+"IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		   }
		
		else {
			    System.out.println("Please choose a supported browser");
			 }
        }
	
	else {
		    System.out.println("Please choose a supported mode");
	     }
	
	driver.get(prop.getProperty("url"));
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
}
