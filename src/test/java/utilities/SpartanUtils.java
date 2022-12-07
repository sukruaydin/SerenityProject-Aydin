package utilities;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtils {

    private static Faker faker;

    public static Map<String,Object> generateSpartan(){
        Map<String,Object> map = new LinkedHashMap<>();

        faker = new Faker();
        int numberBetween = faker.number().numberBetween(1,3);//3 is not included
        String gender = (numberBetween==1) ? "Male" : "Female";

        map.put("name",faker.name().firstName());
        map.put("gender",gender);
        map.put("phone",Long.parseLong(faker.numerify("##########")));

        return map;
    }
}
