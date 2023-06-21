package Pages;

import Utilities.base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Utilities.randomGenerator.generateRandomNumber;
import static io.restassured.RestAssured.given;

public class UserAPIPage extends base {

    public static int id = generateRandomNumber(2);
    public static Response createNewUser(String firstName, String userName, String url){

        String payload = "{\n" +
                "  \"id\":"+id +",\n" +
                "  \"username\": \""+userName+"\",\n" +
                "  \"firstName\": \"" +firstName+ "\",\n" +
                "  \"lastName\": \"\",\n" +
                "  \"email\": \"shan123@email.com\",\n" +
                "  \"password\": \"12345\",\n" +
                "  \"phone\": \"54321\",\n" +
                "  \"userStatus\": 1\n" + "}";

        Response response = given().contentType(ContentType.JSON).
                accept(ContentType.JSON).body(payload).
                when().post(url);
        return response;
    }

    public static Response getUser(String userName, String url){
        Response response = given().queryParam("username", userName).
                when().get(url);
        return response;
    }

    public static Response updateNewUser(String firstName, String userName, String url){

        String payload = "{\n" +
                "   \"id\": " + id + ",\n" +
                "  \"username\": \""+userName+"\",\n" +
                "  \"firstName\": \"" + firstName + "\",\n" +
                "  \"lastName\": \"\",\n" +
                "  \"email\": \"john@email.com\",\n" +
                "  \"password\": \"12345\",\n" +
                "  \"phone\": \"12345\",\n" +
                "  \"userStatus\": 1\n" + "}";

        Response response =  given().contentType(ContentType.JSON).
                accept(ContentType.JSON).queryParam("username",userName).body(payload).
                when().put(url);

        return response;
    }

    public static Response deleteUser(String userName, String url){
        Response response = given().queryParam("username", userName).
                when().delete(url);
        return response;
    }
}
