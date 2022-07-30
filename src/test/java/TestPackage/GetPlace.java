package TestPackage;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class GetPlace 
{
	@Test
	public void getPlace()
	{
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick").queryParam("place_id", "6171d733c9996c24ff5127da1d866179")
		.when()
			.get("/maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200);
	}
}
