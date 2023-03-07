package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//running test without tag
//here in features we have given path till package level so all features file under 
//feature package will run
//if there are multiple feature files and if we want to run specific feature file,
//then we can give path of that .feature file instead of giving package level path
@CucumberOptions
(features="src/test/java/features",
plugin="json:target/jsonReports/cucumber-report.json",
glue="stepDefinitions")
public class TestRunner extends AbstractTestNGCucumberTests {

}

////running test with tag
//@CucumberOptions(features="src/test/java/features",glue="stepDefinitions",tags= "@DeletePlace")
//public class TestRunner extends AbstractTestNGCucumberTests {
//
//}
