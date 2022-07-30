package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import Utilities.APIResources;
import Utilities.Utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetUserList 
{
	
	// I made the use of Spec builder to optimize the code which is common code for every class
	RequestSpecification reqs;
	Response response;
	APIResources resourceAPI;
		@Given("I have a end point URI")
		public void i_have_a_end_point_uri() throws FileNotFoundException {
			reqs= given().spec(Utility.requestSpecification());
		}

		@When("I invoke the getuser end point {string} and I verify the status")
		public void i_invoke_the_getuser_end_point_and_i_verify_the_status(String resource) {
			resourceAPI = APIResources.valueOf(resource);
			System.out.println("called api is :"+resource);
			//Here I made the  concept of making dynamic end point by creating APIResources at one place
			response=reqs.when().get(resourceAPI.getResource())
					.then()
					.spec(Utility.responeSpecification())
					.extract().response();
		}

		@Then("I verify the response body")
		public void i_verify_the_response_body() {
		    
			JsonPath js= new JsonPath(response.asString());
		    int datasize=js.getInt("data.size()");
			System.out.println("Size of the data"+datasize);
			
			for(int i=0; i<datasize;i++)
			{
				System.out.println(js.get("data["+i+"].id").toString());
				System.out.println(js.get("data["+i+"].email").toString());
				System.out.println(js.get("data["+i+"].first_name").toString());
				System.out.println("**************************");
			}
		
		
	}
}


