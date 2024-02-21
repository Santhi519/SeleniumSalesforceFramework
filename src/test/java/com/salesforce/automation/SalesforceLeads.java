package com.salesforce.automation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.BaseSalesforce;

public class SalesforceLeads extends BaseSalesforce {
	protected Logger AutomationSalesforceLeadslog=LogManager.getLogger();
	
	@Test
   public void CheckLeadsTab_TC20() throws InterruptedException {
		login_salesforce();
		Thread.sleep(2000);
		WebElement leads=driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leads, "Leads Tab");
		String Exp_Title="Leads: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
   }
   @Test
   public void LeadsSelectView_TC21() throws InterruptedException {
	    login_salesforce();
	    Thread.sleep(2000);
		WebElement leads=driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leads, "Leads Tab");
		String Exp_Title="Leads: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String[] exp_list= {"All Open Leads", "My Unread Leads", "Recently Viewed Leads", "Today's Leads", "View - Custom 1", "View - Custom 2"};
		WebElement drop=driver.findElement(By.id("fcf"));
		Select select=new Select(drop);
		List<WebElement> view_list=select.getOptions();  
		validatedropdownoptions(view_list, exp_list);
   }
   @Test
   public void ValidateDefaultView_TC22() throws InterruptedException {
		login_salesforce();
		Thread.sleep(2000);
		WebElement leads=driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leads, "Leads Tab");
		String Exp_Title="Leads: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement view_list=driver.findElement(By.id("fcf"));
		selectByTextData(view_list,"My Unread Leads", "Leads List View");
		AutomationSalesforceLeadslog.info("My Unread Leads is selected from dropdown");
		WebElement user_menu=driver.findElement(By.id("userNav"));
		clickElement(user_menu, "User menu");
		WebElement logout=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logout,"Logout");
		Thread.sleep(3000);
		String Exp_Title1="Login | Salesforce";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement name=driver.findElement(By.id("username"));
		clearElement(name, "User Name");
		enterText(name, "santhik@salesforce.com","Username");
		WebElement password_1=driver.findElement(By.id("password"));
		clearElement(password_1, "Password");
		enterText(password_1, "Welcome@123", "Password");
		WebElement re_login=driver.findElement(By.id("Login"));
		clickElement(re_login, "Login Button");
		Thread.sleep(1000);
		WebElement leads_tab=driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leads_tab, "Leads Tab");
		String Exp_Title2="Leads: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement go=driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[1]/input"));
		clickElement(go, "Go Button");
		Thread.sleep(3000);
		WebElement view_list1=driver.findElement(By.id("00Bal000000ET6i_listSelect"));
		Select sel=new Select(view_list1);
		String actual=getTextFromElement(sel.getFirstSelectedOption(), "Default List View");
		String expected="My Unread Leads";
		Assert.assertEquals(actual, expected);
   }
   @Test
   public void ValidateselectedView_TC23() throws InterruptedException {
		login_salesforce();
		Thread.sleep(2000);
		WebElement leads=driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leads, "Leads Tab");
		String Exp_Title="Leads: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement view_list=driver.findElement(By.id("fcf"));
		selectByTextData(view_list,"Today's Leads","List View");
		AutomationSalesforceLeadslog.info("Today's Leads is selected from dropdown");
		Thread.sleep(5000);
		WebElement view_list1=driver.findElement(By.id("00Bal000000ET6w_listSelect"));
		Select sel=new Select(view_list1);
		String actual=getTextFromElement(sel.getFirstSelectedOption(), "Selected List view");
		String expected="Today's Leads";
		Assert.assertEquals(actual, expected);
   }
   @Test
   public void newleadbutton_TC24() throws InterruptedException {
	   login_salesforce();
	   Thread.sleep(2000);
		WebElement leads=driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leads, "Leads Tab");
		String Exp_Title="Leads: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement new_btn=driver.findElement(By.name("new"));
		clickElement(new_btn, "New Button");
		String Exp_Title1="Lead Edit: New Lead ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement lastname=driver.findElement(By.id("name_lastlea2"));
		enterText(lastname, "ABCD", "Lastname");
		WebElement com_name=driver.findElement(By.id("lea3"));
		enterText(com_name, "ABCD", "Company name");
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save Button");
		Thread.sleep(1000);
		WebElement new_lead=driver.findElement(By.className("topName"));
		String actual=getTextFromElement(new_lead, "Lead name");
		String expected="ABCD";
		Assert.assertEquals(actual, expected);
   }

}
