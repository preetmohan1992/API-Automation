package TestPackage;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetUser 
{
	@Test
	public void getUserdata()
	{
		RestAssured.baseURI="https://reqres.in";
		Response res= given().log().all().queryParam("page","2")
		.when().get("/api/users")
		.then().log().all().assertThat().statusCode(200)
		.extract().response();
		
		JsonPath js= new JsonPath(res.asString());
	    int datasize=js.getInt("data.size()");
		System.out.println(datasize);
		
		for(int i=0; i<datasize;i++)
		{
			System.out.println(js.get("data["+i+"].id").toString());
			System.out.println(js.get("data["+i+"].email").toString());
			System.out.println(js.get("data["+i+"].first_name").toString());
			System.out.println("**************************");
		}
		
	}
}
