package com.ohrm.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DummyClassTest extends BaseClass{
	@Test
	public void Dummytest()
	{
//        String title = driver.getTitle();
		String title = getDriver().getTitle();  //New changes as per Thread
        assert title.equals("OrangeHRM"):"Test failed Title is not matching";
        System.out.println("Test Passed Title is matching");
//        WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        WebElement username = getDriver().findElement(By.xpath("//input[@placeholder='Username']"));
        username.sendKeys("Admin");
        
//        driver.findElement(By.xpath("//input[@placeholder='Password']"));
        getDriver().findElement(By.xpath("//input[@placeholder='Password']"));
//        driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        getDriver().findElement(By.xpath("//button[normalize-space()='Login']"));
        
        
   
    }
}
