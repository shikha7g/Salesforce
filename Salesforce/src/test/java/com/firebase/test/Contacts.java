package com.firebase.test;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.firebase.base.BaseTest;
import com.firebase.utility.CommonUtilities;
import com.firebase.utility.Constants;

import net.bytebuddy.matcher.CollectionOneToOneMatcher;

public class Contacts extends BaseTest{
	
	
	@Test
	public static void testCase25() throws InterruptedException {
		getContactPage();
		Thread.sleep(5000);
		
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		driver.findElement(By.xpath("//td[@class='pbButton']//input[@type='button']")).click();
		WebElement lastName=driver.findElement(By.id("name_lastcon2"));
		String lastNameVal= CommonUtilities.getPropertyValue(Constants.EDIT_PROFILE_LASTNAME);
		enterText(lastName,lastNameVal,"Last Name");
		WebElement accountName=driver.findElement(By.id("con4"));
		String accountNameVal= CommonUtilities.getPropertyValue(Constants.OPPORTUNITY_ACCOUNT_NAME);
		enterText(accountName,accountNameVal,"Account Name");
		WebElement saveButton= driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']"));
		clickElement(saveButton, "Save Contact");
		getScreenshot(driver,"NewContact_TC25");
		
	}
	
	@Test
	public static void testCase26() throws InterruptedException {
		getContactPage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		driver.findElement(By.xpath("//a[contains(text(),'Create New View')]")).click();
		WebElement viewName=driver.findElement(By.id("fname"));
		String viewNameVal= CommonUtilities.getPropertyValue(Constants.VIEW_NAME);
		enterText(viewName,viewNameVal,"View Name");
		WebElement uniqueName=driver.findElement(By.id("devname"));
		String viewUniqueNameVal= CommonUtilities.getPropertyValue(Constants.VIEW_UNIQUE_NAME);
		enterText(uniqueName,viewUniqueNameVal,"View Unique Name");
		driver.findElement(By.xpath("//td[@class='pbButtonb']//input[@title='Save']")).click();
		getScreenshot(driver,"CreateNewViewContact_TC26");
		
	}
	
	@Test
	public static void testCase27() throws InterruptedException {
		getContactPage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		WebElement recentCreated=driver.findElement(By.id("hotlist_mode"));
		selectByTextData(recentCreated, "Recently Created", "Recently Created");
		getScreenshot(driver, "RecentlyCreatedContact_TC27");
		
	}	
	
	@Test
	public static void testCase28() throws InterruptedException {
		getContactPage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		WebElement viewDropDown=driver.findElement(By.id("fcf"));
		selectByTextData(viewDropDown, "My Contacts", "My Contacts");
		getScreenshot(driver, "My Contacts_TC28");
		
	}	
	
	@Test
	public static void testCase29() throws InterruptedException {
		getContactPage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		driver.findElement(By.xpath("//table[@class='list']//tr[2]//th")).click();
		String contactName= CommonUtilities.getPropertyValue(Constants.EDIT_PROFILE_LASTNAME);
		driver.findElement(By.linkText(contactName)).click();
		
	}	
	
	@Test
	public static void testCase30() throws InterruptedException {
	
			getContactPage();
			Thread.sleep(5000);
			WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
			clickElement(closePopUp, "Close Pop Up");
			
			driver.findElement(By.xpath("//a[contains(text(),'Create New View')]")).click();
			WebElement uniqueName=driver.findElement(By.id("devname"));
			enterText(uniqueName, "EFGH", "Unique Name");
			driver.findElement(By.xpath("//td[@class='pbButtonb']//input[@title='Save']")).click();
			String errorMsg= driver.findElement(By.xpath("//div[contains(text(),'You must enter a value')]")).getText();
			
			
				assertEquals(errorMsg, Constants.CONTACT_ERROR_MSG);
				
	}		
	

	@Test
	public static void testCase31() throws InterruptedException {
		getContactPage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		driver.findElement(By.xpath("//a[contains(text(),'Create New View')]")).click();
		WebElement viewName=driver.findElement(By.id("fname"));
		enterText(viewName, "ABCD", "View Name");
		WebElement uniqueName=driver.findElement(By.id("devname"));
		enterText(uniqueName, "EFGH", "Unique Name");
		driver.findElement(By.xpath("//td[@class='pbButtonb']//input[@title='Cancel']")).click();
		getScreenshot(driver, "ContactsHomePage");
		
	}	
	
	@Test
	public static void testCase32() throws InterruptedException {
		getContactPage();
		Thread.sleep(5000);
		String expectedTitle="New Contact Page";
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		driver.findElement(By.xpath("//td[@class='pbButton']//input[@type='button']")).click();
		WebElement lastName=driver.findElement(By.id("name_lastcon2"));
		enterText(lastName,"Indian","Last Name");
		WebElement accountName=driver.findElement(By.id("con4"));
		enterText(accountName,"Global Media","Account Name");
		WebElement saveButton= driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save & New']"));
		clickElement(saveButton, "Save & New Contact");
		
			assertEquals(driver.getTitle(), expectedTitle);
			
	}	
	
	public static void getContactPage() throws InterruptedException {
		
		String user= CommonUtilities.getPropertyValue("userid");
		String password= CommonUtilities.getPropertyValue("password");
		login(user,password);
		clickLoginButton();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		WebElement contacts = driver.findElement(By.linkText("Contacts"));
		clickElement(contacts, "Contact Link");
		report.logTestInfo("Contact page displayed successfully");
	}

}
