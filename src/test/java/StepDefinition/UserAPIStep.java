package StepDefinition;

import Managers.FileReaderManager;
import Pages.UserAPIPage;
import Utilities.base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.basePath;


public class UserAPIStep extends base {

    String baseURi = FileReaderManager.getInstance().getConfigFileReader().getBaseURi();
    public static String fName;
    public static String updatedFName;
    public static String userName = "TestUser001";
    public String getField;

    public static String urlAddUser;
    public static String urlRUDUser;

    @Given("^i perform POST operation for (.+) as (.+)$")
    public void createNewUser(String base, String name) {
        fName = name;
        basePath = base;
        urlAddUser = baseURi+basePath;
        Response response = UserAPIPage.createNewUser(fName, userName, urlAddUser);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,"Status Code not matched, Expected: 200 but Actual: " + response.getStatusCode());
        logger.info("New user created successfully");
    }

    @Then("i perform GET and verify newly created user")
    public void verifyNewUserCreated() {
        urlRUDUser = urlRUDUser+userName;

        Response response =  UserAPIPage.getUser(userName,urlRUDUser);
        response.then().log().all();

        getField = response.jsonPath().getString("username");
        Assert.assertEquals(getField, fName,"firstName field not matched, Expected: " + fName + " but Actual: " + getField);
        Assert.assertEquals(response.getStatusCode(), 200,"Status Code not matched, Expected: 200 but Actual: " + response.getStatusCode());
        logger.info("Both status code and firstName field are matched");
    }

    @And("^i perform PUT operation for newly created user with (.+)$")
    public void updateUser(String updatedUserName) {
        updatedFName = updatedUserName;

        Response response = UserAPIPage.updateNewUser(updatedFName, userName, urlRUDUser);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,"Status Code not matched, Expected: 200 but Actual: " + response.getStatusCode());
        logger.info("User updated successfully");
        }


    @Then("^i perform GET and verify the updated first name as (.+)$")
    public void verifyUpdatedUser(String updatedUserName) {
        Response response = UserAPIPage.getUser(userName,urlRUDUser);
        response.then().log().all();

        getField = response.jsonPath().getString("firstname");
        Assert.assertEquals(getField, updatedUserName,"firstName field not matched, Expected: " + updatedUserName + " but Actual: " + getField);
        Assert.assertEquals(response.getStatusCode(), 200,"Status Code not matched, Expected: 200 but Actual: " + response.getStatusCode());
        logger.info("Both status code and firstName field are matched");
    }

    @And("i should perform DELETE for newly created user")
    public void deleteNewlyCreatedUser() {
        Response response = UserAPIPage.deleteUser(userName, urlRUDUser);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,"Status Code not matched, Expected: 200 but Actual: " + response.getStatusCode());
        logger.info("User updated successfully");
    }

    @Then("i perform GET and should verify the newly created user is deleted")
    public void verifyUserIsDeleted() {
        Response response = UserAPIPage.getUser(userName,urlRUDUser);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 404,"Status Code not matched, Expected: 404 but Actual: " + response.getStatusCode());
        Assert.assertEquals(response.getBody().toString(), "User not found","Responds Body message not matched");
        logger.info("The user is deleted successfully");
    }
}