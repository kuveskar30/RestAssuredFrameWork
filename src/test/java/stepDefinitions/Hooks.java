package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	//this code will run before @DeletePlace Test/Scenario
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException{
		StepDefinitions1 sd1 = new StepDefinitions1();
		if(StepDefinitions1.place_id==null) {
			sd1.add_place_payload_with("gameZone1", "near railway station", "256398");
			sd1.user_calls_with_http_request("addPlaceApi", "POST");
			sd1.verify_place_id_created_maps_to_using("gameZone1", "getPlaceApi");
		}
	}
}
