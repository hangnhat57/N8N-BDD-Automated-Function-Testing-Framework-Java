package tech.nhatnguyen.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tech.nhatnguyen.automated.Base;

import static tech.nhatnguyen.common.SysConfig.GetTimeOut;


public class Functions extends Base{


    public boolean isVisible(WebElement element){
        try {
            _WaitFor(element);
            return true;
        }catch(TimeoutException e){
            return false;
        }
    }

    public static void _WaitForAlert(){
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void _WaitUntillURL(String expect){
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        wait.until(ExpectedConditions.urlContains(expect));
    }

    public static void _WaitFor(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        try {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e){
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        }
    }

    public static void _WaitFor(String xpath){
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

}
