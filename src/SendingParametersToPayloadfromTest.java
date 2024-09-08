import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class SendingParametersToPayloadfromTest {

	
	@Test
	
	public void addBook()
	
	
	{
		// Add book
		RestAssured.baseURI = "http://216.10.245.166";
		String response =  given().log().all().header("Content-type","application/json").
		body(payload.Addbook("ABCD","9104645")).when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		
		String id = js.get("ID");
	
		System.out.println(id); 
		
	
	}
	
	
	
	
	
	
	
	
	
}
