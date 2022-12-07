package finspire.spartan_auth.editor;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBase;
import utilities.SpartanUtils;
import static net.serenitybdd.rest.SerenityRest.*;

@SerenityTest
public class SpartanAuthApi_PostTest extends SpartanAuthTestBase {

    @DisplayName("editor")
    @Test
    public void test1(){

        given().auth().basic("editor","editor")
                .and().contentType(ContentType.JSON)
                .and().body(SpartanUtils.generateSpartan())
                .when().post("/spartans");

    }
}
