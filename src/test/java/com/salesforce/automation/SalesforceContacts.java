package com.salesforce.automation;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.BaseSalesforce;

public class SalesforceContacts extends BaseSalesforce {
	protected Logger AutomationSalesforceContactslog=LogManager.getLogger();
	@Test
	public void CreateNewContact_TC25() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement con_tab=driver.findElement(By.id("Contact_Tab"));
		clickElement(con_tab, "Contact Tab");
		String Exp_Title1="Contacts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement new_btn=driver.findElement(By.xpath("//input[@name='new']"));
		clickElement(new_btn, "New");
		String Exp_Title2="Contact Edit: New Contact ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement last_name=driver.findElement(By.id("name_lastcon2"));
		enterText(last_name,"John", "Last name");
		WebElement account_name=driver.findElement(By.id("con4_lkwgt"));
		clickElement(account_name, "Account LookUp");
		String parentWindow = driver.getWindowHandle();  // to get current window handle
		switchToNewWindowFrom(parentWindow);
		driver.switchTo().frame("resultsFrame");			
		WebElement lookup_account=driver.findElement(By.xpath("//a[contains(text(),'Edge Communications')]"));
		clickElement(lookup_account, "Look Up Account");
		driver.switchTo().window(parentWindow); 
		AutomationSalesforceContactslog.info("Switched back to parent window");
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		String Exp_Title3="Contact: John ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title3);
	}
	//Change different view unique name -used Random Number generator for this
	@Test
	public void CreateNewViewContact_TC26() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement con_tab=driver.findElement(By.id("Contact_Tab"));
		clickElement(con_tab, "Contact Tab");
		String Exp_Title1="Contacts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement new_view=driver.findElement(By.linkText("Create New View"));
		waitForVisibility(new_view, 20, "Create New View");
		clickElement(new_view, "Create New view");
		String Exp_Title2="Contacts: Create New View ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement view_name=driver.findElement(By.id("fname"));
		enterText(view_name,"My Custom View", "View Name");   
		WebElement unique_name=driver.findElement(By.id("devname"));
		AutomationSalesforceContactslog.info("View Unique Name is displayed? "+unique_name.isDisplayed());
		clickElement(unique_name, "View Unique name textbox");
		clearElement(unique_name, "Unique View name");
		Random rand = new Random();
		int rand_int = rand.nextInt(100);
		enterText(unique_name,"My_Custom_View"+rand_int, "unique name");
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		WebElement view_list1=driver.findElement(By.name("fcf"));
		waitForVisibility(view_list1, 30, "List View");
		Select sel=new Select(view_list1);
		String actual=getTextFromElement(sel.getFirstSelectedOption(), "Newly Created View");
		String expected="My Custom View";
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void validatecreatedcontact_TC27() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement con_tab=driver.findElement(By.id("Contact_Tab"));
		clickElement(con_tab, "Contact Tab");
		String Exp_Title1="Contacts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement recent_create=driver.findElement(By.id("hotlist_mode"));
		selectByTextData(recent_create, "Recently Created", "Recently Created");
		Select sel=new Select(recent_create);
		String actual=getTextFromElement(sel.getFirstSelectedOption(), "Recently Created");
		String expected="Recently Created";
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void MyContactsViewTC_28() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement con_tab=driver.findElement(By.id("Contact_Tab"));
		clickElement(con_tab, "Contact Tab");
		String Exp_Title1="Contacts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement contact_list=driver.findElement(By.id("fcf"));
		selectByTextData(contact_list, "My Contacts", "My Contacts View");
		WebElement view_list1=driver.findElement(By.name("fcf"));
		waitForVisibility(view_list1, 30, "List View");
		Select sel=new Select(view_list1);
		String actual=getTextFromElement(sel.getFirstSelectedOption(), "My Contacts");
		String expected="My Contacts";
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void viewcontact_TC29() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement con_tab=driver.findElement(By.id("Contact_Tab"));
		clickElement(con_tab, "Contact Tab");
		String Exp_Title1="Contacts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement recent_con=driver.findElement(By.linkText("John")); 
		clickElement(recent_con, "Recent Contact-1");
		String Exp_Title2="Contact: John ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
	}
	@Test
	public void errormsgcontactview_TC30() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement con_tab=driver.findElement(By.id("Contact_Tab"));
		clickElement(con_tab, "Contact Tab");
		String Exp_Title1="Contacts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement new_view=driver.findElement(By.linkText("Create New View"));
		waitForVisibility(new_view, 20, "Create New View");
		clickElement(new_view, "Create New view");
		String Exp_Title2="Contacts: Create New View ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement unique_name=driver.findElement(By.id("devname"));
		enterText(unique_name,"EFGH","View Unique Name");
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		WebElement error=driver.findElement(By.className("errorMsg"));
		String expected="Error: You must enter a value";
		String actual=getTextFromElement(error, "Error Message");
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void cancelcreatenewview_TC31() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement con_tab=driver.findElement(By.id("Contact_Tab"));
		clickElement(con_tab, "Contact Tab");
		String Exp_Title1="Contacts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement new_view=driver.findElement(By.linkText("Create New View"));
		waitForVisibility(new_view, 20, "Create New View");
		clickElement(new_view, "Create New view");
		String Exp_Title2="Contacts: Create New View ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement view_name=driver.findElement(By.id("fname"));
		enterText(view_name,"ABCD","View name");
		WebElement unique_name=driver.findElement(By.id("devname"));
		enterText(unique_name,"EFGH", "View Unique Name");
		WebElement cancel=driver.findElement(By.name("cancel"));
		clickElement(cancel, "Cancel");
		String Exp_Title3="Contacts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title3);
	}
	@Test
	public void save_New_TC32(){
		login_salesforce();
		waitUntilPageLoads();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement con_tab=driver.findElement(By.id("Contact_Tab"));
		clickElement(con_tab, "Contact Tab");
		String Exp_Title1="Contacts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement new_btn=driver.findElement(By.xpath("//input[@name='new']"));
		clickElement(new_btn, "New");
		String Exp_Title2="Contact Edit: New Contact ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement last_name=driver.findElement(By.id("name_lastcon2"));
		enterText(last_name,"Indian","Last Name");
		WebElement account_name=driver.findElement(By.id("con4_lkwgt"));
		clickElement(account_name, "Account LookUp");
		String parentWindow = driver.getWindowHandle();  // to get current window handle
		switchToNewWindowFrom(parentWindow);
		driver.switchTo().frame("resultsFrame");			
		WebElement lookup_account=driver.findElement(By.xpath("//a[contains(text(),'Tekarch')]"));
		clickElement(lookup_account, "Look Up Account");
		driver.switchTo().window(parentWindow); 
		AutomationSalesforceContactslog.info("Switched back to parent window");
		WebElement save_new=driver.findElement(By.name("save_new"));
		clickElement(save_new, "Save&New");
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement recent_contact=driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[1]/div/div[2]/div[2]/div[2]/div[1]/div/a/span"));
		clickElement(recent_contact, "Indian");
		String Exp_Title3="Contact: Indian ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title3);
	}

}
