package cmn;

import com.github.shyiko.dotenv.DotEnv;

import java.util.Map;

public class System {
    public static String Env(String keyword){
        Map<String, String> dot = DotEnv.load();
        return dot.get(keyword);
    }
    public static int GetTimeOut(){
        String q = Env("TIMEOUT");
        if (q!=null){
            return new Integer(q);
        }
        else{ return 30;}
    }
}
