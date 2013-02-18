package testautomation.testcases;




import static org.junit.Assert.*;
//import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import java.util.logging.Level;

import testautomation.framework.AssertionTest;
import testautomation.framework.CaptureScreenshot;
import testautomation.framework.LogGenerator;
import testautomation.framework.SetupDriver;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.saucerest.SauceREST;

import java.awt.datatransfer.StringSelection;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
		

@RunWith(Parameterized.class)
public class MultiBrowserTestsOld {
	
	
	
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
		
	public static int index=0;
	private String browserchoice;
	


	   
	public MultiBrowserTestsOld(String opt){

		browserchoice=opt;

	  }
		 
 
		 
	  @Parameters
	   public static Collection<Object[]> data() {
	   
		   Object[][] data = new Object[][] {{"1"}};
	    return Arrays.asList(data);

	   }
	
	
		
		private RemoteWebDriver driver = null;
		
		
	   Process process=null;
		
		@Before
		public void setUp() 
		{
		
			
			SetupDriver swd = new SetupDriver(browserchoice);
			driver = swd.setDriver();
			driver.manage().window().maximize();
			System.out.println("ss");
			Assume.assumeTrue(swd.valid==true);
			System.out.println("after assume");
			LogGenerator lg = new LogGenerator(driver);
			
			
		}
				
		@After
		public void tearDown() throws Exception 
		{

			if(driver!=null)
			driver.quit();
			
			System.out.println("Driver ShutDown");

		}

						
				private  JavascriptExecutor js;
				private  SauceREST client;	
				
//				@Ignore
				@Test
				public void TestCase() throws Exception  {
					
					
					
					LogGenerator.addLog(Level.INFO, "Starting Test Case_001");
				
				 	driver.get("http://www.google.com");			
					// Find the text input element by its name
					WebElement element = driver.findElement(By.name("q"));			
					element.sendKeys("JUnit");		
					element.submit();


			     	String name= (new Exception().getStackTrace()[0].getMethodName());
			     	CaptureScreenshot.takeScreenshot(driver, name);	

			     	String actual = driver.getTitle();
			     	String expected = "JUnit - Google Search";
			     	AssertionTest.assertjob(driver, actual, expected);
			
			     	
				
				}

				
}

