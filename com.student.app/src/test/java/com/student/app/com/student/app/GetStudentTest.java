package com.student.app.com.student.app;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class GetStudentTest {
	
	@BeforeTest
	public void init(){
		
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8080;
		RestAssured.basePath="/student";
	}
	@Test
	public void getAllStudentInfo(){
		/**
		 * given()
		 * set cookies,add auth,adding paremeter,setting header info
		 * When()
		 * GET,POST,PUT,Delete
		 * then()
		 * validate the status code,extract response,extract header,cookies,extract the response body
		 * 
		 * 
		*/
		
		Response response= given()
		.when()
		.get("/list");
		
		System.out.println("response is : "+response.body().prettyPrint());
		
		//validate the response code
		given()
		.when()
		.get("/list")
		.then()
		.statusCode(400);
		
		
		
		
		
	}

}
