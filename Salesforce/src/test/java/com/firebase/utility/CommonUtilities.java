package com.firebase.utility;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonUtilities {

	public static String getPropertyValue(String key)  {
		String path=Constants.APPLICATION_PROPERTIES_PATH;
		Properties ob= new Properties();
		FileInputStream fis=null;
		String value=null;
		
		try {
			fis = new FileInputStream(new File(path));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			try {
				ob.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 value=ob.getProperty(key);
		}finally {
				
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
		
	}
	
	public static void setClipboardData(String path) {
		// String selection is a class used to copy data to clipboard
		StringSelection stringSelection = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	public static String takescreenshot(WebDriver driver,String fileName ) {
		//Convert web driver object to TakeScreenshot
		GenerateReports report=GenerateReports.getInstance();
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		report.logTestInfo("screeen shot captured");
		String filePath=Constants.SCRRENSHOT_PATH+fileName;
		File DestFile=new File(filePath);
		//Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;

	}
	
}
