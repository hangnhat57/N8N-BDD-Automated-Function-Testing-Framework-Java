package tech.nhatnguyen.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import tech.nhatnguyen.automated.Base;

import static tech.nhatnguyen.common.StringUtils._ConvertXpath;
import static tech.nhatnguyen.selenium.Functions._WaitFor;
import static tech.nhatnguyen.selenium.Functions._WaitForAlert;


public class Actions extends Base{

    public void _Hover(WebElement element){
        _WaitFor(element);
        org.openqa.selenium.interactions.Actions act =
                new org.openqa.selenium.interactions.Actions(driver);
        act.moveToElement(element).build().perform();

    }
    public void _MoveToThenClick(WebElement element){
        _WaitFor(element);
        Coordinates coordinate = ((Locatable)element).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
        element.click();

    }
    public static void _Click( WebElement element){
        _WaitFor(element);
        element.click();
    }

    public static void _Click(String xpath){
        _WaitFor(xpath);
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void _Sendkey(WebElement element,String keyword){
        _WaitFor(element);
        element.sendKeys(keyword);
    }

    public static void _SelectOption(String xpath, String option){
        xpath = _ConvertXpath(xpath,option);
        _Click(xpath);
    }
    public static void _AlertHandle(String ok_or_cancel){
        _WaitForAlert();
        Alert alert = driver.switchTo().alert();
        if (ok_or_cancel.toUpperCase().equals("OK")||ok_or_cancel.
                toUpperCase().equals("ACCEPT")){
            alert.accept();
        }
        else {
            alert.dismiss();
        }
    }
    public void _JSClicker(WebElement element){
        _WaitFor(element);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
