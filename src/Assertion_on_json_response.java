import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import files.payload;



public class Assertion_on_json_response {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		//Validate if  Add place API is working as expected
		
				//given -all input details,
				//when - Submit the API, -resource,http method
				//then - Validate the response
				
				RestAssured.baseURI= "https://rahulshettyacademy.com";
				given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json")
				.then().log().all().assertThat().body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)");

	}

}
