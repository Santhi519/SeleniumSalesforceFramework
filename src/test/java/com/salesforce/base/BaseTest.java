package com.salesforce.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.salesforce.utilities.ExtentReportsUtility;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

	protected static WebDriver driver = null;
	protected Logger baseTestlog=LogManager.getLogger();
	protected static ExtentReportsUtility extentReport=ExtentReportsUtility.getInstance();
   //Launching the browser
	public void launchBrowser(String browserName) {

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			baseTestlog.info("browser instance chrome created.");
			driver.manage().window().maximize();
			baseTestlog.info("window is maximized");
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;

		case "opera":
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			driver.manage().window().maximize();
			break;

		case "safari":
			// Safari does not require a separate driver setup, as it is included with macOS
			driver = new SafariDriver();
			break;

		default:
			baseTestlog.info("Unsupported browser: " + browserName);
		}

	}
   //Navigating to the URL
	public void goToUrl(String url) {
		driver.get(url);
		baseTestlog.info(url + " is entered");

	}
   //Passing the text data to the fields
	public void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			baseTestlog.info("Data is entered in " + objectName + " textbox");
		} else {
			baseTestlog.info(objectName + " element is not displayed");
		}
	}
	//To clear the existing data in the fields
	public void clearElement(WebElement ele, String ObjectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			baseTestlog.info(ObjectName + " is cleared");
		} else {
			baseTestlog.info(ObjectName + " element is not displayed");
		}
	}
	
   //Clicking the button element
	public void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			baseTestlog.info(objectName +" button is clicked");

		} else {
			baseTestlog.info(objectName+" element is not enabled");

		}
	}
   //Extracting the text from element
	public String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		baseTestlog.info("Text is extracted from "+objectName);
		return data;
	}
	
    // To get the Title of the page
	public String getTitle() {
		String data = driver.getTitle();
		baseTestlog.info("Title of the current webpage is "+data);
		return data;
	}
   //To close the Browser
	public void closeBrowser() {
		driver.close();
		baseTestlog.info("Browser instance closed");
		driver=null;
	}
	//To close all the open windows
	public void quitBrowser() {
		driver.quit();
		baseTestlog.info("all browser closed");
		driver=null;
		
	}

	
	//To validate the extracted text with actual text
		public void validatetextwithactual(String expected, String objectName,String actual) {
			if(expected.equals(actual)) {
				baseTestlog.info("Expected "+objectName+" matched with actual "+objectName);
			}else
				baseTestlog.info("Expected "+objectName+" not matched with actual "+objectName);
		}
	//To validate the dropdown options with actual dropdown options
		public void validatedropdownoptions(List<WebElement> view_list,String[] act_list) {
			for (WebElement view : view_list) {
				baseTestlog.info(""+view.getText());
				boolean match=false;
			    for(int i=0;i<act_list.length;i++) {	  
			      if(view.getText().equals(act_list[i])) {
					match=true;
				  }		
			}
			if(match) {
				baseTestlog.info("Expected dropdown value matched with actual dropdown value");
			}else
				baseTestlog.info("Expected dropdown value not matched with actual dropdown value");
			
			}
		}
		//To switch to alert
		public Alert switchToAlert() {

			Alert alert = driver.switchTo().alert();
			baseTestlog.info("switched to alert");
			return alert;
		}
       //To accept the alert
		public void AcceptAlert(Alert alert) {

			baseTestlog.info("Alert accepted");
			alert.accept();

		}
      //To get the text from alert message
		public String getAlertText(Alert alert, String objectname) {
			baseTestlog.info("etracting text in the " + objectname + "alert");
			String text = alert.getText();
			baseTestlog.info("text is extracted from alert box is==" + text);
			return text;

		}
    // To dismiss the alert
		public void dismisAlert() {

			Alert alert = switchToAlert();
			alert.dismiss();
			baseTestlog.info("Alert dismissed");

		}
		//To Perform Mousehover on an element
		public void mouseOverOnElement(WebElement ele, String objName) {
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
			baseTestlog.info(" cursor moved to web element " + objName);
		}
       //To rightclick on an element
		public void ContextClickOnElement(WebElement ele, String objName) {
			Actions action = new Actions(driver);
			action.contextClick(ele).build().perform();
			baseTestlog.info("right click performed on web element " + objName);
		}
		//To click the element using action class
		public void mouseClickOnElement(WebElement ele, String objName) {
			Actions action = new Actions(driver);
			action.click(ele).build().perform();
			baseTestlog.info("clicked on web element " + objName);
		}
		//To select the text using selectByVisibleText method
		public void selectByTextData(WebElement element, String text, String objName) {
			Select selectCity = new Select(element);
			selectCity.selectByVisibleText(text);
			baseTestlog.info(objName + " selected " + text);

		}
		//To select the text using selectByIndex method
		public void selectByIndexData(WebElement element, int index, String objName) {
			waitForVisibility(element, 5, objName);
			Select selectCity = new Select(element);
			selectCity.selectByIndex(index);
			baseTestlog.info(objName + " selected with index=" + index);

		}
		//To select the text using selectByValue method
		public void selectByValueData(WebElement element, String text, String objName) {
			Select selectCity = new Select(element);
			selectCity.selectByValue(text);
			baseTestlog.info(objName + " selected ");
		}
		//To select the text from the List
		public WebElement selectFromListUsingText(List<WebElement> list, String text) {
			WebElement element = null;
			for (WebElement i : list) {
				if (i.getText().equalsIgnoreCase(text)) {
					baseTestlog.info("selected=" + i.getText());
					element = i;
					break;
				}

			}
			return element;

		}
		
      //Method for pageLoadTimout
		public void waitUntilPageLoads() {
			baseTestlog.info("waiting until page loads with 30 sec maximum");
			driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		}
		//Method for fluent wait for the visibility of the element
		public void waitForVisibility(WebElement ele, int time, int pollingtime, String objectName) {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait
			.withTimeout(Duration.ofSeconds(time))
			.pollingEvery(Duration.ofSeconds(pollingtime))
			.ignoring(ElementNotInteractableException.class)
			.withMessage(objectName+" is not visible.fluent wait time expires");

			wait.until(ExpectedConditions.visibilityOf(ele));
			baseTestlog.info(objectName + " is waited for visibility using fluent wait");
		}
      //Method for explicit wait until the presence of the element
		public void WaitUntilPresenceOfElementLocatedBy(By locator, String objName) {
			baseTestlog.info("waiting for an web element " + objName + " for its visibility");
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		}
		//Method for explicit wait of the element to be clickable
		public void waitUntilElementToBeClickable(By locator, String objName) {
			baseTestlog.info("waiting for an web element " + objName + " to be clickable");
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		//Method for explicit wait for the element to be visible
		public void waitForVisibility(WebElement ele, int time, String objectName) {
			baseTestlog.info(objectName + " is waited for visibility ");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));
		}
		//Method for explicit wait for the alert to be present
		public void waitForAlertPresent(int time) {
			baseTestlog.info( "waited for alert to display ");
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.alertIsPresent());
		}
        //To switch to new window
		public void switchToNewWindowFrom(String currentWindowHandle) {
			Set<String> allWindowHandles = driver.getWindowHandles();
			baseTestlog.info(""+allWindowHandles.size());
			for (String handle : allWindowHandles) {
				if (!currentWindowHandle.equalsIgnoreCase(handle))
					driver.switchTo().window(handle);
			}
			baseTestlog.info("switched to new window");
		}
		
		//Screen shot for the webpage at a time
		public void takescreenshot(String filepath) {
			 TakesScreenshot screenCapture=(TakesScreenshot)driver;
			 File src=screenCapture.getScreenshotAs(OutputType.FILE);
			 File destination=new File(filepath);
			 try {
				Files.copy(src, destination);
				baseTestlog.info("captured the screen");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				baseTestlog.info("Went wrong when capturing the screen");
				
			}
		}
		//screenshot for single webelement
		public void takescreenshot(WebElement element,String filepath) {
			 File src=element.getScreenshotAs(OutputType.FILE);
			 File destination=new File(filepath);
			 try {
				Files.copy(src, destination);
				baseTestlog.info("Captured Element Screenshot");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				baseTestlog.info("Went wrong when capturing the screen");
				
			}
		}

}



