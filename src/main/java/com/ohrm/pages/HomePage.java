package com.ohrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.ohrm.actionDriver.Action;
import com.ohrm.base.BaseClass;

public class HomePage 
{
	private Action action;
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIDButton = By.className("oxd-userdropdown-name");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By oranageHRMlogo = By.xpath("//div[@class='oxd-brand-banner']//img");

	/**
	//Initialize Action object by passing WebDriver instance
	public HomePage(WebDriver driver) 
	{
		this.action = new Action(driver);
	}
	**/
	                                                                                                                                             
	//Initialize Action object by passing WebDriver instance
	public HomePage(WebDriver driver) 
	{
		this.action = BaseClass.getActionDriver();
		
	}

	// Method to verify if Admin tab is visible
	public boolean isAdminTabVisible() 
	{
		return action.isDisplayed(adminTab);
	}

	public boolean verifyOrangeHRMlogo() {
		return action.isDisplayed(oranageHRMlogo);
	}

	// Method to perform logout
	public void clickOnLogout() {
		action.click_element(userIDButton);
		action.click_element(logoutButton);
	}
}
