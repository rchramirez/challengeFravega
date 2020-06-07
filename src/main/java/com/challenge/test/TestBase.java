package com.challenge.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.challenge.framework.BrowserFactory;

public class TestBase {

	public static WebDriver driver;
	BrowserFactory obj1;
	
	public TestBase() {
	}
	
	@BeforeSuite
	public void beforeSuite() {
		
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("in @BeforeClass");
	}
	
	@BeforeMethod
	public void beforeMethodClass() {
		System.out.println("in @BeforeMethod");
		TestBase.driver = BrowserFactory.getDriver("chrome");
	}
	
	@AfterMethod
	public void close() {
		//this.driver.close();
	}
	
	@AfterClass
	public void afterClass() {
		
	}
	
	@AfterSuite
	public void afterSuite() throws IOException {
		driver.quit();
	}
}
