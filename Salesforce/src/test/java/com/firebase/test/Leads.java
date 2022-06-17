package com.firebase.test;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firebase.base.BaseTest;
import com.firebase.utility.CommonUtilities;
import com.firebase.utility.Constants;

public class Leads extends BaseTest {
	
	@Test
	public static void testCase20() {
		getLeadsPage();
		
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
	}
	
	@Test
	public static void testCase21() throws InterruptedException {
		getLeadsPage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		WebElement viewLeadsDropDown= driver.findElement(By.id("fcf"));

		Select select = new Select(viewLeadsDropDown);  
		 List<WebElement> options = select.getOptions();  
		 ArrayList<String>actualValues= new ArrayList<String>();
		 for(WebElement actualValuesText:options)  
		 {  
			 actualValues.add(actualValuesText.getText());
		 }
		
		ArrayList<String> expectedOptionList= new ArrayList<String>();
		expectedOptionList.add("All Open Leads");
		expectedOptionList.add("My Unread Leads");
		expectedOptionList.add("Recently Viewed Leads");
		expectedOptionList.add("Today's Leads");
		
		
		boolean flag= actualValues.containsAll(expectedOptionList);
			assertTrue(flag,"Drop down is having all values");
			
		
	}
	
	@Test
	public static void testCase22() throws InterruptedException {
		getLeadsPage();
		Thread.sleep(5000);
		
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		WebElement viewLeadsDropDown= driver.findElement(By.id("fcf"));
		
		selectByTextData(viewLeadsDropDown, "Today's Leads", "Todays Lead Selected");
		By userMenu = By.xpath("//div[@id='userNavButton']");
		waitUntilvisibilityOfElementLocated(userMenu, "User menu displayed");
		WebElement userMenuElement= driver.findElement(userMenu);
		mouseOver(userMenuElement, "Mouse on UserMenu");
		clickElement(userMenuElement, "User Menu");
		By logoutButtonLocator= By.xpath("//a[contains(text(),'Logout')]");
		waitUntilvisibilityOfElementLocated(logoutButtonLocator, "waiting for logout button");
		WebElement logoutButton= driver.findElement(logoutButtonLocator);
		
		clickElement(logoutButton, "Click logout link");
		getLeadsPage();
		Thread.sleep(5000);
		WebElement goButton= driver.findElement(By.xpath("//input[@title='Go!']"));
		clickElement(goButton, "Go Button");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		getScreenshot(driver, "LeadsView_TC22");
	
	}
	
	@Test
	public static void testCase23() throws InterruptedException {
		getLeadsPage();
		Thread.sleep(5000);
		
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		WebElement viewLeadsDropDown= driver.findElement(By.id("fcf"));
		
		selectByTextData(viewLeadsDropDown, "Today's Leads", "Todays Lead Selected");
		WebElement goButton= driver.findElement(By.xpath("//input[@title='Go!']"));
		clickElement(goButton, "Go Button");
		getScreenshot(driver, "TodayLeadsView_TC23");
	
	}
	
	@Test
	public static void testCase24() throws InterruptedException {
		getLeadsPage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		driver.findElement(By.xpath("//td[@class='pbButton']//input[@type='button']")).click();
		WebElement lastName=driver.findElement(By.id("name_lastlea2"));
		enterText(lastName,"ABCD","Last Name");
		WebElement companyName=driver.findElement(By.id("lea3"));
		enterText(companyName,"ABCD","Company Name");
		WebElement saveButton= driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']"));
		clickElement(saveButton, "Save Lead");
		getScreenshot(driver,"TodayLead_TC24");
			
	}
	public static void getLeadsPage() {
		
		String user= CommonUtilities.getPropertyValue("userid");
		String password= CommonUtilities.getPropertyValue("password");
		login(user,password);
		clickLoginButton();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		By userMenu= By.xpath("//div[@id='userNavButton']");
		waitUntilvisibilityOfElementLocated(userMenu, "User menu displayed");
		WebElement userNameElement= driver.findElement(userMenu);
		String userName = userNameElement.getText();
		String expectedName= CommonUtilities.getPropertyValue(Constants.EXPECTED_USER_NAME);
		Assert.assertEquals(userName, expectedName);
		WebElement accounts = driver.findElement(By.linkText("Leads"));
		clickElement(accounts, "Leads Link");
		report.logTestInfo("Leads page is displayed succesfully");
	}
}
