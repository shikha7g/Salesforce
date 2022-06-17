package com.firebase.test;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
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

public class CreateOpportunitiesScript extends BaseTest {
	
	@Test
	
	public static void testCase15() {
		
		getOpportunityPage();
		
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		WebElement viewOpportunitiesDropDown= driver.findElement(By.id("fcf"));
		Select select = new Select(viewOpportunitiesDropDown);  
		 List<WebElement> options = select.getOptions();  
		 ArrayList<String>actualValues= new ArrayList<String>();
		 for(WebElement actualValuesText:options)  
		 {  
			 actualValues.add(actualValuesText.getText());
		 }
		
		ArrayList<String> expectedOptionList= new ArrayList<String>();
		expectedOptionList.add("All Opportunities");
		expectedOptionList.add("Closing Next Month");
		expectedOptionList.add("My Opportunities");
		expectedOptionList.add("New This Week");
		expectedOptionList.add("Recently Viewed Opportunities");
		expectedOptionList.add("Won");
		
		boolean flag= actualValues.containsAll(expectedOptionList);
		
		
			assertTrue(flag,"Drop down is having all values");
			
			
	}	
	
	@Test
	
	public static void testCase16() throws InterruptedException {
		getOpportunityPage();
		Thread.sleep(5000);
	
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
	
		driver.findElement(By.xpath("//td[@class='pbButton']//input[@type='button']")).click();
	
		WebElement oppName=driver.findElement(By.id("opp3"));
		String opportunityName= CommonUtilities.getPropertyValue(Constants.OPPORTUNITY_NAME);
		enterText(oppName,opportunityName,"Opportunity Name");
		WebElement accountName=driver.findElement(By.xpath("//*[@id='opp4']"));
	
		String accountNameVal= CommonUtilities.getPropertyValue(Constants.OPPORTUNITY_ACCOUNT_NAME);
		
		enterText(accountName,accountNameVal,"Account Name");
		WebElement closedDate= driver.findElement(By.id("opp9"));
		closedDate.click();
		WebElement selectMonth= driver.findElement(By.xpath("//select[@id='calMonthPicker']"));
		selectByIndexData(selectMonth, 3, "Select Month");
		
		WebElement selectDate= driver.findElement(By.xpath("//table[@id='datePickerCalendar']//tr[2]//td[3]"));
			
		mouseOver(selectDate, "Select Date");
		clickElement(selectDate, "Seelct Date");
		
		
		WebElement stage= driver.findElement(By.id("opp11"));
		selectByIndexData(stage, 2, "Stage ");
		WebElement probability= driver.findElement(By.id("opp12"));
		enterText(probability, "1", "Probability");
		WebElement leadSource= driver.findElement(By.id("opp6"));
		selectByIndexData(leadSource, 1, "Stage ");
		WebElement primaryCampaign= driver.findElement(By.id("opp17"));
		enterText(primaryCampaign, "", "Primary Campaign");
		WebElement saveButton= driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']"));
		clickElement(saveButton, "Save Opportunity");
		getScreenshot(driver,"NewOpportunity_TC16");
	
		
	}
	
	@Test
	
	public static void testCase17() throws InterruptedException {
		getOpportunityPage();
		Thread.sleep(5000);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		WebElement opportunityPipelineLink= driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]"));
		clickElement(opportunityPipelineLink, "Click Opportunity link");
		getScreenshot(driver, "OpportunityReportPage_TC17");
		
	}
	
	@Test
	
	public static void testCase18() throws InterruptedException {
		getOpportunityPage();
		Thread.sleep(5000);
		
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement opportunityStruckLink= driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		clickElement(opportunityStruckLink, "Struck Opportunity link");
		getScreenshot(driver, "OpportunityStruckReportPage_TC18");
	
	}
	
	@Test
	
	public static void testCase19() throws InterruptedException {
		getOpportunityPage();
		WebElement interval= driver.findElement(By.id("quarter_q"));
		selectByTextData(interval, "Current FQ", "interval");
		
		WebElement include= driver.findElement(By.id("open"));
		selectByTextData(include, "All Opportunities", "include");
		WebElement runReport=driver.findElement(By.xpath("//input[@title='Run Report']"));
		waitUntilVisible(runReport, "Run Report");
		Thread.sleep(5000);
		clickElement(runReport, "Run Report");
		getScreenshot(driver, "OpportunityReportThatSatifiesPage_TC19");
		
	}
	
	public static void getOpportunityPage() {
		
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
		WebElement accounts = driver.findElement(By.linkText("Opportunities"));
		clickElement(accounts, "Opportunities Link");
		report.logTestInfo("Opportunity page is displayed");
	}
}
