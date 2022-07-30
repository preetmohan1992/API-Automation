package TestPackage;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class RegisterUser 
{
	@Test
	public void registerUser()
	{
	given()
		
	
	.when()
		.get("https://reqres.in/api/register")
	.then()
	.statusCode(200);
	}
}
