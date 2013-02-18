package testautomation.testcases;




import org.openqa.selenium.*;  
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import testautomation.framework.AssertionTest;
import testautomation.framework.LogGenerator;
import testautomation.framework.SetupDriver;

@RunWith(Parameterized.class)
public class Admin_PearsonHSCTests {
		
	public static int index=0;
	private static String browserchoice;
	private String baseUrl;
	private RemoteWebDriver driver = null;
	Process process=null;
	public Admin_PearsonHSCTests(String opt)
	{
		browserchoice=opt;
	}
    @Parameters
    public static Collection<Object[]> data() 
    {	   
    	//Object[][] data = new Object[][] {{"1"},{"2"},{"3"},{"4"},{"5"},{"6"}};
    	//Object[][] data = new Object[][] {{"4"}};
    	Object[][] data = new Object[][] {{"2"},{"3"},{"4"}};
    	return Arrays.asList(data);
   }
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
			if(driver!=null)
			driver.quit();			
			System.out.println("Driver ShutDown");			
	}
	@Test
	public void HSCTest_Test() throws Exception 
	{
		
		//****************Launch Site and Login*********//
		baseUrl ="http://d1-pearson-hsc.herokuapp.com/?qa=true";
		//baseUrl ="http://p-pearson-hsc.herokuapp.com/?qa=true";
		driver.get(baseUrl + "#/home");		
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys("admin@compro.com");
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys("admin");
		driver.findElement(By.id("login-button")).click();		
		synchronized (driver) 
		{
			driver.wait(4000);			
		}
		//**********Discipline Checks***************//
			
		//Verify 1st Discipline
		WebElement Discipline1 = driver.findElement(By.xpath("//span[text()=\"Criminal Justice\"]"));
		String str_disc1 = Discipline1.getText();
		//System.out.println("Discipline 1 = "+str_disc1);
		AssertionTest.assertjob(driver, str_disc1, "Criminal Justice");
				
		//Verify Last Discipline
		WebElement Discipline2 = driver.findElement(By.xpath("//span[text()=\"Trades\"]"));
		String str_disc2 = Discipline2.getText();
		System.out.println("Discipline 2 = "+str_disc2);		
		AssertionTest.assertjob(driver, str_disc2, "Trades");
		//************************************************************//
		//*******Open Logs and verify version number*******//	
		//Click on Tools option
		WebElement Logsicon = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li/a/i"));
		Logsicon.click();
			
		synchronized (driver) 
		{
			driver.wait(4000);
		}
		//Verify a popup appear with version number
		WebElement logs_popup = driver.findElement(By.xpath("//*[@id=\"ajax-error-label\"]"));
		String str_logs_popup = logs_popup.getText();
		//System.out.println("PopUp = "+str_logs_popup);
		//Version verification for d1
		AssertionTest.assertjob(driver, str_logs_popup, "HSC-APP version 52");
		
		//Click "OK" button in popup
		WebElement okbutton = driver.findElement(By.xpath("/html/body/div/div[3]/button[2]"));
		okbutton.click();
		synchronized (driver)
		{
			driver.wait(5000);
			
		}
			
		//Version verification for production site
		//AssertionTest.assertjob(driver, str_logs_popup, "HSC-APP version 53");
		//********************************************************************************
		//********Open Profile page, verify user name and Open "Manage Accounts"************
		
		//Clicking on Profile Icon
		WebElement flash1 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
		flash1.click();
		synchronized (driver) 
		{
			driver.wait(15000);					
		}
		//Verify the user name
		WebElement username = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div/div/div[2]/div/div/div/div/div/div[2]/p"));
		String User = username.getText();
		//System.out.println("User Name="+User);
		AssertionTest.assertjob(driver, User, "Compro Admin");				
		//Click on Manage Accounts button
		WebElement manageaccount = driver.findElement(By.xpath("//*[@id=\"user-button\"]"));
		manageaccount.click();
		//*****************************************************************************
		//**********Manage Account page check and open add new user page***************		
		//Verify label "Manage Accounts"
		WebElement labelmanacc = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div/div/ul/li/h2"));
		String labelmanacc_text = labelmanacc.getText();
		System.out.println("Label for Manage Account page="+labelmanacc_text);
		AssertionTest.assertjob(driver, labelmanacc_text, "Manage Accounts");				
		//Click on Add new user
		WebElement addnewuser = driver.findElement(By.xpath("//*[@id=\"addNewUser\"]"));
		addnewuser.click();
		//*********************************************************************		
		//***************New User page check and adding a new user****************		
		//Verify label 'New user'
		WebElement newuser = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div/div/ul/li[2]/h2"));
		String newuser_text = newuser.getText();
		System.out.println("Label for New User page="+newuser_text);
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
		synchronized (driver) 
		{
			driver.wait(8000);
		}
		//**************************************************************************		
		//*******Verify if user is created**************//		
		//Verify 'Next' button is enabled or not
		WebElement nextbutton = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/ul/li[2]"));
		String str = nextbutton.getAttribute("class");
		System.out.println("current state of next button= "+str);
		//Check if "Next" button is enabled or not
		while (str.equals("next"))
		  {						
			//Checking if created user is on 1st page
			try
			{
				driver.findElement(By.xpath("//small[contains(.,\"comprotest\")]"));
				break;
			}
			//If user is not on 1st page then click on "Next" button to move next page.
			catch (NoSuchElementException e) 
			{
				//Click on Next button
				WebElement clicknext = driver.findElement(By.xpath("//*[@id=\"nxtPage\"]"));
				clicknext.click();			
				synchronized (driver)
				{
					driver.wait(5000);
				}
				//Checking if "Next" button is enabled.
				WebElement nextbutton1 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/ul/li[2]"));
				String str1 = nextbutton1.getAttribute("class");
				System.out.println("current state of next button= "+str1);
				//Updating variable value for current state of Next button
				str=str1;
				System.out.println("inside while lop");
			}			
		  }
		System.out.println("Outside while lop");				
		synchronized (driver) 
		{
			driver.wait(5000);
		}
						
		//Verify New user cell
		WebElement newusercreated = driver.findElement(By.xpath("//small[contains(.,\"comprotest\")]"));
		String newusercreated_text = newusercreated.getText().trim();
		System.out.println("new username="+newusercreated_text);
		AssertionTest.assertjob(driver, newusercreated_text, "(comprotest)");
		//***********************************************************************************
								
		//******Edit User********************************//
		//Click on "Edit" icon appear with added user "comprotest"
		WebElement editicon=driver.findElement(By.xpath("//small[contains(.,\"comprotest\")]/../../../../td/a[1]/i"));
		editicon.click();
		synchronized (driver) 
		{
			driver.wait(5000);
			
		}		
		//Updating/Changing Last name of user
		driver.findElement(By.name("lastName")).clear();
		driver.findElement(By.name("lastName")).sendKeys("Test1");
		driver.findElement(By.id("adminEditUser")).click();
		//********************************************************************************
				
		//********Delete User***********************//
		//Get current state of Next button
		WebElement nextbutton2 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/ul/li[2]"));
		String str2 = nextbutton2.getAttribute("class");
		System.out.println("current state of next button= "+str2);
		//Verify 'Next' button is enabled or not
		while (str2.equals("next"))
		  {
			//Checking if created user is on 1st page
			try
			{
				driver.findElement(By.xpath("//small[contains(.,\"comprotest\")]"));
				break;
			}
			catch (NoSuchElementException e) 					
			{			
				//Click on Next button
				WebElement clicknext2 = driver.findElement(By.xpath("//*[@id=\"nxtPage\"]"));
				clicknext2.click();			
				synchronized (driver) 
				{
					driver.wait(5000);
				}
				//Verify current state of Next button
				WebElement nextbutton3 = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div[2]/div/div/ul/li[2]"));
				String str3 = nextbutton3.getAttribute("class");
				System.out.println("current state of next button= "+str3);
				//Updating variable value for current state of Next button		
				str2=str3;			
				System.out.println("inside while lop");
			}
			
		  }		   
		System.out.println("Outside while lop");				
		synchronized (driver) 
		{
			driver.wait(5000);
		}
		//Click on "Delete" icon appear with added user "comprotest".		
		WebElement DelIcon=driver.findElement(By.xpath("//small[contains(.,\"comprotest\")]/../../../../td/a[2]/i"));
		DelIcon.click();
		synchronized (driver) 
		{
			driver.wait(5000);
			
		}			
		//Click on "Delete" button to delete the user.
		driver.findElement(By.id("adminDeleteUser")).click();
		synchronized (driver) 
		{
			driver.wait(5000);
			
		}	
		//*****************************************************************************
		//****Open Profile page and "Browser Discipline"***//
		WebElement flashprofile = driver.findElement(By.xpath("/html/body/div[4]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
		flashprofile.click();
		//Waiting
		synchronized (driver) 
		{
			driver.wait(15000);
		}
		//Click on Browse Discipline button
		WebElement BrowseDiscipline = driver.findElement(By.xpath("//*[@id=\"discipline-button\"]"));
		BrowseDiscipline.click();
		//Waiting
		synchronized (driver) 
		{
			driver.wait(15000);
		}
		//**********************************************************************************
		//*************Discipline Page Checks**********//
		//Verify 1st Discipline
		WebElement Discipline_1 = driver.findElement(By.xpath("//span[text()=\"Criminal Justice\"]"));
		String str_disc_1 = Discipline_1.getText();
		//System.out.println("Discipline 1 = "+str_disc_1);
		AssertionTest.assertjob(driver, str_disc_1, "Criminal Justice");						
		//Verify Last Discipline
		WebElement Discipline_2 = driver.findElement(By.xpath("//span[text()=\"Trades\"]"));
		String str_disc_2 = Discipline_2.getText();
		//System.out.println("Discipline 2 = "+str_disc_2);			
		AssertionTest.assertjob(driver, str_disc_2, "Trades");
		//*******************************************************************************
		//*****Open profile page and Logout*******//
		//Click on profile icon
		WebElement flashprofile1 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/ul/li[4]/a/i[@class=\"icon-user\"]"));
		flashprofile1.click();				
		//Waiting for Profile Page
		synchronized (driver) 
		{
			driver.wait(10000);					
		}				
		//Click on Logout Button
		driver.findElement(By.xpath("//*[@id=\"logout-button\"]")).click();				
		synchronized (driver) 
		{
			driver.wait(4000);
		}
				
	}
			
}





