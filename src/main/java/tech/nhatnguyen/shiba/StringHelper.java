package tech.nhatnguyen.shiba;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class StringHelper {

    public static String convertXpath(String xpath, String value) {
        ArrayList ary = new ArrayList(Arrays.asList(xpath.split("''")));
        String e = "\"" + value + "\"";
        ary.add(1, e);
        return String.join("", ary);

    }

    public static String convertEmail(String email) {
        Random rand = new Random();
        int n = rand.nextInt(9999999) + 1;
        email = email.replace("@gmail.com", "");
        email = email.concat("+q" + n);
        email = email.concat("@gmail.com");
        return email;
    }
}
