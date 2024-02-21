package com.salesforce.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.salesforce.base.BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SalesforcechropathTest extends BaseTest{
	
	public void chropathTest() throws InterruptedException {
		launchBrowser("chrome");
		goToUrl("https://login.salesforce.com/");
		Thread.sleep(1000);
		WebElement username=driver.findElement(By.xpath("//input[@id='username']"));
		enterText(username, "santhik@salesforce.com", "Username");
		WebElement password=driver.findElement(By.xpath("//input[@id='password']"));
		enterText(password, "Welcome@123", "Password");
		WebElement login=driver.findElement(By.xpath("//input[@id='Login']"));
		clickElement(login, "Login Button");
		closeBrowser();
		
	}
	public void headlessTest() {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com/");
		System.out.println("Title is: " +driver.getTitle());
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		SalesforcechropathTest ob=new SalesforcechropathTest();
		ob.chropathTest();
		ob.headlessTest();

	}

}
