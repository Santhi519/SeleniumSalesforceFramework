package com.salesforce.automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.salesforce.base.BaseSalesforce;


public class SalesforceUserMenu extends BaseSalesforce {
	protected Logger AutomationSalesforceUserMenulog=LogManager.getLogger();
	//Precondition Testcase to Change the username as Santhi K
	@Test(priority = 1)
	public void changeusername() throws InterruptedException {
		login_salesforce();
		WebElement user_menu=driver.findElement(By.id("userNav"));
		clickElement(user_menu, "User Menu");
		WebElement my_profile=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
		clickElement(my_profile, "My Profile link");
		Thread.sleep(2000);
		WebElement edit=driver.findElement(By.className("contactInfoLaunch"));
		clickElement(edit, "Edit Contact");
		Thread.sleep(2000);
		driver.switchTo().frame("contactInfoContentId");
		AutomationSalesforceUserMenulog.info("Switched to pop-up window");
		WebElement about_tab=driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		AutomationSalesforceUserMenulog.info("username changed to Santhi K");
		clickElement(about_tab, "About tab");
		WebElement last_name=driver.findElement(By.id("lastName"));
		enterText(last_name, "K", "Last Name");
		WebElement save_all=driver.findElement(By.xpath("//input[@value='Save All']"));
		clickElement(save_all, "Save All");
		driver.switchTo().defaultContent();
	}
	
	
	@Test(dependsOnMethods ="changeusername" )
	public void selectUserMenu_TC05() {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi K";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		clickElement(user_menu, "User Menu");
		List<WebElement> view_list=new ArrayList<>();
		for(int i=1;i<=4;i++) {
			 WebElement view=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a["+i+"]"));
			 view_list.add(view);
		}
		String[] act_list= {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		validatedropdownoptions(view_list,act_list);
	}
	@Test(dependsOnMethods ="selectUserMenu_TC05" )
	public void selectMyProfile_TC06() throws InterruptedException, AWTException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Thread.sleep(1000);
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi K";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		clickElement(user_menu, "User Menu");
		List<WebElement> view_list=new ArrayList<>();
		for(int i=1;i<=4;i++) {
			 WebElement view=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a["+i+"]"));
			 view_list.add(view);
		}
		String[] act_list= {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		validatedropdownoptions(view_list,act_list);
		WebElement my_profile=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
		clickElement(my_profile, "My Profile link");
		String Exp_Title1="User: Santhi K ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		Thread.sleep(2000);
		WebElement edit=driver.findElement(By.className("contactInfoLaunch"));
		clickElement(edit, "Edit Contact");
		Thread.sleep(2000);
		driver.switchTo().frame("contactInfoContentId");
		AutomationSalesforceUserMenulog.info("Switched to pop-up window");
		String act_title=driver.getTitle();
		AutomationSalesforceUserMenulog.info(act_title);
		WebElement contact_tab=driver.findElement(By.xpath("//a[contains(text(),'Contact')]"));
		String tab=contact_tab.getText();
		AutomationSalesforceUserMenulog.info(tab);
		WebElement about_tab=driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		String tab1=about_tab.getText();
		AutomationSalesforceUserMenulog.info(tab1);
		clickElement(about_tab, "About tab");
		WebElement last_name=driver.findElement(By.id("lastName"));
		enterText(last_name, "Krishna", "Last Name");
		WebElement save_all=driver.findElement(By.xpath("//input[@value='Save All']"));
		clickElement(save_all, "Save All");
		driver.switchTo().defaultContent();
		String Exp_Title2="User: Santhi Krishna ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		String Exp_username1="Santhi Krishna";
		WebElement user_name=driver.findElement(By.id("userNav"));
		String act_username1=getTextFromElement(user_name, "Username");
		Assert.assertEquals(act_username1,Exp_username1);
		WebElement post=driver.findElement(By.id("publisherAttachTextPost"));
		clickElement(post, "Post Link");
		WebElement frame=driver.findElement(By.className("cke_wysiwyg_frame"));
		driver.switchTo().frame(frame);
		WebElement post_text=driver.findElement(By.xpath("/html/body"));
		enterText(post_text, "Hello!", "Text");
		driver.switchTo().parentFrame();
		WebElement share=driver.findElement(By.id("publishersharebutton"));
		clickElement(share, "Share");
		Thread.sleep(2000);
		WebElement latest_post=driver.findElement(By.xpath("//*[@class='cxfeeditemtextwrapper']/div[1]/span/p"));
		String exp_text="Hello!";
		Thread.sleep(1000);
		String text=latest_post.getText();
		AutomationSalesforceUserMenulog.info(text);
		validatetextwithactual(exp_text, "Text Entered", text);
		WebElement file=driver.findElement(By.id("publisherAttachContentPost"));
		clickElement(file, "File Link");
		WebElement file_upload=driver.findElement(By.id("chatterUploadFileAction"));
		clickElement(file_upload, "Upload a file from your computer");
		Thread.sleep(2000);
		WebElement choose_file=driver.findElement(By.id("chatterFile"));//table[@class='contentdetails']/tbody/tr/td/div/a/span
		//clickElement(choose_file, "Choose File");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",choose_file);
		Thread.sleep(2000);
		StringSelection stringSelection = new StringSelection("C:\\Users\\jgiri\\Pictures\\IMG_E3806.JPG");
	    Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clpbrd.setContents(stringSelection, null);
	    AutomationSalesforceUserMenulog.info("copied to clipboard");
		Thread.sleep(5000);
		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		AutomationSalesforceUserMenulog.info("File Uploaded");
		Thread.sleep(3000);
//		choose_file.sendKeys("C:\\Users\\jgiri\\Pictures\\DDVP4709.JPG");//publishersharebutton //*[@id="title_203428636"]
		WebElement share1=driver.findElement(By.id("publishersharebutton"));
		clickElement(share1, "Share");
		Thread.sleep(5000);
		WebElement preview=driver.findElement(By.xpath("//table[@class='contentdetails']/tbody/tr/td/div/a/span"));	
		clickElement(preview, "Image Preview");
		String Exp_Title3="File: IMG_E3806 ~ Salesforce - Developer Edition";
		validatetextwithactual(Exp_Title3, "Title of the Page", getTitle());
		driver.navigate().back();
		Thread.sleep(3000);
		WebElement moderator=driver.findElement(By.id("displayBadge"));
		Actions action=new Actions(driver);
		action.moveToElement(moderator).build().perform();
		AutomationSalesforceUserMenulog.info("Mouseover on the image");
		Thread.sleep(5000);
		WebElement add_photo=driver.findElement(By.xpath("//*[@id=\"uploadLink\"]"));
		waitUntilElementToBeClickable(By.xpath("//*[@id=\"uploadLink\"]"),"Add Photo");
		clickElement(add_photo, "Add Photo");
		String Parentwindow=driver.getWindowHandle();
		switchToNewWindowFrom(Parentwindow);
		WebElement frame1=driver.findElement(By.xpath("//iframe[@id=\"uploadPhotoContentId\"]"));
		driver.switchTo().frame(frame1);
		AutomationSalesforceUserMenulog.info("Switched to new frame");
		Thread.sleep(3000);
		WebElement choose_pic=driver.findElement(By.xpath("//*[@id=\"j_id0:uploadFileForm:uploadInputFile\"]"));  //className("fileInput")
		Thread.sleep(3000);
		choose_pic.sendKeys("C:\\Users\\jgiri\\Pictures\\Salesforce-Characters-13.PNG");
		AutomationSalesforceUserMenulog.info("File Uploaded");
		WebElement save=driver.findElement(By.xpath("//*[@id=\"j_id0:uploadFileForm:uploadBtn\"]"));  // id("j_id0:uploadFileForm:save"));   
		clickElement(save, "Save");
		Thread.sleep(20000);
	// Uploading profile picture showing an error We can't upload your photo right now. Please wait a few minutes and try again.
		WebElement save1=driver.findElement(By.id("j_id0:j_id7:save"));   
		clickElement(save1, "Save");
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
			
	}
	@Test
	public void chatterappmenu() {
		login_salesforce();
		WebElement app_menu=driver.findElement(By.id("tsid"));
		clickElement(app_menu, "App Menu");
		WebElement salesforce_chatter=driver.findElement(By.xpath("//div[@id='tsid-menuItems']/a[contains(text(),'Salesforce Chatter')]"));
		waitForVisibility(salesforce_chatter, 30, "Salesforce Chatter");
		clickElement(salesforce_chatter, "Salesforce Chatter");
		List<WebElement> tabs=driver.findElements(By.xpath("//ul[@id='tabBar']/li/a[contains(text(),'Reports')]"));
		if(tabs.size()>0) {
			AutomationSalesforceUserMenulog.info("Reports Tab is displayed");
			WebElement app_menu1=driver.findElement(By.id("tsid"));
			clickElement(app_menu1, "App Menu");
			WebElement sales_menu=driver.findElement(By.xpath("//div[@id='tsid-menuItems']/a[contains(text(),'Sales')]"));
			clickElement(sales_menu, "Sales");
			WebElement user_menu=driver.findElement(By.id("userNav"));
			clickElement(user_menu, "User Menu");
			WebElement my_settings=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[2]"));  //className("debugLogLink menuButtonMenuLink"));
			clickElement(my_settings, "My Settings");
			WebElement display_layout=driver.findElement(By.id("DisplayAndLayout_font"));
			clickElement(display_layout, "Display & Layout Link");
			WebElement customize_tabs=driver.findElement(By.id("CustomizeTabs_font"));
			clickElement(customize_tabs, "Customize My Tabs");
			WebElement custom_app=driver.findElement(By.id("p4"));
			clickElement(custom_app, "Custom App");
			selectByTextData(custom_app,"Salesforce Chatter", "Custom App option");
			WebElement selected=driver.findElement(By.id("duel_select_1"));
			selectByTextData(selected,"Reports", "Reports");
			WebElement remove=driver.findElement(By.id("duel_select_0_left"));
			clickElement(remove, "Remove");
			WebElement save=driver.findElement(By.name("save"));
			clickElement(save, "Save");
			AutomationSalesforceUserMenulog.info("Reports Tab is removed");
		}else
			AutomationSalesforceUserMenulog.info("Reports Tab is removed");
		WebElement app_menu1=driver.findElement(By.id("tsid"));
		clickElement(app_menu1, "App Menu");
		WebElement sales_menu=driver.findElement(By.xpath("//div[@id='tsid-menuItems']/a[contains(text(),'Sales')]"));
		clickElement(sales_menu, "Sales");
		AutomationSalesforceUserMenulog.info("Appmenu changed from Salesforce Chatter to Sales");
	}
// Make sure Reports are not selected in salesforcechatter app menu and the user should not be in salesforcechatter while executing this TC
	@Test(dependsOnMethods ="chatterappmenu" )
	public void mysettings_TC07() throws InterruptedException {
		login_salesforce();
		Thread.sleep(1000);
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		clickElement(user_menu, "User Menu");
		List<WebElement> view_list=new ArrayList<>();
		for(int i=1;i<=4;i++) {
			 WebElement view=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a["+i+"]"));
			 view_list.add(view);
		}
		String[] act_list= {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		validatedropdownoptions(view_list,act_list);
		WebElement my_settings=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[2]"));  //className("debugLogLink menuButtonMenuLink"));
		clickElement(my_settings, "My Settings");
		String Exp_Title1="Hello, Santhi Krishna! ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title1);
		WebElement personal=driver.findElement(By.id("PersonalInfo_font"));
		clickElement(personal, "Personal Link");
		WebElement login_history=driver.findElement(By.id("LoginHistory_font"));
		clickElement(login_history, "Login History Link");
		WebElement download=driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a"));
		clickElement(download, "Download Link");
		 String FILES_DIRECTORY = "C:\\Users\\jgiri\\Downloads";
		 String filename="LoginHistory";
	        File Folder = new File(FILES_DIRECTORY);
	        File[] allFiles = new File(Folder.getPath()).listFiles();
	        for (File file : allFiles) {
	            String eachFile = file.getName();
	            if (eachFile.contains(filename))
	                AutomationSalesforceUserMenulog.info("--Verified: File : " + filename + " Has Been Download.");
	            else continue;
	        }
		WebElement display_layout=driver.findElement(By.id("DisplayAndLayout_font"));
		clickElement(display_layout, "Display & Layout Link");
		WebElement customize_tabs=driver.findElement(By.id("CustomizeTabs_font"));
		clickElement(customize_tabs, "Customize My Tabs");
		String Exp_Title2="Customize My Tabs ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title2);
		WebElement custom_app=driver.findElement(By.id("p4"));
		clickElement(custom_app, "Custom App");
		selectByTextData(custom_app,"Salesforce Chatter", "Custom App option");
		WebElement available=driver.findElement(By.id("duel_select_0"));
		selectByTextData(available,"Reports", "Reports");
		WebElement add=driver.findElement(By.id("duel_select_0_right"));
		clickElement(add, "Add");
		WebElement selected=driver.findElement(By.id("duel_select_1"));
		Select select1=new Select(selected);
		String Exp_text="Reports";
		WebElement act_text=select1.getFirstSelectedOption();
		validatetextwithactual(Exp_text, "Reports Option", getTextFromElement(act_text, "Selected option"));
		WebElement save=driver.findElement(By.name("save"));
		clickElement(save, "Save");
		WebElement app_menu=driver.findElement(By.id("tsid"));
		clickElement(app_menu, "App Menu");
//		WebElement marketing=driver.findElement(By.xpath("//a[contains(text(),'Marketing CRM Classic')]"));
//		waitForVisibility(marketing, 30, "Marketing");
//		clickElement(marketing, "Marketing");
//		WebElement rep_tab2=driver.findElement(By.id("report_Tab"));
//		String text2=rep_tab2.getText();
//		validatetextwithactual(Exp_text,"Tab Name in Marketing CRM Classic", text2);
		WebElement salesforce_chatter=driver.findElement(By.xpath("//div[@id='tsid-menuItems']/a[contains(text(),'Salesforce Chatter')]"));
		waitForVisibility(salesforce_chatter, 30, "Salesforce Chatter");
		clickElement(salesforce_chatter, "Salesforce Chatter");
		WebElement rep_tab1=driver.findElement(By.id("report_Tab"));
		String act_text1=rep_tab1.getText();
		Assert.assertEquals(act_text1, Exp_text);
		WebElement app_menu1=driver.findElement(By.id("tsid"));
		clickElement(app_menu1, "App Menu");
		WebElement sales_menu=driver.findElement(By.xpath("//div[@id='tsid-menuItems']/a[contains(text(),'Sales')]"));
		clickElement(sales_menu, "Sales");
		WebElement user_menu1=driver.findElement(By.id("userNav"));
		clickElement(user_menu1, "User menu");
		WebElement my_settings1=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[2]")); 
		waitForVisibility(my_settings1, 30, "My Settings");
		clickElement(my_settings1, "My Settings");
		WebElement email=driver.findElement(By.id("EmailSetup_font"));
		clickElement(email, "Email");
		WebElement email_settings=driver.findElement(By.id("EmailSettings_font"));
		clickElement(email_settings, "Email Settings");
		WebElement email_name=driver.findElement(By.id("sender_name"));
		enterText(email_name,"Santhi Krishna", "Email Name");
		WebElement email_address=driver.findElement(By.id("sender_email"));
		enterText(email_address,"santhi519@gmail.com", "Email Address");
		WebElement yes=driver.findElement(By.id("auto_bcc1"));
		clickElement(yes, "Yes");
		WebElement save1=driver.findElement(By.name("save"));
		clickElement(save1, "Save");
		String Exp_Title3="My Email Settings ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title3);
		WebElement calendar_Reminder=driver.findElement(By.id("CalendarAndReminders_font"));
		clickElement(calendar_Reminder, "Calendar & Reminders");
		WebElement activity_Reminder=driver.findElement(By.xpath("//a[@id='Reminders_font']"));
		clickElement(activity_Reminder, "Activity Reminders");
		String Exp_Title4="Activity Reminders ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title4);
		WebElement test_Reminder=driver.findElement(By.id("testbtn"));
		String Parentwindow=driver.getWindowHandle();
		clickElement(test_Reminder, "Open a Test Reminder");
		switchToNewWindowFrom(Parentwindow);
	}
	@Test
	public void usermenuDeveloperconsole_TC08() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		clickElement(user_menu, "User Menu");
		List<WebElement> view_list=new ArrayList<>();
		for(int i=1;i<=4;i++) {
			 WebElement view=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a["+i+"]"));
			 view_list.add(view);
		}
		String[] act_list= {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		validatedropdownoptions(view_list,act_list);
		WebElement dev_console=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]"));  //className("debugLogLink menuButtonMenuLink"));
		clickElement(dev_console, "Developer Console");
		Thread.sleep(2000);
		String parentWindow = driver.getWindowHandle();  // to get current window handle
		AutomationSalesforceUserMenulog.info("parent window handle="+parentWindow);
		Set<String> listOfHAndles=driver.getWindowHandles();
		AutomationSalesforceUserMenulog.info("total windows opened currently="+listOfHAndles.size());
		for(String handle:listOfHAndles)
		{
			AutomationSalesforceUserMenulog.info("current active window handle: "+handle);
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle); 
				String exp_title="Developer Console";
				Assert.assertEquals(getTitle(), exp_title);
//				driver.switchTo().window(handle).close();
//				AutomationSalesforceUserMenulog.info("Closed the current active window");
			}
			
		}
//		AutomationSalesforceUserMenulog.info("Closed the browser instance");
		
	}
	@Test
	public void usermenuLogout_TC09() throws InterruptedException {
		login_salesforce();
		String Exp_Title="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(getTitle(), Exp_Title);
		String Exp_username="Santhi Krishna";
		WebElement user_menu=driver.findElement(By.id("userNav"));
		String act_username=getTextFromElement(user_menu, "Username");
		Assert.assertEquals(act_username,Exp_username);
		clickElement(user_menu, "User Menu");
		List<WebElement> view_list=new ArrayList<>();
		for(int i=1;i<=4;i++) {
			 WebElement view=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a["+i+"]"));
			 view_list.add(view);
		}
		String[] act_list= {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
		validatedropdownoptions(view_list,act_list);
		WebElement logout=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logout,"Logout");
		Thread.sleep(1000);
		String Exp_Title2="Login | Salesforce";
		Assert.assertEquals(getTitle(), Exp_Title2);
	}
	
	

}
