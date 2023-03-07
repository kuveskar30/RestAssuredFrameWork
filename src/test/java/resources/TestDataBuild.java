package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojoClasses.AddPlacePojoClass;
import pojoClasses.Location;

public class TestDataBuild {

	public AddPlacePojoClass addPlacePayload(String placeName,String address,String contact) {
		AddPlacePojoClass add_place_data = new AddPlacePojoClass();
		add_place_data.setAccuracy(50);
		add_place_data.setName(placeName);
		add_place_data.setPhone_number(contact);
		add_place_data.setAddress(address);
		add_place_data.setWebsite("http://google.com");
		add_place_data.setLanguage("French-IN");

		List<String> types = new ArrayList<String>(Arrays.asList("central park", "ashirwad bangla"));
		add_place_data.setTypes(types);

		Location lcn = new Location();
		lcn.setLat(-38.383494);
		lcn.setLng(33.427362);
		add_place_data.setLocation(lcn);
		
		return add_place_data;
	}
	
	public String getDeletePlacePayload(String placeId) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
		
	}
	
}
