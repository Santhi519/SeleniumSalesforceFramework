package com.salesforce.automation;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.salesforce.base.BaseSalesforce;

public class RandomTest extends BaseSalesforce {
	public  void windowTest() throws InterruptedException {
		login_salesforce();
		WebElement user_menu=driver.findElement(By.id("userNav"));
		clickElement(user_menu, "User menu");
		WebElement dev_console=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]"));  //className("debugLogLink menuButtonMenuLink"));
		clickElement(dev_console, "Developer Console");
		Thread.sleep(2000);
		String parentWindow = driver.getWindowHandle();  // to get current window handle
		System.out.println("parent window handle="+parentWindow);
		Set<String> listOfHAndles=driver.getWindowHandles();
		System.out.println("total windows opened currently="+listOfHAndles.size());
		for(String handle:listOfHAndles)
		{
			System.out.println("current active window handle: "+handle);
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);   // switching to the new window
				WebElement test=driver.findElement(By.id("testMenuEntry-btnEl"));
				clickElement(test, "Test dropdown");
				WebElement run_all=driver.findElement(By.id("testRunAllButton-itemEl"));
				clickElement(run_all, "Run All");
				Thread.sleep(2000);
				WebElement msg_box=driver.findElement(By.id("messagebox-1001"));
				Actions action=new Actions(driver);
				action.moveToElement(msg_box);
				WebElement msg=driver.findElement(By.id("messagebox-1001-displayfield-inputEl"));
				System.out.println("Error displayed is: "+msg.getText());
				WebElement ok=driver.findElement(By.id("button-1005-btnEl"));
				clickElement(ok, "Okay");		  
			}
		}
		driver.switchTo().window(parentWindow);
		WebElement user_menu1=driver.findElement(By.id("userNav"));
		clickElement(user_menu1, "User menu");
		WebElement logout=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		clickElement(logout,"Logout");
		driver.quit();

	}

	public static void main(String[] args) throws InterruptedException {
		RandomTest ob=new RandomTest();
		ob.windowTest();
	}

}
