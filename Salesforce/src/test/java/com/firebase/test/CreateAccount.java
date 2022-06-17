package com.firebase.test;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firebase.base.BaseTest;
import com.firebase.utility.CommonUtilities;
import com.firebase.utility.Constants;

public class CreateAccount extends BaseTest {

	@Test
	public static void testCase10() throws InterruptedException {
		getAccountPage();
		Thread.sleep(5000);
	//	waitUntilElementToBeClickable(By.xpath("//td[@class='pbButton']//input[@type='button']"), "New");
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String actualTitle= driver.getTitle();
		String expectedTitle= CommonUtilities.getPropertyValue(Constants.ACCOUNT_PAGE_TITLE);
		assertEquals(actualTitle, expectedTitle);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		driver.findElement(By.xpath("//td[@class='pbButton']//input[@type='button']")).click();
		String accountName= CommonUtilities.getPropertyValue(Constants.ACCOUNT_NAME);
		WebElement accName=driver.findElement(By.id("acc2"));
		enterText(accName, accountName,"Account Name");
		WebElement saveButton= driver.findElement(By.xpath("//td[@class='pbButtonb']//input[@title='Save']"));
		clickElement(saveButton, "Save Account Details");
		getScreenshot(driver,"NewAccountPage_TC10"); 
		
		
	}
	
	@Test
	public static void testCase11() throws InterruptedException {
		getAccountPage();
		Thread.sleep(5000);
	//	waitUntilElementToBeClickable(By.xpath("//td[@class='pbButton']//input[@type='button']"), "New");
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		driver.findElement(By.xpath("//a[contains(text(),'Create New View')]")).click();
		WebElement viewNameElement=driver.findElement(By.id("fname"));
		String viewName= CommonUtilities.getPropertyValue(Constants.VIEW_NAME);
		String viewUniqueName= CommonUtilities.getPropertyValue(Constants.VIEW_UNIQUE_NAME);
		enterText(viewNameElement, viewName,"Account Name");
		WebElement viewUniqueNameElement=driver.findElement(By.id("devname"));
		viewUniqueNameElement.clear();
		enterText(viewUniqueNameElement, viewUniqueName, "View Unique Name");
		WebElement saveButton= driver.findElement(By.xpath("//td[@class='pbButtonb']//input[@title='Save']"));
		clickElement(saveButton, "Save Account Details");
		driver.wait(5000);		
		getScreenshot(driver,"NewAddedView_TC11");
		
	}
	
	public static void getAccountPage() throws InterruptedException {
		String expectedName=CommonUtilities.getPropertyValue(Constants.EXPECTED_USER_NAME);
		String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
		String password= CommonUtilities.getPropertyValue(Constants.PASSWORD);
		login(user,password);
		clickLoginButton();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		By userMenu= By.xpath("//div[@id='userNavButton']");
		waitUntilvisibilityOfElementLocated(userMenu, "User menu displayed");
		WebElement userNameElement= driver.findElement(userMenu);
		String userName = userNameElement.getText();
		Assert.assertEquals(userName, expectedName);
		WebElement accounts = driver.findElement(By.linkText("Accounts"));
		clickElement(accounts, "Accounts Link");
		report.logTestInfo("Account page is displayed");
	}
	
	@Test
	public static void testCase12() throws InterruptedException {
		getAccountPage();
		Thread.sleep(5000);
	//	waitUntilElementToBeClickable(By.xpath("//td[@class='pbButton']//input[@type='button']"), "New");
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		WebElement accountViewDropDown= driver.findElement(By.id("fcf"));
		String viewName= CommonUtilities.getPropertyValue(Constants.VIEW_NAME);
		selectByTextData(accountViewDropDown, viewName, "View Name Selected");
		driver.findElement(By.linkText("Edit")).click();
		WebElement viewNameElement= driver.findElement(By.id("fname"));
		String updatedViewName= CommonUtilities.getPropertyValue(Constants.UPDATED_VIEW_NAME);
		enterText(viewNameElement, updatedViewName, "Update View Name");
		WebElement filterField= driver.findElement(By.xpath("//select[@id='fcol1']"));
		selectByTextData(filterField, "Account Name", "Field Filter");
		WebElement operatorField= driver.findElement(By.xpath("//select[@id='fop1']"));
		selectByTextData(operatorField, "equals", "Operator Filter");
		WebElement valueField= driver.findElement(By.id("fval1"));
		String accountName= CommonUtilities.getPropertyValue(Constants.ACCOUNT_NAME);
		enterText(valueField, accountName, "Enter Value");
		
		WebElement displayField= driver.findElement(By.xpath("//select[@id='colselector_select_0']"));
		selectByTextData(displayField, "Last Activity", "Display field");
		WebElement addButton= driver.findElement(By.xpath("//a[@id='colselector_select_0_right']"));
		clickElement(addButton, "Add Button");
		
		WebElement saveButton= driver.findElement(By.xpath("//td[@class='pbButtonb']//input[@title='Save']"));
		clickElement(saveButton, "Save Button");
		
		getScreenshot(driver, "EditViewPage_TC12");
	
	}
	
	@Test
	public static void testCase13() throws InterruptedException {
		getAccountPage();
		Thread.sleep(5000);
	
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		WebElement mergeAccounts= driver.findElement(By.xpath("//a[contains(text(),'Merge Accounts')]"));
		clickElement(mergeAccounts, "merge Accounts link");
		String accountName= CommonUtilities.getPropertyValue(Constants.ACCOUNT_NAME);
		driver.findElement(By.id("srch")).sendKeys(accountName);
		driver.findElement(By.xpath("//div[contains(@class,'pbWizardBody')]//input[contains(@type,'submit')]")).click();
		
		WebElement chkbox= driver.findElement(By.id("cid0"));
		selectCheckbox(chkbox, "Checkbox");
		WebElement chkbox1= driver.findElement(By.id("cid1"));
		selectCheckbox(chkbox1, "Checkbox");
		driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@title='Next']")).click();
		driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@title='Merge']")).click();
		AcceptAlert();
	
		getScreenshot(driver, "NewMergeAccount_TC13");
		
	}
	
	@Test
	public static void testCase14() throws InterruptedException, AWTException {
		

		getAccountPage();
		Thread.sleep(5000);
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement closePopUp= driver.findElement(By.id("tryLexDialogX"));
		clickElement(closePopUp, "Close Pop Up");
		
		WebElement lastActivity= driver.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]"));
		clickElement(lastActivity, "Account Activity >30 days");
		WebElement dateFieldDropdown = driver.findElement(By.name("dateColumn"));
		clickElement(dateFieldDropdown, "Date Field dropdown menu");
		WebElement createdDateOption = driver.findElement(By.xpath("//div[contains(text(),'Created Date')]"));
		clickElement(createdDateOption, "Created Date option");
		WebElement fromDatePicker = driver.findElement(By.id("ext-gen152"));
		clickElement(fromDatePicker, "From date picker");
		WebElement todayButton = driver.findElement(By.xpath("//button[contains(text(),'Today')]")); //problem with the id of Today button. It's constantly changing in every Test run.
		clickElement(todayButton, "Today button in From date picker");
		WebElement toDatePicker = driver.findElement(By.id("ext-gen154"));
		clickElement(toDatePicker, "To date picker");
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_SPACE); //hitting the spacebar key selects Today's date
		robot.keyRelease(KeyEvent.VK_SPACE);
		System.out.println("Today's date sent in To date picker");
		System.out.println("Today's date sent in To date picker");
		// WebElement todayButton1 = locate(By.xpath("/html[1]/body[1]/div[18]/ul[1]/li[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]/table[1]/tbody[1]/tr[2]/td[2]/em[1]/button[1]"));
		// singleClick(todayButton1, "Today button in To date picker");
		Thread.sleep(3000);
		WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
		clickElement(saveButton, "Save button");
		//save and run report
		By reportLocator = By.cssSelector("#saveReportDlg_reportNameField");
//		fluentWait(reportLocator);
		WebElement reportName = driver.findElement(reportLocator);
		enterText(reportName, "Test report3", "Report Name textbox");
		WebElement uniqueReportName = driver.findElement(By.name("reportDevName"));
		enterText(uniqueReportName, "Test_report3unique", "Report Unique Name textbox");
		Thread.sleep(3000);
		WebElement saveRunReportButton = driver.findElement(By.xpath("//button[contains(text(),'Save and Run Report')]"));
		clickElement(saveRunReportButton, "Save and Run Report button");

	//	waitforPageToLoad();
	//	String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		getScreenshot(driver, "ReportPage_TC14");
		


		
	}
	
}
