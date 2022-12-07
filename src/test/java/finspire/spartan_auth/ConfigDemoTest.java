package finspire.spartan_auth;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utilities.ConfigReader;

@Disabled
public class ConfigDemoTest {

    @Test
    public void test1(){

        String property1 = ConfigReader.getProperty("serenity.project.name");
        String property2 = ConfigReader.getProperty("spartan.editor.username");

        System.out.println("property1 = " + property1);
        System.out.println("property2 = " + property2);

    }


}
