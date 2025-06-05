package com.ohrm.testCasesPage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ohrm.base.BaseClass;
import com.ohrm.pages.HomePage;
import com.ohrm.pages.LoginPage;

public class HomePageTest extends BaseClass
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
	 public void verify_OrangeHRMLogo()
	 {
		 loginpage.make_validLogin("admin", "admin123");
		 homepage.verifyOrangeHRMlogo();
		 Assert.assertTrue(homepage.verifyOrangeHRMlogo(), "Logo is not visible");
	 }
		 
	 
	 
	 
	 
	 
}

