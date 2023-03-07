package resources;

public enum ApiResources {
	//An enum is a special "class" that represents a group of constants 
	//(unchangeable variables, like final variables).
	
	//here we have collection of methods
	//these methods will return string on calling
	//all methods are separated by comma and at end of last method semicolon is uesed
	addPlaceApi("/maps/api/place/add/json"),
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceApi("/maps/api/place/delete/json");
	
	private String resource;
	
	//we have to define a constructor when working with enum
	//structure of enum constructor should be same as structure of enum
	
	//below constructor will load resource variable with value of provided methodName
	//e.g. if resource variable is=addPlaceApi then value of addPlaceApi will be 
	//loaded in it
	ApiResources(String resource){
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

}
