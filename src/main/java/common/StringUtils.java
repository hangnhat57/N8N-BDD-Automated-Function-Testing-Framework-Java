package common;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class StringUtils {

    public static String _ConvertXpath(String xpath,String value){
        ArrayList ary = new ArrayList(Arrays.asList(xpath.split("''")));
        String e = "\'"+value+"\'";
        ary.add(1,e);
        return String.join("", ary);

    }

    public static String _ConvertEmail(String email) {
        Random rand = new Random();
        int  n = rand.nextInt(9999999) + 1;
        email= email.replace("@gmail.com","");
        email=email.concat("+q"+n);
        email=email.concat("@gmail.com");
        return email;
    }
}
