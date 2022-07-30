package TestPackage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class GetListUser 
 
{	static Properties prop;
	static FileInputStream ip;
	RequestSpecification request;
	@BeforeMethod
	public void setup() throws IOException
	{
		prop = new Properties();
		ip= new FileInputStream("C:\\Users\\sony\\eclipse-workspace\\PlanAPIAutomation\\src\\test\\java\\Config\\Config.properties");
		prop.load(ip);
		request= RestAssured.given();
		request.header("Content-Type", "application/json");
		//request.header("Authorization", prop.getProperty("token"));
		
	}
	
	@Test(priority=2)
	public void getListUser()
	{	
		//given
		
		
	Response response= get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());	
		System.out.println(response.getBody().asString());
		System.out.println(response.getTime());
		System.out.println(response.getHeader("content-type"));
		
		int statuscode= response.statusCode();
		Assert.assertEquals(statuscode, 200);
		String body =response.getBody().asString();
		
		JsonPath js= response.jsonPath();
		String email_value= js.get("data[2].email");
		String first_name=js.get("x.data[2].first_name");
		String last_name=js.get("x.data[2].last_name");
		String avatar=js.get("x.data[2].avatar");
		Assert.assertEquals(email_value.equals("tobias.funke@reqres.in"), true, "response have correct emailid");
		Assert.assertEquals(first_name.equals("Tobias"), true, "response have correct name");
		Assert.assertEquals(last_name.equals("Funke"), true, "response have correct lname");
		Assert.assertEquals(avatar.equals("https://reqres.in/img/faces/9-image.jpg"), true, "response have correct img");
	}
		
		
	
	
	@Test(priority=1)
	public void getUser()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		.then()
			.statusCode(200)
			.assertThat()
			.header("Content-Type", "application/json; charset=utf-8")
			.body("total_pages", equalTo(2))
			.and() .body("x.data[0].email", equalTo("michael.lawson@reqres.in"));
			
			
	}	
		
	@Test
	
		public void verifyResponse()
		{
		}
					
}



