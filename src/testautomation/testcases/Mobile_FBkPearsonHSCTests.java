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
public class Mobile_FBkPearsonHSCTests {
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
		
	public static int index=0;
	private static String browserchoice;
//	@RunWith(Parameterized.class)
//	public class TestGoogleBase extends SeleneseTestBase {
	   
	public Mobile_FBkPearsonHSCTests(String opt){
		//index++;
		browserchoice=opt;
		 // System.out.println("index"+index);

	  // System.out.println("opt"+opt);
	  }
	 
	  @Parameters
	   public static Collection<Object[]> data() {
	   
		//Object[][] data = new Object[][] {{"1"},{"2"},{"3"},{"4"},{"5"},{"6"}};
		 Object[][] data = new Object[][] {{"2"},{"3"},{"4"},{"6"}};
		//Object[][] data = new Object[][] {{"2"},{"6"}};
	    return Arrays.asList(data);

	   }
	
	
		private String baseUrl;
		
	private RemoteWebDriver driver = null;
		//static WebDriver driver;}
		
	   Process process=null;
		
		@Before
		public void setUp() 
		{
			
			SetupDriver swd = new SetupDriver(browserchoice);
			driver = swd.setDriver();
			System.out.println("ss");
			Assume.assumeTrue(swd.valid==true);
			System.out.println("after assume");
			LogGenerator lg = new LogGenerator(driver);
			
			
			
			
		}
				
		@After
		public void tearDown() throws Exception 
		{
			
			
			//SetupDriver.exit();
			if(driver!=null)
			driver.quit();
			
			System.out.println("Driver ShutDown");
			//process.destroy();
		}
			
		@Test
		public void HSCTest_Test() throws Exception {
		
			//Launch 
			baseUrl ="http://p-pearson-hsc.herokuapp.com/?qa=true";			
			driver.get(baseUrl + "#/home");	
					
		   //Perform the click operation that opens new window
			driver.findElement(By.xpath("//button[@id=\"facebook-login\"]")).click();
			   synchronized (driver) {
					driver.wait(15000);
					
				}
			   
			   Set <String> handles =driver.getWindowHandles();
			   Iterator<String> it = handles.iterator();
			   //iterate through your windows
			   while (it.hasNext()){
			   String parent = it.next();
			   String newwin = it.next();
			   String browsername = driver.getCapabilities().getBrowserName();
			   System.out.println("Browser Name = "+browsername);
			   if (driver.getCapabilities().getBrowserName().equals("android"))
			   {
			   driver.switchTo().window(parent);
			   }
			   else
			   {
				   driver.switchTo().window(newwin); 
			   }
			
			//Fill facebook login details and login   			
			driver.findElement(By.name("email")).clear();
			driver.findElement(By.name("email")).sendKeys("comprodelhi@gmail.com ");
			driver.findElement(By.name("pass")).clear();
			driver.findElement(By.name("pass")).sendKeys("pearsonhsc");
			driver.findElement(By.name("login")).click();
			//Waiting for new window to open
			synchronized (driver) {
				driver.wait(8000);
				
			}
		    //Applying a condition for "android" and other platform
			 if (driver.getCapabilities().getBrowserName().equals("android"))
			 {
			
				 driver.switchTo().window(newwin);
			
			 } 
			 else
			 {
				 driver.switchTo().window(parent);
			 }
			   
			   }
		
		
	        synchronized (driver) {
				driver.wait(4000);
				
			}
			//Verify discipline page has launched
			//Verify 1st Discipline
			WebElement Discipline1 = driver.findElement(By.xpath("//span[text()=\"Criminal Justice\"]"));
			String str_disc1 = Discipline1.getText();
			System.out.println("Discipline 1 = "+str_disc1);
		
			assertEquals("Criminal Justice", str_disc1);
			
			//Verify Last Discipline
			WebElement Discipline2 = driver.findElement(By.xpath("//span[text()=\"Trades\"]"));
			String str_disc2 = Discipline2.getText();
			System.out.println("Discipline 2 = "+str_disc2);
		
			assertEquals("Trades", str_disc2);
			
			//Open discipline "Criminal Justice"
			
			WebElement flash = driver.findElement(By.xpath("//span[text()=\"Criminal Justice\"]"));
			flash.click();
			

			synchronized (driver) {
				driver.wait(4000);
				
			}	
			//Verify that product page has launched
			//Verify 1st Product
			WebElement Product1 = driver.findElement(By.xpath("//span[text()=\"CJ Today, 12/e\"]"));
			String str_prd1 = Product1.getText();
			System.out.println("Product1 = "+str_prd1);
		
			assertEquals("CJ Today, 12/e", str_prd1);
			
			//Verify Last Product
			WebElement Product2 = driver.findElement(By.xpath("//span[text()=\"Criminal Law Today, 5/e\"]"));
			String str_prod2 = Product2.getText();
			System.out.println("Product 2 = "+str_prod2);
		
			assertEquals("Criminal Law Today, 5/e", str_prod2);
			
			//Open book "Criminal Justice Today, 12e" 
			WebElement flash2 = driver.findElement(By.xpath("//span[text()=\"CJ Today, 12/e\"]"));
			flash2.click();

			
			synchronized (driver) {
				driver.wait(4000);
				
			}
			//Verify that product page has launched.
			WebElement audio3 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[4]/div/div[3]/div[2]/ul/li[3]/a"));
			String str_aud3 = audio3.getText();
			System.out.println("Audio 3 = "+str_aud3);		
			assertEquals("3 Driving Sell Thru", str_aud3);
			
			
			//Launch FlashCards
			
			WebElement flash3 = driver.findElement(By.cssSelector("div.span8 > button"));
			flash3.click();
	
			
			//Select Correct Option in Q#1 
			
			WebElement flash4 = driver.findElement(By.xpath("//span[text()=\"MyCJLab\"]"));
			flash4.click();

			
			synchronized (driver) {
				driver.wait(4000);
				
			}
			//Verify that green check mark is appearing for correct answer.
			
			WebElement CorrectAns = driver.findElement(By.xpath("//span[text()=\"MyCJLab\"]/.."));
			String str = CorrectAns.getAttribute("class");
	
			assertEquals("btn radio-control active correct-ans", str);
						
			//Click on Next button to move Q#2
			
			WebElement flash5 = driver.findElement(By.xpath("//button[text()=\"Next\"]"));
			flash5.click();

				//Clicking on Profile Icon
				WebElement flash1 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
				flash1.click();
				
				//Waiting for profile page to appear
				synchronized (driver) {
					driver.wait(20000);
					
				}
				//Verify the user name
				WebElement username = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div/div/div[2]/div/div/div/div/div/div[2]/p"));
				String User = username.getText();
				System.out.println("User Name="+User);
				//assertEquals("Compro Delhi", User);
				AssertionTest.assertjob(driver, User, "Compro Delhi");
				
				//Verify the email id
				WebElement emailid = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div/div/div[2]/div/div/div/div/div/div[2]/p[2]"));
				String email = emailid.getText();
				System.out.println("Email ID ="+email);
				AssertionTest.assertjob(driver, email, "comprodelhi@gmail.com");
				//assertEquals("comprodelhi@gmail.com", email);
				
				//Searching Logout Button
				/*
				synchronized (driver) {
					driver.wait(15000);
					
				}
				*/
				//Click on Logout Button
				driver.findElement(By.xpath("//*[@id=\"logout-button\"]")).click();
				
				synchronized (driver) {
					driver.wait(4000);}
				
			}
			
			
				}





