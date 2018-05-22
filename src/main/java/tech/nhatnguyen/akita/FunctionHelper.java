package tech.nhatnguyen.akita;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tech.nhatnguyen.nihonsupittsu.Base;


import static tech.nhatnguyen.shiba.SystemConfiguration.getTimeOut;


public class FunctionHelper extends Base {


    public static boolean isVisible(WebElement element) {
        try {
            waitFor(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, getTimeOut());
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitUntilUrlBecome(String expect) {
        WebDriverWait wait = new WebDriverWait(driver, getTimeOut());
        wait.until(ExpectedConditions.urlContains(expect));
    }

    public static void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, getTimeOut());
        try {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e) {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        }
    }

    public static void waitFor(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, getTimeOut());
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

}
