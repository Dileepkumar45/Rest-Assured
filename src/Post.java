import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import files.payload;


import files.Remethods;
import io.restassured.path.json.JsonPath;


public class Post {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
RestAssured.baseURI="https://rahulshettyacademy.com";
String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
.body(payload.AddPlace()).post("/maps/api/place/add/json")
.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)")
.extract().response().asString();  //log all should be used for output showing in console logs
System.out.println(response);
 
JsonPath js=new JsonPath(response);
String place_id= js.get("place_id");
System.out.println(place_id);

//Update Place
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
	String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",place_id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js1=Remethods.rawToJson(getPlaceResponse);
	String actualAddress =js1.getString("address");
	System.out.println(actualAddress);
	
	
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
	}

}