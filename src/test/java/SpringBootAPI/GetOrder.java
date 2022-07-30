package SpringBootAPI;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class GetOrder 
{
	@Test
	public void getToken()
	{
		RestAssured.baseURI="http://localhost:8088";
		String resp= given().log().all().body("{\r\n"
				+ "    \"username\": \"test\",\r\n"
				+ "    \"password\": \"1234\"\r\n"
				+ "}").
		when().post("/auth/login").
		then().log().all().statusCode(200).extract().asString();
		
		JsonPath js= new JsonPath(resp);
		String token= js.get("token");
		System.out.println(token);
		
	}
	

}
