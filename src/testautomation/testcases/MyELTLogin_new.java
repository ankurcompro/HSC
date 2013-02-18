package testautomation.testcases;

import junit.framework.TestCase;  
import org.openqa.selenium.*;  
import org.openqa.selenium.remote.*;  
import java.net.URL;  
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
//import static org.testng.Assert.*;
import java.awt.AWTException;
import java.awt.Choice;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.server.SeleniumServer;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;


import testautomation.framework.AssertionTest;
import testautomation.framework.CaptureScreenshot;
import testautomation.framework.LogGenerator;
import testautomation.framework.SetupDriver;

import org.junit.Assume.*;



import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

@RunWith(Parameterized.class)
public class MyELTLogin_new {
		
	public static int index=0;
	
	private static String browserchoice;
	private String baseUrl;
	private RemoteWebDriver driver = null;
	
	//static WebDriver driver;
	Process process=null;
	   
	public MyELTLogin_new(String opt){
		browserchoice=opt;
		// System.out.println("opt"+opt);
	  }
		 
	@Parameters
	public static Collection<Object[]> data() {	   
		   //Object[][] data = new Object[][] {{"1"},{"2"},{"3"},{"4"},{"5"},{"6"}};		   
		   Object[][] data = new Object[][] {{"2"}};
		  // Object[][] data = new Object[][] {{"6"},{"3"},{"4"},{"2"}};
		   return Arrays.asList(data);
	   }		
	
	@Before
	public void setUp() 
	{	
		SetupDriver swd = new SetupDriver(browserchoice);
		driver = swd.setDriver();
		
		//Assume.assumeTrue(swd.valid==true);
		//System.out.println("after assume");
		
		LogGenerator lg = new LogGenerator(driver);
	}
				
	@After
	public void tearDown() throws Exception 
	{	
		if(driver!=null)
			driver.quit();	
		
		System.out.println("Driver ShutDown");	
	}
			
	@Test
	  public void AdmintestMyeltLogin() throws Exception {
		  
		baseUrl ="http://myelt2.comprotechnologies.com/";
		driver.get(baseUrl);
		
	    //driver.get("http://myelt2.comprotechnologies.com/");
	    //assertEquals("MyELT | Online English Language Learning", driver.getTitle());
	   
	    
	    driver.findElement(By.id("loginDisplayField")).clear();
	    driver.findElement(By.id("loginDisplayField")).sendKeys("admin_compro@myelt.com");
	    driver.findElement(By.id("passwordDisplayField")).clear();
	    driver.findElement(By.id("passwordDisplayField")).sendKeys("thomson");
	    driver.findElement(By.name("submit")).click();
	    //assertEquals("MyELT System Check", driver.getTitle());
	    
	    synchronized (driver) {
			driver.wait(8000);				
		}
	    
	   //assertEquals("MyELT | Online English Language Learning | Home", driver.getTitle());
	   
	 //Verify ‘Home’ tab is present on the page.
	 		WebElement home = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/div[2]/span[2]"));
	 		String str_home = home.getText();
	 		System.out.println("TAB = "+str_home);
	 		AssertionTest.assertjob(driver, str_home, "Home");
	   
	
	 		
	   driver.findElement(By.linkText("Sign Out")).click();	   
	   
	   //Verify the “Username” field to ensure that user has been logged out.
	   WebElement username = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/div[2]/div/div/div/table/tbody/tr/td/table/tbody/tr/td/div/div/form/table/tbody/tr/td/div"));
	   String str_username = username.getText();
		System.out.println("Logout = "+str_username);
		AssertionTest.assertjob(driver, str_username, "Username");
	    //assertEquals("MyELT | Online English Language Learning", driver.getTitle());
	    //assertEquals("MyELT | Online English Language Learning", driver.getTitle());
	  }
	
	}





