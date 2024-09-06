import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
	JsonPath js = new JsonPath(payload.CoursePrice());
		
	//Print No ofcourses returned by API
	System.out.println("//Print No ofcourses returned by API");
		int count = js.getInt("courses.size()");
System.out.println(count);	

//Print purchase amount
System.out.println("//Print purchase amount");
int totalamount = js.getInt("dashboard.purchaseAmount");

System.out.println(totalamount);

//Print title of the first course
System.out.println("//Print title of the first course");
String titlefirstcourse = js.get("courses[0].title");
System.out.println(titlefirstcourse);

//Print All course titles and their respective Prices
System.out.println("Print All course titles and their respective Prices");
for(int i=0;i<count;i++)
{
	
  String courseTitles = js.get("courses["+i+"].title");
  
  
  
  System.out.println(courseTitles);
  
  System.out.println(js.get("courses["+i+"].price").toString());

	}

//5. Print no of copies sold by RPA Course

System.out.println("Print no of copies sold by Cypress Course");

for(int i=0;i<count;i++)
{
	String courseTitles = js.get("courses["+i+"].title");
	if(courseTitles.equalsIgnoreCase("cypreSs"))
	{
	int copies = js.get("courses["+i+"].copies");
	System.out.println("The copies sold for " +courseTitles+ " are " + copies);
	break;
	
}


	}




}
}