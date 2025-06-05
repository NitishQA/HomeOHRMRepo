package com.ohrm.actionDriver;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ohrm.base.BaseClass;
import com.ohrm.base.BaseClass.*;

public class Action {
	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger;

	public Action(WebDriver driver) {
		this.driver = driver;
		int explicitWait = Integer.parseInt(BaseClass.getconProp().getProperty("explicitwait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		System.out.println("WebDriver instance is created of Action class constructor");
		logger.info("WebDriver instance is created of Action class constructor logger info");
	}

	// Method to Click an Element
	public void click_element(By by) {
		String elementDescription = getElementDescription(by);
		try {
			waitForElement_toBeClickable(by);
			driver.findElement(by).click();
			logger.info("Clicked an element logger info : "+elementDescription);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to Click element : " + e.getMessage());
			logger.error("Unable to click element logger error");
		}
	}

	// Wait for element to be clickable
	private void waitForElement_toBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element is not clickable : " + e.getMessage());
		}
	}

	// Wait for element to be visible
	private void waitForElement_toBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element is not visible : " + e.getMessage());
		}
	}

	// Method to enter Text in Input field
	public void enterText_inInputField(By by, String value) {
		try {
			waitForElement_toBeVisible(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
			logger.info("Entered text on :"+getElementDescription(by)+" --> "+value);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			System.out.println("Unable to enter the value : " + e.getMessage());
			logger.error("Unable to enter the value : "+e.getMessage());
		}
	}

	// Method to getText from input field
	public String getText_fromField(By by) {
		try {
			waitForElement_toBeVisible(by);
			String textValue = driver.findElement(by).getText();
			return textValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to get the text : " + e.getMessage());
			return "";
		}

	}

	// Method to compare text
	public boolean compareText(By by, String expectedText) {
		try {
			waitForElement_toBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (expectedText.equals(actualText)) {
				logger.info("Texts are matching: " + actualText + " equals " + expectedText);
//				System.out.println("Texts are matching: " + actualText + " equals " + expectedText);
			} else {
				logger.info("Texts are not matching: " + actualText + " not equals " + expectedText);
//				System.out.println("Texts are not matching: " + actualText + " not equals " + expectedText);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unable to compare texts " + e.getMessage());
//			System.out.println("Unable to compare texts " + e.getMessage());
		}

		return false;
	}

	/**
	// Method to check if Element in displayed
	public boolean isDisplayed(By by) {
		try {
			waitForElement_toBeVisible(by);
			boolean is_displayed = driver.findElement(by).isDisplayed();
			if (is_displayed) {
				System.out.println("Element is displayed");
				return is_displayed;
			} else {
				System.out.println("Element is not displayed : ");
				return is_displayed;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element is not displayed: " + e.getMessage());
			return false;
		}
	}
	**/
	
	// Method to check if Element in displayed
	public boolean isDisplayed(By by) 
	{
		try {
			waitForElement_toBeVisible(by);
			boolean element = driver.findElement(by).isDisplayed();
			logger.info("Element is displayed : "+getElementDescription(by));
			return element;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Element is not displayed : "+e.getMessage());
			return false;
		}
		
	}
	

	// Scroll to element
	public void scrolltoElement(By by) {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			System.out.println("Unable to scroll to elements : " + e.getMessage());
		}
	}

	// Method to get the description of an element using By locator
	public String getElementDescription(By locator) 
	{
		if (driver == null)

			return "driver is null";

		if (locator == null)

			return "locator is null";

		
		
		
		// Find the element using the locator
		WebElement webElement = driver.findElement(locator);

		// Get element Attribute
		String name = webElement.getDomAttribute("name");
		String id = webElement.getDomAttribute("id");
		String text = webElement.getText();
		String className = webElement.getDomAttribute("class");
		String placeholder = webElement.getDomAttribute("placeholder");
		
		try 
		{
			// Return the description based on element attributes
			if(isNotEmpty(name))
			{
				return "Element with name : "+name;
			}
			else if(isNotEmpty(id))
			{
				return "Element with id : "+id;
			}
			else if(isNotEmpty(text))
			{
				return "Element with text : "+truncate(text, 50);
			}
			else if(isNotEmpty(className))
			{
				return "Element with className : "+className;
			}
			else if(isNotEmpty(placeholder))
			{
				return "Element with placeholder : "+placeholder;
			}
		} 
		catch (Exception e) 
		{
			logger.error("Unable to describe the element : "+e.getMessage());	
		}
		
		return "Unable to describe the element";
			

	}
	
	//Utility method to check a String is empty or not
	private boolean isNotEmpty(String value)
	{
		return value!=null && !value.isEmpty();
	}
	
	//Utility method to truncate long String
	private String truncate (String value, int maxlength)
	{
		if(value==null || value.length()<=maxlength)
		{
			return value;
		}
		return value.substring(0, maxlength)+"....";
	}

}
