package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	WebDriver driver;
@FindBy(id="input-email")
private WebElement emailAddressField;

@FindBy(id="input-password")
private WebElement passwordField;

@FindBy(xpath="//input[@value='Login']")
private WebElement loginButton;

@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
private WebElement emailPasswordNotMatchingWarning;

public Loginpage(WebDriver driver)

{
	this.driver=driver;
	PageFactory.initElements(driver,this);
}
public void enterEmailAddress(String emailText)
{
	emailAddressField.sendKeys(emailText);
}
public void enterPassword(String passwordText)
{
	passwordField.sendKeys(passwordText);
}
public Accountpage clickOnLoginButton()
{
	loginButton.click();
	return new Accountpage(driver);
}
public Accountpage login(String emailText,String passwordText)
{
	emailAddressField.sendKeys(emailText);
	passwordField.sendKeys(passwordText);
	loginButton.click();
	return new Accountpage(driver);
}
public String retrieveEmailPasswordNotMatchingWarningMessageText()
{
	String warningText=emailPasswordNotMatchingWarning.getText();
	return warningText;
}
}
