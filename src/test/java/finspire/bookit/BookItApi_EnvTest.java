package finspire.bookit;

import org.junit.jupiter.api.Test;
import utilities.ConfigReader;

public class BookItApi_EnvTest {

    @Test
    public void test1(){

        String baseUrl = ConfigReader.getProperty("base.url");
        String teacher_email = ConfigReader.getProperty("teacher_email");
        String teacher_password = ConfigReader.getProperty("teacher_password");

        System.out.println("baseUrl = " + baseUrl);
        System.out.println("teacher_email = " + teacher_email);
        System.out.println("teacher_password = " + teacher_password);

    }

}
