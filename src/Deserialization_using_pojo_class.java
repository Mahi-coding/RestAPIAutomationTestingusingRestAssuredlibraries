import java.util.*;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO.Api;
import POJO.GetCourse;
import POJO.Mobile;
import POJO.WebAutomation;
import files.ReUsableMethods;
import io.restassured.path.json.JsonPath;

public class Deserialization_using_pojo_class {

	public static void main(String[] args) {
		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor","Rest API"};
		
				//RestAssured.baseURI = "https://rahulshettyacademy.com";
				String response =given()
				.formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParams("grant_type","client_credentials")
				.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").then().extract().response().asString();

				System.out.println(response);
				JsonPath js = ReUsableMethods.rawToJson(response);
				String accessToken = js.getString("access_token");
				System.out.println(accessToken);
				
				GetCourse gc = given()
				.queryParams("access_token",accessToken)
				.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().extract().response().as(GetCourse.class);
				System.out.println(gc.getLinkedIn());
				System.out.println(gc.getInstructor());
				System.out.println(gc.getCourses().getWebAutomation().get(0).getCourseTitle());
				System.out.println(gc.getCourses().getMobile().get(0).getPrice());
				
				/*Iterate to scan for courses titles because list can be updated with
				 more courses */
				
				List<Api> apiCourses = gc.getCourses().getApi();
				for(int i=0;i<apiCourses.size();i++)
				{
					if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
						
						System.out.println("The price for "+apiCourses.get(i).getCourseTitle()+" is "+apiCourses.get(i).getPrice());
						
					}
					
				}
				
				//Print all the courseTitles for webAutomation course
				
				System.out.println("Printing all the courseTitles for webAutomation course");
				List<WebAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
				for(int i=0;i<webAutomationCourses.size();i++)
					
				{
					
					System.out.println(webAutomationCourses.get(i).getCourseTitle());
					
				}
				
				
//Print all the courseTitles & prices for Mobile course
				
				System.out.println("Printing all the courseTitles & Prices for Mobile course");
				List<Mobile> MobileCourses = gc.getCourses().getMobile();
				for(int i=0;i<MobileCourses.size();i++)
					
				{
					
					System.out.println(MobileCourses.get(i).getCourseTitle());
					System.out.println(MobileCourses.get(i).getPrice());
					
				}
	// Comparing expected Automation courses versus Actual
				
				ArrayList<String> actualList= new ArrayList<String>();
				
                for(int j=0;j<webAutomationCourses.size();j++)
					
				{
					actualList.add(webAutomationCourses.get(j).getCourseTitle());
					
					
				}
                
                //adding one item to actualList in run time
                actualList.add("Rest API");
				
				 List<String> expectedList= Arrays.asList(courseTitles);
	
	            Assert.assertTrue(actualList.equals(expectedList));
	            System.out.println("actual list is "+ actualList);
	            System.out.println("Expected list is "+ expectedList);
	}

}
