package StepDefinition;

import Managers.FileReaderManager;
import Utilities.base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import static Utilities.randomGenerator.generateRandomNumber;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;


public class UserAPIStep extends base {
    String baseURi = FileReaderManager.getInstance().getConfigFileReader().getBaseURi();
    public static int id = generateRandomNumber(2);
    public static String fName;
    public static String updatedFName;
    public static String userName = "TestUser001";
    public String getField;

    @Given("^i perform POST operation for (.+) as (.+)$")
    public void createNewUser(String base, String name) {
        fName = name;
        String payload = "{\n" +
                "  \"id\": " +id +",\n" +
                "  \"username\": \""+userName+"\",\n" +
                "  \"firstName\": \"" +fName+ "\",\n" +
                "  \"lastName\": \"\",\n" +
                "  \"email\": \"shan123@email.com\",\n" +
                "  \"password\": \"12345\",\n" +
                "  \"phone\": \"54321\",\n" +
                "  \"userStatus\": 1\n" + "}";
        basePath = base;

        given().baseUri(baseURi).
                contentType("application/json").body(payload).
                when().post(basePath).
                then().log().all().extract().response().
                then().assertThat().statusCode(200);
    }

    @Then("i perform GET and verify newly created user")
    public void verifyNewUserCreated() {
        basePath = "/user/"+userName;
        Response response = given().baseUri(baseURi).contentType("application/json").
                when().get(basePath).
                then().log().all().extract().response();

        getField = String.valueOf(response.jsonPath().getString("username"));
        Assert.assertEquals(getField, fName,"firstName field not matched, Expected: " + fName + " but Actual: " + getField);
        Assert.assertEquals(response.getStatusCode(), 200,"Status Code not matched, Expected: 200 but Actual: " + response.getStatusCode());
        logger.info("Both status code and firstName field are matched");
    }

    @And("^i perform PUT operation for newly created user with (.+)$")
    public void updateUser(String userName) {
        updatedFName =userName;
        basePath = "/user/"+userName;
        String payload = "{\n" +
                "   \"id\": " + id + ",\n" +
                "  \"username\": \""+userName+"\",\n" +
                "  \"firstName\": \"" + updatedFName + "\",\n" +
                "  \"lastName\": \"\",\n" +
                "  \"email\": \"john@email.com\",\n" +
                "  \"password\": \"12345\",\n" +
                "  \"phone\": \"12345\",\n" +
                "  \"userStatus\": 1\n" + "}";

        given().baseUri(baseURi).contentType("application/json").body(payload).
                when().put(basePath).
                then().log().all().assertThat().statusCode(200);
    }


    @Then("^i perform GET and verify the updated first name as (.+)$")
    public void verifyUpdatedUser() {
        basePath = "/user/"+userName;
        Response response = given().baseUri(baseURi).contentType("application/json").
                when().get(basePath).
                then().log().all().extract().response();

        getField = String.valueOf(response.jsonPath().getString("username"));
        Assert.assertEquals( getField, updatedFName, "updated firstName field not matched, Expected: " + updatedFName + " but Actual: " + getField);
        Assert.assertEquals(response.getStatusCode(), 200,"Status Code not matched, Expected: 200 but Actual: " + response.getStatusCode());
        logger.info("Both status code and updated firstName field are matched");
    }

    @And("i should perform DELETE for newly created user")
    public void deleteNewlyCreatedUser() {
        basePath = "/user/"+userName;
        given().baseUri(baseURi).contentType("application/json").
                when().delete(basePath).
                then().assertThat().statusCode(200);
    }

    @Then("i perform GET and should verify the newly created user is deleted")
    public void verifyUserIsDeleted() {
        basePath = "/user/"+userName;
        Response response = given().baseUri(baseURi).contentType("application/json").
                when().get(basePath).
                then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), 404,"Status Code not matched, Expected: 404 but Actual: " + response.getStatusCode());
        logger.info("The user is deleted successfully");
    }
}