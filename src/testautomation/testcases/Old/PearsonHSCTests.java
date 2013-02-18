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
public class PearsonHSCTests {
	
	
	
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
		
	public static int index=0;
	private static String browserchoice;
//	@RunWith(Parameterized.class)
//	public class TestGoogleBase extends SeleneseTestBase {
	   
	public PearsonHSCTests(String opt){
		//index++;
		browserchoice=opt;
		 // System.out.println("index"+index);

	  // System.out.println("opt"+opt);
	  }
		 
 
		 
	  @Parameters
	   public static Collection<Object[]> data() {
	   
	  //Object[][] data = new Object[][] {{"1"},{"2"},{"3"},{"4"},{"5"},{"6"}};
	 Object[][] data = new Object[][] {{"2"},{"3"},{"4"},{"6"}};
	 //Object[][] data = new Object[][] {{"2"}};
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
			driver.get(baseUrl + "#/home");		
			driver.findElement(By.name("j_username")).clear();
			driver.findElement(By.name("j_username")).sendKeys("hsc");
			driver.findElement(By.name("j_password")).clear();
			driver.findElement(By.name("j_password")).sendKeys("breakthrough");
			driver.findElement(By.id("login-button")).click();
			
			synchronized (driver) {
				driver.wait(8000);
				
			}
			//Verify discipline page has launched
			//Verify 1st Discipline
			WebElement Discipline1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/span"));
			String str_disc1 = Discipline1.getText();
			System.out.println("Discipline_1 = "+str_disc1);
			AssertionTest.assertjob(driver, str_disc1, "Criminal Justice");
			//String job = SetupDriver.job.toString();
			//System.out.println("https://saucelabs.com/tests/"+job);
			//assertEquals("Criminal Justice", str_disc1);
			
			//Verify Last Discipline
			WebElement Discipline2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div[4]/div/div/span"));
			String str_disc2 = Discipline2.getText();
			System.out.println("Discipline_2 = "+str_disc2);
			AssertionTest.assertjob(driver, str_disc2, "Trades");
			//assertEquals("Trades", str_disc2);
			
			//Open discipline "Criminal Justice"
			
			WebElement flash = driver.findElement(By.xpath("//span[text()=\"Criminal Justice\"]"));
			flash.click();
			
			synchronized (driver) {
				driver.wait(4000);}
			
			//Verify that product page has launched
			//Verify 1st Product
			WebElement Product1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div/div/div/div/span"));
			String str_prd1 = Product1.getText();
			System.out.println("Product_1 = "+str_prd1);
			AssertionTest.assertjob(driver, str_prd1, "CJ Today, 12/e");
			//assertEquals("CJ Today, 12/e", str_prd1);
			
			//Verify Last Product
			WebElement Product2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div[4]/div[2]/div/div/span"));
			String str_prod2 = Product2.getText();
			System.out.println("Product_2 = "+str_prod2);
			AssertionTest.assertjob(driver, str_prod2, "Criminal Law Today, 5/e");
			//assertEquals("Criminal Law Today, 5/e", str_prod2);
				
			
			//Click on 'Play All'
			WebElement ClickPlayAll = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div/div/div/button"));
			ClickPlayAll.click();
			
			
			synchronized (driver) {
				driver.wait(4000);}
			/*
			//Ensure 'Play All' has launched
			WebElement drivingcellthrough = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div/div/div/div[2]/ul/li[3]/a"));
			String drivingcellthrough_text = drivingcellthrough.getText();
			System.out.println("Audio text = "+drivingcellthrough_text);
			
			
			//Ensure 'Play All' functionality has launched
			WebElement abc = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div/div/div/button/span"));
			String def = abc.getText();
			System.out.println("Button = "+def);
			*/
			
			//Ensure 'Play All' functionality has launched			
			WebElement TheStory = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div/div/div/div[2]/ul/li"));
			String str_class = TheStory.getAttribute("class");
			System.out.println("State of Play All functionality = "+str_class);
			AssertionTest.assertjob(driver, str_class, "sm2_playing");
			
			//Verify audio file name in first product.
			WebElement audiofilename = driver.findElement(By.xpath("//div/ul/li/a[@class='sm2_link']"));
			String audiofilename_text = audiofilename.getText();
			System.out.println("Audio_1 text = "+audiofilename_text);			
			AssertionTest.assertjob(driver, audiofilename_text, "1 The Story");
			
			//Click on 'Stop All'
			WebElement ClickStopAll = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div/div/div/button"));
			ClickStopAll.click();
			
			synchronized (driver) {
				driver.wait(4000);}
			
			//Ensure 'Play All' functionality has stopped
			WebElement StopAll = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div/div/div/button/span"));
			String Button_text = StopAll.getText();
			System.out.println("Button displayed after clicking Stop = "+Button_text);
			AssertionTest.assertjob(driver, Button_text, "Play All");
			
			//Open book "Criminal Justice Today, 12e" 
			WebElement flash2 = driver.findElement(By.xpath("//span[text()=\"CJ Today, 12/e\"]"));
			flash2.click();
			
			synchronized (driver) {
				driver.wait(4000);}
			
			//Verify that product page has launched.
			WebElement audio3 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[4]/div/div[3]/div[2]/ul/li[3]/a"));
			String str_aud3 = audio3.getText();
			System.out.println("Audio_3 text = "+str_aud3);
			AssertionTest.assertjob(driver, str_aud3, "3 Driving Sell Thru");
			//assertEquals("3 Driving Sell Thru", str_aud3);
			
			
			//Launch FlashCards
			
			WebElement flash3 = driver.findElement(By.cssSelector("div.span8 > button"));
			flash3.click();
			
						
			//Verify question text of first question.
			WebElement questiontext = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div[2]/div/div[2]/div/div[2]/div/h2/span"));
			String str_questiontext = questiontext.getText();
			System.out.println("Question_1 text = "+str_questiontext);
			AssertionTest.assertjob(driver, str_questiontext, "What product should you lead with when selling in intro to CJ?");
			//assertEquals("What product should you lead with when selling in intro to CJ?", str_questiontext);
			
			
			//Select Correct Option in Q#1 
			WebElement flash4 = driver.findElement(By.xpath("//span[text()=\"MyCJLab\"]"));
			flash4.click();

			
			synchronized (driver) {
				driver.wait(4000);}
			
			//Verify that green check mark is appearing for correct answer.
			WebElement CorrectAns = driver.findElement(By.xpath("//span[text()=\"MyCJLab\"]/.."));
			String str = CorrectAns.getAttribute("class");
			System.out.println("Selected option (MyCJLab) = "+str);
			AssertionTest.assertjob(driver, str, "btn radio-control active correct-ans");
			//assertEquals("btn radio-control active correct-ans", str);
				
			
			//Click on Next button to move Q#2
			WebElement flash5 = driver.findElement(By.xpath("//button[text()=\"Next\"]"));
			flash5.click();
			

			//Waiting for Question2 to appear
			synchronized (driver) {
				driver.wait(2000);}
			
			/*			
			//Verify question text of second question.
			WebElement questiontext1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div[2]/div/div[2]/div/div[2]/div[2]/h2/span"));
			String str_questiontextone = questiontext1.getText();
			System.out.println("Question Text2 = "+str_questiontextone);
			AssertionTest.assertjob(driver, str_questiontextone, "What author from McGraw-Hill should you be targeting?");

			
			//Verify question number for Question2
			WebElement questiontext2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div[2]/div/div[2]/div/div/div/div/h4"));
			String str_questiontext2 = questiontext2.getText();
			System.out.println("Question Text2 = "+str_questiontext2);
			AssertionTest.assertjob(driver, str_questiontext2, "Question 2 of 5");
			*/
			
			//Click on Next button to move Q#3
			WebElement flash6 = driver.findElement(By.xpath("//button[text()=\"Next\"]"));
			flash6.click();
			
			//Waiting
			synchronized (driver) {
				driver.wait(2000);}
			
			//Click on Next button to move Q#4
			WebElement flash7 = driver.findElement(By.xpath("//button[text()=\"Next\"]"));
			flash7.click();
			
			//Waiting
			synchronized (driver) {
				driver.wait(2000);}
			
			//Click on Next button to move Q#5
			WebElement flash8 = driver.findElement(By.xpath("//button[text()=\"Next\"]"));
			flash8.click();
			
			//Waiting
			synchronized (driver) {
				driver.wait(2000);}
			
			//Verify question number for Question5
			WebElement questiontext2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div[2]/div/div[2]/div/div/div/div/h4"));
			String str_questiontext2 = questiontext2.getText();
			System.out.println("Question_5 number = "+str_questiontext2);
			AssertionTest.assertjob(driver, str_questiontext2, "Question 5 of 5");
			
			//Waiting
			synchronized (driver) {
				driver.wait(3000);}
			
			
			
				//Clicking on Profile Icon
				WebElement flash1 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
				flash1.click();

				//Waiting
				synchronized (driver) {
					driver.wait(4000);}
				
				//Click on Browse Discipline
				WebElement flash9 = driver.findElement(By.xpath("//*[@id=\"discipline-button\"]"));
				flash9.click();
				
				//Waiting
				synchronized (driver) {
					driver.wait(4000);}
				
				//Verify discipline page has launched
				//Verify 1st Discipline
				WebElement Discipline_1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/span"));
				String str_disc_1 = Discipline_1.getText();
				System.out.println("Discipline_1 = "+str_disc_1);
				AssertionTest.assertjob(driver, str_disc_1, "Criminal Justice");
				//String job = SetupDriver.job.toString();
				//System.out.println("https://saucelabs.com/tests/"+job);
				//assertEquals("Criminal Justice", str_disc1);
				
				//Verify Last Discipline
				WebElement Discipline_2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div[4]/div/div/span"));
				String str_disc_2 = Discipline_2.getText();
				System.out.println("Discipline_2 = "+str_disc2);
				AssertionTest.assertjob(driver, str_disc_2, "Trades");
				//assertEquals("Trades", str_disc2);
				
				//Clicking on Profile Icon
				WebElement flash10 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
				flash10.click();

				
				//Waiting for Logout Button to appear
				synchronized (driver) {
					driver.wait(7000);}
				
				//Click on Logout Button
				driver.findElement(By.xpath("//*[@id=\"logout-button\"]")).click();
				
				synchronized (driver) {
					driver.wait(4000);}
				
			}
			
			
				}





