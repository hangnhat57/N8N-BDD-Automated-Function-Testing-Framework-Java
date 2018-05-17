package tech.nhatnguyen.automated;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static tech.nhatnguyen.common.SysConfig.Env;

public class Hook {


    private final String BROWSER = Env("BROWSER").toLowerCase();
    private final String OS = System.getProperty("os.name").toLowerCase();
    private final String MODE = Env("BROWSER_MODE");
    private final String ENVIRONMENT = Env("ENVIRONMENT");
    private final String BROWSERSIZE = browser_size();

    public void TestInit(Scenario scenario) throws Exception {
        System.out.println(StartScenario(scenario));
        switch (BROWSER){
            case "chrome":
                Base.driver = Chrome();
                break;
            case "firefox":
                Base.driver = Firefox();
                break;
            case "edge":
                Base.driver = Edge();
                break;
            case "remote-chrome":
                Base.driver = RemoteChrome();
                break;
            case "remote-firefox":
                Base.driver = RemoteFirefox();
                break;
        }

    }

    public void TestTearDown(Scenario result) throws URISyntaxException, IOException {
        if(result.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Base.driver)
                    .getScreenshotAs(OutputType.BYTES);
            result.embed(screenshot, "image/png");
        }
        System.out.println(EndScenario(result));
        Base.driver.quit();

    }

    private WebDriver Edge(){
        EdgeDriverManager.getInstance().setup();
        return new EdgeDriver();
    }

    private WebDriver Firefox(){
        FirefoxDriverManager.getInstance().arch64().setup();
        FirefoxOptions ffoption = new FirefoxOptions();
        ffoption.addArguments(browser_size());
        if (MODE.equals("headless")){
            ffoption.addArguments("--headless");}
        return new FirefoxDriver(ffoption);
    }
    private WebDriver Chrome(){
        ChromeDriverManager.getInstance().arch64().setup();
        ChromeOptions option = new ChromeOptions();
        Map<String, Object> preferences = new HashMap<String, Object>();
        option.addArguments(browser_size());
        if (MODE.equals("headless")){
            option.addArguments("--headless");
        }
        return new ChromeDriver(option);
    }
    /** Chrome Docker *********/
    private RemoteWebDriver RemoteChrome() throws Exception {
        ChromeOptions option = new ChromeOptions();
        option.addArguments(browser_size());
        if (MODE.equals("headless")) {
            option.addArguments("--headless");
        }
        return new RemoteWebDriver(new URL("http://localhost:35029/wd/hub"), option);
    }
    private RemoteWebDriver RemoteFirefox() throws Exception {
        FirefoxOptions option = new FirefoxOptions();
        option.addArguments(browser_size());
        if (MODE.equals("headless")) {
            option.addArguments("--headless");
        }
        return new RemoteWebDriver(new URL("http://localhost:35029/wd/hub"), option);
    }
    private String browser_size(){
        String browser_size = "window-size=3840,2160";
        switch (Env("BROWSER_SIZE").toLowerCase())
        {
            case "2k":browser_size =  "window-size=1920,1080";break;
            case "4k":browser_size =  "window-size=3840,2160";break;
        }
        return browser_size;
    }
    private String StartScenario(Scenario scenario){
        return "\n " + "========(^.^) Execute "+ scenario.getName()
                + " in "+ENVIRONMENT + " with " + BROWSER +" on " + OS +" (^.^)========" + "\n";
    }
    private String EndScenario(Scenario scenario){
        return  "\n"  + "******* Execute scenario "+ scenario.getName() +
                " done as :" + scenario.getStatus().toUpperCase()+" ********"
                + "\n";
    }

}
