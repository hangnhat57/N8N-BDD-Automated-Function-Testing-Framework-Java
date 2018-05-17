package tech.nhatnguyen.automated;

import com.github.shyiko.dotenv.DotEnv;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static tech.nhatnguyen.common.SysConfig.Env;

public class Base {
    public WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public static String url(){
        return Env("URL").toLowerCase();
    }
}
