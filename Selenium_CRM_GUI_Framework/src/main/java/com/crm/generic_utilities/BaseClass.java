package com.crm.generic_utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	public WebDriver driver = new ChromeDriver();
	@BeforeSuite
	public void configBS() {
		System.out.println("Connect to DB, Report Config");
	}
	
	@AfterSuite
	public void configAS() {
		System.out.println("Close DB, Report Backup");
	}
	
	@BeforeClass
	public void configBC() {
		System.out.println("Launch the browser");
	}
	
	@AfterClass
	public void configAC() {
		System.out.println("Close the browser");
	}

	@BeforeMethod
	public void configBM() {
		System.out.println("Login to application");
	}
	
	@AfterMethod
	public void configAM() {
		System.out.println("Logout from application");
	}
}
