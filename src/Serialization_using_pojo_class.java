import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class Serialization_using_pojo_class {

	public static void main(String[] args) {
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddPlace m = new AddPlace();
		m.setAccuracy(50);
		m.setAddress("29, side layout, cohen 09");
		m.setPhone_number("(+91) 983 893 3937");
		m.setWebsite("http://google.com");
		m.setName("Frontline house");
		m.setLanguage("French-IN");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		m.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
        l.setLng(33.427362);		
        m.setLocation(l);
        
		Response res =  given().queryParam("key","qaclick123").body(m).log().all().when().post("/maps/api/place/add/json").then()
		.assertThat().statusCode(200).extract().response();
		String responseString = res.asString();
		System.out.println(responseString);

	}

}
