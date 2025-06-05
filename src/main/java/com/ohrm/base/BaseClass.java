package com.ohrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ohrm.actionDriver.Action;
import com.ohrm.utilities.LoggerManager;

public class BaseClass 
{
	
	protected static Properties conProp; //Made is static so that can run multiple class in testng.xml
	
//	protected static WebDriver driver;   //In order to use this outside the class, we need to use getter and setter method
//	private static Action action;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<Action> action = new ThreadLocal<>();
	
	public static final Logger logger = LoggerManager.getLogger(BaseClass.class);
	
	@BeforeSuite
	public void loadConfig() throws IOException
	{
//		Load the Config file
		
		conProp = new Properties();
		File conPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");

		FileInputStream conFis = new FileInputStream(conPropFile);
		conProp.load(conFis);
		logger.info("config.properties file loaded logger");
		

	}
	
	@BeforeMethod
	public synchronized void setup()
	{
		System.out.println("Setting the webdriver for : "+this.getClass().getSimpleName());
		launchBrowser();
		configureBroswer();
		staticwait(2);
		logger.info("WewbDriver initialized and Browser Maximized logger info ");
		logger.trace("Trace message logger");
		logger.error("Error message logger");
		logger.debug("Error message logger");
		logger.fatal("fatal message logger");
		logger.warn("warn message logger");
		
        /******		
		//Initialize action only once
		if(action == null)
		{
			action = new Action(driver);
//			System.out.println("Action instance is created");
			logger.info("Action instance is created "+Thread.currentThread().getId());
		}
	
        ******/
		
		//Initialize action for current thread
		
		action.set(new Action(getDriver()));
		logger.info("Action initialized for thread "+Thread.currentThread().getId());
	}
	
	
	private void launchBrowser()
	{
	String browserName = conProp.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
//			driver = new ChromeDriver(); 
			driver.set(new ChromeDriver()); //New changes as per Thread
			logger.info("Chrome driver Instance is created logger");
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
//			driver = new FirefoxDriver(); 
			driver.set(new FirefoxDriver()); //New changes as per Thread
		}		
		else if(browserName.equalsIgnoreCase("edge"))
		{
//			driver = new EdgeDriver();
			driver.set(new EdgeDriver());   //New changes as per Thread
		}
		else
		{
			System.out.println("Invalid broswer entry");
		}
	}
	
	private void configureBroswer()
	{

		
//		Implicit wait
		int implicit_Wait = Integer.parseInt(conProp.getProperty("implicitwait"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit_Wait));
//		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit_Wait)); or write below
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit_Wait));
		
//		maximize the browser
//		driver.manage().window().maximize();
//		driver.get().manage().window().maximize(); or write below
		getDriver().manage().window().maximize();
		
//		Navigate to url
//		driver.get(conProp.getProperty("url"));
//		driver.get().get(conProp.getProperty("url")); or write below
		getDriver().get(conProp.getProperty("url"));
		
		
	}
	
	@AfterMethod
	public synchronized void teardown()
	{
		if(getDriver()!=null)
		{
			getDriver().quit();
		}
//		System.out.println("Webdrive instance is closed");
		logger.info("Webdrive instance is closed logger");
//		driver=null;
//		action=null;
		driver.remove();
		action.remove();
		
	}
	
//	Static wait for Pause
	public void staticwait(int seconds)
	{
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
	
/**	
	// driver getter method
	public WebDriver getDriver()
	{
		return driver;
	}
**/
	// driver getter method
	public static WebDriver getDriver()
	{
		if(driver.get()==null)
		{
			System.out.println("Webdriver is not initialized");
			throw new IllegalStateException("Webdriver is not initialized illegal"); 
			
		}
		return driver.get();
	}
	
	// driver setter method
	public void setDriver(ThreadLocal<WebDriver> driver)
	{
		this.driver = driver;	
	}
	
	// action getter method
	public static Action getActionDriver()
	{
		if(action.get()==null)
		{
			System.out.println("actiondriver is not initialized");
			throw new IllegalStateException("actiondriver is not initialized illegal"); 
			
		}
		return action.get();
	}
	
	// conProp getter method
	public static Properties getconProp()
	{
		return conProp;
	}
	

	
}


