package StepDefinition;

import DataProviders.ConfigFileReader;
import io.cucumber.java.en.Given;

import static io.restassured.RestAssured.*;

public class UserAPIStep extends ConfigFileReader {
    String baseURi = properties.getProperty(baseURI);

    @Given("^I perform POST operation for (.+) as (.+)$")
    public void i_perform_post_operation_for(String base, String name) {
        baseURI = baseURi;
        basePath = base;
        given().contentType("application/json").body(name)
                .when().post()
                .then().statusCode(200);
    }
}