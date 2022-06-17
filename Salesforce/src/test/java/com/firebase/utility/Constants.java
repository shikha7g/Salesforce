package com.firebase.utility;

public class Constants {
	
	public static final  String USER_DIR = System.getProperty("user.dir");
	public static final  String APPLICATION_PROPERTIES_PATH =USER_DIR+"/src/main/resources/DataProperties.properties";
	public static final  String URL=null;
	public static final String SCRRENSHOT_PATH=USER_DIR+"/screenshots/";
	public static final String CSV_SCRRENSHOT="csvsceenshot.jpg";
	
	//login functionality constants
	public static final String USER_ID="userid";
	public static final String PASSWORD="password";
	public static final String FORGOT_PAGE_TITLE="Forgot Your Password | Salesforce";
	public static final String INVALID_USERNAME_PASSWORD="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
	public static final String LOGIN_ERROR_MESSAGE="Please enter your password.";
	public static final String LOGIN_USERNAME="pooja@tekarch.com";
	public static final String USERID_TESTCASE4B="userid_testcase4b";
	public static final String PASSWORD_TESTCASE4B="password_testcase4b";
	public static final String HOME_PAGE_TITLE="HomepageTitle";
	public static final String[] Drop_DOWN_OPTIONS= {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
	
	//My profile functionality
	
	public static final String EDIT_PROFILE_TITLE="User: Pooja chabra ~ Salesforce - Developer Edition";
	public static final String EDIT_PROFILE_LASTNAME="lastname";
	public static final String UPDATE_POST_TEXT="posttext";
	public static final String EDIT_PROFILE_FILEPATH="Editprofilefilepath";
	public static final String EDIT_PROFILE_PHOTOPATH="Editprofilephotopath";
	
	public static final String EMAIL_NAME="EmailName";
	public static final String EMAIL_ADDRESS="EmailAddress";
	public static final String EMAIL_SETTINGS_SAVED_MSG="Your settings have been successfully saved.";
	
	public static final String CONTACT_ERROR_MSG= "Error: You must enter a value";

	
	
	
	//CreateAccount functionality
	public static final String EXPECTED_USER_NAME="expectedName";
	public static final String ACCOUNT_NAME="accountname";
	public static final String VIEW_NAME="viewname";
	public static final String VIEW_UNIQUE_NAME="viewUniqueName";
	public static final String UPDATED_VIEW_NAME="updateViewName";
	public static final String ACCOUNT_PAGE_TITLE="accountPageTitle";
	
	public static final String OPPORTUNITY_NAME="opportunityName";
	public static final String OPPORTUNITY_ACCOUNT_NAME="opportunityAccountName";
	
	//Reports
	public static final String GENERATE_REPORT_PATH=USER_DIR+"/ExtentReports/report.html";
	
	}
