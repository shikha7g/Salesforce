package com.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.test.constants.SourcePath;

public class Utils {

	public static String getConfigProperty(String key)  {
		String path=SourcePath.CONFIG_PROPERTIES_PATH;
		System.out.println("the path of properties file is::"+path);
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
			System.out.println("value of url:::"+key);
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
	
}
