package com.api.test;

import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.ConfigManager.*;

import api.test.pojo.UserCredentialLogin;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	
	public void loginAPITest() throws IOException {
		
		
		UserCredentialLogin userCredentialLogin = new UserCredentialLogin("iamfd", "password");
		
		given()
		.baseUri(getProperty("BASE_URL"))
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
