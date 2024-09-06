import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumofCourses()
	
	{
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		int amount = 0;
		for(int i=0;i<count;i++)
		{
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			  
			   amount = amount +(price*copies);
			  
		}
		
			  System.out.println("Actual amount is "+amount);
			  
			int totalpurchaseAmount = js.getInt("dashboard.purchaseAmount");
			System.out.println("Expected amount is "+totalpurchaseAmount);
			Assert.assertEquals(amount,totalpurchaseAmount);
				
			
		}
		
	}


	

