package com.ohrm.testCasesPage;
//Test Update 12:35PM from Nitish PC
// Test Update 8th June 6:26 pm from Nitish EXL pc
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ohrm.base.BaseClass;
import com.ohrm.pages.HomePage;
import com.ohrm.pages.LoginPage;

public class LoginPageTest extends BaseClass
{
	 private LoginPage loginpage;
	 private HomePage homepage;

	 
	 @BeforeMethod
	 public void setupPages()
	 {
		 loginpage = new LoginPage(getDriver());
		 homepage = new HomePage(getDriver());
	 }
	 
	 @Test
	 public void verify_validLogin()
	 {
		 		 
		 loginpage.make_validLogin(conProp.getProperty("validusername"),conProp.getProperty("validpassword"));
		 Assert.assertTrue(homepage.isAdminTabVisible() , "Admin tab should be visible after successful login");
		 homepage.clickOnLogout();
		 staticwait(2);
	 }
	 
	 
	 @Test
	 public void verify_InvalidLogin()
	 {		 
		 loginpage.make_validLogin(conProp.getProperty("invalidusername"),conProp.getProperty("invalidpassword"));
		 String expectedErrorMessage = "Invalid credentials";
		 Assert.assertTrue(loginpage.verifyErrorMessage(expectedErrorMessage), "Test Failed: Invalid error message");

//		 String actualErrorMessage = loginpage.getErrorMessageText();
//		 System.out.println("Comparing actual and expected error messages");
//		 Assert.assertTrue(actualErrorMessage.equals(expectedErrorMessage),"Test Case failed i.e. expected and actual error texts are different");
	 
	 }
	 
	 
}

