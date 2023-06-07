package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;

import static utilities.AuthenticationMedunna.generateToken;

public class MedunnaBaseUrl {
    public static RequestSpecification spec;

    public static void setUp() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + generateToken())
                .setBaseUri(ConfigReader.getProperty("medunnaUrl")).build();
    }


}
