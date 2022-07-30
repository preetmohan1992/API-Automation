package TestPackage;
import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetSingleUser 
{
	@Test()
	public void userList()
	{
		given()
		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.when()
			.post("https://reqres.in/api/users/2")
		.then()
			.assertThat()
			.statusCode(201);
		
			
	}
	
	
	@Test(priority =1)
	public void getUser()
	{
		Response response= get("https://reqres.in/api/users/2");
		
		System.out.println(response.getStatusCode());	
		System.out.println(response.getBody().asString());
		System.out.println(response.getTime());
		System.out.println(response.getHeader("content-type"));
		
		int statuscode= response.statusCode();
		Assert.assertEquals(statuscode, 200);
		String body =response.getBody().asString();
		Assert.assertEquals(body.contains("email"), true);
		Assert.assertEquals(body.contains("first_name"), true);
		Assert.assertEquals(body.contains("last_name"), true);
		Assert.assertEquals(body.contains("avatar"), true);
		Assert.assertEquals(body.contains("email"), true);
		//Assert.assertEquals(body.contains("michael.lawson@reqres.in"), true);
		
		
		JsonPath js= response.jsonPath();
		String email_value= js.get("data.email");
		String first_name=js.get("data.first_name");
		String last_name=js.get("data.last_name");
		String avatar=js.get("data.avatar");
		Assert.assertEquals(email_value.equals("janet.weaver@reqres.in"), true, "response have correct emailid");
		Assert.assertEquals(first_name.equals("Janet"), true, "response have correct name");
		Assert.assertEquals(last_name.equals("Weaver"), true, "response have correct lname");
		Assert.assertEquals(avatar.equals("https://reqres.in/img/faces/2-image.jpg"), true, "response have correct img");
	}
	
}
