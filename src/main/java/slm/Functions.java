package slm;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static cmn.SysConfig.GetTimeOut;


public class Functions {


    public boolean isVisible(WebDriver driver,WebElement element){
        try {
            _WaitFor(driver,element);
            return true;
        }catch(TimeoutException e){
            return false;
        }
    }

    public static void _WaitForAlert(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void _WaitUntillURL(WebDriver driver,String expect){
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        wait.until(ExpectedConditions.urlContains(expect));
    }

    public static void _WaitFor(WebDriver driver,WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        try {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e){
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        }
    }

    public static void _WaitFor(WebDriver driver,String xpath){
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

}
