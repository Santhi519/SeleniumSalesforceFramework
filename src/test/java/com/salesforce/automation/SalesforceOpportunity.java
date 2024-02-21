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

public class SalesforceOpportunity extends BaseSalesforce{
	protected Logger AutomationSalesforceOpportunitylog=LogManager.getLogger();
	@Test
	public void opportunitydropdown_TC15() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username, Exp_username);
		WebElement oppty=driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		clickElement(oppty, "Opportunity Tab");
		String Exp_Title1="Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		String[] act_list= {"All Opportunities","Closing Next Month","Closing This Month","My Opportunities","New Last Week",
				             "New This Week","Opportunity Pipeline","Private","Recently Viewed Opportunities","Won"};
		WebElement oppty_list=driver.findElement(By.id("fcf"));
		Select select=new Select(oppty_list);
		List<WebElement> view_list=select.getOptions();  
		validatedropdownoptions(view_list, act_list);
	}
	@Test
	public void Newopportunity_TC16() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username, Exp_username);
		WebElement oppty=driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		clickElement(oppty, "Opportunity Tab");
		String Exp_Title1="Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement new_oppty=driver.findElement(By.name("new"));
		clickElement(new_oppty,"New");
		WebElement oppty_name=driver.findElement(By.id("opp3"));
		enterText(oppty_name, "IT deals", "Opportunity Name");
		WebElement account_name=driver.findElement(By.id("opp4_lkwgt"));
		clickElement(account_name, "Account LookUp");
		String parentWindow = driver.getWindowHandle();  // to get current window handle
		AutomationSalesforceOpportunitylog.info("parent window handle="+parentWindow);
		switchToNewWindowFrom(parentWindow);
		driver.switchTo().frame("resultsFrame");			
		WebElement lookup_account=driver.findElement(By.xpath("//a[contains(text(),'Edge Communications')]"));
		clickElement(lookup_account, "Look Up Account");
		driver.switchTo().window(parentWindow);   // switching to the parent window
		WebElement close_date=driver.findElement(By.id("opp9"));//*[@id="opp9"]
		clickElement(close_date, "Date");////
		WebElement month=driver.findElement(By.id("calMonthPicker"));
		selectByTextData(month, "April", "Month");
		WebElement year=driver.findElement(By.id("calYearPicker"));
		selectByTextData(year,"2024", "Year");
		WebElement pick_date=driver.findElement(By.xpath("//*[@id=\"calRow2\"]/td[4]"));
		clickElement(pick_date, "Date Element");
		WebElement stage=driver.findElement(By.id("opp11"));
		selectByValueData(stage,"Value Proposition", "Stage");
		WebElement prob=driver.findElement(By.id("opp12"));
		enterText(prob, "60", "Probability");
		WebElement lead_source=driver.findElement(By.id("opp6"));
		selectByValueData(lead_source,"Web", "Lead Source");
		WebElement campaign_source=driver.findElement(By.id("opp17_lkwgt"));
		clickElement(campaign_source, "Campaign Look Up");
		switchToNewWindowFrom(parentWindow);
		driver.switchTo().frame("resultsFrame");			
		WebElement campaign=driver.findElement(By.xpath("//a[contains(text(),'International Electrical Engineers Association Trade Show - Mar 4-5, 2002')]"));
		clickElement(campaign, "Look Up Campaign");
		driver.switchTo().window(parentWindow); 
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		WebElement saved_oppty=driver.findElement(By.id("opp3_ileinner"));
		String actual=getTextFromElement(saved_oppty, "Opportunity Name");
		String expected="IT deals";
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void OpportunityPipelineReport_TC17() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username, Exp_username);
		WebElement oppty=driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		clickElement(oppty, "Opportunity Tab");
		String Exp_Title1="Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement oppty_pipe=driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]"));
		clickElement(oppty_pipe, "Opportunity Pipeline");
		String Exp_Title2="Opportunity Pipeline ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
	}
	@Test
	public void StuckOpportunitiesReport_TC18() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username, Exp_username);
		WebElement oppty=driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		clickElement(oppty, "Opportunity Tab");
		String Exp_Title1="Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement oppty_stuck=driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		clickElement(oppty_stuck, "Stuck Opportunities");
		String Exp_Title2="Stuck Opportunities ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
	}
	@Test
	public void QuarterlySummaryReport_TC19() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username, Exp_username);
		WebElement oppty=driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		clickElement(oppty, "Opportunity Tab");
		String Exp_Title1="Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement interval=driver.findElement(By.id("quarter_q"));
		selectByTextData(interval,"Current FY", "Interval");
		WebElement include=driver.findElement(By.id("open"));
		selectByTextData(include,"Closed/Won Opportunities", "Include");
		WebElement run_report=driver.findElement(By.xpath("//input[@value='Run Report']"));
		clickElement(run_report, "Run Report");
		String Exp_Title2="Opportunity Report ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		String Exp_oppty_status="Closed Won";
		WebElement oppty_status=driver.findElement(By.id("open"));
		Select sel=new Select(oppty_status);
		String act_oppty_status=getTextFromElement(sel.getFirstSelectedOption(), "Opportunity Status");
		Assert.assertEquals(act_oppty_status,Exp_oppty_status);
	}

}
