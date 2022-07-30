package Utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public  class Utility {
	
	public static RequestSpecification requestSpecification() throws FileNotFoundException
	{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
	RequestSpecification reqspec= new RequestSpecBuilder().setBaseUri("https://reqres.in")
			.addQueryParam("Key", "qalink123").setContentType(ContentType.JSON)
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.build();
	return reqspec;
	}

	public static ResponseSpecification responeSpecification()
	{
	ResponseSpecification response= new ResponseSpecBuilder().expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();
	return response;
	}
	
	

}
