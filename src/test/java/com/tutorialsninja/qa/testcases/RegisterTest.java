package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.Homepage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	public RegisterTest()
	{
		super();
	}
	public WebDriver driver;
	RegisterPage registerpage;
	AccountSuccessPage accountSuccessPage;
	@BeforeMethod
	public void setup()
	{
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        Homepage homepage=new Homepage(driver);
		registerpage=homepage.navigateToRegisterPage();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1) 
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
		accountSuccessPage= registerpage.registerWithMandatoryFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), Utilities.genrateEmailwithTimeStamp(), dataprop.getProperty("contactNumber"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataprop.getProperty("accountSucessfullyCreatedHeading"),"Account success page is not displayed");
		
	}
	
	@Test(priority = 2) 
	public void verifyRegisteringAccountByProvidingAllFields()
	{

		accountSuccessPage=registerpage.registerWithAllFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), Utilities.genrateEmailwithTimeStamp(), dataprop.getProperty("contactNumber"), prop.getProperty("validPassword")); 
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(), dataprop.getProperty("accountSucessfullyCreatedHeading"),"Account success page is not displayed");

	}
	
	@Test(priority = 3) 
	public void verifyRegisteringAccountWithExistingEmailAddress()
	{
	
		registerpage.registerWithAllFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), prop.getProperty("validEmail"), dataprop.getProperty("contactNumber"), prop.getProperty("validPassword")); 
		Assert.assertTrue(registerpage.retrieveDuplicateEmailAddressWarning().contains(dataprop.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate e-mail Address is not displayed");
		
		
	}
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{
		registerpage.clickOnContinueButton();
		Assert.assertTrue(registerpage.displayStausOfWarningMessages(dataprop.getProperty("privacyPolicyWarning"),dataprop.getProperty("firstNameWarning"),dataprop.getProperty("lastNameWarning"),dataprop.getProperty("emailWarning"),dataprop.getProperty("telephoneWarning"),dataprop.getProperty("passwordWarning")));

		
	
	}
	
	
}
