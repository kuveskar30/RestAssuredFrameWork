package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.AddPlacePojoClass;
import pojoClasses.Location;
import resources.ApiResources;
import resources.CustomUtilities;
import resources.TestDataBuild;

public class StepDefinitions1 extends CustomUtilities{
	
	TestDataBuild testData = new TestDataBuild();
	RequestSpecification req_spec;
	Response res;
	static String place_id;//static variable will hold its value across all test cases
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String placeName, String address, String contact) throws IOException {

		// request part
		req_spec = given().spec(customReqSpecBuilder())
				.body(testData.addPlacePayload(placeName, address, contact));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethodName) {
		//constructor will be called with value of resource which we pass 
		ApiResources apiResource = ApiResources.valueOf(resource);
		System.out.println(apiResource.getResource());
		if(httpMethodName.equalsIgnoreCase("POST")) {
			res = req_spec.when().post(apiResource.getResource());
		}
		else if(httpMethodName.equalsIgnoreCase("GET")) {
			res = req_spec.when().get(apiResource.getResource());
		}
	}

	@Then("The API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(int expectedStatusCode) {
		Assert.assertEquals(res.getStatusCode(), expectedStatusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedKeyValue) {	
		Assert.assertEquals(getJsonPath(res, key), expectedKeyValue);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedPlaceName, String resource) throws IOException {
		place_id = getJsonPath(res,"place_id");
		req_spec = given().spec(customReqSpecBuilder()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualPlaceName = getJsonPath(res,"name");
		Assert.assertEquals(actualPlaceName, expectedPlaceName);
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		req_spec = given().spec(customReqSpecBuilder()).body(testData.getDeletePlacePayload(place_id));
	}

}
