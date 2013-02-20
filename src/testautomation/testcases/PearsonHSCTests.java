package testautomation.testcases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
//import org.openqa.jetty.jetty.servlet.AbstractSessionManager.Session;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
//import com.opera.core.systems.scope.protos.SelftestProtos.SelftestResult.Result;
import testautomation.framework.AssertionTest;
import testautomation.framework.LogGenerator;
import testautomation.framework.SetupDriver;
//import testautomation.testcases.SimpleMail.SMTPAuthenticator;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;

import java.util.Properties;


@RunWith(Parameterized.class)
public class PearsonHSCTests 
{
	public static int index=0;
	private String browserchoice;
	private String baseUrl;
	private RemoteWebDriver driver = null;
	//static WebDriver driver;
	Process process=null;
	public static String testresult="";
 	public PearsonHSCTests(String opt){
		browserchoice=opt;
		// System.out.println("opt"+opt);
	  }
	@Parameters
	public static Collection<Object[]> data() {
		   //Object[][] data = new Object[][] {{"1"},{"2"},{"3"},{"4"},{"5"},{"6"}};
		  	//Object[][] data = new Object[][] {{"2"},{"3"}};
		   Object[][] data = new Object[][] {{"2"},{"3"},{"4"},{"6"}};
		   return Arrays.asList(data);
	   }
	@Before
	public void setUp()
	{
		SetupDriver swd = new SetupDriver(browserchoice);
		driver = swd.setDriver();
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
	public void HSCTest_Test() throws Exception {

		//*****Launch Site and Login****//
		//baseUrl ="http://d1-pearson-hsc.herokuapp.com/?qa=true";
		baseUrl ="http://p-pearson-hsc.herokuapp.com/?qa=true";
		driver.get(baseUrl + "#/home");
		String platform1= driver.getCapabilities().getPlatform().toString();
		String broswername1=driver.getCapabilities().getBrowserName();
		String browserversion1=driver.getCapabilities().getVersion();
		String jobid1=driver.getSessionId().toString();
		//System.out.println("TEST O.S. Browser Version Jobid=\tPearsonHSC\t"+platform1+"\t"+broswername1+" "+browserversion1+"\thttps://saucelabs.com/tests/"+jobid1);
		testresult=testresult+"PearsonHSC\t"+platform1+"\t"+broswername1+" "+browserversion1+"\thttps://saucelabs.com/tests/"+jobid1+"\n";
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys("hsc");
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys("breakthrough");
		driver.findElement(By.id("login-button")).click();
		//*************************************************************//

		synchronized (driver) {
			driver.wait(5000);
		}

		//*****Discipline Home Page Checks****//
		//Verify 1st Discipline is Criminal Justice
		WebElement Discipline1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/span"));
		String str_disc1 = Discipline1.getText();
		//System.out.println("Discipline_1 = "+str_disc1);
		AssertionTest.assertjob(driver, str_disc1, "Criminal Justice");


		//Verify Last Discipline is Trades
		WebElement Discipline2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div[4]/div/div/span"));
		String str_disc2 = Discipline2.getText();
		//System.out.println("Discipline_2 = "+str_disc2);
		AssertionTest.assertjob(driver, str_disc2, "Trades");
		//assertEquals("Trades", str_disc2);
		//*************************************************************//

		//*****Open Discipline "Criminal Justice" & verify product home page****//
		WebElement flash = driver.findElement(By.id("481"));
		flash.click();
		synchronized (driver) {
					driver.wait(5000);
		}
		WebElement PlayAll = driver.findElement(By.xpath("//*[@id='panel_discipline-home']"));
		String str_class1 = PlayAll.getAttribute("style");
		System.out.println("panel_discipline-home Attributes="+str_class1);


		//Verify that product page has launched
		//Verify 1st Product
		WebElement Product1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div/div/div/div/span"));
		String str_prd1 = Product1.getText();
		//System.out.println("Product_1 = "+str_prd1);
		AssertionTest.assertjob(driver, str_prd1, "CJ Today, 12/e");
		//assertEquals("CJ Today, 12/e", str_prd1);

		//Verify Last Product
		WebElement Product2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div[4]/div[2]/div/div/span"));
		String str_prod2 = Product2.getText();
		//System.out.println("Product_2 = "+str_prod2);
		AssertionTest.assertjob(driver, str_prod2, "Criminal Law Today, 5/e");
		//assertEquals("Criminal Law Today, 5/e", str_prod2);
		//Click 'Play All'
		WebElement ClickPlayAll = driver.findElement(By.xpath("//button[@class='btn playAll']"));
		Boolean playalldisplayed=driver.findElement(By.xpath("//button[@class='btn playAll']")).isEnabled();
		System.out.println("Is Play All Button Enabled="+playalldisplayed);
		
		//ClickPlayAll.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ClickPlayAll);
		
		String buttonstate = "NotPlayed";
				while(!buttonstate.equals("Played"))
				{
					try
					{
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div/div/div/div[2]/ul/li"));
						buttonstate = "Played";
						System.out.println("Audio is playing");
					}
					catch (NoSuchElementException e)
					{
						synchronized (driver)
						{
							driver.wait(5000);

						}
						System.out.println("Waiting for Audio to play");
					}
		}

		//Ensure 'Play All' functionality has launched
		WebElement TheStory = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div/div/div/div[2]/ul/li"));
		String str_class = TheStory.getAttribute("class");
		//System.out.println("State of Play All functionality = "+str_class);
		AssertionTest.assertjob(driver, str_class, "sm2_playing");

		//Verify audio file name in first product.
		WebElement audiofilename = driver.findElement(By.xpath("//div/ul/li/a[@class='sm2_link']"));
		String audiofilename_text = audiofilename.getText();
		//System.out.println("Audio_1 text = "+audiofilename_text);
		AssertionTest.assertjob(driver, audiofilename_text, "1 The Story");

		//Click on 'Stop All'
		WebElement ClickStopAll = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div/div/div/button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ClickStopAll);
		//ClickStopAll.click();

		synchronized (driver) {
			driver.wait(10000);}

		//Ensure 'Play All' functionality has stopped
		WebElement StopAll = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div/div/div/button/span"));
		String Button_text = StopAll.getText();
		//System.out.println("Button displayed after clicking Stop = "+Button_text);
		AssertionTest.assertjob(driver, Button_text, "Play All");
		//*************************************************************//

		//*****Open Product "Criminal Justice Today, 12e" and verify product page************//
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
		//***********************************************************************************//

		//*******Launch Flash Cards and verify************************************************//

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
		synchronized (driver)
		{
			driver.wait(5000);
		}

		/*
		//Verify question text of second question.
		WebElement questiontext111 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div[2]/div/div[2]/div/div[2]/div[2]/h2/span"));
		//WebElement questiontext1 = driver.findElement(By.xpath("//div[@id=\"body-set\"]/div[2]/h2/span"));
		String str_questiontextone111 = questiontext111.getText();
		System.out.println("Question Text2 = "+str_questiontextone111);
		AssertionTest.assertjob(driver, str_questiontextone111, "What author from McGraw-Hill should you be targeting?");
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

		//**************************************************************************************************//


		//********Click on profile icon and browse discipline***********************************************//
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
		//******************************************************************************************************//
		//*****Discipline Home Page Checks****//
		//Verify 1st Discipline
		WebElement Discipline_1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/span"));
		String str_disc_1 = Discipline_1.getText();
		System.out.println("Discipline_1 = "+str_disc_1);
		AssertionTest.assertjob(driver, str_disc_1, "Criminal Justice");

		//Verify Last Discipline
		WebElement Discipline_2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div[4]/div/div/span"));
		String str_disc_2 = Discipline_2.getText();
		System.out.println("Discipline_2 = "+str_disc2);
		AssertionTest.assertjob(driver, str_disc_2, "Trades");
		//*******************************************************************************************************//

		//***********Open Profile page and verify***********//
		//Clicking on Profile Icon
		WebElement flash10 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
		flash10.click();

		//Waiting for Logout Button to appear
		synchronized (driver) {
			driver.wait(7000);}

		//Click on Logout Button
		driver.findElement(By.xpath("//*[@id=\"logout-button\"]")).click();
		
		// Waiting for user to get Logged out.
					String logout = "Not_logged_out";
					while(!logout.equals("logged_out"))
					{
						try
						{
							driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
							logout = "logged_out";
							System.out.println("User has been logged out");
						}
						catch (NoSuchElementException e)
						{
							synchronized (driver)
							{
								driver.wait(5000);

							}
							System.out.println("Waiting for user to get logged out");
						}
			}

//		synchronized (driver) {
//			driver.wait(7000);}
		
				
		
		//*******************************************************************************************************
		//********************************************************End of Test 1***********************************************
		}
	@Test
	public void FBHSCTest_Test1() throws Exception {

	//*****Launch Site ****//
	baseUrl ="http://p-pearson-hsc.herokuapp.com/?qa=true";
	driver.get(baseUrl + "#/home");
	String platform1= driver.getCapabilities().getPlatform().toString();
	String broswername1=driver.getCapabilities().getBrowserName();
	String browserversion1=driver.getCapabilities().getVersion();
	String jobid1=driver.getSessionId().toString();
	//System.out.println("TEST O.S. Browser Version Jobid=\tFacebookLogin\t"+platform1+"\t"+broswername1+" "+browserversion1+"\thttps://saucelabs.com/tests/"+jobid1);
	testresult=testresult+"Facebook Login\t"+platform1+"\t"+broswername1+" "+browserversion1+"\thttps://saucelabs.com/tests/"+jobid1+"\n";
	//***********************************************************//

	//************Click on Facebook button and open new window and login*****************************
	//Store the parent window before opening new Facebook login window
	 String parent = driver.getWindowHandle();
	//Perform the click operation that opens new window
	 driver.findElement(By.id("facebook-login")).click();
	 Set <String> handles =driver.getWindowHandles();
	 Iterator<String> it = handles.iterator();
	 String newwin = "";
	 //iterate through the windows to get the new window
	   while (it.hasNext()){
		   String newWinHandle = it.next();
		   if (!newWinHandle.equals(parent))
			   newwin = newWinHandle;
	   }
	  driver.switchTo().window(newwin);
	  String winname = "HSCLogin";
	  while(!winname.equals("facebooklogin"))
		{
			try
			{
				driver.findElement(By.name("email"));
				winname = "facebooklogin";
				System.out.println("Facebook Login Page Launched.");
			}
			catch (NoSuchElementException e)
			{
				synchronized (driver)
				{
					driver.wait(5000);

				}
				System.out.println("Waiting for Facebook Login Page");
			}
		}

		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("comprodelhi@gmail.com");
		driver.findElement(By.name("pass")).clear();
		driver.findElement(By.name("pass")).sendKeys("pearsonhsc");
		driver.findElement(By.name("login")).click();
		synchronized (driver)
		{
			driver.wait(5000);

		}
		driver.switchTo().window(parent);
		String window = "facebook";
		 while(!window.equals("pearsonhsc"))
			{
				try
				{
					driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/span"));
					window = "pearsonhsc";
					System.out.println("Pearon HSC Login Page Launched.");
				}
				catch (NoSuchElementException e)
				{
					synchronized (driver)
					{
						driver.wait(5000);

					}
					System.out.println("Waiting for PearsonHSC Login Page");
				}
			}
	  //***********************************************************************************************

      //*****Discipline Home Page Checks****//
		//Verify 1st Discipline
		WebElement Discipline1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/span"));
		String str_disc1 = Discipline1.getText();
		System.out.println("Discipline_1 = "+str_disc1);
		AssertionTest.assertjob(driver, str_disc1, "Criminal Justice");


		//Verify Last Discipline
		WebElement Discipline2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div[4]/div/div/span"));
		String str_disc2 = Discipline2.getText();
		System.out.println("Discipline_2 = "+str_disc2);
		AssertionTest.assertjob(driver, str_disc2, "Trades");

		synchronized (driver) {
			driver.wait(5000);

		}
		//***************************************************

		////*****Open Discipline "Criminal Justice" & verify product home page****//
		WebElement flash = driver.findElement(By.id("481"));
		flash.click();
		synchronized (driver) {
					driver.wait(5000);

		}
		WebElement PlayAll = driver.findElement(By.xpath("//*[@id='panel_discipline-home']"));
		String str_class1 = PlayAll.getAttribute("style");
		System.out.println("panel_discipline-home Attributes="+str_class1);

		//Verify that product page has launched
		//Verify 1st Product
		WebElement Product1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div/div/div/div/span"));
		String str_prd1 = Product1.getText();
		System.out.println("Product_1 = "+str_prd1);
		AssertionTest.assertjob(driver, str_prd1, "CJ Today, 12/e");

		//Verify Last Product
		WebElement Product2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div[4]/div[2]/div/div/span"));
		String str_prod2 = Product2.getText();
		System.out.println("Product_2 = "+str_prod2);
		AssertionTest.assertjob(driver, str_prod2, "Criminal Law Today, 5/e");
		//Click on 'Play All'
		WebElement ClickPlayAll = driver.findElement(By.xpath("//button[@class='btn playAll']"));
		Boolean playalldisplayed=driver.findElement(By.xpath("//button[@class='btn playAll']")).isEnabled();
		System.out.println("Is Play All Button Enabled="+playalldisplayed);

				//driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div/div/div/button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ClickPlayAll);
		//ClickPlayAll.click();
		String buttonstate = "NotPlayed";
		while(!buttonstate.equals("Played"))
		{
			try
			{
				driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div[2]/div/div/div/div[2]/ul/li"));
				buttonstate = "Played";
				System.out.println("Audio is playing");
			}
			catch (NoSuchElementException e)
			{
				synchronized (driver)
				{
					driver.wait(5000);

				}
				System.out.println("Waiting for Audio to play");
			}
		}
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
			driver.wait(2000);}

		//Ensure 'Play All' functionality has stopped
		WebElement StopAll = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[3]/div/div/div/div/button/span"));
		String Button_text = StopAll.getText();
		System.out.println("Button displayed after clicking Stop = "+Button_text);
		AssertionTest.assertjob(driver, Button_text, "Play All");
		//**************************************************************************************************

		//*****Open Product "Criminal Justice Today, 12e" and verify product page************//
		WebElement flash2 = driver.findElement(By.xpath("//span[text()=\"CJ Today, 12/e\"]"));
		flash2.click();

		synchronized (driver) {
			driver.wait(4000);}

		//Verify that product page has launched.
		WebElement audio3 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[4]/div/div[3]/div[2]/ul/li[3]/a"));
		String str_aud3 = audio3.getText();
		System.out.println("Audio_3 text = "+str_aud3);
		AssertionTest.assertjob(driver, str_aud3, "3 Driving Sell Thru");
		//************************************************************************************//

		//*******Launch Flash Cards and verify************************************************//
		WebElement flash3 = driver.findElement(By.cssSelector("div.span8 > button"));
		flash3.click();

		synchronized (driver) {
			driver.wait(2000);}

		//Verify question text of first question.
		WebElement questiontext = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div[2]/div/div[2]/div/div[2]/div/h2/span"));
		String str_questiontext = questiontext.getText();
		System.out.println("Question_1 text = "+str_questiontext);
		AssertionTest.assertjob(driver, str_questiontext, "What product should you lead with when selling in intro to CJ?");


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


		//Click on Next button to move Q#2
		WebElement flash5 = driver.findElement(By.xpath("//button[text()=\"Next\"]"));
		flash5.click();

		synchronized (driver) {
			driver.wait(5000);}
		/*
		//Verify question text of second question.
		WebElement questiontext1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div[2]/div/div[2]/div/div[2]/div[2]/h2/span"));
		//WebElement questiontext1 = driver.findElement(By.xpath("//div[@id=\"body-set\"]/div[2]/h2/span"));
		String str_questiontextone = questiontext1.getText();
		System.out.println("Question Text2 = "+str_questiontextone);
		AssertionTest.assertjob(driver, str_questiontextone, "What author from McGraw-Hill should you be targeting?");
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
		//**********************************************************************************

		//*************Open Profile Page and browser discipline*********//
		//Clicking on Profile Icon
			WebElement flash1 = driver.findElement(By.xpath("//i[@class=\"icon-user\"]"));
			flash1.click();
			synchronized (driver) {
				driver.wait(4000);}

			//Click on Browse Discipline
			WebElement flash9 = driver.findElement(By.xpath("//*[@id=\"discipline-button\"]"));
			flash9.click();

			//Waiting
			synchronized (driver) {
				driver.wait(4000);}
			//************************************************************
			//*******Discipline Page Checks***********//
			//Verify 1st Discipline
			WebElement Discipline_1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div/div/span"));
			String str_disc_1 = Discipline_1.getText();
			System.out.println("Discipline_1 = "+str_disc_1);
			AssertionTest.assertjob(driver, str_disc_1, "Criminal Justice");

			//Verify Last Discipline
			WebElement Discipline_2 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div[4]/div/div/span"));
			String str_disc_2 = Discipline_2.getText();
			System.out.println("Discipline_2 = "+str_disc2);
			AssertionTest.assertjob(driver, str_disc_2, "Trades");
			//***********************************************************
			//*******Open profile page and verify**************
			//Clicking on Profile Icon
			WebElement flash10 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
			flash10.click();
			//Waiting for Logout Button to appear
			synchronized (driver) {
				driver.wait(5000);}
			//Verify the user name
			WebElement username = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div/div/div[2]/div/div/div/div/div/div[2]/p"));
			String User = username.getText();
			System.out.println("User Name = "+User);
			AssertionTest.assertjob(driver, User, "Compro Delhi");

			//Verify the email id
			WebElement emailid = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div/div/div[2]/div/div/div/div/div/div[2]/p[2]"));
			String email = emailid.getText();
			System.out.println("Email ID = "+email);
			AssertionTest.assertjob(driver, email, "comprodelhi@gmail.com");

			//Searching Logout Button
			synchronized (driver) {
				driver.wait(5000);

			}

			//Click on Logout Button
			driver.findElement(By.xpath("//*[@id=\"logout-button\"]")).click();

			// Waiting for user to get Logged out.
			String logout = "Not_logged_out";
			while(!logout.equals("logged_out"))
			{
				try
				{
					driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
					logout = "logged_out";
					System.out.println("User has been logged out");
				}
				catch (NoSuchElementException e)
				{
					synchronized (driver)
					{
						driver.wait(5000);

					}
					System.out.println("Waiting for user to get logged out");
				}
	}
			
//			synchronized (driver) {
//				driver.wait(10000);}
			
			//********************************************************************************
		//***********************************End of Facebook Test***********************************
		}
@AfterClass
	public static void output()
	{
		System.out.println("TEST Name\tO.S.\tBrowser\tSauce Lab URL\n");
		System.out.println(testresult);
		 /*
		 File f=new File("Result(Spreadsheet compatible).txt");

	        StringBuffer sb = new StringBuffer("TEST Name\tO.S.\tBrowser\tSauce Lab URL\n");
	        try{
	            FileWriter fwriter = new FileWriter(f);
	            BufferedWriter bwriter = new BufferedWriter(fwriter);
	            bwriter.write(sb.toString()+testresult);
	            bwriter.close();
	         }
	        catch (Exception e){
	              e.printStackTrace();
	         }
	         */

	   
	 }
public class SendEmail
{
   public void main(String [] args)
   {    
      // Recipient's email ID needs to be mentioned.
      String to = "ankur.sharma@comprotechnologies.com";

      // Sender's email ID needs to be mentioned
      String from = "web@gmail.com";

      // Assuming you are sending email from localhost
      String host = "smtp.sendgrid.net";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Now set the actual message
         message.setText("This is actual message");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}



}





