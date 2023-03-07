package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CustomUtilities {
	//because of static keyword once variable is initialized it will hold it's value
	//in all tests, if test with different parameter is run
	public static RequestSpecification req_spec_builder;
	
	public RequestSpecification customReqSpecBuilder() throws IOException {
		
		//when providing multiple dataset from feature file,
		//old console_log_data.txt file is getting replaced by new
		//we don't want to do that
		//hence we will run below code only once, and on subsequent calls else part
		//will return req_spec_builder which will get constructed on first run
		if(req_spec_builder==null) {
			//no need to create console_log_data.txt file it will be automatically created
			PrintStream log = new PrintStream(new FileOutputStream("console_log_data.txt"));
			
			req_spec_builder = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseUri"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
			return req_spec_builder;
			}
		else {return req_spec_builder;}
	}
	
	//method for accessing global properties by providing key name as parameter
	public static String getGlobalProperty(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(new File("E:\\pratik30\\Software testing\\Eclipse_workfiled\\RestAssured_framework\\src\\test\\java\\resources\\global.properties"));
		prop.load(fis);
		
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String keyName) {
		String resString = response.asString();
		JsonPath js = new JsonPath(resString);
		return (js.get(keyName).toString());
	}

}
