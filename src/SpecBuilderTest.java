import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class SpecBuilderTest {

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
        
        RequestSpecification reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
        .setContentType(ContentType.JSON).build();
        
        ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        
		RequestSpecification res =  given().spec(reqspec).body(m);
				
		Response response = res.when().post("/maps/api/place/add/json").then()
		.spec(resspec).extract().response();
		
		String responseString = response.asString();
		System.out.println(responseString);

	}

}
