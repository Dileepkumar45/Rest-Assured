package files;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class dynamicHandling {
	@Test
	public void Addbook() {
		RestAssured.baseURI="http://216.10.245.166";
		 given().log().all().header("Content-Type","application/json").body(payload.AddBook()).
		when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200);
//		JsonPath js=new JsonPath(response);
//		String id=js.get("ID");
//		System.out.println(id);
	}

}