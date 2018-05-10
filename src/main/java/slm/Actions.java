package slm;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static cmn.StringUtils._ConvertXpath;
import static slm.Functions._WaitForAlert;
import static slm.Functions._WaitForElement;
import static slm.Functions._WaitForLocation;

public class Actions {

    public void _Hover(WebDriver driver,WebElement element){
        _WaitForElement(driver,element);
        org.openqa.selenium.interactions.Actions act =
                new org.openqa.selenium.interactions.Actions(driver);
        act.moveToElement(element).build().perform();

    }
    public void _MoveToThenClick(WebDriver driver,WebElement element){
        _WaitForElement(driver,element);
        Coordinates coordinate = ((Locatable)element).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
        element.click();

    }
    public static void _ClickElement(WebDriver driver, WebElement element){
        _WaitForElement(driver,element);
        element.click();
    }

    public static void _ClickLocation(WebDriver driver,String xpath){
        _WaitForLocation(driver,xpath);
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void _Sendkey(WebDriver driver,WebElement element,String keyword){
        _WaitForElement(driver,element);
        element.sendKeys(keyword);
    }

    public static void _SelectOption(WebDriver driver,String xpath, String option){
        xpath = _ConvertXpath(xpath,option);
        _ClickLocation(driver,xpath);
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
        _WaitForElement(driver,element);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
