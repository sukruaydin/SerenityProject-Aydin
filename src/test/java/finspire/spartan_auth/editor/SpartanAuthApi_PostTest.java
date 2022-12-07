package finspire.spartan_auth.editor;

import io.cucumber.java.it.Ma;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.yecht.Data;
import utilities.SpartanAuthTestBase;
import utilities.SpartanUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.*;

@Disabled
@SerenityTest
public class SpartanAuthApi_PostTest extends SpartanAuthTestBase {

    @DisplayName("editor")
    @Test
    public void test1(){
        //better create spartan here, since later it will enable me to reach its keys-values
        Map<String, Object> spartanMap = SpartanUtils.generateSpartan();

        //Serenity already comes with jackson, we don't need any dependency for (de)serialization
        given().auth().basic("editor", "editor")
                .and().contentType(ContentType.JSON)
                .and().body(spartanMap)
                .when().post("/spartans");

          /*
                status code is 201
                content type is Json
                success message is A Spartan is Born!
                id is not null
                name is correct
                gender is correct
                phone is correct
                *this is good* check location header ends with newly generated id
         */

        Ensure.that("StatusCode is 201",c -> c.statusCode(201));
        Ensure.that("Content-type is Json",c -> c.contentType(ContentType.JSON));
        Ensure.that("Success message is A Spartan is Born!",c -> c.body("success", Matchers.is("A Spartan is Born!")));
        Ensure.that("id is not null",c -> c.body("data.id",Matchers.notNullValue()));
        Ensure.that("name is correct",c -> c.body("data.name",Matchers.is(spartanMap.get("name"))));
        Ensure.that("gender is correct",c -> c.body("data.gender",Matchers.is(spartanMap.get("gender"))));
        Ensure.that("phone is correct",c -> c.body("data.phone",Matchers.is(spartanMap.get("phone"))));
        Ensure.that("check location header ends with newly generated id",
                c -> c.header("Location",Matchers.endsWith(lastResponse().jsonPath().getString("data.id"))));


    }


    @ParameterizedTest(name = "New Spartan {index} - name: {0}")
    @CsvFileSource(resources = "/SpartanData.csv",numLinesToSkip = 1)
    public void test2(String name, String gender, long phone){
         /*
            we can give name to each execution using name = ""
            and if you want to get index of iteration we can use {index}
            and also if you are to include parameter in your test name
            {0},{1},{2} --> based on the order you provide as argument.
         */

        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        Map<String,Object> mapSpartan = new LinkedHashMap<>();
        mapSpartan.put("name",name);
        mapSpartan.put("gender",gender);
        mapSpartan.put("phone",phone);

        given().and().auth().basic("editor","editor")
                .and().contentType(ContentType.JSON)
                .and().body(mapSpartan)
                .when().post("/spartans");

        Ensure.that("StatusCode is 201",c -> c.statusCode(201));
        Ensure.that("Content-type is Json",c -> c.contentType(ContentType.JSON));
        Ensure.that("Success message is A Spartan is Born!",c -> c.body("success", Matchers.is("A Spartan is Born!")));
        Ensure.that("id is not null",c -> c.body("data.id",Matchers.notNullValue()));
        Ensure.that("name is correct",c -> c.body("data.name",Matchers.is(mapSpartan.get("name"))));
        Ensure.that("gender is correct",c -> c.body("data.gender",Matchers.is(mapSpartan.get("gender"))));
        Ensure.that("phone is correct",c -> c.body("data.phone",Matchers.is(mapSpartan.get("phone"))));
        Ensure.that("check location header ends with newly generated id",
                c -> c.header("Location",Matchers.endsWith(lastResponse().jsonPath().getString("data.id"))));

    }
}
