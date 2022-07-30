package stepDefinitions;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import PayloadFile.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

	public class Add_Place
	{ 
		
		static String placeId;
	
		@Test(priority=1)
		public void addPlace()
		{
			String response=given().spec(requestSpecification)
			.body(PayLoad.addPlace())
			.when().post("/maps/api/place/add/json")
			.then().log().all()
			.assertThat().statusCode(200).body("scope", equalTo("APP"))
			.header("server", "Apache/2.4.18 (Ubuntu)")
			.extract().response().asString();
		//System.out.println(response);
		//for parsing json , it accept the string and convert it in to json
		JsonPath js= new JsonPath(response); 
		placeId= js.getString("place_id");
		System.out.println("The place id is:"+placeId);
		
	}
		
		@Test(priority=2)
		public void putPlace()
		{
		RestAssured.baseURI="https://rahulshettyacademy.com";
			given().log().all().queryParam("key","qaclick123")
			.header("Content-Type", "application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+placeId+"\",\r\n"
					+ "\"address\":\"new Tulsi Nagar Etawah\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ "")
					
			.when().put("/maps/api/place/update/json")
			.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		}
		
		@Test(priority=3)
		
		public void getPlace()
		{
			RestAssured.baseURI="https://rahulshettyacademy.com";
		String getResponse=	given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
			.when()
				.get("/maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200)
				.header("Content-Type", "application/json;charset=UTF-8")
				.extract().response().asString();
			JsonPath jsget= new JsonPath(getResponse);
			String responsemsg= jsget.getString("msg");
			System.out.println(responsemsg);
			String address= jsget.getString("address");
			Assert.assertEquals(address, "new Tulsi Nagar Etawah");
			
			
		}
		
}
