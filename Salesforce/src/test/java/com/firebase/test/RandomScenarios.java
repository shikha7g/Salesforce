package com.firebase.test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.firebase.base.BaseTest;
import com.firebase.utility.CommonUtilities;

public class RandomScenarios extends BaseTest {

	
	@Test
	public static void testCase33() throws InterruptedException {
		getHomePage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		driver.findElement(By.xpath("//a[contains(text(),'Pooja chabra')]")).click();
	
		getScreenshot(driver, "HomeProfilePage_33");
		
	}
	
	@Test
	public static void testCase34() throws InterruptedException {
		getHomePage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		driver.findElement(By.xpath("//a[contains(text(),'Pooja Chabra')]")).click();
	
		WebElement editProfileLink= driver.findElement(By.xpath("//a[contains(@class,'contactInfoLaunch editLink')]"));
		waitUntilVisible(editProfileLink, " Edit profile screen");
		clickElement(editProfileLink, " Edit profile button");
		driver.switchTo().frame("contactInfoContentId");
		By aboutLocator= By.linkText("About");
		
		waitUntilvisibilityOfElementLocated(aboutLocator, "Waiting About Tab");
		WebElement aboutTab= driver.findElement(aboutLocator);
		clickElement(aboutTab, " About Tab");
		WebElement lastName= driver.findElement(By.id("lastName"));
		enterText(lastName, "Abcd","Updated last name");
		WebElement saveAll = driver.findElement(By.xpath("//input[@value='Save All']"));
		clickElement(saveAll, " Save All");
		driver.switchTo().parentFrame();
		getScreenshot(driver, "UpdatedUserProfile_TC34");
		
	}
	
	
	
	@Test
	public static void testCase35() throws InterruptedException {
		String user= CommonUtilities.getPropertyValue("userid");
		String password= CommonUtilities.getPropertyValue("password");
		login(user,password);
		clickLoginButton();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//img[@class='allTabsArrow']")).click();
		
		driver.findElement(By.xpath("//td[contains(@class,'bCustomize')]//input[contains(@type,'button')]")).click();
		WebElement selectedTabs= driver.findElement(By.id("duel_select_1"));
		selectByTextData(selectedTabs, "Subscriptions", "Selected Tabs");
		
		driver.findElement(By.xpath("//img[contains(@class,'leftArrowIcon')]")).click();
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']")).click();
		By userMenu = By.xpath("//div[@id='userNavButton']");
		waitUntilvisibilityOfElementLocated(userMenu, "User menu displayed");
		WebElement userMenuElement= driver.findElement(userMenu);
		mouseOver(userMenuElement, "Mouse on UserMenu");
		clickElement(userMenuElement, "ClickUser Menu");
		By logout= By.xpath("//a[contains(text(),'Logout')]");
		
		waitUntilvisibilityOfElementLocated(logout, "Logout");
		WebElement logoutLink= driver.findElement(logout);
		clickElement(logoutLink, " Logout option");
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		Thread.sleep(5000);
		login(CommonUtilities.getPropertyValue("userid"),CommonUtilities.getPropertyValue("password"));
		clickLoginButton();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public static void testCase36() throws InterruptedException {
		getHomePage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		//Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement currentDate= driver.findElement(By.xpath("//*[@id='ptBody']/div/div[2]/span/a"));
		mouseOver(currentDate, "Current Date link");
		clickElement(currentDate, "Current Date ");
		Thread.sleep(8000);
		
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		WebElement eightPM= driver.findElement(By.id("p:f:j_id25:j_id61:28:j_id64"));
		mouseOver(eightPM, "Eight PM");
		clickElement(eightPM, "eight pm");
		driver.findElement(By.xpath("//img[@title='Subject Combo (New Window)']")).click();
		Thread.sleep(5000);
		String baseWindowHandle=driver.getWindowHandle();
		System.out.println("handle value of base window="+baseWindowHandle);
	
		Set<String> allWindowHandles=driver.getWindowHandles();
		int windowHandleSize= allWindowHandles.size();
		System.out.println(windowHandleSize);
		Thread.sleep(5000);
		for(String handle:allWindowHandles) {
			if(!handle.equalsIgnoreCase(baseWindowHandle)) {
				driver.switchTo().window(handle);
				driver.findElement(By.linkText("Other")).click();
				break;
			}
		}	
				
		driver.switchTo().window(baseWindowHandle);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='EndDateTime_time']")).clear();
		driver.findElement(By.xpath("//input[@id='EndDateTime_time']")).sendKeys("9.00 PM");
	
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']")).click();
	
	}	
	
	
	@Test
	public static void testCase37() throws InterruptedException {
		getHomePage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
	
		WebElement currentDate= driver.findElement(By.xpath("//*[@id='ptBody']/div/div[2]/span/a"));
		mouseOver(currentDate, "Current Date link");
		clickElement(currentDate, "Current Date ");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,150)");
		WebElement fourPM= driver.findElement(By.id("p:f:j_id25:j_id61:20:j_id64"));
		mouseOver(fourPM, "Four PM");
		clickElement(fourPM, "four pm");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//img[@title='Subject Combo (New Window)']")).click();
		Thread.sleep(5000);
		String baseWindowHandle=driver.getWindowHandle();
		System.out.println("handle value of base window="+baseWindowHandle);
	
		Set<String> allWindowHandles=driver.getWindowHandles();
		int windowHandleSize= allWindowHandles.size();
		System.out.println(windowHandleSize);
		Thread.sleep(5000);
		for(String handle:allWindowHandles) {
			if(!handle.equalsIgnoreCase(baseWindowHandle)) {
				driver.switchTo().window(handle);
				driver.findElement(By.linkText("Other")).click();
				break;
			}
		}	
				
		driver.switchTo().window(baseWindowHandle);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='EndDateTime_time']")).clear();
		driver.findElement(By.xpath("//input[@id='EndDateTime_time']")).sendKeys("7:00 PM");
		
		WebElement recurrenceChkBox=driver.findElement(By.id("IsRecurrence"));
		if(recurrenceChkBox.isSelected()==false) {
			recurrenceChkBox.click();
		}
		
		driver.findElement(By.id("rectypeftw")).click();
		WebElement recurrenceEndDate= driver.findElement(By.id("RecurrenceEndDateOnly"));
		recurrenceEndDate.click();
	
		WebElement selectMonth= driver.findElement(By.xpath("//select[@id='calMonthPicker']"));
		selectByIndexData(selectMonth, 6, "Select Month");

		WebElement selectDate= driver.findElement(By.xpath("//table[@id='datePickerCalendar']//tr[2]//td[3]"));
			
		mouseOver(selectDate, "Select Date");
		clickElement(selectDate, "Seelct Date");
		System.out.println("ccccc");
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']")).click();
		//driver.findElement(By.xpath("//img[@class='monthViewIconOn']")).click();
		driver.findElement(By.xpath("//span[@class='dwmIcons']/a[@title='Month View']/img[@class='monthViewIcon']")).click();
		getScreenshot(driver, "CalenderViewMonthly_TC37");
	
	}	
	
	public static void getHomePage() throws InterruptedException {
		String user= CommonUtilities.getPropertyValue("userid");
		String password= CommonUtilities.getPropertyValue("password");
		login(user,password);
		clickLoginButton();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		WebElement home = driver.findElement(By.xpath("//li[@id='home_Tab']"));
		clickElement(home, "Home");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
		report.logTestInfo("Home page displayed successfully");
	}
}
