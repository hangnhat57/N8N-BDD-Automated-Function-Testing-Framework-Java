package tech.nhatnguyen.automated;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

    private final Base base;
    public Hook(Base base) {
        this.base = base;
    }
    private final String browser = Env("BROWSER").toLowerCase();//dot.get("BROWSER").toLowerCase();
    private String browser_size(){
        String browser_size = "window-size=3840,2160";
        switch (Env("BROWSER_SIZE").toLowerCase())
        {
            case "2k":browser_size =  "window-size=1920,1080";break;
            case "4k":browser_size =  "window-size=3840,2160";break;
        }
        return browser_size;
    }
    private final String os = System.getProperty("os.name").toLowerCase();
    private final String mode = Env("BROWSER_MODE");
    private final String Envi = Env("ENVIRONMENT");

    @Before
    public void TestInit(Scenario scenario) throws Exception {

        switch (browser){
            case "chrome":
                ChromeDriverManager.getInstance().arch64().setup();
                ChromeOptions option = new ChromeOptions();
                Map<String, Object> preferences = new HashMap<String, Object>();
                option.addArguments(browser_size());
                if (mode.equals("headless")){
                    option.addArguments("--headless");}
                base.driver = new ChromeDriver(option);
                break;
            case "firefox":
                FirefoxDriverManager.getInstance().arch64().setup();
                FirefoxOptions ffoption = new FirefoxOptions();
                ffoption.addArguments(browser_size());
                if (mode.equals("headless")){
                    ffoption.addArguments("--headless");}
                base.driver = new FirefoxDriver(ffoption);
                break;
            case "edge":
                EdgeDriverManager.getInstance().setup();
                base.driver = new EdgeDriver();
                break;
            case "remote-chrome":
                base.driver = RemoteChrome();
                break;
            case  "BSiphone":
                base.driver = BSiphone();
                break;
            case "testingbot":
                base.driver = TestingBot();
                break;
        }

    }
    @After
    public void endTestCase(Scenario result) throws URISyntaxException, IOException {
        if(result.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) base.driver)
                    .getScreenshotAs(OutputType.BYTES);
            result.embed(screenshot, "image/png");
        }
        System.out.println("\n");
        System.out.println("******* Execute scenario "+ result.getName() +
                " done as :" + result.getStatus().toString().toUpperCase()+" ********");
        System.out.println("\n");
        base.driver.quit();

    }




    // Register remote chrome desktop
    private RemoteWebDriver RemoteChrome() throws Exception {
        ChromeOptions option = new ChromeOptions();
        option.addArguments(browser_size());
        if (mode.equals("headless")) {
            option.addArguments("--headless");
        }
        return new RemoteWebDriver(new URL("http://localhost:35029/wd/hub"), option);
    }

    //BROWSER stack
    private RemoteWebDriver BSiphone() throws MalformedURLException {
        String USERNAME = "lanbihack1";
        String AUTOMATE_KEY = "xeRSXyMH9a4xfLW2p2Vi";
        String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY
                + "@hub-cloud.browserstack.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "iPhone");
        caps.setCapability("device", "iPhone 7");
        caps.setCapability("realMobile", "true");
        caps.setCapability("os_version", "10.3");

        return new RemoteWebDriver(new URL(URL), caps);
    }

    /** Testing bot Browser **/

    private RemoteWebDriver TestingBot() throws MalformedURLException {
        String KEY = "e55c0f0d0e67adb82d005554d7dc84e2";
        String SECRET = "a557934ddd89ad36b15eb0b80d27f0ba";
        String URL = "https://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "safari");
        caps.setCapability("version", "11.2");
        caps.setCapability("platform", "iOS");
        caps.setCapability("deviceName", "iPhone X");
        caps.setCapability("platformName", "iOS");


        return new RemoteWebDriver(new URL(URL), caps);

    }

}
