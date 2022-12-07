package finspire.spartan_auth.admin;


import io.cucumber.java.af.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Disabled
@SerenityTest //wrapper RestAssured
public class SpartanAuthApi_GetTest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.82.123.95:7000";
    }

    @DisplayName("regular get request")
    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON);

    }


    @DisplayName("lastResponse() method")
    @Test
    public void test2(){

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .and().pathParam("id",15)
                .when().get("/api/spartans/{id}");
        /*
            if you send a request using SerenityRest, the response object can be obtained from the method
            called lastResponse() without being saved separately. same with Response response object
         */

        int statusCode = lastResponse().statusCode();
        System.out.println("statusCode = " + statusCode);

        int id = lastResponse().path("id");
        System.out.println("id = " + id);

        String name = lastResponse().jsonPath().getString("name");
        System.out.println("name = " + name);

    }

    @DisplayName("Serenity Assertion")
    @Test
    public void test3(){

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .and().pathParam("id",15)
                .when().get("/api/spartans/{id}");

        //Serenity assertion
        //soft assertion, if one of them fails, it keeps asserting following ones
        Ensure.that("statusCode is 200",validatableResponse -> validatableResponse.statusCode(200));
        Ensure.that("Content-type is JSON",vRes -> vRes.contentType(ContentType.JSON));
        Ensure.that("id is 15",vRes -> vRes.body("id", Matchers.is(15)));
    }

}
