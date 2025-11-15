package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//"@ErrorValidation or @PlaceOrder"
//@tag
@CucumberOptions(features = "src/test/java/cucumber", glue = "stepdefinition",tags = "@ErrorValidation or @PlaceOrder or @VerifyOrder", 
monochrome = true, plugin = {"html:reports/cucumber.html"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
