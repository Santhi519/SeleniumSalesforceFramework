package com.salesforce.automation;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.BaseSalesforce;

public class SalesforceAccount extends BaseSalesforce {
	protected Logger AutomationSalesforceAccountlog=LogManager.getLogger();
	
	//Testcase for Removing Accounts tab from Menu bar for TC10a
	@Test(priority = 1)
	public void removeaccountstab() throws InterruptedException {
		login_salesforce();
		List<WebElement> tabs=driver.findElements(By.xpath("//ul[@id='tabBar']/li/a[contains(text(),'Accounts')]"));
		if(tabs.size()>0) {
			AutomationSalesforceAccountlog.info("Accounts Tab is displayed");
			WebElement tab=driver.findElement(By.className("allTabsArrow"));
			clickElement(tab, "+");
			WebElement custom_tab=driver.findElement(By.className("btnImportant"));
			clickElement(custom_tab, "Customize my Tabs");
			WebElement account=driver.findElement(By.id("duel_select_1"));
			selectByTextData(account, "Accounts", "Accounts");
			Thread.sleep(2000);
			WebElement remove=driver.findElement(By.id("duel_select_0_left"));
			clickElement(remove, "Remove");
			WebElement save=driver.findElement(By.name("save"));
			clickElement(save, "Save");
			AutomationSalesforceAccountlog.info("Now the Accounts Tab is removed");
		}else
			AutomationSalesforceAccountlog.info("Accounts Tab is removed");
	}
	
	@Test(dependsOnMethods ="removeaccountstab" )
	public void CreateAccountTab_TC10a() throws InterruptedException {
		login_salesforce();
		WebElement tab=driver.findElement(By.className("allTabsArrow"));
		clickElement(tab, "+");
		String Exp_Title="All Tabs ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement custom_tab=driver.findElement(By.className("btnImportant"));
		clickElement(custom_tab, "Customize my Tabs");
		String Exp_Title1="Customize My Tabs ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement account=driver.findElement(By.id("duel_select_0"));
		selectByTextData(account, "Accounts", "Accounts");
		Thread.sleep(2000);
		WebElement add=driver.findElement(By.id("duel_select_0_right"));
		clickElement(add, "Add");
		WebElement account_sel=driver.findElement(By.id("duel_select_1"));
		Select select1=new Select(account_sel);
		String Exp_text="Accounts";
		String act_text=getTextFromElement(select1.getFirstSelectedOption(),"Selected List");  
		Assert.assertEquals(act_text, Exp_text);
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement user_menu=driver.findElement(By.id("userNav"));
		clickElement(user_menu, "User menu");
		WebElement logout=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logout,"Logout");
		Thread.sleep(1000);
		String Exp_Title2="Login | Salesforce";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement name=driver.findElement(By.id("username"));
		clearElement(name, "User name");
		enterText(name, "santhik@salesforce.com","Username");
		WebElement password_1=driver.findElement(By.id("password"));
		clearElement(password_1, "Password");
		enterText(password_1, "Welcome@123", "Password");
		WebElement re_login=driver.findElement(By.id("Login"));
		clickElement(re_login, "Login Button");
		Thread.sleep(1000);
		String Exp_Title3="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title3);
		WebElement acc_tab=driver.findElement(By.id("Account_Tab"));
		String text=getTextFromElement(acc_tab, "Account tab");
		Assert.assertEquals(text, Exp_text);
	}
	@Test
	public void  CreateAccount_TC10() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		WebElement acc_tab=driver.findElement(By.id("Account_Tab"));
		clickElement(acc_tab, "Account Tab");
		String Exp_Title1="Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement user_menu1=driver.findElement(By.id("userNav"));
		String act_username1=getTextFromElement(user_menu1, "Username");
		Assert.assertEquals(act_username1,Exp_username);
		WebElement new_btn=driver.findElement(By.xpath("//input[@name='new']"));
		clickElement(new_btn, "New");
		WebElement acc_name=driver.findElement(By.id("acc2"));
		enterText(acc_name,"Selenium Training" , "Account Name");
		WebElement type=driver.findElement(By.id("acc6"));
		selectByTextData(type,"Technology Partner", "Type");
		WebElement cus_priority=driver.findElement(By.id("00Nal000000KU6S"));
		selectByTextData(cus_priority,"High", "Priority");
		Thread.sleep(3000);
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		String Exp_Title2="Account: Selenium Training ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
	}
	// Make sure to pass the different view names before execution-used Random Number generator for this
	@Test
	public void Createnewview_TC11() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		WebElement acc_tab=driver.findElement(By.id("Account_Tab"));
		clickElement(acc_tab, "Account Tab");
		String Exp_Title1="Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement user_menu1=driver.findElement(By.id("userNav"));
		String act_username1=getTextFromElement(user_menu1, "Username");
		Assert.assertEquals(act_username1,Exp_username);
		WebElement create_view=driver.findElement(By.linkText("Create New View"));
		waitForVisibility(create_view, 30, "Create New View");
		clickElement(create_view, "Create New View Link");
		WebElement view_name=driver.findElement(By.id("fname"));
		enterText(view_name,"Active Accounts", "View Name"); //change view name here
		WebElement unique_name=driver.findElement(By.id("devname"));
		AutomationSalesforceAccountlog.info("View Unique Name is displayed? "+unique_name.isDisplayed());
		clickElement(unique_name, "View Unique name textbox");
		clearElement(unique_name, "Unique View name");
		Random rand = new Random();
		int rand_int = rand.nextInt(100);
		enterText(unique_name,"Active_Accounts"+rand_int, "unique name");
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		WebElement view=driver.findElement(By.name("fcf"));
		waitForVisibility(view, 30, "View");
		String act_text="Active Accounts";
		Select select=new Select(view);
		String Exp_text=getTextFromElement(select.getFirstSelectedOption(),"View");  
		Assert.assertEquals(act_text,Exp_text);
	}
	@Test(priority = 2)
	public void accountviewname() {
		login_salesforce();
		WebElement acc_tab=driver.findElement(By.id("Account_Tab"));
		clickElement(acc_tab, "Account Tab");
		WebElement view_list=driver.findElement(By.id("fcf"));
		Select sel=new Select(view_list);
		List<WebElement> list=sel.getOptions();
		for (WebElement i : list) {
			if (i.getText().equalsIgnoreCase("All Accounts")) {
				WebElement edit=driver.findElement(By.linkText("Edit"));
				clickElement(edit, "Edit Link");
				WebElement last_activity=driver.findElement(By.id("colselector_select_1"));
				selectByTextData(last_activity,"Last Activity","Last Activity");
				WebElement remove=driver.findElement(By.id("colselector_select_0_left"));
				clickElement(remove, "Remove");
				WebElement save=driver.findElement(By.name("save"));
				waitForVisibility(save, 30, "save");
				clickElement(save, "Save");
				break;
			}else if(i.getText().equalsIgnoreCase("All Salesforce Accounts")) {
				WebElement edit=driver.findElement(By.linkText("Edit"));
				clickElement(edit, "Edit Link");
				WebElement new_acc=driver.findElement(By.id("fname"));
				clearElement(new_acc,"Account Name");
				enterText(new_acc, "All Accounts", "View Name");
//				WebElement last_activity=driver.findElement(By.id("colselector_select_1"));
//				selectByTextData(last_activity,"Last Activity","Last Activity");
//				WebElement remove=driver.findElement(By.id("colselector_select_0_left"));
//				clickElement(remove, "Remove");
				WebElement save=driver.findElement(By.name("save"));
				waitForVisibility(save, 30, "save");
				clickElement(save, "Save");
				break;
			}
		}
		
	}
	//Make sure view_list name is 'All Accounts' and Last activity is not selected while creating view name
	@Test(dependsOnMethods ="accountviewname" )
	public void Editview_TC12() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		WebElement acc_tab=driver.findElement(By.id("Account_Tab"));
		clickElement(acc_tab, "Account Tab");
		String Exp_Title1="Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);;
		WebElement user_menu1=driver.findElement(By.id("userNav"));
		String act_username1=getTextFromElement(user_menu1, "Username");
		Assert.assertEquals(act_username1,Exp_username);
		WebElement view_list=driver.findElement(By.id("fcf"));
		selectByTextData(view_list,"All Accounts", "View name");
		WebElement edit=driver.findElement(By.linkText("Edit"));
		clickElement(edit, "Edit Link");
		String Exp_Title2="Accounts: Edit View ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement new_acc=driver.findElement(By.id("fname"));
		clearElement(new_acc,"Account Name");
		enterText(new_acc, "All Salesforce Accounts", "View Name");
		WebElement field=driver.findElement(By.id("fcol1"));
		selectByTextData(field,"Account Name","Field");
		WebElement operator=driver.findElement(By.id("fop1"));
		selectByTextData(operator,"contains","operator");
		WebElement value=driver.findElement(By.id("fval1"));
		enterText(value, "a", "Account name");
		WebElement last_activity=driver.findElement(By.id("colselector_select_0"));
		selectByTextData(last_activity,"Last Activity","Last Activity");
		AutomationSalesforceAccountlog.info("Last Activity is selected");
		WebElement add=driver.findElement(By.id("colselector_select_0_right"));
		clickElement(add, "Add");
		WebElement save=driver.findElement(By.name("save"));
		waitForVisibility(save, 30, "save");
		clickElement(save, "Save");
		WebElement view=driver.findElement(By.id("00Bal000000ETNX_listSelect"));
		String Exp_text="All Salesforce Accounts";
		Select select4=new Select(view);
		String act_text=getTextFromElement(select4.getFirstSelectedOption(),"view name"); 
		Assert.assertEquals(act_text, Exp_text);
		WebElement allheaders= driver.findElement(By.xpath("//table/thead"));
		 List<WebElement> headers=  allheaders.findElements(By.xpath("//tr/td"));
		 for(WebElement header:headers) {
			 if(header.getText().equalsIgnoreCase("Last Activity")) {
			 AutomationSalesforceAccountlog.info("Last Activity column has successfully added to the view");
			 }
		 }
		 List<WebElement> account_names=  allheaders.findElements(By.xpath("//tr/td[4]"));
		 for(WebElement name:account_names) {
			 if(name.getText().contains("a")) {
				 AutomationSalesforceAccountlog.info(""+name.getText());
			 AutomationSalesforceAccountlog.info("View has displayed as per the criteria selected");
			 }
		 }
	}
	//Make sure there are more than 2 accounts for the given account name
	@Test
	public void MergeAccounts_TC13() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		validatetextwithactual(Exp_Title, "Title of the Page", getTitle());
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		WebElement acc_tab=driver.findElement(By.id("Account_Tab"));
		clickElement(acc_tab, "Account Tab");
		String Exp_Title1="Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement user_menu1=driver.findElement(By.id("userNav"));
		String act_username1=getTextFromElement(user_menu1, "Username");
		Assert.assertEquals(act_username1,Exp_username);
		WebElement merge_acc=driver.findElement(By.linkText("Merge Accounts"));
		clickElement(merge_acc, "Merge Accounts");
		WebElement search=driver.findElement(By.id("srch"));
		enterText(search,"Selenium Training", "Account Name");
		WebElement find_account=driver.findElement(By.name("srchbutton"));
		clickElement(find_account, "Find Accounts");
		WebElement next=driver.findElement(By.name("goNext"));
		clickElement(next, "Next");
		String Exp_Title2="Merge My Accounts ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement save=driver.findElement(By.name("save"));
		waitForVisibility(save, 30,"save");
		clickElement(save, "Merge");
		Alert al=switchToAlert();
		AcceptAlert(al);
		AutomationSalesforceAccountlog.info("Alert Accepted");
		Assert.assertEquals(getTitle(), Exp_Title1);
		 List<WebElement> headers=  driver.findElements(By.xpath("//table/tbody/tr/th"));
		 for(WebElement header:headers) {
			 if(header.getText().equalsIgnoreCase("Selenium Training")) {
			 AutomationSalesforceAccountlog.info("Merged account is displayed in the Recent Accounts");
			 }
		 }
	}
	//Make sure to pass different Report unique name than existing-used Random Number generator for this
	@Test
	public void Createaccountreport_TC14() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		WebElement acc_tab=driver.findElement(By.id("Account_Tab"));
		clickElement(acc_tab, "Account Tab");
		String Exp_Title1="Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement user_menu1=driver.findElement(By.id("userNav"));
		String act_username1=getTextFromElement(user_menu1, "Username");
		Assert.assertEquals(act_username1,Exp_username);
		WebElement Acc30=driver.findElement(By.linkText("Accounts with last activity > 30 days"));
		clickElement(Acc30, "Accounts with last activity > 30 days");
		String Exp_Title2="Unsaved Report ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement field=driver.findElement(By.id("ext-gen148"));
		clickElement(field, "Date Field");
		Thread.sleep(2000);
		WebElement date_field=driver.findElement(By.xpath("//div[contains(text(),'Created Date')]"));
		clickElement(date_field, "Created Date");
		WebElement from_cal=driver.findElement(By.id("ext-gen152"));
		clickElement(from_cal, "Calender");
		Thread.sleep(2000);
		WebElement from=driver.findElement(By.xpath("//table[1]/tbody/tr[3]/td[1]/table[1]/tbody/tr[2]/td/em/button"));
		clickElement(from, "Today");
		AutomationSalesforceAccountlog.info("From date Selected");
		WebElement cal_text=driver.findElement(By.id("ext-comp-1045"));
		clearElement(cal_text, "Calendear date");
		WebElement To_cal=driver.findElement(By.id("ext-gen154"));
		clickElement(To_cal, "Calender");
		WebElement To=driver.findElement(By.xpath("//table[@id='ext-gen288']/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/em/button[@id='ext-gen296']"));
		waitForVisibility(To, 30, "To");
		clickElement(To, "Today");
		AutomationSalesforceAccountlog.info("To date Selected");
		WebElement save=driver.findElement(By.id("ext-gen49"));
		clickElement(save, "Save");
		String parentWindow = driver.getWindowHandle(); 
		switchToNewWindowFrom(parentWindow);
		WebElement report_name=driver.findElement(By.id("saveReportDlg_reportNameField"));
		enterText(report_name, "Today's Report", "Report");
		WebElement unique_name=driver.findElement(By.id("saveReportDlg_DeveloperName"));
		clickElement(unique_name, "Report Unique name textbox");
		clearElement(unique_name,"Report Unique Name");
		Random rand = new Random();
		int rand_int = rand.nextInt(100);
		enterText(unique_name,"Today_Report"+rand_int,"Report Unique Name");
		WebElement save_run=driver.findElement(By.xpath("//table[@id='dlgSaveAndRun']/tbody/tr[2]/td[2]/em/button"));   
		waitForVisibility(save_run, 30,"Save and Run");
		mouseOverOnElement(save_run, "Save and Run report");
		Thread.sleep(3000);
		mouseClickOnElement(save_run, "Save and Run report");
		driver.switchTo().defaultContent(); 
		AutomationSalesforceAccountlog.info("Switched back to Parentwindow");
		Thread.sleep(5000);
		String exp_title="Today's Report ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), exp_title);
		WebElement report=driver.findElement(By.xpath("//*[@class='content']/h1"));
		String exp_report="Today's Report";
		String act_report=getTextFromElement(report, "Actual Report");
		Assert.assertEquals(act_report, exp_report);
	}

}
