package tech.nhatnguyen.akitahelper;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tech.nhatnguyen.initializer.Base;

import static tech.nhatnguyen.shibahelper.SystemConfiguration.GetTimeOut;


public class FunctionHelper extends Base {


    public static boolean isVisible(WebElement element) {
        try {
            _WaitFor(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void _WaitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void _WaitUntillURL(String expect) {
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        wait.until(ExpectedConditions.urlContains(expect));
    }

    public static void _WaitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        try {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e) {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        }
    }

    public static void _WaitFor(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, GetTimeOut());
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

}
