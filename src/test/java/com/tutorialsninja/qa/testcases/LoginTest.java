package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.Accountpage;
import com.tutorialsninja.qa.pages.Homepage;
import com.tutorialsninja.qa.pages.Loginpage;
import com.tutorialsninja.qa.utils.Utilities;

//modified Login-modification is done

public class LoginTest extends Base{
	
	public LoginTest()
	{
		super();
	}
	public WebDriver driver;
	Loginpage loginpage;
	@BeforeMethod
	public void setup()
	{
        driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        Homepage homepage=new Homepage(driver);
		loginpage=homepage.navigateToLoginPage();
		
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1,dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email,String password)
	{
	    
	    Accountpage accountpage=loginpage.login(email, password);
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information message is not displayed");
		
		}
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data= Utilities.getTestDataFromExcel("Login");
		return data;
		}
		
	
	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials()
	{
		 loginpage.login(Utilities.genrateEmailwithTimeStamp(),dataprop.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")), "Expected warning message is not displayed");
		
		}
	@Test(priority = 3)
	public void  verifyLoginWithInvalidEmailAndValidPassword()
	{
		    loginpage.login(Utilities.genrateEmailwithTimeStamp(), prop.getProperty("validPassword"));
		    Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")), "Expected warning message is not displayed");
		
	}
	@Test(priority = 4)
	public void  verifyLoginWithvalidEmailAndInvalidPassword()
	{
		loginpage.login(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")), "Expected warning message is not displayed");
		
	}
	@Test(priority = 5)
	public void verifyWithoutProvidingCredentials()
	{
	
	    loginpage.clickOnLoginButton();
		Assert.assertTrue(loginpage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")), "Expected warning message is not displayed");

		
		
	}
	
}
