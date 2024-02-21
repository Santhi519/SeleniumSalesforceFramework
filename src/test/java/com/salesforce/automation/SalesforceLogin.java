package com.salesforce.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.BaseSalesforce;

public class SalesforceLogin extends BaseSalesforce {
	protected Logger AutomationSalesforceLoginlog=LogManager.getLogger();
    @Test
	public void LoginErrorMessage_TC1() {
		WebElement username=driver.findElement(By.id("username"));
		enterText(username, "santhik@salesforce.com", "username");
		WebElement password=driver.findElement(By.id("password"));
		enterText(password, "", "password");
		WebElement login=driver.findElement(By.id("Login"));
		clickElement(login, "Login");
		WebElement error=driver.findElement(By.id("error"));
		String error_msg=getTextFromElement(error, "Error Message");
		AutomationSalesforceLoginlog.info(""+error_msg);	
	}
    
    @Test
	public void LoginToSalesForce_TC2() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
	}
    
    @Test
	public void CheckRemeberMe_TC3() throws InterruptedException {
		WebElement username=driver.findElement(By.id("username"));
		enterText(username,"santhik@salesforce.com", "Username");
		WebElement password=driver.findElement(By.id("password"));
		enterText(password, "Welcome@123", "Password");
		WebElement remember_check=driver.findElement(By.id("rememberUn"));
		clickElement(remember_check, "Remember Me");
		WebElement login=driver.findElement(By.id("Login"));
		clickElement(login, "Login");
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement user_menu=driver.findElement(By.id("userNav"));
		clickElement(user_menu, "User menu");
		WebElement logout=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logout,"Logout");
		Thread.sleep(2000);
		String Exp_Title1="Login | Salesforce";
		Assert.assertEquals(getTitle(), Exp_Title1);
		validatetextwithactual(Exp_Title1, "Title of the Page", getTitle());
		WebElement name=driver.findElement(By.id("username"));
		String actual=name.getAttribute("value");
		String expected="santhik@salesforce.com";
		Assert.assertEquals(actual, expected);
	}
    
    @Test
	public void ForgotPassword_TC4A() {
		WebElement forgot_pwd=driver.findElement(By.id("forgot_password_link"));
		clickElement(forgot_pwd, "Forgot your password?");
		String Exp_Title="Forgot Your Password | Salesforce";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement username=driver.findElement(By.id("un"));
		enterText(username,"santhik@salesforce.com", "Username");
		WebElement submit=driver.findElement(By.id("continue"));
		clickElement(submit, "Continue");
		WebElement message=driver.findElement(By.className("message"));
		String msg=getTextFromElement(message,"Password reset Message" );
		AutomationSalesforceLoginlog.info(""+msg);
	}
    
   @Test
	public void ValidateLoginErrorMessage_TC4B() {
		WebElement username=driver.findElement(By.id("username"));
		enterText(username, "123", "Username");
		WebElement password=driver.findElement(By.id("password"));
		enterText(password, "22131", "Password");
		WebElement login=driver.findElement(By.id("Login"));
		clickElement(login, "Login");
		WebElement error=driver.findElement(By.id("error"));
		String msg=getTextFromElement(error,"Login Failure message" );
		AutomationSalesforceLoginlog.info(""+msg);

	}

}
