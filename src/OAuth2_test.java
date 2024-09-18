import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
//import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class OAuth2_test {

	public static void main(String[] args) throws InterruptedException {
		
		//Deriving code
		/*System.setProperty("webDriver.chrome.driver", "E://chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("islandmahesh@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Life@best1");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		String url = driver.getCurrentUrl();*/ //google has stopped the automation
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AQlEd8wuCfAt4IsqSgP7miLe_r4zEekynyKApvDB1xsEu5CQA4PGhdxQ7uR_PC9Vd-2Enw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=consent";
		String partialcode = url.split("code=")[1]; //partialcode
		String code = partialcode.split("&scope")[0]; //code actual which is required
		
		
		//Deriving Access token
		String accessTokenResponse  = given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token")
		.then().extract().response()
		.asString();
		
		JsonPath js= new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		System.out.println("accessToken is "+accessToken);
		
		//using Access token to access actual URI
		String response = given().queryParam("access_token",accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php")
		.then().extract().response().asString();
		System.out.println(response);

	}

}
