package com.testng.Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {
	ChromeOptions options = new ChromeOptions();
	WebDriver driver;
	public void launchChromeBrowser()
	{
		options.addArguments("--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		try {
			driver.get("http://opencart.abstracta.us/");
			driver.manage().timeouts().implicitlyWait (Duration.ofSeconds(3));
			
		}catch(Exception e){
			System.out.println("An error occurred during login: " + e.getMessage());
		}
	}
	
	public void registerPage()
	{
		WebElement dropdown = driver.findElement(By.cssSelector("li.dropdown > a.dropdown-toggle"));
	    dropdown.click(); 

	    // Locate and click the "Register" button
	    WebElement registerButton = driver.findElement(By.xpath("//a[contains(@href, 'account/register')]"));
	    registerButton.click(); 
	}
	
	public void loginPage()
	{
		WebElement dropdown = driver.findElement(By.cssSelector("li.dropdown > a.dropdown-toggle"));
	    dropdown.click(); 

	    // Locate and click the "Register" button
	    WebElement registerButton = driver.findElement(By.xpath("//a[contains(@href, 'account/login')]"));
	    registerButton.click(); 
	}
	
	
	
	public void personalDetails()
	{
		driver.findElement(By.id("input-firstname")).sendKeys("Robayet");
		driver.findElement(By.id("input-lastname")).sendKeys("Ahasan");
		driver.findElement(By.id("input-email")).sendKeys("greatrifat@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("01700000000");
		driver.findElement(By.id("input-password")).sendKeys("GreatRifat@2");
		driver.findElement(By.id("input-confirm")).sendKeys("GreatRifat@2");
		
		WebElement checkbox = driver.findElement(By.xpath("//input[@name='agree']"));
		checkbox.click();
		
		WebElement continueBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		continueBtn.click();
	}
	
	public void login()
	{
		driver.findElement(By.id("input-email")).sendKeys("greatrifat@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("GreatRifat@2");
		
		WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		loginBtn.click();
	}
	
	
	public void closeBrowser()
	{
		driver.close();
	}
}

