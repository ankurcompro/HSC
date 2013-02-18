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
import java.util.List;
import java.util.Random;
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
public class Admin_PearsonHSCTests {
	
	
	
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
		
	public static int index=0;
	private static String browserchoice;
//	@RunWith(Parameterized.class)
//	public class TestGoogleBase extends SeleneseTestBase {
	   
	public Admin_PearsonHSCTests(String opt){
		//index++;
		browserchoice=opt;
		 // System.out.println("index"+index);

	  // System.out.println("opt"+opt);
	  }
		 
 
		 
	  @Parameters
	   public static Collection<Object[]> data() {
	   
	  //Object[][] data = new Object[][] {{"1"},{"2"},{"3"},{"4"},{"5"},{"6"}};
		   //Object[][] data = new Object[][] {{"2"},{"3"},{"4"},{"6"}};
	 Object[][] data = new Object[][] {{"2"}};
	    return Arrays.asList(data);

	   }
	
	
		private String baseUrl;
		
	private RemoteWebDriver driver = null;
		//static WebDriver driver;
		
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
		
			//Launch and Login
			//baseUrl ="http://d1-pearson-hsc.herokuapp.com/?qa=true";
			baseUrl ="http://p-pearson-hsc.herokuapp.com/?qa=true";
			driver.get(baseUrl + "/#/home");		
			driver.findElement(By.name("j_username")).clear();
			driver.findElement(By.name("j_username")).sendKeys("admin@compro.com");
			driver.findElement(By.name("j_password")).clear();
			driver.findElement(By.name("j_password")).sendKeys("admin");
			driver.findElement(By.id("login-button")).click();
			
			synchronized (driver) {
				driver.wait(4000);
				
			}
			//Verify discipline page has launched
			//Verify 1st Discipline
			WebElement Discipline1 = driver.findElement(By.xpath("//span[text()=\"Criminal Justice\"]"));
			String str_disc1 = Discipline1.getText();
			System.out.println("Discipline 1 = "+str_disc1);
	//		AssertionTest.assertjob(driver, str_disc1, "Criminal Justice");
	//		String job = SetupDriver.job.toString();
		//	System.out.println("https://saucelabs.com/tests/"+job);
			//assertEquals("Criminal Justice", str_disc1);
			
			//Verify Last Discipline
			WebElement Discipline2 = driver.findElement(By.xpath("//span[text()=\"Trades\"]"));
			String str_disc2 = Discipline2.getText();
			System.out.println("Discipline 2 = "+str_disc2);
		
			AssertionTest.assertjob(driver, str_disc2, "Trades");
			//assertEquals("Trades", str_disc2);
			
			//Click on Tools option
			WebElement Logsicon = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li/a/i"));
			Logsicon.click();
			
			synchronized (driver) {
				driver.wait(4000);}
			
			//Verify a popup appear with version number
			WebElement logs_popup = driver.findElement(By.xpath("//*[@id=\"ajax-error-label\"]"));
			String str_logs_popup = logs_popup.getText();
			System.out.println("PopUp = "+str_logs_popup);
			//Version verification for d1
			AssertionTest.assertjob(driver, str_logs_popup, "HSC-APP version 52");
			
			//Version verification for production site
			//AssertionTest.assertjob(driver, str_logs_popup, "HSC-APP version 53");
			
			
			//Click 'ok' to exit logs
			WebElement exitlogs = driver.findElement(By.xpath("/html/body/div/div[3]/button[2]"));
			exitlogs.click();
			synchronized (driver) {
				driver.wait(3000);
				
			}
			
				//Clicking on Profile Icon
				//For prod
				//WebElement flash1 = driver.findElement(By.xpath("/html/body/div[4]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
				//For d1
				WebElement flash1 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
				flash1.click();
				synchronized (driver) {
					driver.wait(15000);
					
				}
				//Verify the user name
				WebElement username = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div/div/div[2]/div/div/div/div/div/div[2]/p"));
				String User = username.getText();
				System.out.println("User Name = "+User);
				AssertionTest.assertjob(driver, User, "Compro Admin");
				
				//Click on Manage Accounts button
				WebElement manageaccount = driver.findElement(By.xpath("//*[@id=\"user-button\"]"));
				manageaccount.click();
				
				//Verify label "Manage Accounts"
				WebElement labelmanacc = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div/div/ul/li/h2"));
				String labelmanacc_text = labelmanacc.getText();
				System.out.println("Label for Manage Account page = "+labelmanacc_text);
				AssertionTest.assertjob(driver, labelmanacc_text, "Manage Accounts");
				
				//Click on Add new user
				WebElement addnewuser = driver.findElement(By.xpath("//*[@id=\"addNewUser\"]"));
				addnewuser.click();
				
				//Verify label 'New user'
				WebElement newuser = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div/div/ul/li[2]/h2"));
				String newuser_text = newuser.getText();
				System.out.println("Label for New User page = "+newuser_text);
				AssertionTest.assertjob(driver, newuser_text, "New user");
				
				//Entering User Details
				driver.findElement(By.name("username")).clear();
				driver.findElement(By.name("username")).sendKeys("comprotest");
				driver.findElement(By.name("password")).clear();
				driver.findElement(By.name("password")).sendKeys("comprotest");
				driver.findElement(By.name("firstName")).clear();
				driver.findElement(By.name("firstName")).sendKeys("Compro");
				driver.findElement(By.name("lastName")).clear();
				driver.findElement(By.name("lastName")).sendKeys("Test");
				driver.findElement(By.id("adminAddUser")).click();
				
				

				synchronized (driver) {
					driver.wait(8000);}
				
				//Verify user on page
				WebElement usercheck = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/table/tbody//tr/td[2]/div/div/small"));
				String usercheck_text = usercheck.getText();
				System.out.println("USERNAME = "+usercheck_text);
				//AssertionTest.assertjob(driver, usercheck_text, "New user");
				
				while(!usercheck_text.contains("(comprotest)"))
				{
					
					//Verify 'Next' button is enabled or not
					WebElement nextbutton = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/ul/li[2]"));
					String str = nextbutton.getAttribute("class");
					System.out.println("current state of next button= "+str);
					
					if(str=="next disabled")
					{
						System.out.println("user not created");
					}
					else
					{
						//Click on Next button
						WebElement clicknext = driver.findElement(By.xpath("//*[@id=\"nxtPage\"]"));
						clicknext.click();
					}
					
					WebElement usercheck1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/table/tbody//tr/td[2]/div/div/small"));
					String usercheck1_text = usercheck1.getText();
					System.out.println("USERNAME = "+usercheck1_text);
					
					usercheck_text=usercheck1_text;
				}
				
				
				
				/*
				//For prod (p) site
				//Verify 'Next' button is enabled or not
				WebElement nextbutton = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/ul/li[2]"));
				String str = nextbutton.getAttribute("class");
				System.out.println("current state of next button= "+str);
								
				//Applying 'while' condition.
				while (str.equals("next"))
				  {
					//Click on Next button
					WebElement clicknext = driver.findElement(By.xpath("//*[@id=\"nxtPage\"]"));
					clicknext.click();
					
					synchronized (driver) {
						driver.wait(5000);}
					
					WebElement nextbutton1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/ul/li[2]"));
					String str1 = nextbutton1.getAttribute("class");
					System.out.println("current state of next button= "+str1);
							
					str=str1;
					
					System.out.println("inside while lop");
				  }
				   
				System.out.println("Outside while lop");
				
				synchronized (driver) {
					driver.wait(5000);}
				*/
				
				/*
				//Verify New user cell
				WebElement newusercreated = driver.findElement(By.xpath("//small[contains(.,\"comprotest\")]"));
				String newusercreated_text = newusercreated.getText().trim();
				System.out.println("new username = "+newusercreated_text);
				AssertionTest.assertjob(driver, newusercreated_text, "(comprotest)");
					*/
				
				//Edit User		
				WebElement editicon=driver.findElement(By.xpath("//small[contains(.,\"comprotest\")]/../../../../td/a[1]/i"));
				editicon.click();
				synchronized (driver) {
					driver.wait(5000);
					
				}			
				driver.findElement(By.name("lastName")).clear();
				driver.findElement(By.name("lastName")).sendKeys("Test1");
				driver.findElement(By.id("adminEditUser")).click();
				
				//Delete User
				
				WebElement DelIcon=driver.findElement(By.xpath("//small[contains(.,\"comprotest\")]/../../../../td/a[2]/i"));
				DelIcon.click();
				synchronized (driver) {
					driver.wait(5000);
					
				}			
				driver.findElement(By.id("adminDeleteUser")).click();
				synchronized (driver) {
					driver.wait(5000);
					
				}	
				//Click on profile icon
				WebElement flashprofile = driver.findElement(By.xpath("/html/body/div[4]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
				flashprofile.click();
				
				synchronized (driver) {
					driver.wait(15000);}
				
				//Click on Browse Discipline button
				WebElement BrowseDiscipline = driver.findElement(By.xpath("//*[@id=\"discipline-button\"]"));
				BrowseDiscipline.click();
				
				synchronized (driver) {
					driver.wait(15000);}
				
				//Verify discipline page has launched
				//Verify 1st Discipline
				WebElement Discipline_1 = driver.findElement(By.xpath("//span[text()=\"Criminal Justice\"]"));
				String str_disc_1 = Discipline_1.getText();
				System.out.println("Discipline 1 = "+str_disc_1);
				AssertionTest.assertjob(driver, str_disc_1, "Criminal Justice");
		//		String job = SetupDriver.job.toString();
			//	System.out.println("https://saucelabs.com/tests/"+job);
				//assertEquals("Criminal Justice", str_disc1);
				
				//Verify Last Discipline
				WebElement Discipline_2 = driver.findElement(By.xpath("//span[text()=\"Trades\"]"));
				String str_disc_2 = Discipline_2.getText();
				System.out.println("Discipline 2 = "+str_disc_2);
			
				AssertionTest.assertjob(driver, str_disc_2, "Trades");
				
				//Click on profile icon
				WebElement flashprofile1 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
				flashprofile1.click();
				//Searching Logout Button
				synchronized (driver) {
					driver.wait(10000);
					
				}
				
				//Click on Logout Button
				driver.findElement(By.xpath("//*[@id=\"logout-button\"]")).click();
				
				synchronized (driver) {
					driver.wait(4000);}
				
			}
			
			
				}





