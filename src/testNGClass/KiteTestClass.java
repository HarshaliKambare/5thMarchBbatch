package testNGClass;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClass.BaseClasses;

import pomClass.KiteHomePage;
import pomClass.KiteLoginPage1;
import pomClass.KitePinPage;
import utilityClass.KiteUtility;

public class KiteTestClass extends BaseClasses{
   
      KiteLoginPage1 login;
	  KitePinPage pin;
	  KiteHomePage home;
	  @BeforeClass
	  public void launchBrowser()
	  {
	  openBrowser();
	  login=new KiteLoginPage1 (driver);
	  pin= new KitePinPage(driver);
	  home= new KiteHomePage(driver);
	  }
	  @BeforeMethod
	  public void loginToKiteApp() throws EncryptedDocumentException, IOException
	  {
	  login.sendUserName(KiteUtility.readDataFormExcel(0, 0));
	  login.sendPassword(KiteUtility.readDataFormExcel(0,1));
	  login.clickOnLoginButton();
	  driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
	  pin.sendPin(KiteUtility.readDataFormExcel(0, 2));
	  pin.clickOnContinueButton();
	  }
	  @Test
	  public void validateUserName() throws EncryptedDocumentException, IOException
	  {
	  String actualUserName = home.getActulUserID();
	  String expectedUserName=KiteUtility.readDataFormExcel(0, 0);
	  Assert.assertEquals(actualUserName, expectedUserName,"TC is FAILED actual and expected user names not matching");
	  Reporter.log("User names are matching TC is passed", true);
	  KiteUtility.takeScreenshot(driver);
	  }
	  @AfterMethod
	  public void logoutfromKiteApp() throws InterruptedException
	  {
	  home.clickOnLogoutButton();
	  }
	  @AfterClass
	  public void closeBrowser()
	  {
	  driver.close();
	  }
	
  }

