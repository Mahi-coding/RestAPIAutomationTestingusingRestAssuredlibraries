
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;



public class TestNG_Assertions {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		//Validate if  Add place API is working as expected
		
				//given -all input details,
				//when - Submit the API, -resource,http method
				//then - Validate the response
				
				RestAssured.baseURI= "https://rahulshettyacademy.com";
				String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json")
				.then().assertThat().body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
				
				
System.out.print(response);
JsonPath js=new JsonPath(response); //for parsing json
String placeid = js.getString("place_id");
System.out.println("\n" + placeid);
//update place
String newAddress = "Summer Walk,  Africa";
given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
.body("{\r\n"
		+ "\"place_id\":\""+placeid+"\",\r\n"
		+ "\"address\":\""+newAddress+"\",\r\n"
		+ "\"key\":\"qaclick123\"\r\n"
		+ "}").
when().put("maps/api/place/update/json")
        .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

//Get Place
String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
.queryParam("place_id",placeid )
.when().get("maps/api/place/get/json")
.then().assertThat().log().all().statusCode(200).extract().response().asString();

JsonPath js1= ReUsableMethods.rawToJson(getPlaceResponse);
String updatedAddress = js1.getString("address");
System.out.println("\n" + updatedAddress);
Assert.assertEquals(newAddress, updatedAddress);
	}

}
