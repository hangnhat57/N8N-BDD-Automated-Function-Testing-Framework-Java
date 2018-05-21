package tech.nhatnguyen.shibahelper;

import com.github.shyiko.dotenv.DotEnv;

import java.util.Map;

public class SystemConfiguration {
    public static String Env(String keyword) {
        Map<String, String> dot = DotEnv.load();
        return dot.get(keyword);
    }

    public static int GetTimeOut() {
        String q = Env("TIMEOUT");
        if (q != null) {
            System.out.println("Set time out to: " + q);
            return new Integer(q);
        } else {
            return 30;
        }
    }


}
