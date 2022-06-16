package com.test.helpers;

import com.test.models.AddUserPojo;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReusableMethods {

	static AddUserPojo addUser;
	
	public static AddUserPojo getUserDataToAdd() {
		
		addUser= new AddUserPojo();
		
		addUser.setAccountno("TA-22266U9");
		addUser.setDepartmentno("8");
		addUser.setSalary("76543");
		addUser.setPincode("234567");
		
		return addUser;
	}
	
	public static void verifyStatusCode(Response res, int statusCode) {
		res.then().statusCode(statusCode);
	}
	
	public static String getJsonPathData(Response res, String path) {
				
		return res.jsonPath().get(path);
	}
	
	public static void verifyResponseTimeIn(Response res, Long unit) {
		 res.then().time(Matchers.lessThan(unit));
		}
	
	public int getStatusCode(Response res) {
		return res.getStatusCode();
		}
	
	public static void verifyContentType(Response res) {
		res.then().contentType(ContentType.JSON);
	}
	
	public static void configureLog(Response res) {
		res.then().log().all();
	}
}
