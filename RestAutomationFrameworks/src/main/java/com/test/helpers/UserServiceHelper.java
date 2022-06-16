package com.test.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.test.models.LoginObjectPojo;
import com.test.models.LoginResPojo;
import com.test.models.UserPojo;
import com.test.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.test.models.DeletePojo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.constants.Endpoints;
import com.test.models.AddUserPojo;
import com.test.models.UpdateUserPojo;
import com.test.models.UserPojo;
import com.test.models.LoginObjectPojo;

public class UserServiceHelper {

	public static Response res;
	public static String token;
	public static RequestSpecification requestSpecification;
	
	public static String getBaseUri() {
		String baseUri=Utils.getConfigProperty("baseuri");
		return baseUri;
	}
	
	public static List<Map<String,String>> LoginToApplication() throws JsonMappingException, JsonProcessingException {
		String username= Utils.getConfigProperty("username");
		String password= Utils.getConfigProperty("password");
		
		LoginObjectPojo login= new LoginObjectPojo();
		login.setUsername(username);
		login.setPassword(password);
		res=RestAssured.given().contentType(ContentType.JSON).
				body(login).
				when()
				.post(Endpoints.LOGIN);
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("LoginSchema.json"));
		ObjectMapper mapper= new ObjectMapper();
		List<Map<String,String>> tokenList= mapper.readValue(res.asString(),new TypeReference<List<Map<String,String>>>(){});
		return tokenList;
	}
	
	public static String getToken() throws IOException {
		List<Map<String,String>> getAllTokens =LoginToApplication();
		token= getAllTokens.get(0).get("token");
	//	res.prettyPrint();
	//	token= res.jsonPath().getString("[0].token");
	//	LoginResPojo[] listOfToken = res.as(LoginResPojo[].class);
	//	token= listOfToken[0].getToken();
		return token;
		
	}
	
	public static List<UserPojo> getUserData() throws IOException {
		if(token==null) {
			getToken();
		}
		Header header= new Header("token",token);
		res=RestAssured.given().header(header).
				when()
				.get(Endpoints.GET_DATA);
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GetUsersSchema.json"));
		
	
		ObjectMapper mapper = new ObjectMapper();
		List<UserPojo> allUsers= mapper.readValue(res.asString(),new TypeReference<List<UserPojo>>() {});
	//	UserPojo[] userPojo= res.as(UserPojo[].class);
	//	List<UserPojo> allUsers= new ArrayList<UserPojo>(Arrays.asList(userPojo));
		return allUsers;
	}
	
	
	public static void setupRequestSpecification() throws IOException
	{
		if(token==null) {
			getToken();
		}
		Header header= new Header("token",token);
		requestSpecification = RestAssured.given().header(header).contentType(ContentType.JSON);
		
		
	}
	public static Response addUserData() throws IOException {
//		if(token==null) {
	//		getToken();
	//	}
	//	Header header= new Header("token",token);
		setupRequestSpecification();
		AddUserPojo user= ReusableMethods.getUserDataToAdd();
	
//		Response res=RestAssured.given().header(header).contentType(ContentType.JSON)
	//	Response res = RestAssured.given(requestSpecification)  differnet ways of calling reqest specification
		Response res = RestAssured.given().spec(requestSpecification)
					.body(user)  
					.when().post(Endpoints.ADD_DATA);
		return res;
	}
	
	

	
	
	
	
	public Response updateUserData(String accNum, String userId, String id) throws IOException {
	
		Header header= new Header("token",token);
		UpdateUserPojo updateUser= new UpdateUserPojo();
		updateUser.setAccountno(accNum);
		updateUser.setDepartmentno("8");
		updateUser.setSalary("9989");
		updateUser.setPincode("234562");
		updateUser.setUserid(userId);
		updateUser.setId(id);
	//	setupRequestSpecification();
		RequestSpecification spec =RestAssured.given().header(header).contentType(ContentType.JSON)
				.body(updateUser);
		
		res=spec.when().put(Endpoints.UPDATE_DATA);
		return res;
	}
	
	public Response deleteUserData(String userId, String id) throws IOException {
		if(token==null) {
			getToken();
		}
	//	Header header1= new Header("token",token);
		DeletePojo deleteUser= new DeletePojo();
		deleteUser.setUserid(userId);
		deleteUser.setId(id);
		System.out.println("value of userid:::"+userId);
		System.out.println("value of Id::"+id);
		System.out.println("value of token::"+token);
		RequestSpecBuilder specBuilder= new RequestSpecBuilder();
		specBuilder.setContentType(ContentType.JSON);
		specBuilder.addHeader("token", token);
		RequestSpecification req = specBuilder.build();
	//	res=RestAssured.given().header(header1).contentType(ContentType.JSON)
		res=RestAssured.given(req)
					.body(deleteUser)
					.when().delete("/deleteData");
		return res;
	}
}
