package com.firebase.test;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firebase.base.BaseTest;
import com.firebase.utility.CommonUtilities;
import com.firebase.utility.Constants;

public class UserMenuDropDownScript extends BaseTest{

	@Test
	public static void goToUserMenu() throws InterruptedException  {
	
			String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
			String password= CommonUtilities.getPropertyValue(Constants.PASSWORD);
			login(user,password);
			clickLoginButton();
			
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		
			Thread.sleep(5000);
		
			String actualTitle= driver.getTitle();
			String expectedTitle= CommonUtilities.getPropertyValue(Constants.HOME_PAGE_TITLE);
			assertEquals(actualTitle, expectedTitle);
			
			String actuallUserName= driver.findElement(By.xpath("//span[@id='userNavLabel']")).getText();
			String expectedName= CommonUtilities.getPropertyValue(Constants.EXPECTED_USER_NAME);
			assertEquals(actuallUserName, expectedName);
			
			By userMenu = By.xpath("//div[@id='userNavButton']");
			waitUntilvisibilityOfElementLocated(userMenu, "User menu displayed");
			WebElement userMenuElement= driver.findElement(userMenu);
			mouseOver(userMenuElement, "Mouse on UserMenu");
			waitUntilElementToBeClickable(userMenu," User Menu Drop Down");
			clickElement(userMenuElement, "ClickUser Menu");
			
			List<WebElement> dropDownEntries= driver.findElements(By.xpath("//div[@id='userNav-menuItems']/a"));
			int a= dropDownEntries.size();
			System.out.println("list size::"+a);
			ArrayList<String> dropDownEntryTexts= new ArrayList<String>();
			for(WebElement value: dropDownEntries) {
				
				String text= value.getText();
				
				dropDownEntryTexts.add(text);
				
			}
			System.out.println(dropDownEntryTexts);
			ArrayList<String> expectedOptions= new ArrayList<String>(Arrays.asList(Constants.Drop_DOWN_OPTIONS));
			boolean flag=false;
						System.out.println(expectedOptions);
			flag= dropDownEntries.containsAll(expectedOptions);
			System.out.println(flag);
			
		//		Assert.assertTrue(flag,"Drop Down options not as expected");
				
		
	}
		
	public static void goToProfileLink() throws InterruptedException {
		goToUserMenu();
		By myProfileLocator= By.xpath("//a[contains(text(),'My Profile')]");
		waitUntilvisibilityOfElementLocated(myProfileLocator, "profile button");
		WebElement profileButton= driver.findElement(myProfileLocator);
		
		clickElement(profileButton, " My Profile link");
		report.logTestInfo("My profile page is displayed");
	}
	
	@Test	
	public static void testCase6() throws InterruptedException {
		goToProfileLink();		
		editProfileButton();
	
	}
	
	@Test	
	public static void testCase6a() throws InterruptedException {  // post link functionality
		goToProfileLink();
		WebElement post = driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'Post')]"));
		clickElement(post, "Post Link");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("/html/body")).sendKeys(CommonUtilities.getPropertyValue(Constants.UPDATE_POST_TEXT));
		driver.switchTo().parentFrame();
		WebElement shareButton = driver.findElement(By.id("publishersharebutton"));
		clickElement(shareButton, "Share button");
	
	}
	
	@Test
	
	public static void testCase6b() throws InterruptedException {  // file link functionality
		goToProfileLink();
	
		Thread.sleep(5000);
		WebElement fileLink = driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]"));
		clickElement(fileLink, "File Link");
		//Thread.sleep(5000);
		
		WebElement uploadFileFromComputer = driver.findElement(By.id("chatterUploadFileAction"));
		waitUntilVisible(uploadFileFromComputer, "upload file link");
		
		mouseOver(uploadFileFromComputer,"Upload a file");
		clickElement(uploadFileFromComputer, "link upload");
		WebElement chatterfile = driver.findElement(By.id("chatterFile"));
		//using direct file path and send keys
		chatterfile.sendKeys(CommonUtilities.getPropertyValue(Constants.EDIT_PROFILE_FILEPATH));
		waitUntilElementToBeClickable(By.id("publishersharebutton"),"Share Button");
		mouseOver(driver.findElement(By.id("publishersharebutton")),"Share Button");
		clickElement(driver.findElement(By.id("publishersharebutton")), "Share button");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		/*
		 * Actions action = new Action(driver);
		 * action.click(name)
		 * .keydown(keys.shift)
		 * .sendkeys("shikha")
		 * .keyup(keys.shift)
		 * .build.perform();
		 */
	/*	try {
			CommonUtilities.setClipboardData("C:\\Users\\tarun\\Downloads\\wallpaper.jpg");
			Robot robot;
		
			robot = new Robot();
		
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			getScreenshot(driver);
		}
		*/
		
	}
	
	@Test
	
	public static void testCase6c() throws InterruptedException  {
		goToProfileLink();
		
		WebElement MyProfilePhoto= driver.findElement(By.xpath("//div[@id='photoSection']"));
		mouseOver(MyProfilePhoto, "My Profile Photo");
		WebElement addPhoto= driver.findElement(By.xpath("//a[@id='uploadLink']"));
		clickElement(addPhoto, "Add Photo");
	
		driver.switchTo().frame(2);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement browseButton=driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
		browseButton.sendKeys(CommonUtilities.getPropertyValue(Constants.EDIT_PROFILE_PHOTOPATH));
		waitUntilvisibilityOfElementLocated(By.xpath("//*[@id=\'j_id0:uploadFileForm:uploadBtn\']"), "Upload Button");
		WebElement uploadButton= driver.findElement(By.xpath("//*[@id=\'j_id0:uploadFileForm:uploadBtn\']"));
		mouseOver(uploadButton, "Upload Button");
		clickElement(uploadButton, "Save Button");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement saveButton= driver.findElement(By.xpath("//*[@id=\'j_id0:j_id7:save\']"));
		mouseOver(saveButton, "Save Button");
		clickElement(saveButton, "Save Button");
		driver.switchTo().defaultContent();
		
	}
	
	public static void editProfileButton() {
		WebElement editProfileLink= driver.findElement(By.xpath("//a[contains(@class,'contactInfoLaunch editLink')]"));
		waitUntilVisible(editProfileLink, " Edit profile screen");
		clickElement(editProfileLink, " Edit profile button");
		driver.switchTo().frame("contactInfoContentId");
		By aboutLocator= By.linkText("About");
		
		waitUntilvisibilityOfElementLocated(aboutLocator, "Waiting About Tab");
		WebElement aboutTab= driver.findElement(aboutLocator);
		clickElement(aboutTab, " About Tab");
		WebElement lastName= driver.findElement(By.id("lastName"));
		String lastNameToBeUpdated= CommonUtilities.getPropertyValue(Constants.EDIT_PROFILE_LASTNAME);
		enterText(lastName, lastNameToBeUpdated, "Updated last name");
		WebElement saveAll = driver.findElement(By.xpath("//input[@value='Save All']"));
		clickElement(saveAll, " Save All");
		driver.switchTo().parentFrame();
		String actualUserNameOnProfilePage= driver.findElement(By.id("tailBreadcrumbNode")).getText();
		int index= actualUserNameOnProfilePage.indexOf(' ');
		int len= actualUserNameOnProfilePage.length();
		String LastNameOnProfilePage=actualUserNameOnProfilePage.substring(index+1,len-1);
		Assert.assertEquals(LastNameOnProfilePage, lastNameToBeUpdated);
	}
	
		
	@Test
	public static void testcase7() throws IOException, InterruptedException {
		goToUserMenu();
		By mySettingsLocator= By.xpath("//a[contains(text(),'My Settings')]");
		waitUntilvisibilityOfElementLocated(mySettingsLocator, "My Settings link");
		WebElement settingsLink= driver.findElement(mySettingsLocator);
		
		clickElement(settingsLink, " My Settings option");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		WebElement personalLink= driver.findElement(By.xpath("//span[@id='PersonalInfo_font']"));
		clickElement(personalLink, "Personal link");
		WebElement loginHistory = driver.findElement(By.xpath("//span[@id='LoginHistory_font']"));
		clickElement(loginHistory,"Login History");
		
		WebElement downloadCSVFile = driver.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]"));
		clickElement(downloadCSVFile, "Download CSV file");	
		WebElement displaynLayout=  driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']"));
		clickElement(displaynLayout, "Display & Layout");
		WebElement customizeMyTab = driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']"));
		clickElement(customizeMyTab, "Customize My Tabs");
		WebElement customApp=  driver.findElement(By.id("p4"));
		selectByTextData(customApp, "Salesforce Chatter", "Salesforce Chatter");
		
		WebElement availableTabs=  driver.findElement(By.id("duel_select_0"));
		
		selectByValueData(availableTabs, "report");
		
		WebElement addButton= driver.findElement(By.xpath("//img[contains(@class,'rightArrowIcon')]"));
		clickElement(addButton, "Add");
		

		WebElement emailLink = driver.findElement(By.xpath("//span[@id='EmailSetup_font']"));
		clickElement(emailLink, "Email Link");
		WebElement MyEmailSettings = driver.findElement(By.xpath("//a[@id='EmailSettings_font']"));
		clickElement(MyEmailSettings, " My Email Settings");
		WebElement emailName= driver.findElement(By.xpath("//input[@id='sender_name']"));
		String getEmailName= CommonUtilities.getPropertyValue(Constants.EMAIL_NAME);
		enterText(emailName, getEmailName,"Email Name");
		WebElement emailAddress= driver.findElement(By.xpath("//input[@id='sender_email']"));
		String getEmailAddress= CommonUtilities.getPropertyValue(Constants.EMAIL_ADDRESS);
		enterText(emailAddress, getEmailAddress, "Email Address");
		WebElement automaticBCC= driver.findElement(By.xpath("//input[@id='auto_bcc1']"));
		boolean checked = false;        
		checked = automaticBCC.isSelected();// will return True if button is selected.
		
		if(checked==false) {
			clickElement(automaticBCC, "Yes option in Bcc");
		}	
		WebElement saveButtonOnEmailPage= driver.findElement(By.xpath("//input[@title='Save']"));
		clickElement(saveButtonOnEmailPage, "Save Button");
		WebElement saveSettingsMsg= driver.findElement(By.xpath("//div[@class='messageText']"));
		String settingsSaveMsg= saveSettingsMsg.getText();
		String expectedSettingsSaveMsg= Constants.EMAIL_SETTINGS_SAVED_MSG;
		Assert.assertEquals(settingsSaveMsg, expectedSettingsSaveMsg);   
		WebElement calenderRemainder= driver.findElement(By.xpath("//span[@id='CalendarAndReminders_font']"));
		clickElement(calenderRemainder, "Calender & Remainders");
		WebElement activityRemainder= driver.findElement(By.xpath("//span[@id='Reminders_font']"));
		clickElement(activityRemainder, "Activity Remainder");
	
		WebElement openTestRemainder= driver.findElement(By.xpath("//input[@id='testbtn']"));
		clickElement(openTestRemainder, "Open a Test Remainder");
	//	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(5000);
		String baseWindowHandle=driver.getWindowHandle();
			
		Set<String> allWindowHandles=driver.getWindowHandles();
		int windowHandleSize= allWindowHandles.size();
		System.out.println(windowHandleSize);
		for(String handle:allWindowHandles) {
			if(!handle.equalsIgnoreCase(baseWindowHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			//	driver.findElement(By.id("dismiss_all")).click();
				break;
			}
			
		}	 
		
		driver.switchTo().window(baseWindowHandle);
	
	
				
	}
	
	@Test
	public static void testCase8() throws InterruptedException {
		goToUserMenu();
		By developerConsoleLocator= By.xpath("//a[contains(@class,'debugLogLink menuButtonMenuLink')]");
		waitUntilvisibilityOfElementLocated(developerConsoleLocator, "Developer Console link");
		WebElement developerConsoleLink= driver.findElement(developerConsoleLocator);
		
		clickElement(developerConsoleLink, " Developer Console option");
		String baseWindowHandle=driver.getWindowHandle();
			
		Set<String> allWindowHandles=driver.getWindowHandles();
		int windowHandleSize= allWindowHandles.size();
		System.out.println(windowHandleSize);
		for(String handle:allWindowHandles) {
			if(!handle.equalsIgnoreCase(baseWindowHandle)) {
				driver.switchTo().window(handle);
				Thread.sleep(5000);
				driver.close();
				break;
			}
		}
		driver.switchTo().window(baseWindowHandle);
	
	}
	@Test
	
	public static void testCase9() throws InterruptedException {
	
			goToUserMenu();
			By logout= By.xpath("//a[contains(text(),'Logout')]");
			
			waitUntilvisibilityOfElementLocated(logout, "Logout");
			WebElement logoutLink= driver.findElement(logout);
			clickElement(logoutLink, " Logout option");
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(5000);
			String title= driver.getTitle();
			
			
				Assert.assertEquals(title, "Login | Salesforce");
		
		
		
	}
}
