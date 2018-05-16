package tech.nhatnguyen.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import static tech.nhatnguyen.common.StringUtils._ConvertXpath;
import static tech.nhatnguyen.selenium.Functions._WaitFor;
import static tech.nhatnguyen.selenium.Functions._WaitForAlert;


public class Actions {


    public void _Hover(WebDriver driver,WebElement element){
        _WaitFor(driver,element);
        org.openqa.selenium.interactions.Actions act =
                new org.openqa.selenium.interactions.Actions(driver);
        act.moveToElement(element).build().perform();

    }
    public void _MoveToThenClick(WebDriver driver,WebElement element){
        _WaitFor(driver,element);
        Coordinates coordinate = ((Locatable)element).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
        element.click();

    }
    public static void _Click(WebDriver driver, WebElement element){
        _WaitFor(driver,element);
        element.click();
    }

    public static void _Click(WebDriver driver,String xpath){
        _WaitFor(driver,xpath);
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void _Sendkey(WebDriver driver,WebElement element,String keyword){
        _WaitFor(driver,element);
        element.sendKeys(keyword);
    }

    public static void _SelectOption(WebDriver driver,String xpath, String option){
        xpath = _ConvertXpath(xpath,option);
        _Click(driver,xpath);
    }
    public static void _AlertHandle(WebDriver driver,String ok_or_cancel){
        _WaitForAlert(driver);
        Alert alert = driver.switchTo().alert();
        if (ok_or_cancel.toUpperCase().equals("OK")||ok_or_cancel.
                toUpperCase().equals("ACCEPT")){
            alert.accept();
        }
        else {
            alert.dismiss();
        }
    }
    public void _JSClicker(WebDriver driver,WebElement element){
        _WaitFor(driver,element);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
