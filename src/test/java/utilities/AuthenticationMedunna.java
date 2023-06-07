package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationMedunna {

    public static String generateToken() {
        String body = "{ \"password\": \"Mark.123\", \"rememberMe\": true, \"username\": \"mark_twain\"}";
        Response response = given().contentType(ContentType.JSON).body(body).
                post(ConfigReader.getProperty("medunnaUrl")+"/api/authenticate");
        return response.jsonPath().getString("id_token");
    }
}
