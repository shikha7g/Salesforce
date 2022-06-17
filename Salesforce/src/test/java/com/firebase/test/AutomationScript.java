package com.firebase.test;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.firebase.base.BaseTest;
import com.firebase.utility.CommonUtilities;
import com.firebase.utility.Constants;

//@Listeners(com.firebase.utility.GenerateReportListener.class)
public class AutomationScript extends BaseTest{

	@Test
	public static void testCase1() {
		
		String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
		WebElement userNameLocator=driver.findElement(By.id("username"));
		System.out.println();
		
		enterText(userNameLocator, user, "Username ");
		WebElement passLocator=driver.findElement(By.id("password"));
		clearElement(passLocator, " Password");
		clickLoginButton();
		
		String expectedMsg=Constants.LOGIN_ERROR_MESSAGE;
		WebElement errorElement=driver.findElement(By.id("error"));
		waitUntilVisible(errorElement, user);
		String actualMsg= errorElement.getText();
		Assert.assertEquals(actualMsg, expectedMsg);
	
	}
	

		
		@Test
		public static void testCase2() {
		
		String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
		String password= CommonUtilities.getPropertyValue(Constants.PASSWORD);
		login(user,password);
		clickLoginButton();
	
	}
		
	@Test
	public static void testCase3() throws InterruptedException {
		
			String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
			String password= CommonUtilities.getPropertyValue(Constants.PASSWORD);
			login(user, password);
			
			WebElement rememberMeChkBox= driver.findElement(By.xpath("//input[@id='rememberUn']"));
			if(rememberMeChkBox.isSelected()== false){
				clickElement(rememberMeChkBox, "Check box");
			}
			clickLoginButton();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			By userMenu = By.xpath("//div[@id='userNavButton']");
			waitUntilvisibilityOfElementLocated(userMenu, "User menu displayed");
			WebElement userMenuElement= driver.findElement(userMenu);
			mouseOver(userMenuElement, "Mouse on UserMenu");
			clickElement(userMenuElement, "User Menu");
			
			By logoutButtonLocator= By.xpath("//a[contains(text(),'Logout')]");
			waitUntilvisibilityOfElementLocated(logoutButtonLocator, "logout button");
			WebElement logoutButton= driver.findElement(logoutButtonLocator);
			
			clickElement(logoutButton, "logout link");
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			Thread.sleep(5000);
			WebElement userNameField= driver.findElement(By.id("idcard-identity"));
			waitUntilVisible(userNameField,"User Name" );
			
			String expectedUserName=Constants.LOGIN_USERNAME;
			String actualUserName=userNameField.getText();
		
			Assert.assertEquals(actualUserName, expectedUserName);
		
		
	}
	
	@Test
	public static void testCase4a()  {
		
		String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
		WebElement forgotPassword= driver.findElement(By.id("forgot_password_link"));
		clickElement(forgotPassword, "Forgot Password");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		String actualTitle=driver.getTitle();
		String expectedTitle= Constants.FORGOT_PAGE_TITLE;
		try {
			Assert.assertEquals(actualTitle, expectedTitle);
		}catch(Exception e) {
			report.logTestFailedWithException(e);
		}
		By userNameLocator= By.id("un");
		waitUntilvisibilityOfElementLocated(userNameLocator,"Forgot Page");
		WebElement userNameElement = driver.findElement(userNameLocator);
		enterText(userNameElement, user, "Enter username");
		WebElement continueButton= driver.findElement(By.id("continue"));
		clickElement(continueButton, "Continue Button");
		
	
	}
	
	@Test
	public static void testCase4b() {
		
			WebElement userNameLocator=driver.findElement(By.id("username"));
			String userName= CommonUtilities.getPropertyValue(Constants.USERID_TESTCASE4B);
			String password= CommonUtilities.getPropertyValue(Constants.PASSWORD_TESTCASE4B);
			enterText(userNameLocator, userName, "Username entered");
			WebElement passLocator=driver.findElement(By.id("password"));
			enterText(passLocator,password, "Entered Password");
			clickLoginButton();
			
			String expectedMsg=Constants.INVALID_USERNAME_PASSWORD;
			
			WebElement errorElement=driver.findElement(By.id("error"));
			waitUntilVisible(errorElement, "Error message");
			String actualMsg= errorElement.getText();
			
			
				Assert.assertEquals(actualMsg, expectedMsg);
		
		
	}
}
