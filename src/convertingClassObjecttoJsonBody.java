import static io.restassured.RestAssured.given;

import POJO.AddEmployee;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class convertingClassObjecttoJsonBody {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://www.google.com";
		AddEmployee e = new AddEmployee();
		e.setName("morpheus");
		e.setJob("leader");
		
		Response res =  given().body(e).log().all().when().post("/test").then()
				.assertThat().statusCode(200).extract().response();
		
				String responseString = res.asString();
				System.out.println(responseString);

	}

	/*
	 * {
	 * "name":"morpheus",
	 * "job":"leader"
	 * }
	 * 
	 * www.google.com/test?a=1
	 * post method
	 * 
	 */
	
}
