package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.Homepage;
import com.tutorialsninja.qa.pages.Searchpage;
//Updated comment
public class SearchTest extends Base {
	public SearchTest()
	{
		super();
	}
	public WebDriver driver;
	Searchpage searchpage;
	Homepage homepage;
	@BeforeMethod
	public void setup()
	{
		driver=intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homepage=new Homepage(driver);
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test(priority = 1)
	public void  verifySearchWithValidProduct()
	{
	    searchpage=homepage.searchForAProduct(dataprop.getProperty("validProduct"));
		Assert.assertTrue(searchpage.DisplayStatusOFHPValidProduct(),"Valid product HP is not displayed in the search result");
	}
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct()
	{
		 searchpage=homepage.searchForAProduct(dataprop.getProperty("InvalidProduct"));
		Assert.assertEquals(searchpage.retrieveNoProductMessageText(),"abcd","No product message in search result is not displayed");
	}
@Test(priority = 3,dependsOnMethods = {"verifySearchWithValidProduct","verifySearchWithInvalidProduct"})
public void verifySearchWithoutAnyProduct()
{

	searchpage=homepage.clickOnSearchButton();
	Assert.assertEquals(searchpage.retrieveNoProductMessageText(),dataprop.getProperty("NoProductTextInSearchResults"),"No product message in search result is not displayed");
	
}
	
}
