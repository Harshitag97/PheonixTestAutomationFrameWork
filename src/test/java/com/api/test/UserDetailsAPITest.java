
package com.api.test;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.constants.Role.*;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		
	Header authHeader = new Header("Authorization",getToken(FD));
	
	    given()
	    .baseUri(ConfigManager.getProperty("BASE_URL"))
	    .and()
	    .header(authHeader)
	    .and()
	    .contentType(ContentType.JSON)
	    .and()
	    .accept(ContentType.JSON)
	    .and()
	    .when()
	    .get("userdetails")
	    .then()
	    .statusCode(200)
	    .time(Matchers.lessThan(2000L))
	    .log().all()
	    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserdetailsResponseSchema.json"));

	}

}
