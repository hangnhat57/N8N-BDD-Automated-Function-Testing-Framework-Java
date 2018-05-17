package tech.nhatnguyen.automated;

import com.github.shyiko.dotenv.DotEnv;
import org.openqa.selenium.WebDriver;
import tech.nhatnguyen.common.ReadProperties;

import java.util.Map;

import static tech.nhatnguyen.common.SysConfig.Env;

public class Base {
    public WebDriver driver;
    public static ReadProperties urlreader = new ReadProperties("urls.properties");

    public static String url(){
        String env =  Env("ENVIRONMENT").toLowerCase();
        switch (env){
            case "ci":
                return urlreader.getValue("ci");
            case "local":
                return urlreader.getValue("local");
            case "staging":
                return   urlreader.getValue("staging");
            case "demo":
                return   urlreader.getValue("demo");
            case "production":
                return "Do not test on production!";
            default:
                return "Please enter environment in config file";
        }
    }
}
