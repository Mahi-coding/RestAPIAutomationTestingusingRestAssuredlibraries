//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import files.ReUsableMethods;

public class OAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		
		String response2 = given()
		.queryParams("access_token",accessToken)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().extract().response().asString();
		System.out.println(response2);
		
	}

}
