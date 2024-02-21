package com.salesforce.automation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;


import com.salesforce.base.BaseSalesforce;

public class SalesforceRandomScenarios extends BaseSalesforce {
	protected Logger AutomationSalesforceRandomScenarioslog=LogManager.getLogger();
	
	@Test(priority = 1)
	public void validatename_TC33() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement home_tab=driver.findElement(By.id("home_Tab"));
		clickElement(home_tab, "Home Tab");
		WebElement user_name=driver.findElement(By.className("currentStatusUserName"));
		getTextFromElement(user_name, "Santhi Krishna");
		clickElement(user_name, "User Name Link");
		String Exp_Title1="User: Santhi Krishna ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
	}
	@Test(dependsOnMethods ="validatename_TC33" )
	public void updateLastName_TC34() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement home_tab=driver.findElement(By.id("home_Tab"));
		clickElement(home_tab, "Home Tab");
		WebElement user_name=driver.findElement(By.className("currentStatusUserName"));
		getTextFromElement(user_name, "Santhi Krishna");
		clickElement(user_name, "User Name Link");
		String Exp_Title1="User: Santhi Krishna ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement edit=driver.findElement(By.className("contactInfoLaunch"));
		clickElement(edit, "Edit Contact");
		Thread.sleep(2000);
		driver.switchTo().frame("contactInfoContentId");
		AutomationSalesforceRandomScenarioslog.info("Switched to pop-up window");
		String act_title=driver.getTitle();
		AutomationSalesforceRandomScenarioslog.info(act_title);
		WebElement contact_tab=driver.findElement(By.xpath("//a[contains(text(),'Contact')]"));
		String tab=contact_tab.getText();
		AutomationSalesforceRandomScenarioslog.info(tab);
		WebElement about_tab=driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		String tab1=about_tab.getText();
		AutomationSalesforceRandomScenarioslog.info(tab1);
		clickElement(about_tab, "About tab");
		WebElement last_name=driver.findElement(By.id("lastName"));
		enterText(last_name, "ABCD", "Last Name");
		WebElement save_all=driver.findElement(By.xpath("//input[@value='Save All']"));
		clickElement(save_all, "Save All");
		driver.switchTo().defaultContent();
		String Exp_Title2="User: Santhi ABCD ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		String Exp_username1="Santhi ABCD";
		WebElement username=driver.findElement(By.id("userNav"));
		String act_username1=getTextFromElement(username, "Username");
		Assert.assertEquals(act_username1,Exp_username1);
		WebElement username2=driver.findElement(By.id("tailBreadcrumbNode"));
		String Exp_username2="Santhi ABCD ";
		String act_username2=getTextFromElement(username2, "Username");
		Assert.assertEquals(act_username2,Exp_username2);
	}
	@Test
	public void addaccountstab() throws InterruptedException {
			login_salesforce();
			List<WebElement> tabs=driver.findElements(By.xpath("//ul[@id='tabBar']/li/a[contains(text(),'Accounts')]"));
			if(tabs.size()>0) {
				AutomationSalesforceRandomScenarioslog.info("Accounts Tab is displayed");
			}else
				AutomationSalesforceRandomScenarioslog.info("Accounts Tab is removed");
			WebElement tab=driver.findElement(By.className("allTabsArrow"));
			clickElement(tab, "+");
			WebElement custom_tab=driver.findElement(By.className("btnImportant"));
			clickElement(custom_tab, "Customize my Tabs");
			WebElement account=driver.findElement(By.id("duel_select_0"));
			selectByTextData(account, "Accounts", "Accounts");
			Thread.sleep(2000);
			WebElement add=driver.findElement(By.id("duel_select_0_right"));
			clickElement(add, "Add");
			WebElement save=driver.findElement(By.name("save"));
			clickElement(save, "Save");
			AutomationSalesforceRandomScenarioslog.info("Now the Accounts Tab is removed");
		
	}
	//Make sure Accounts should be in selected Tabs
	@Test(dependsOnMethods ="addaccountstab" )
	public void tabcustomization_TC35() throws InterruptedException {
		login_salesforce();
		WebElement tab=driver.findElement(By.className("allTabsArrow"));
		clickElement(tab, "+");
		String Exp_Title="All Tabs ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement custom_tab=driver.findElement(By.className("btnImportant"));
		clickElement(custom_tab, "Customize my Tabs");
		String Exp_Title1="Customize My Tabs ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement account=driver.findElement(By.id("duel_select_1"));
		selectByTextData(account,"Accounts", "Accounts");
		WebElement remove=driver.findElement(By.id("duel_select_0_left"));
		clickElement(remove, "Remove");
		WebElement account_sel=driver.findElement(By.id("duel_select_0"));
		Select select1=new Select(account_sel);
		String Exp_text="Accounts";
		String act_text=select1.getFirstSelectedOption().getText();
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
		clearElement(name, "User Name");
		enterText(name, "santhik@salesforce.com","Username");
		WebElement password_1=driver.findElement(By.id("password"));
		clearElement(password_1, "Password");
		enterText(password_1, "Welcome@123", "Password");
		WebElement re_login=driver.findElement(By.id("Login"));
		clickElement(re_login, "Login Button");
		Thread.sleep(3000);
		String Exp_Title3="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title3);
		List<WebElement> tabs=driver.findElements(By.xpath("//ul[@id='tabBar']/li/a[contains(text(),'Accounts')]"));
		if(tabs.size()>0) {
			AutomationSalesforceRandomScenarioslog.info("Accounts Tab is displayed");
		}else
			AutomationSalesforceRandomScenarioslog.info("Accounts Tab is removed");
	}
	@Test
	public void blockevent_TC36() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement home_tab=driver.findElement(By.id("home_Tab"));
		clickElement(home_tab, "Home Tab");
		SimpleDateFormat curtime = new SimpleDateFormat("EEEE MMMM dd, yyyy");
		Date thisDate = new Date(); 
		AutomationSalesforceRandomScenarioslog.info(curtime.format(thisDate));
		String today = curtime.format(thisDate);
		WebElement current_date=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a")); 
		String act_today=getTextFromElement(current_date, "Date Link");
		Assert.assertEquals(act_today,  today);
		clickElement(current_date, "Current Date Link");
		String Exp_Title1="Calendar for Santhi ABCD ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement table= driver.findElement(By.id("calTable")); 
		waitForVisibility(table, 30, "Table");
		WebElement time_col= table.findElement(By.xpath("//table[@id='calTable']/tbody/tr[2]/td/div[29]/a")); 
		clickElement(time_col, "8:00 PM");
		String Exp_Title2="Calendar: New Event ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		if(driver.switchTo().activeElement().findElement(By.xpath("//*[@id=\"evt5\"]")) != null) {
			AutomationSalesforceRandomScenarioslog.info("Cursor is at Subject field");
		}
		WebElement subject= driver.findElement(By.className("comboboxIcon")); 
		clickElement(subject, "Subject Combo Box Icon");
		String Parentwindowhandle=driver.getWindowHandle();
		switchToNewWindowFrom(Parentwindowhandle);
		String Exp_Title3="ComboBox";
		Assert.assertEquals(getTitle(), Exp_Title3);
		driver.manage().window().maximize();
		WebElement other= driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a"));      
		clickElement(other, "Other");
		AutomationSalesforceRandomScenarioslog.info("Combo window closed and switched back to parent window");
		driver.switchTo().window(Parentwindowhandle);
		Thread.sleep(3000);
		String exp_text="Other";
//		WebElement sub=driver.findElement(By.id("evt5"));
//		clickElement(sub, "subject");
//		Thread.sleep(3000);
//		String act_text=getTextFromElement(sub, "Subject");
//		SoftAssert sa=new SoftAssert();
//		sa.assertEquals(act_text, exp_text);
//		sa.assertAll();
		WebElement end_time=driver.findElement(By.id("EndDateTime_time"));
		String exp_time="9:00 PM";
		String act_time=end_time.getAttribute("value");
		Assert.assertEquals(act_time, exp_time);
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement other_text= driver.findElement(By.xpath("//tbody/tr[2]/td[2]/div[29]/span[1]/div[1]/div[1]/div[1]/span[1]/a[1]")); 
		String act_text1=getTextFromElement(other_text, "Other Link");
		Assert.assertEquals(act_text1, exp_text);
			
	}
	@Test
	public void blockeventrecurrence_TC37() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		WebElement home_tab=driver.findElement(By.id("home_Tab"));
		clickElement(home_tab, "Home Tab");
		SimpleDateFormat curtime = new SimpleDateFormat("EEEE MMMM dd, yyyy");
		Date thisDate = new Date(); 
		AutomationSalesforceRandomScenarioslog.info(curtime.format(thisDate));
		String today = curtime.format(thisDate);
		WebElement current_date=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a"));   
		validatetextwithactual(today, "Today date Link", getTextFromElement(current_date, "Date Link"));
		clickElement(current_date, "Current Date Link");
		String Exp_Title1="Calendar for Santhi ABCD ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement table= driver.findElement(By.id("calTable")); 
		waitForVisibility(table, 30, "Table");
		WebElement time_col= table.findElement(By.xpath("//table[@id='calTable']/tbody/tr[2]/td/div[21]/a")); 
		clickElement(time_col, "4:00 PM");
		String Exp_Title2="Calendar: New Event ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		if(driver.switchTo().activeElement().findElement(By.xpath("//*[@id=\"evt5\"]")) != null) {
			AutomationSalesforceRandomScenarioslog.info("Cursor is at Subject field");
		}
		WebElement subject= driver.findElement(By.className("comboboxIcon")); 
		clickElement(subject, "Subject Combo Box Icon");
		String Parentwindowhandle=driver.getWindowHandle();
		switchToNewWindowFrom(Parentwindowhandle);
		String Exp_Title3="ComboBox";
		Assert.assertEquals(getTitle(), Exp_Title3);
		driver.manage().window().maximize();
		WebElement other= driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a"));     
		clickElement(other, "Other");
		AutomationSalesforceRandomScenarioslog.info("Combo window closed and switched back to parent window");
		driver.switchTo().window(Parentwindowhandle);
		Thread.sleep(3000);
		String exp_text="Other";
//		WebElement sub=driver.findElement(By.id("evt5"));
//		String act_text=getTextFromElement(sub, "Subject");
//		Assert.assertEquals(act_text, exp_text);
		WebElement end_time1=driver.findElement(By.id("EndDateTime_time"));
		String exp_time1="5:00 PM";
		String act_time1=end_time1.getAttribute("value");
		Assert.assertEquals(act_time1, exp_time1);
		end_time1.clear();
		WebElement end_time=driver.findElement(By.id("timePickerItem_38"));
		clickElement(end_time, "7:00 PM");
		Thread.sleep(1000);
		String exp_time="7:00 PM";
		String act_time=end_time.getAttribute("value");
		Assert.assertEquals(act_time, exp_time);
		WebElement is_recur=driver.findElement(By.id("IsRecurrence"));
		clickElement(is_recur, "Create Recurring Series of Events");
		//validating the options to be diplayed as arrayList
		List<WebElement> recur_list=new ArrayList<>();
		for(int i=1;i<=3;i++) {
			 WebElement option=driver.findElement(By.xpath("//div[@id='recpat']/table/tbody/tr["+i+"]/td[1]/label"));
			 recur_list.add(option);
		}
		String[] act_list= {"Frequency","Recurrence Start","Recurrence End"};
		validatedropdownoptions(recur_list,act_list);
		AutomationSalesforceRandomScenarioslog.info("Frequency,Recurrence Start,Recurrence End sections are displayed");
		WebElement weekly= driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[2]/label[1]"));     
		clickElement(weekly, "Weekly");
		WebElement recur_day=driver.findElement(By.id("wi"));
		String exp_value="1";
		String act_value=recur_day.getAttribute("value");
		Assert.assertEquals(act_value, exp_value);
		
		SimpleDateFormat day = new SimpleDateFormat("EEEE");
		Date date_2day = new Date();
		
		String exp_day=day.format(date_2day);;
		for(int i=1;i<=7;i++) {
			 WebElement checkbox_option=driver.findElement(By.xpath("//tbody/tr[1]/td[2]/div[2]/div[2]/input["+i+"]"));
			 if(checkbox_option.isSelected()) {
				 WebElement checkbox_text=driver.findElement(By.xpath("//tbody/tr[1]/td[2]/div[2]/div[2]/label["+i+"]"));
				 AutomationSalesforceRandomScenarioslog.info(""+checkbox_text.getText()+" is Selected");
				 String act_day=checkbox_text.getText();
				 Assert.assertEquals(act_day, exp_day);
			 }
		}		
		WebElement recur_end_date=driver.findElement(By.id("RecurrenceEndDateOnly"));
		clickElement(recur_end_date, "Recurrence End date");
		
		SimpleDateFormat cur_time = new SimpleDateFormat("MM/dd/yyyy");
		int noOfDays = 14; //i.e two weeks
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		Date date1 = calendar.getTime();
		String exp_date1 = cur_time.format(date1);
		SimpleDateFormat cur_month = new SimpleDateFormat("MMMM");
		SimpleDateFormat cur_day = new SimpleDateFormat("d");
		AutomationSalesforceRandomScenarioslog.info(exp_date1);
		AutomationSalesforceRandomScenarioslog.info(cur_month.format(date1));
		AutomationSalesforceRandomScenarioslog.info(cur_day.format(date1));
		
		AutomationSalesforceRandomScenarioslog.info(""+exp_date1);
		WebElement cal_table= driver.findElement(By.xpath("//table[@id='datePickerCalendar']/tbody"));   
		WebElement cal_month=driver.findElement(By.id("calMonthPicker"));
		selectByTextData(cal_month,cur_month.format(date1), "Month");
		List<WebElement> cal_data= cal_table.findElements(By.xpath("//tr/td"));
		WebElement date=selectFromListUsingText(cal_data, cur_day.format(date1));
		clickElement(date, "Date");
		AutomationSalesforceRandomScenarioslog.info("Date is selected");
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement other_text= driver.findElement(By.xpath("//tbody/tr[2]/td[2]/div[21]/span[1]/div[1]/div[1]/div[1]/span[1]/a[1]")); 
		String act_text1=getTextFromElement(other_text, "Other Link");
		Assert.assertEquals(act_text1, exp_text);
		WebElement month_view=driver.findElement(By.className("monthViewIcon"));
		clickElement(month_view, "Month View Icon");
		String Exp_Title4="Calendar for Santhi ABCD - Month View ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title4);
		WebElement month_table= driver.findElement(By.xpath("//table[@class='calendarMonthView secondaryPalette']/tbody"));  
		List<WebElement> month_data= month_table.findElements(By.xpath("//tr/td/div/a[2]"));
		for (WebElement i : month_data) {
			if (i.getText().equalsIgnoreCase("11")) {
				WebElement text= driver.findElement(By.xpath("//tbody/tr[2]/td[6]/div[2]/a"));
				String act_text2=getTextFromElement(text, "Other");
				Assert.assertEquals(act_text2, exp_text);
			}
		}		
		
	}

}

