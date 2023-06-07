package stepdefinitions.e2e_test;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pojos.RoomPojo;

import java.io.IOException;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepdefinitions.e2e_test.MedunnaRoomCreationStepDefs.roomId;
import static stepdefinitions.e2e_test.MedunnaRoomCreationStepDefs.roomNumberFaker;

public class ApiRoomStepDefs {
    Response response;
    public static RoomPojo expectedData;
    RoomPojo actualData;
    @Given("send get request")
    public void send_get_request() {
        //Set the url -->https://medunna.com/api/rooms?sort=createdDate,desc
        spec.pathParams("first", "api", "second", "rooms")
                .queryParams("sort", "createdDate,desc");
        //Set the expected data
        //Send the request and get the response
        response = given(spec).get("{first}/{second}");
    }

    @Then("validate body")
    public void validate_body() { // bu methodda cok fazla hard coding yaptik
        Object roomType = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.roomType").get(0);
        Object status = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.status").get(0);
        Object price = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.price").get(0);
        Object description = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.description").get(0);
        Object roomNumber = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.roomNumber").get(0);

        assertEquals(200, response.statusCode());
        assertEquals("SUITE", roomType);
        assertEquals(true, status);
        assertEquals("123.0", price + "");
        assertEquals("Created For End To End Test", description);
        assertEquals(roomNumberFaker, roomNumber);
    }

    @Given("send get request by id")
    public void sendGetRequestById() {
        //Set the url -->https://medunna.com/api/rooms/:id
        spec.pathParams("first", "api", "second", "rooms", "third", roomId);
        //Set the expected data
        expectedData = new RoomPojo(roomNumberFaker, "SUITE", true, 123.00, "Created For End To End Test");

        //Send the request and get the response
        response = given(spec).get("{first}/{second}/{third}");
    }

    @Then("validate response body")
    public void validateResponseBody() throws IOException {
        actualData = new ObjectMapper().readValue(response.asString(),RoomPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getRoomNumber(), actualData.getRoomNumber());
        assertEquals(expectedData.getPrice(), actualData.getPrice());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getDescription(), actualData.getDescription());
        assertEquals(expectedData.getRoomType(), actualData.getRoomType());
    }
}