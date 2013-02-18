import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Platform;
//Following are used for Data Driven Testing
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;

public class KoreanLang {
	private static WebDriver driver;
	private static String baseUrl;
	private static StringBuffer verificationErrors = new StringBuffer();
	private String username="gauravmehta1";
	private String firstname="Gaurav";
	private String lastname="Mehta1";
	private String emailid="anil.dani@comprotechnologies.com";
	private String instusername="gauravmehtainst1";
	private String instfirstname="Gaurav Inst";
	private String instlastname="Mehta1";
	private String instemailid="anil.dani@comprotechnologies.com";
	private String coursename="Compro_MyPG_Test_Course1";
	private String assignmentname="MyPG1_Unit1to5";
	@BeforeClass
	public static void setUp() throws Exception {
		
		/*
		driver = new InternetExplorerDriver();
		//driver = new FirefoxDriver();
		//baseUrl = "http://myelt.heinle.com/";
		baseUrl = "http://myelt2.comprotechnologies.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		*/
		
			baseUrl = "http://myelt.heinle.com/";
			DesiredCapabilities capabillities = DesiredCapabilities.firefox();
			// DesiredCapabilities capabillities = DesiredCapabilities.internetExplorer();
		     capabillities.setCapability("version", "6");
		     capabillities.setCapability("platform", org.openqa.selenium.Platform.XP);
		     driver = new RemoteWebDriver(new URL("http://anildanidelhi:56354a60-1118-42a7-84ad-67fb70a17fc9@ondemand.saucelabs.com:80/wd/hub"),
							  capabillities);
		     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		//Login
		driver.get(baseUrl + "/ilrn/authentication/signIn.do?inst=MYELT");
		new Select(driver.findElement(By.id("locale"))).selectByVisibleText("English");
		assertTrue(isTextPresent("Welcome to MyELT"));
		driver.findElement(By.id("loginDisplayField")).clear();
		driver.findElement(By.id("loginDisplayField")).sendKeys("admin_anil");
		driver.findElement(By.id("passwordDisplayField")).clear();
		driver.findElement(By.id("passwordDisplayField")).sendKeys("thomson");
		driver.findElement(By.name("submit")).click();
		synchronized (driver) {
			driver.wait(10000);}
	
	}

	@Test
	public void createStudent() throws Exception {
		synchronized (driver) {
			driver.wait(10000);}
		//Click on Admin Tools
		driver.findElement(By.linkText("Admin Tools")).click();
		synchronized (driver) {
			driver.wait(10000);}
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame]]
		//Moving to the frame
		driver.switchTo().frame("contentiFrame");
		driver.findElement(By.linkText("Add/Edit/Delete Users")).click();
		synchronized (driver) {
			driver.wait(8000);}
		//Moving to another frame
		driver.switchTo().frame("bottom_right_frame");
		
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame]]
		driver.findElement(By.linkText("Add new user")).click();
		
		//Filling User Details
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.name("pass_word")).clear();
		driver.findElement(By.name("pass_word")).sendKeys("thomson");
		driver.findElement(By.name("pass_word2")).clear();
		driver.findElement(By.name("pass_word2")).sendKeys("thomson");
		driver.findElement(By.name("last_name")).clear();
		driver.findElement(By.name("last_name")).sendKeys(lastname);
		driver.findElement(By.name("first_name")).clear();
		driver.findElement(By.name("first_name")).sendKeys(firstname);
		driver.findElement(By.name("verification")).clear();
		driver.findElement(By.name("verification")).sendKeys("abc");
		driver.findElement(By.id("e_mail")).clear();
		driver.findElement(By.id("e_mail")).sendKeys(emailid);
		driver.findElement(By.name("e_mail2")).clear();
		driver.findElement(By.name("e_mail2")).sendKeys(emailid);
		driver.findElement(By.name("add_user_pressed")).click();
		//Verify Student has created.
		synchronized (driver) {
			driver.wait(20000);}
		assertTrue(isTextPresent("Added new user"));
		
	}
	@Test
	public void createInstructor() throws Exception {
		synchronized (driver) {
			driver.wait(15000);}
		driver.switchTo().defaultContent();
		//Click on Admin Tools
		driver.findElement(By.linkText("Admin Tools")).click();
		synchronized (driver) {
			driver.wait(10000);}
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame]]
		//Moving to a Frame
		driver.switchTo().frame("contentiFrame");
		driver.findElement(By.linkText("Add/Edit/Delete Users")).click();
		synchronized (driver) {
			driver.wait(15000);}
		//Moving to Another Frame
		driver.switchTo().frame("bottom_right_frame");
		
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame]]
		driver.findElement(By.linkText("Add new user")).click();
		synchronized (driver) {
			driver.wait(8000);}
		//Filling Entering
		new Select(driver.findElement(By.id("user_type"))).selectByVisibleText("INSTRUCTOR");
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys(instusername);
		driver.findElement(By.name("pass_word")).clear();
		driver.findElement(By.name("pass_word")).sendKeys("thomson");
		driver.findElement(By.name("pass_word2")).clear();
		driver.findElement(By.name("pass_word2")).sendKeys("thomson");
		driver.findElement(By.name("last_name")).clear();
		driver.findElement(By.name("last_name")).sendKeys(instlastname);
		driver.findElement(By.name("first_name")).clear();
		driver.findElement(By.name("first_name")).sendKeys(instfirstname);
		driver.findElement(By.name("verification")).clear();
		driver.findElement(By.name("verification")).sendKeys("abc");
		driver.findElement(By.id("e_mail")).clear();
		driver.findElement(By.id("e_mail")).sendKeys(instemailid);
		driver.findElement(By.name("e_mail2")).clear();
		driver.findElement(By.name("e_mail2")).sendKeys(instemailid);
		driver.findElement(By.name("add_user_pressed")).click();
		//Verify that instructor has created
		synchronized (driver) {
			driver.wait(20000);}
		assertTrue(isTextPresent("Added new user"));
		
	}
	@Test
	public void editPermStudent() throws Exception {
		synchronized (driver) {
			driver.wait(15000);}
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Admin Tools")).click();
		synchronized (driver) {
			driver.wait(10000);}
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame]]
		//Moving to a frame.
		driver.switchTo().frame("contentiFrame");
		driver.findElement(By.linkText("Add/Edit/Delete Users")).click();
		synchronized (driver) {
			driver.wait(8000);}
		//Moving to another frame.		
		driver.switchTo().frame("top_left_frame");
		//Searching a user name for permission
		driver.findElement(By.name("user_name")).clear();
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("search_button")).click();
		synchronized (driver) {
			driver.wait(25000);}
		//Moving to Default Content
		driver.switchTo().defaultContent();
		//Moving to a frame
		driver.switchTo().frame("contentiFrame");
		//Moving to another frame
		driver.switchTo().frame("bottom_right_frame");
		driver.findElement(By.linkText("perm")).click();
		synchronized (driver) {
			driver.wait(15000);}
		//Opening discipline 'MyPG Online'.
		
		driver.findElement(By.xpath("//td/small[text()=\"MyPG Online\"]")).click();
		//Select 'PG_A', 'PG_B' and 'PG_C' books		
		driver.findElement(By.xpath("(//input[@name='feature.book-view-PG_A'])[2]")).click();
		driver.findElement(By.xpath("(//input[@name='feature.book-view-PG_B'])[2]")).click();
		driver.findElement(By.xpath("(//input[@name='feature.book-view-PG_C'])[2]")).click();
		//Giving permission to the user
		driver.findElement(By.name("commit_changes_button")).click();
		
		
	}
	@Test
	public void editPermInstructor() throws Exception {
		synchronized (driver) {
			driver.wait(25000);}
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Admin Tools")).click();
		synchronized (driver) {
			driver.wait(9000);}
		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame]]
		//Moving to a frame
		driver.switchTo().frame("contentiFrame");
		driver.findElement(By.linkText("Add/Edit/Delete Users")).click();
		synchronized (driver) {
			driver.wait(10000);}
		//Moving to another frame
		driver.switchTo().frame("top_left_frame");
		//Entering a instructor for searching
		driver.findElement(By.name("user_name")).clear();
		driver.findElement(By.name("user_name")).sendKeys(instusername);
		driver.findElement(By.name("search_button")).click();
		synchronized (driver) {
			
			driver.wait(20000);}
		//Moving to default content
		driver.switchTo().defaultContent();
		//Moving to a frame
		driver.switchTo().frame("contentiFrame");
		//Moving to another frame
		driver.switchTo().frame("bottom_right_frame");
		driver.findElement(By.linkText("perm")).click();
		synchronized (driver) {
			driver.wait(9000);}
		//Opening discipline 'MyPG Online'
		driver.findElement(By.xpath("//td/small[text()=\"MyPG Online\"]")).click();
		
		//Selecting books 'PG_A', 'PG_B'and 'PG_C'
		driver.findElement(By.xpath("(//input[@name='feature.book-view-PG_A'])[2]")).click();
		driver.findElement(By.xpath("(//input[@name='feature.book-view-PG_B'])[2]")).click();
		driver.findElement(By.xpath("(//input[@name='feature.book-view-PG_C'])[2]")).click();
		//Giving book permissions.
		driver.findElement(By.name("commit_changes_button")).click();
		
		
	}
	@Test
	public void createCourse() throws Exception {
		synchronized (driver) {
			driver.wait(15000);}
		driver.switchTo().defaultContent();
		//Moving to Course Tab
		driver.findElement(By.linkText("Courses")).click();
		//Create a new course
		driver.findElement(By.cssSelector("img[alt=\"Create a new course\"]")).click();
		synchronized (driver) {
			driver.wait(8000);}
		driver.findElement(By.id("visibleCourseName")).clear();
		//Entering Course Details
		driver.findElement(By.id("visibleCourseName")).sendKeys(coursename);
		synchronized (driver) {
			driver.wait(9000);}
		driver.findElement(By.cssSelector("input[type=\"image\"]")).click();
		synchronized (driver) {
			driver.wait(9000);}
		driver.findElement(By.id("saveAndReturn")).click();
		driver.findElement(By.linkText("Return to Course List")).click();
		//Verify that Course has been created
		synchronized (driver) {
			driver.wait(9000);}
		assertTrue(isTextPresent(coursename));		
	}
	@Test
	public void createAssignments() throws Exception {
		synchronized (driver) {
			driver.wait(15000);}
		driver.switchTo().defaultContent();
		
		//Moving to Assignment Tab
		driver.findElement(By.linkText("Assignments")).click();
		synchronized (driver) {
			driver.wait(9000);}
		//driver.findElement(By.xpath("//div[@id='divTC_CMSCourseTree']/table/tbody/tr//td/img")).click();
		//Click on Drop down image to find course in which assignments need to create.
		driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div/div/div[2]/div[2]/table/tbody/tr/td/div/div/div/table/tbody/tr/td[3]/img")).click();
		synchronized (driver) {
			driver.wait(9000);}
		//Selecting course
		driver.findElement(By.xpath("/html/body/div/div/div/div//div/span[2][text()=\""+coursename+"\"]")).click();
		synchronized (driver) {
			driver.wait(15000);}
		//Click on 'Create a Assignments' button
		driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td/div/div/table/tbody/tr[2]/td/div/table/tbody/tr/td[2]/input")).click();
		synchronized (driver) {
			driver.wait(5000);}
		//Open Discipline Tree
		//driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div/div/div[2]/div[3]/table/tbody/tr[2]/td/form/div/div/div/div/div[33]/span/img")).click();
		driver.findElement(By.xpath("//span[text()=\"MyPG Online\"]/../*[1]/*")).click();
		
		//Open Book 'MyPG Online Level 1' Tree
		
		//driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div/div/div[2]/div[3]/table/tbody/tr[2]/td/form/div/div/div/div/div[33]/div/div/span/img")).click();
		driver.findElement(By.xpath("//span[text()=\"MyPG Online Level 1\"]/../*[1]/*[1]")).click();
		//Select Unit 1 to 5
		//driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div/div/div[2]/div[3]/table/tbody/tr[2]/td/form/div/div/div/div/div[33]/div/div/div/div/span/img[2]")).click();
		driver.findElement(By.xpath("//span[text()=\"1-5\"]/../*[1]/*[2]")).click();
		//click on 'Continue' Button
		driver.findElement(By.id("continueButton")).click();
		synchronized (driver) {
			driver.wait(5000);}
		//Moving to Popup and selecting 'Yes' in the popup menu.
		driver.switchTo().alert().accept();
		//Moving from popup to Default content
		driver.switchTo().defaultContent();
		synchronized (driver) {
			driver.wait(8000);}
		//Entering assignments details.
		driver.findElement(By.name("groupName")).clear();
		synchronized (driver) {
			driver.wait(2000);}
		driver.findElement(By.name("groupName")).sendKeys(assignmentname);
		new Select(driver.findElement(By.name("maxTakesNumber"))).selectByVisibleText("10");
		driver.findElement(By.name("startDateAnyTime")).click();
		driver.findElement(By.name("dueDateNever")).click();
		driver.findElement(By.cssSelector("input.button-space")).click();
		synchronized (driver) {
			driver.wait(5000);}
		driver.findElement(By.linkText("Return to Assignment List")).click();
		//Verify that assignment has created
		synchronized (driver) {
			driver.wait(5000);}
		//Verify that assignment has been created.
		assertTrue(isTextPresent(assignmentname));
		
		
	}
	@Test
	public void enrollStudent() throws Exception {
		synchronized (driver) {
			driver.wait(15000);}
		driver.switchTo().defaultContent();
		
		//Moving to Student Tab
		driver.findElement(By.linkText("Students")).click();
		synchronized (driver) {
			driver.wait(9000);}
		
		//Click on Drop down image to find course in which Student need to Enroll.
		driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div/div/div[2]/div[2]/div/div/div/table/tbody/tr/td[3]/img")).click();
		
		synchronized (driver) {
			driver.wait(9000);}
		//Selecting Course in which student has to enroll.
		driver.findElement(By.xpath("/html/body/div/div/div/div//div/span[2][text()=\""+coursename+"\"]")).click();
		synchronized (driver) {
			driver.wait(9000);}
		//Click on Enroll Button
		driver.findElement(By.cssSelector("img.button-space")).click();
		synchronized (driver) {
			driver.wait(9000);}
		driver.findElement(By.id("login")).clear();
		//Searching Student Name
		driver.findElement(By.id("login")).sendKeys(username);
		driver.findElement(By.cssSelector("input.button")).click();
		//Enrolling a Student
		synchronized (driver) {
			driver.wait(7000);}
		driver.findElement(By.id("enrollCB_1")).click();
		synchronized (driver) {
			driver.wait(9000);}
		driver.findElement(By.id("enroll")).click();
		
		
		
	}
	public static boolean isTextPresent(String text){
        try{
            boolean b = driver.getPageSource().contains(text);
            return b;
        }
        catch(Exception e){
            return false;
        }
    }

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
