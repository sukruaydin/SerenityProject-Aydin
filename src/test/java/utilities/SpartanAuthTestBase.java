package utilities;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.reset;

public class SpartanAuthTestBase {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init(){
        baseURI = "http://54.82.123.95";
        port = 7000;
        basePath = "/api"; //each end-point starts with /api

        requestSpec = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin");

        responseSpec = expect().statusCode(200)
                .contentType(ContentType.JSON);

    }

    @AfterAll
    public static void close(){
        //reset the info we set above
        reset();
    }
}
