package files;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQLScript {
	
	
	public static void main(String args[])
	
	
	{
		//Query 
		int locationId = 13687;
		int episodeId = 10449;
		String Response = given().log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"query\": \"query($locationId: Int!,$episodeId: Int!)\\n\\n{\\n  \\n  location(locationId:$locationId)\\n  {\\n    \\n    name\\n    type\\n    dimension\\n    id\\n    \\n  }\\n  \\n  episode(episodeId:$episodeId)\\n  \\n  {\\n    \\n    name\\n    air_date\\n    episode\\n    id\\n    \\n  }\\n  \\n  \\n  \\n  \\n}\",\r\n"
				+ "  \"variables\": {\r\n"
				+ "    \"locationId\": "+locationId+",\r\n"
				+ "    \"episodeId\": "+episodeId+"\r\n"
				+ "  }\r\n"
				+ "}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		System.out.println(Response);
		
		JsonPath js = new JsonPath(Response);
		String locationName = js.getString("data.location.name");
		String episodeName = js.getString("data.episode.name");
		System.out.println(locationName);
		Assert.assertEquals(locationName, "White Field");
		
		//Mutations
		String locationName1 = locationName;
		String episodeName1 = episodeName;
		
		String Response1 = given().log().all().header("Content-Type","application/json")
				.body("{\"query\":\"mutation($locationName:String!,$episodeName:String!)\\n{\\n  \\n  createLocation(location :{name: $locationName,type: \\\"SouthZone\\\",dimension: \\\"234\\\"})\\n \\n  \\n  {\\n    \\n    \\n    id\\n    \\n    \\n  }\\n  \\n  createCharacter(character: {name: \\\"Robin\\\",type: \\\"Macho\\\",status: \\\"dead\\\",species: \\\"fantasy\\\",gender: \\\"male\\\",image: \\\"png\\\",originId: 15,locationId: 15} )\\n  \\n  {\\n    \\n    id\\n  }\\n  \\n  createEpisode(episode: {name: $episodeName,air_date: \\\"2024\\\",episode: \\\"NetFlix\\\"})\\n  {\\n    \\n    id\\n  }\\n\\n\\ndeleteLocations(locationIds: [13671,13672,13669,13670,13671])\\n  \\n  {\\n    \\n    locationsDeleted\\n  }\\n\\n}\\n  \\n\",\"variables\":{\"locationName\":\""+locationName1+"\",\"episodeName\":\""+episodeName1+"\"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql")
				.then().extract().response().asString();
		
		JsonPath js1 = new JsonPath(Response1);
		int episodeId1 = js1.get("data.createEpisode.id");
		System.out.println(episodeId1);
		Assert.assertEquals(episodeId1, 10451);
		
		
	}

}
