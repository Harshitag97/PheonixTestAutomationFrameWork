 package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CountAPITest {
	@Test
	
	public void verifyCountAPIResponse() throws IOException {
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URL"))
		.and()
		.contentType(ContentType.JSON)
		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.log().uri()
		.log().method()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.time(lessThan(1000L))
		.body("data", notNullValue())
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPISchema.json"));
		
	}
	
	@Test
	public void countAPI_MissingAuthToken() throws IOException {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URL"))
		.and()
		.contentType(ContentType.JSON)
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
		
	}

}
