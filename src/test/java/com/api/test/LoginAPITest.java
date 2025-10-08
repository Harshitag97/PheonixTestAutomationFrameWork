package com.api.test;

import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import api.test.pojo.UserCredentialLogin;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	
	public void loginAPITest() {
		
		
		UserCredentialLogin userCredentialLogin = new UserCredentialLogin("iamfd", "password");
		
		given()
		.baseUri("http://64.227.160.186:9000/v1")
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.body(userCredentialLogin)
		.log().uri()
		.log().method()
		.log().body()
		.log().headers()
		.when()
		.post("/login")
		.then()
		.statusCode(200)
		.time(lessThan(1000L))
		.and()
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		.log().all();
		
	
		
		
		
	}

}
