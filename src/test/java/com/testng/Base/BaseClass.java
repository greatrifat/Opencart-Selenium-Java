package com.testng.Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	
	WebDriver driver;
	public void launchChromeBrowser()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		try {
			driver.get("https://alokitofoundation.vercel.app/auth/signin");
			driver.manage().timeouts().implicitlyWait (Duration.ofSeconds(3));
			
		}catch(Exception e){
			System.out.println("An error occurred during login: " + e.getMessage());
		}
	}
	
	public void enterEmail()
	{
		driver.findElement(By.id("email")).sendKeys("greatrifat@gmail.com");
	}
	
	public void enterPassword()
	{
		driver.findElement(By.id("password")).sendKeys("1234");
	}
	
	public void clickLoginBtn()
	{
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	public void closeBrowser()
	{
		driver.close();
	}
}

