package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Functions {


    public static void _SelectOption(WebDriver driver,String xpath, String option){
        StringBuilder str = new StringBuilder(xpath);
        xpath = str.insert(xpath.length() - 3,option).toString();
        _WaitFor(driver,By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
    }


    public static void _WaitUntillURL(WebDriver driver,String expect){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlContains(expect));
    }

    public static void _WaitFor(WebDriver driver,WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e){
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        }
    }
    public static void _WaitFor(WebDriver driver,By element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
