package TestPackage;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertThat;

public class CreateUser 
{
	static RequestSpecification request;
	static Response response;
	
	@Test
	public void createUser()
	{
		//baseURL="https://reqres.in/api/users";
		request= RestAssured.given();
		request.header("Content-Type","Json");
		request.baseUri("baseURL");
		response =post("https://reqres.in/api/users");
		
		
		Map<String, String > map= new HashMap();
		map.put("first_name", "naman");
		map.put("last_name", "thakur");
		// to get the body in the json format
		JSONObject js= new JSONObject(map);
		System.out.println(js);
		
		RestAssured.baseURI="https://reqres.in";
		given()
		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(js)
		
		.when()
			.post("/api/users")
		.then()
			.assertThat()
			.statusCode(201);
			
		
	}
}
