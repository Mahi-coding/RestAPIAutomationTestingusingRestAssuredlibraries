import static io.restassured.RestAssured.given;

import java.io.File;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

public class BugCreationInJira {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI= "https://mahi-coding.atlassian.net/";
		String createIssueResponse = given().log().all().header("Content-Type","application/json")
				.header("Authorization","Basic bWFoZXNoLndhZGh3YTM3MUBnbWFpbC5jb206QVRBVFQzeEZmR0Ywem5EUzNhelpJZERWTDVWa0RMWmY2WEp0aThaTUhUam1IbFdEVDk5c2QxTHRYWk9tclpNT2hTU0lPUVBtWlAxcFZyTXFVUDZQaDhtb1pueFhrOUh5MnExai1OVkRkUFJBX1ZrMWtqeDhPWjItU29YaFd3Y09MWEltN3owaHRLMXVUUEFYV1hNZEdfMWlnRUF6UW9fWk0xMjFQNXZFRW4zbmVZb2x2NkxPZ1hJPUFDMTAzNkZB")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Webs items are not Working.\",\r\n"
				+ "              \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "       }\r\n"
				+ "}").when().log().all().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js1= ReUsableMethods.rawToJson(createIssueResponse);
		
		String issueid1 = js1.getString("id");
		
		System.out.println(issueid1);
		
		//Add attachment
		
		String createIssueResponse1 = given().header("X-Atlassian-Token", "no-check").pathParam("key", issueid1)
		.header("Authorization","Basic bWFoZXNoLndhZGh3YTM3MUBnbWFpbC5jb206QVRBVFQzeEZmR0Ywem5EUzNhelpJZERWTDVWa0RMWmY2WEp0aThaTUhUam1IbFdEVDk5c2QxTHRYWk9tclpNT2hTU0lPUVBtWlAxcFZyTXFVUDZQaDhtb1pueFhrOUh5MnExai1OVkRkUFJBX1ZrMWtqeDhPWjItU29YaFd3Y09MWEltN3owaHRLMXVUUEFYV1hNZEdfMWlnRUF6UW9fWk0xMjFQNXZFRW4zbmVZb2x2NkxPZ1hJPUFDMTAzNkZB")
		.multiPart("file",new File("E:\\Rest API Testing (Automation) from Scratch-Rest Assured Java/POT.bmp"))
		.when().post("rest/api/3/issue/{key}/attachments/").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
         JsonPath js2= ReUsableMethods.rawToJson(createIssueResponse1);
		
		String issueid2 = js2.getString("id");
		var newString = issueid2.substring(1, issueid2.length()-1);
		System.out.println(newString);
		//Get Bug details
		
		given()
		.pathParam("key", newString)
		.header("Authorization","Basic bWFoZXNoLndhZGh3YTM3MUBnbWFpbC5jb206QVRBVFQzeEZmR0Ywem5EUzNhelpJZERWTDVWa0RMWmY2WEp0aThaTUhUam1IbFdEVDk5c2QxTHRYWk9tclpNT2hTU0lPUVBtWlAxcFZyTXFVUDZQaDhtb1pueFhrOUh5MnExai1OVkRkUFJBX1ZrMWtqeDhPWjItU29YaFd3Y09MWEltN3owaHRLMXVUUEFYV1hNZEdfMWlnRUF6UW9fWk0xMjFQNXZFRW4zbmVZb2x2NkxPZ1hJPUFDMTAzNkZB")
		.when().log().all().get("rest/api/3/issue/{key}/").then().log().all().assertThat().statusCode(200);
		//
		
		
		

	}

}
