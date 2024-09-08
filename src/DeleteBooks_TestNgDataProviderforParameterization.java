import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class DeleteBooks_TestNgDataProviderforParameterization {

	
	@Test(dataProvider = "BooksData")
	
	public void addBook(String isbn,String aisle)
	
	
	{
		// Add book
		RestAssured.baseURI = "http://216.10.245.166";
		String response =  given().log().all().header("Content-type","application/json").
		body(payload.Addbook(isbn,aisle)).when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		
		String id = js.get("ID");
	
		System.out.println(id);
		
		//Delete books
		
				given().log().all().header("Content-type","application/json").
				body(payload.DeleteBook(id)).when().post("Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"));


		
		
	}
	
	
	
	
	@DataProvider(name = "BooksData")
	
	public Object[][] getData()
	
	{
		//array = collection of elements
		//multidimensional array = collection of arrays
		return new Object[][] {{"EFGH","0980"},{"IJKl","1980"},{"QRst","2980"}};
		
	}
	
	
	
	
	
	
	
	
}



		