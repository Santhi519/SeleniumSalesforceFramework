package com.salesforce.base;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.salesforce.utilities.Constants;
import com.salesforce.utilities.PropertiesUtility;

public class BaseSalesforce extends BaseTest {
	
protected Logger BaseSalesforcelog=LogManager.getLogger();
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeMethod(@Optional("chrome") String name) {
		BaseSalesforcelog.info(".........BeforeMethod setUpBeforeMethod executed---------------");
		launchBrowser("chrome");
		String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "url");
		goToUrl(url);
	}
	
	@AfterMethod
	public void tearDownAfterTestMethod() {
		closeBrowser();
		BaseSalesforcelog.info("******tearDownAfterTestMethod executed***********");
	}
	//SALESFORCE Reusable Login method
			public void login_salesforce(){
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				WebElement username=driver.findElement(By.id("username"));
				enterText(username, "santhik@salesforce.com", "Username");
				WebElement password=driver.findElement(By.id("password"));
				enterText(password, "Welcome@123", "Password");
				WebElement login=driver.findElement(By.id("Login"));
				clickElement(login, "Login Button");
				
			}
}
