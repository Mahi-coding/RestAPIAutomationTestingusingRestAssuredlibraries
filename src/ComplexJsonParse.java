import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
	JsonPath js = new JsonPath(payload.CoursePrice());
		
	//Print No ofcourses returned by API
		int count = js.getInt("courses.size()");
System.out.println(count);	

//Print purchase amount

int totalamount = js.getInt("dashboard.purchaseAmount");

System.out.println(totalamount);

//Print title of the first course

String titlefirstcourse = js.get("courses[0].title");
System.out.println(titlefirstcourse);

//Print All course titles and their respective Prices

for(int i=0;i<count;i++)
{
	
  String courseTitles = js.get("courses["+i+"].title");
  
  
  
  System.out.println(courseTitles);
  
  System.out.println(js.get("courses["+i+"].price").toString());

	}
	}
}
