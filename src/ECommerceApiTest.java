import io.restassured.builder.RequestSpecBuilder;
import static io.restassured.RestAssured.given;

import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO.LoginRequest;
import POJO.LoginResponse;
import POJO.OrderDetail;
import POJO.Orders;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ECommerceApiTest {

	public static void main(String[] args) {
		
		
		//login
		RequestSpecification req=new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("mahesh.wadhwa371@gmail.com");
		loginRequest.setUserPassword("Password@987");
		RequestSpecification reqLogin = given().spec(req).body(loginRequest);
		LoginResponse loginResponse = reqLogin.when()
		.post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
		String token = loginResponse.getToken();
		System.out.println(loginResponse.getToken());
		System.out.println(loginResponse.getUserId());
		
		//Add Product
		
		RequestSpecification addProductBaseReq=new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token)
				.build();
		
		RequestSpecification reqaddProduct = given().log().all().spec(addProductBaseReq).param("productName","laptop")
		.param("productAddedBy","userId").param("productCategory","fashion")
		.param("productSubCategory","Shirts").param("productFor","men")
		.param("productDescription","Lenova").param("productPrice", "13500")
		.multiPart("productImage",new File("C://Users//Dell//Pictures//image_test.jpg"));
			
		String addProductResponse =  reqaddProduct.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(addProductResponse);
		
		String productId = js.get("productId");
		
		//Create Order
		
		RequestSpecification CreateOrderBasereq=new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization",token).setContentType(ContentType.JSON).build();
		
		OrderDetail OrderDetail = new OrderDetail();
		OrderDetail.setCountry("India");
		OrderDetail.setProductOrderId(productId);
		
		List<OrderDetail> OrderDetailList = new ArrayList<OrderDetail>();
		OrderDetailList.add(OrderDetail);
		
		Orders orders = new Orders();
		orders.setOrders(OrderDetailList);
		
		RequestSpecification createOrderReq = given().log().all().spec(CreateOrderBasereq)
	    .body(orders);
		
		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		
		System.out.println(responseAddOrder);
		
		//delete Order
		
		RequestSpecification DeleteProductBasereq=new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization",token).setContentType(ContentType.JSON).build();
		
		RequestSpecification deleteProdReq = given().log().all().spec(DeleteProductBasereq).pathParam("productId",productId);
		
		String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
	JsonPath js1 = new JsonPath(deleteProductResponse);
	System.out.println(productId);
	Assert.assertEquals(js1.get("message"),"Product Deleted Successfully");
	
	}

}
