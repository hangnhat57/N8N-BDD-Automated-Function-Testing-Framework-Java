package tech.nhatnguyen.shiba;

import com.github.shyiko.dotenv.DotEnv;

import java.util.Map;

public class SystemConfiguration {
    public static String getEnv(String keyword) {
        Map<String, String> dot = DotEnv.load();
        return dot.get(keyword);
    }

    public static int getTimeOut() {
        String q = getEnv("TIMEOUT");
        if (q != null) {
            System.out.println("Set time out to: " + q);
            return new Integer(q);
        } else {
            return 30;
        }
    }


}
