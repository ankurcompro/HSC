package testautomation.testcases;

//import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;

public class MyeltLogin {
  private WebDriver driver;
  //private String baseUrl;
  //private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    //baseUrl = "http://change-this-to-the-site-you-are-testing/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMyeltLogin() throws Exception {
	  
	  
    driver.get("http://myelt2.comprotechnologies.com/");
    assertEquals("MyELT | Online English Language Learning", driver.getTitle());
    driver.findElement(By.id("loginDisplayField")).clear();
    driver.findElement(By.id("loginDisplayField")).sendKeys("admin_compro@myelt.com");
    driver.findElement(By.id("passwordDisplayField")).clear();
    driver.findElement(By.id("passwordDisplayField")).sendKeys("thomson");
    driver.findElement(By.name("submit")).click();
    assertEquals("MyELT System Check", driver.getTitle());
    
    synchronized (driver) {
		driver.wait(8000);				
	}
    
   // assertEquals("MyELT | Online English Language Learning | Home", driver.getTitle());
    driver.findElement(By.linkText("Sign Out")).click();
    assertEquals("MyELT | Online English Language Learning", driver.getTitle());
    assertEquals("MyELT | Online English Language Learning", driver.getTitle());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
/*
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  
  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
*/
}
