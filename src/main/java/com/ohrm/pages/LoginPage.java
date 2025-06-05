package com.ohrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ohrm.actionDriver.Action;
import com.ohrm.base.BaseClass;

public class LoginPage 
{
	private Action action;
	
	//Define Locators
	private By usernameField = By.xpath("//input[@placeholder='Username']");
	private By passwordField = By.xpath("//input[@placeholder='Password']");
	private By loginButton = By.xpath("//button[normalize-space()='Login']");
	private By actualErrorMessage = By.xpath("//p[text()='Invalid credentials']");

	/**
	//Initialize Action object by passing WebDriver instance
	public LoginPage(WebDriver driver) 
	{
		this.action = new Action(driver);
		
	}
	**/
	//Initialize Action object by passing WebDriver instance
		public LoginPage(WebDriver driver) 
		{
			this.action = BaseClass.getActionDriver();
			
		}
	
	
	@Test
	//Method to perform login
	public void make_validLogin(String username, String password)
	{
		action.enterText_inInputField(usernameField, username);
		action.enterText_inInputField(passwordField, password);
		action.click_element(loginButton);
	}
	
	//Method to check if error message is displayed
	public boolean isErrorMessageDisplayed()
	{
		return action.isDisplayed(actualErrorMessage);
	}
	
	//Method to get text from error
	public String getErrorMessageText()
	{
		String text = action.getText_fromField(actualErrorMessage);
		System.out.println(text);
		return text;
//		return action.getText_fromField(actualRErrorMessage);
		
		
	}
	
	//Verify if error is correct or not
	public boolean verifyErrorMessage(String expectedError)
	{
		System.out.println("Login page method -->"+getErrorMessageText());
		
		action.compareText(actualErrorMessage, expectedError);
//		Assert.assertTrue(getErrorMessageText().equals(expectedError));
		return true;
	}
	
}


