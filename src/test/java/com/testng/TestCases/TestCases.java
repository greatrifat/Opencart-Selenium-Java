package com.testng.TestCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.testng.Base.BaseClass;

public class TestCases {
	BaseClass base = new BaseClass();
	
	@BeforeTest
	public void launchBrowser()
	{
		base.launchChromeBrowser();
	}
	
	@Test(priority = 0)
	public void loginFlow() {
		base.enterEmail();
		base.enterPassword();
		base.clickLoginBtn();
	}
	
	public void closeBrowser()
	{
		base.closeBrowser();
	}
	
	
}
