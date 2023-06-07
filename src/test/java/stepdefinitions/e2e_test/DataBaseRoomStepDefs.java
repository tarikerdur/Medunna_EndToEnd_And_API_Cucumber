package stepdefinitions.e2e_test;

import io.cucumber.java.en.*;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static stepdefinitions.e2e_test.ApiRoomStepDefs.expectedData;
import static stepdefinitions.e2e_test.MedunnaRoomCreationStepDefs.roomNumberFaker;

public class DataBaseRoomStepDefs {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("connect to database")
    public void connect_to_database() throws SQLException {
        connection = DriverManager.
                getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2",
                        "select_user", "Medunna_pass_@6");
        statement = connection.createStatement();
    }

    @Then("read room and validate")
    public void read_room_and_validate() throws SQLException {
        String sqlQuery = "SELECT * FROM room WHERE room_number=" + roomNumberFaker;

        resultSet = statement.executeQuery(sqlQuery);

        resultSet.next(); // next() methodu cursor'u siradaki satira alir.
        // query'de "WHERE" kullandigimiz icin zaten tek satir doncek bu yuzden loop'a gerek olmadi.

        // Expected 'DB' datas:
        // public static RoomPojo expectedData'yi kullanacaz


        // Actual 'DB' datas:
        String roomType = resultSet.getString("room_type");
        Boolean status = resultSet.getBoolean("status");
        Double price = resultSet.getDouble("price");
        String description = resultSet.getString("description");
        Integer roomNumber = resultSet.getInt("room_number");


        // Do assertion
        assertEquals(expectedData.getRoomType(), roomType);
        assertEquals(expectedData.getStatus(), status);
        assertEquals(expectedData.getPrice(), price);
        assertEquals(expectedData.getDescription(), description);
        assertEquals(expectedData.getRoomNumber(), roomNumber);


    }
}
