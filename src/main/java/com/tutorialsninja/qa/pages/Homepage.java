package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	WebDriver driver;
@FindBy(xpath ="//span[text()='My Account']")
 private WebElement myAccountDropMenu;

@FindBy(linkText="Login")
private WebElement loginOption;	

@FindBy(linkText="Register")
private WebElement registerOption;

@FindBy(name="search")
private WebElement searchBoxField;	

@FindBy(xpath ="//div[@id='search']/descendant::button")
private WebElement searchButton;	

public Homepage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver,this);
}

public void clickOnMyAccount()
{
	myAccountDropMenu.click();
}
public Loginpage selectLoginOption()
{
	loginOption.click();
	return new Loginpage(driver);
}
public Loginpage navigateToLoginPage()
{
	myAccountDropMenu.click();
	loginOption.click();
	return new Loginpage(driver);
}
public RegisterPage selectRegisterOption()
{
	registerOption.click();
	return new RegisterPage(driver);
}
public RegisterPage navigateToRegisterPage()
{
	myAccountDropMenu.click();
	registerOption.click();
	return new RegisterPage(driver);
}
public void enterProductIntoSearchBoxField(String productText)
{
	searchBoxField.sendKeys(productText);
}
public Searchpage searchForAProduct(String productText)
{
	searchBoxField.sendKeys(productText);
	searchButton.click();
	return new Searchpage(driver);
	
}
public Searchpage  clickOnSearchButton()
{
	searchButton.click();
	return new Searchpage(driver);
}

}
