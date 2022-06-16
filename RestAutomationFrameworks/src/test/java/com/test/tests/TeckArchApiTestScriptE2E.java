package com.test.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;
import com.test.models.UserPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;


public class TeckArchApiTestScriptE2E extends UserServiceHelper{
	
	public static String accNum;
	public static String userId;
	public static String id;
	
	@BeforeClass
	public static void baseUri() {
		RestAssured.baseURI= getBaseUri();
	}
	
	@Test(priority=1)
	public static void TC_001_validLogin() throws IOException {
		String token= getToken();
		System.out.println("value of token::"+token);
	}
	
	@Test(priority=2)
	public static void TC_002_addUserData() throws IOException {
		Response res= addUserData();
		ReusableMethods.verifyStatusCode(res, 201);
		ReusableMethods.verifyContentType(res);
		ReusableMethods.verifyResponseTimeIn(res, 9000L);
		String status= ReusableMethods.getJsonPathData(res, "status");
		Assert.assertEquals(status,"success");
		
		
	}
	
	@Test(priority=3,enabled=true)
	public static void TC_003_getUserData() throws IOException {
		List<UserPojo> listOfUsers= getUserData();
		System.out.println("first account number::"+listOfUsers.get(0).getAccountno());
		
		ReusableMethods.verifyStatusCode(res, 200);
		ReusableMethods.verifyContentType(res);
		ReusableMethods.verifyResponseTimeIn(res, 15000L);
	//	res.then().time(Matchers.lessThan(11000L));
		res.then().log().all();
	//	ArrayList<String> object= res.jsonPath().get("findAll(it->it.accountno==\"99999999\")");
	//	System.out.println(" object content is :::"+object.get(0));
	//	String accNum=res.jsonPath().get("[0].accountno");
	//	UserPojo[] getUsers= res.as(UserPojo[].class);
	//	 accNum=getUsers[0].getAccountno();
		 
		accNum= listOfUsers.get(0).getAccountno();
	//	userId= res.jsonPath().param("addAccountno",accNum).get("find{it->it.accountno==addAccountno}.userid");
		System.out.println("value of userid:::"+userId);
		 userId=listOfUsers.get(0).getUserid();
		 System.out.println("val::::"+userId);
		 id=listOfUsers.get(0).getId();
		
	
		System.out.println("total number of records::"+listOfUsers.size());
		System.out.println("first set of account num::"+accNum);
	//	System.out.println("account no :::"+accNum);
		System.out.println("total number of records:::"+res.jsonPath().get("$.size()"));
	}
	
	@Test(priority=4, enabled=false)
	public void TC_004_updateUserData() throws IOException {
		Response response= updateUserData(accNum,userId,id);
		ReusableMethods.verifyStatusCode(response, 200);
		System.out.println("in update method");
		String status= ReusableMethods.getJsonPathData(response, "status");
		Assert.assertEquals(status,"success");
	}
	
	@Test(priority=4, enabled=true)
	public void TC_005_deleteUserData() throws IOException {
		Response response= deleteUserData(userId,id);
		ReusableMethods.verifyStatusCode(response, 200);
		System.out.println("in delete method");
		String status= ReusableMethods.getJsonPathData(response, "status");
		Assert.assertEquals(status,"success");
	}

	
}
