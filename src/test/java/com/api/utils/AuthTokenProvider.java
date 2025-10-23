package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import com.api.constants.Role;

import static com.api.constants.Role.*;


import api.test.pojo.UserCredentialLogin;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class AuthTokenProvider {
	private AuthTokenProvider() {}
	
	public  static  String getToken(Role role) throws IOException {
		
		UserCredentialLogin userCredentialLogin = null;
		if(role == FD) {
			userCredentialLogin = new UserCredentialLogin("iamfd", "password");
		}
		
		else if(role == SUP) {
			userCredentialLogin = new UserCredentialLogin("iamsup", "password");
		}
		if(role == ENG) {
			userCredentialLogin = new UserCredentialLogin("iameng", "password");
		}
		if(role == QC) {
			userCredentialLogin = new UserCredentialLogin("iamqc", "password");
		}
		
		
	String loginToken = 	given()
		.baseUri(getProperty("BASE_URL"))
		.contentType(ContentType.JSON)
		
		.accept(ContentType.JSON)
		.body(userCredentialLogin)
		.when()
		.post("/login")
		.then()
		.statusCode(200)
		.time(lessThan(1000L))
		.body("message", equalTo("Success"))
		.log().ifValidationFails()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		.extract().body().jsonPath().getString("data.token");
	
	return loginToken;
	 
	  
		
	}
	

}
