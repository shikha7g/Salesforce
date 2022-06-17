package com.firebase.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.firebase.utility.CommonUtilities;
import com.firebase.utility.Constants;
import com.firebase.utility.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static WebElement element;
	 public static ExtentHtmlReporter htmlReporter;
	 public static ExtentReports extent;
	 public static ExtentTest logger;
	 public static GenerateReports report;
	
	 @BeforeTest
	 public static void initialTestSetup() {
		report= GenerateReports.getInstance();
		report.startExtentReport();
	System.out.println("create report object");
	 }  


	@BeforeMethod	
	@Parameters("browser")
	public static void setUp(Method method, String browser) {
	
		report.startSingleTestReport(method.getName());
		System.out.println("Before Method started and browser name="+browser);
	
		getDriver(browser);
			   
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String url=CommonUtilities.getPropertyValue("url");
			
		goToURL(url);
	}
	
	 
	@AfterTest
	public static void finalTestTearDown() {
		report.endReport();
	}
	
	
	
	 /* name of the method:   getDriver
	 * BriefDescription  :   create firefox browser instance
	 * Arguments         :  
	 *            
	 *  createdby        :  Automation team 
	 *  created date     :5/5/22 
	 *  LastModified Date:5/5/22        
	 */
	
	
	
	public static void getDriver(String browser) {
		if (browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup(); //instead of hardcoding starting the server with .pom xml
			driver=new FirefoxDriver();
		
			report.logTestInfo("Firefox Driver Instance Created");
		}else if (browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup(); //instead of hardcoding starting the server with .pom xml
			driver=new ChromeDriver();
			report.logTestInfo("Chrome Driver Instance Created");
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup(); //instead of hardcoding starting the server with .pom xml
			driver=new EdgeDriver();
			report.logTestInfo("Edge Driver Instance Created");
		}
		
		
	}
	
	public static WebDriver getDriverInstance(){
		return driver;
	}
	
	public static void goToURL(String url) {
		driver.get(url);
		report.logTestInfo("Url entered is :"+url);
	}
	

	
	public static void login(String user,String password) {
		
		By username = By.id("username");
		WebElement userNameLocator=driver.findElement(username);
		waitUntilVisible(userNameLocator,"user name");
		
		enterText(userNameLocator, user, "Username entered");
		
		By passwordLocator = By.id("password");
		WebElement passLocator=driver.findElement(passwordLocator);
		enterText(passLocator,password, "Entered Password");
		
	}
	
	public static void clickLoginButton() {
		WebElement loginButton=driver.findElement(By.id("Login"));
		clickElement(loginButton, "Click login");
		
	}
	

	@AfterMethod
	public static void closeAllWindows() {
		closeAllDriver();
	}
	
	public static void closeDriver() {
		driver.close();
		report.logTestInfo("Close the driver");
	}
	
	public static void closeAllDriver() {
		driver.quit();
		report.logTestInfo("Closed all drivers");
	}
	/* name of the method:   enterText
	 * BriefDescription  :   entering textvalue for textbox
	 * Arguments         :  element-->web element
	 *                      text--->to be entered 
	 *            
	 *  createdby        :  Automation team 
	 *  created date     :01/20/22 
	 *  LastModified Date:01/20/22          
	 */
	public static void enterText(WebElement element,String text,String objName) {
		if(element.isDisplayed()) {
			clearElement(element,objName);
			element.sendKeys(text);
			
			report.logTestInfo("text entered in "+ objName +" field");
		}
		else {
			
			report.logTestInfo( objName +"element not displayed");
		
		}
	}
	public static void clickElement(WebElement element,String objName) {
		if(element.isDisplayed()) {
			element.click();
			report.logTestInfo( objName +"element clicked");
			
		}
		else {
			report.logTestInfo(objName +"element not displayed");
			
		}
	}
	
	public static void clearElement(WebElement element,String objName) {
		if(element.isDisplayed()) {
			element.clear();
			report.logTestInfo(objName +"elment value is cleared");
			
		}
		else {
			report.logTestInfo(objName +"elment not displayed");
		}
	}
	public static WebElement elementToBeFind(By locator) {
		element = driver.findElement(locator);
		return element;
	}
	
	public static List elementsToBeFind(By locator) {
		List<WebElement> listOfElements=driver.findElements(locator);
		return listOfElements;
	}
	
	
	
	public static void waitUntilVisible(WebElement element,String objName) {
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
		report.logTestInfo("Waiting for the "+objName +"to be visible");
		}
	public static void waitUntilvisibilityOfElementLocated(By locator,String objName) {
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		report.logTestInfo("Waiting for the locator "+objName +"to be visible");
		}
	
	public static void waitUntilAlertIsPresent() {
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
		report.logTestInfo("Waiting for the alert to be visible");
	}
	public static void waitUntilElementToBeClickable(By locator,String objName) {
		wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		report.logTestInfo("Waiting until the "+objName +"to be clicked");
	}
	
	public static void mouseOver(WebElement element,String objName) {
		waitUntilVisible(element,objName);
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
		report.logTestInfo("Moving the mouse to the "+objName );
	}
	private static Alert switchToAlert() {
		// TODO Auto-generated method stub
		 return driver.switchTo().alert();
		 
	}
	
	public static void AcceptAlert() {
		waitUntilAlertIsPresent();
		Alert alert=switchToAlert();
		alert.accept();
		report.logTestInfo("Alert accepted: "+alert.getText());
	}
	public static void dismisAlert() {
		waitUntilAlertIsPresent();
		Alert alert=switchToAlert();
		alert.dismiss();
		report.logTestInfo("Alert dismissed: "+alert.getText());
		
	}
	public static void selectByTextData(WebElement element,String text,String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByVisibleText(text);
		report.logTestInfo(objName+" selected "+text);
		
		
	}
	public static void selectCheckbox(WebElement element,String objName) {
			if(element.isSelected()==false) {
				element.click();
			}
			report.logTestInfo(objName+ " selected ");
		
		
	}
	public static void selectByIndexData(WebElement element,int index,String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByIndex(index);
		report.logTestInfo(objName+ " selected by index "+index);
	}
	public static void selectByValueData(WebElement element,String text) {
		Select selectCity = new Select(element);
		selectCity.selectByValue(text);
		report.logTestInfo(element+ " selected by value "+text);
	}
	
	public static void getScreenshot(WebDriver driver, String fileName ) {
		TakesScreenshot screenCapture= (TakesScreenshot)driver;
		File sourceFile= screenCapture.getScreenshotAs(OutputType.FILE);
		String path= Constants.SCRRENSHOT_PATH+fileName;
		File destFile= new File(path);
		try {
			FileUtils.copyFile(sourceFile, destFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.logTestFailedWithException(e);
		}
		report.logTestInfo("Screenshot captured successfully");
	}
	

	public static void switchTonewWindow(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
	}
	

	
}
