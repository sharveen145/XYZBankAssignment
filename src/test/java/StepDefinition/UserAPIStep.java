package StepDefinition;

import Managers.FileReaderManager;
import Utilities.base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.testng.Assert;

import static Utilities.randomGenerator.generateRandomNumber;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;


public class UserAPIStep extends base {
    String baseURi = FileReaderManager.getInstance().getConfigFileReader().getBaseURi();
    public static int id= generateRandomNumber(2);
    public static String userName ;
    public  String getField;

    @Given("^i perform POST operation for (.+) as (.+)$")
    public void postNewUser(String base, String name) {
        userName=name;
        String payload = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"username\": \"TestUser\",\n" +
                "  \"firstName\": \""+userName+"\",\n" +
                "  \"lastName\": \"\",\n" +
                "  \"email\": \"shan123@email.com\",\n" +
                "  \"password\": \"12345\",\n" +
                "  \"phone\": \"54321\",\n" +
                "  \"userStatus\": 1\n" +
                "}";
        basePath = base;

        given().baseUri(baseURi).
                contentType("application/json").body(payload).
                when().post(basePath).
                then().log().all().extract().response().
                then().assertThat().statusCode(200);
    }

    @And("i perform GET for newly created user")
    public void verifyNewUserCreated() {
        basePath ="/user/";
       Response response = given().baseUri(baseURi).contentType("application/json").
                when().get(basePath).
                then().log().all().extract().response();

        getField = String.valueOf(response.jsonPath().getString("username"));
        Assert.assertEquals("firstName field not matched, Expected: "+userName+" but Actual: "+getField, getField,userName);
        Assert.assertEquals("Status Code not matched, Expected: 200 but Actual: "+response.getStatusCode(), response.getStatusCode(),"200");
        logger.info("Both status code and firstName field are matched");
    }
}