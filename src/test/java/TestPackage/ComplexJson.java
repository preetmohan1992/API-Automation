package TestPackage;
import org.testng.Assert;

import PayloadFile.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJson 

{
	// This is helpful when you don't have real one API make test case and update the response when API is ready
	//	1. Print No of courses returned by API
	//	2.Print Purchase Amount
	//	3. Print Title of the first course
	//	4. Print All course titles and their respective Prices
	//	5. Print no of copies sold by RPA Course
	//	6. Verify if Sum of all Course prices matches with Purchase Amount
 		
//	1. Print No of courses returned by API
		public static void  main(String[]arg) 
		{
		JsonPath js = new JsonPath(PayLoad.courcePrice());
			int coursecount= js.getInt("courses.size()");
			System.out.println("No of courses returned by API:"+coursecount);
		
	
		
		//	2.Print Purchase Amount
		int totalAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount:" + totalAmount);
		
		
		// 3. Print Title of the first course
	
		String title= js.getString("courses.title[0]");
		System.out.println("Title of first course:"+ title);
		
		//4. Print All course titles and their respective Prices
		
		for(int i=0; i<coursecount;i++)
		{
			String coursetile= js.getString("courses["+i+"].title");
			int courseprice= js.getInt("courses["+i+"].price");
			
			System.out.println("Course Title is:"+coursetile);
			System.out.println("Course Price is:" +courseprice);
		}
		
		//5. Print no of copies sold by RPA Course
		for(int i=0; i<coursecount;i++)
		{
			
			String coursetile= js.getString("courses["+i+"].title");
		
			if(coursetile.equalsIgnoreCase("cypress"))
			{
				int soldcopies= js.getInt("courses["+i+"].copies");
				System.out.println("No of copies sold bye Cypress:"+soldcopies);
			
			break;
			}
		}
		
//		6. Verify if Sum of all Course prices matches with Purchase Amount
		int sum=0;
		for (int i=0; i<coursecount; i++)
		{
			int soldcopies= js.getInt("courses["+i+"].copies");
			int courseprice= js.getInt("courses["+i+"].price");
			sum=sum+courseprice*soldcopies;	
		}
		System.out.println("Total Amont from All sold copies is:"+sum);
		
		Assert.assertEquals(sum, totalAmount);
		
		  if(totalAmount==sum) {
		  System.out.println("we have match the correct amount"); } else {
		  System.out.println("We have not matched the correct Amount"); }
		 
	}		
	
	
}
