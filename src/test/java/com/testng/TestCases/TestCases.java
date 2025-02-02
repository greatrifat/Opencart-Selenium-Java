package com.testng.TestCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import com.testng.Base.BaseClass;

public class TestCases {
	BaseClass base = new BaseClass();
	
	@BeforeTest
	public void launchBrowser()
	{
		base.launchChromeBrowser();
	}
	
	@Test(enabled=false)
	public void registerAccount() {
		base.registerPage();
		base.personalDetails();
	}  
	
	@Test(priority = 0)
	public void loginTest() {
		base.loginPage();
		base.login();
	}
	
	@AfterTest
	public void closeBrowser()
	{
		base.closeBrowser();
	}
	
	
}
