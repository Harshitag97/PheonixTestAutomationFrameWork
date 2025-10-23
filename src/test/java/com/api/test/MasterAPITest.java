package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.ConfigManager;

import static com.api.utils.AuthTokenProvider.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() throws IOException {
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URL"))
		.and()
		.header("Authorization",getToken(Role.FD))
		.and()
		.contentType("")
		.log().all()
		.when()
		.post("master")
		.then()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.time(lessThan(1000L))
		.body("data", notNullValue())
		.log().all();
		
		
	}
	

}
